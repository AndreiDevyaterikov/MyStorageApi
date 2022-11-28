package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mystorage.entities.Product;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptOrSaleModel;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements ru.mystorage.services.DocumentService {

    private final StorageServiceImpl storageServiceImpl;
    private final ProductServiceImpl productServiceImpl;

    @Override
    public ReceiptOrSaleModel addNewReceipt(ReceiptOrSaleModel receiptOrSaleModel) {

        var existStorage = storageServiceImpl.getByName(receiptOrSaleModel.getStorageName());
        var productsOnStorage = productServiceImpl.getAllByStorage(existStorage);
        var products = receiptOrSaleModel.getProducts();

        if (Objects.isNull(products) || products.isEmpty()) {
            throw new MyStorageException("Вы не указали товары для поступления", 400);
        }

        if (productsOnStorage.isEmpty()) {
            products.forEach(productModel -> productServiceImpl.save(Product.builder()
                    .name(productModel.getName())
                    .article(productModel.getArticle())
                    .amount(productModel.getAmount())
                    .lastBuyPrice(productModel.getPrice())
                    .storage(existStorage)
                    .build()));

        } else {
            products.forEach(product -> {
                var productOnStorageOpt = productServiceImpl.getByNameAndArticle(product.getName(), product.getArticle());
                if (productOnStorageOpt.isPresent()) {
                    var productOnStorage = productOnStorageOpt.get();
                    productOnStorage.setAmount(productOnStorage.getAmount() + product.getAmount());
                    productOnStorage.setLastBuyPrice(product.getPrice());
                    productServiceImpl.save(productOnStorage);
                } else {
                    productServiceImpl.save(Product.builder()
                            .name(product.getName())
                            .article(product.getArticle())
                            .amount(product.getAmount())
                            .lastBuyPrice(product.getPrice())
                            .storage(existStorage)
                            .build());
                }
            });
        }
        return receiptOrSaleModel;
    }

    @Override
    public ReceiptOrSaleModel addNewSale(ReceiptOrSaleModel receiptOrSaleModel) {
        var existStorage = storageServiceImpl.getByName(receiptOrSaleModel.getStorageName());

        var productsOnStorage = productServiceImpl.getAllByStorage(existStorage);

        if (productsOnStorage.isEmpty()) {
            throw new MyStorageException(String.format("На указаном складе: %s, отсутствуют товары",
                    existStorage.getName()), 404);
        } else {
            var products = receiptOrSaleModel.getProducts();

            if (Objects.isNull(products) || products.isEmpty()) {
                throw new MyStorageException("Вы не указали товары для продажи", 400);
            }

            for (var product : products) {
                var productOnStorageOpt = productServiceImpl.getByNameAndArticle(product.getName(), product.getArticle());
                if (productOnStorageOpt.isPresent()) {
                    var productOnStorage = productOnStorageOpt.get();
                    var sellAmount = product.getAmount();
                    if (sellAmount > productOnStorage.getAmount()) {
                        String message = String.format("Нельзя продать товара %s больше чем есть на складе. Кол-во на" +
                                        " " +
                                        "складе: %s, кол-во на продажу: %s", productOnStorage.getName(),
                                productOnStorage.getAmount(),
                                product.getAmount());
                        throw new MyStorageException(message, 405);
                    }
                    if (sellAmount.equals(productOnStorage.getAmount())) {
                        productOnStorage.setAmount(0);
                    } else {
                        productOnStorage.setAmount(productOnStorage.getAmount() - product.getAmount());
                    }
                    productOnStorage.setLastSellPrice(product.getPrice());
                    productServiceImpl.save(productOnStorage);
                } else {
                    throw new MyStorageException(String.format("Товар с именем %s и артикулом %s не найден для продажи",
                            product.getName(), product.getArticle()), 404);
                }
            }
            return receiptOrSaleModel;
        }
    }

    @Override
    public MovingBetweenStoragesModel addNewMoving(MovingBetweenStoragesModel movingBetweenStoragesModel) {
        var previousStorage = storageServiceImpl.getByName(movingBetweenStoragesModel.getFromStorageName());
        var nextStorage = storageServiceImpl.getByName(movingBetweenStoragesModel.getToStorageName());
        var productsOnNextStorage = productServiceImpl.getAllByStorage(nextStorage);
        var movingProducts = movingBetweenStoragesModel.getProducts();

        //если на новом складе нет товаров
        if (productsOnNextStorage.isEmpty()) {
            movingProducts.forEach(movingProduct -> {
                var productOnPreviousStorageOpt = productServiceImpl.getByNameAndArticle(movingProduct.getName(),
                        movingProduct.getArticle());
                //если на предыдущем складе есть товар, который мы хотим переместить
                if (productOnPreviousStorageOpt.isPresent()) {
                    var productOnPreviousStorage = productOnPreviousStorageOpt.get();
                    productOnPreviousStorage.setAmount(productOnPreviousStorage.getAmount() - movingProduct.getAmount());
                    productServiceImpl.save(Product.builder()
                            .name(productOnPreviousStorage.getName())
                            .article(productOnPreviousStorage.getArticle())
                            .amount(movingProduct.getAmount())
                            .lastBuyPrice(productOnPreviousStorage.getLastBuyPrice())
                            .lastSellPrice(productOnPreviousStorage.getLastSellPrice())
                            .storage(nextStorage)
                            .build());
                    productServiceImpl.save(productOnPreviousStorage);
                } else {
                    throw new MyStorageException(String.format("Не найдено товара %s для перемещения на склад %s",
                            movingProduct.getName(), nextStorage.getName()), 404);
                }
            });
        } else {
            movingProducts.forEach(movingProduct -> productsOnNextStorage.forEach(existProductOnNextStorage -> {
                var productOnPreviousStorageOpt = productServiceImpl.getByNameAndArticleAndStorage(movingProduct.getName(),
                        movingProduct.getArticle(), previousStorage);
                if (productOnPreviousStorageOpt.isPresent()) {
                    var productOnPreviousStorage = productOnPreviousStorageOpt.get();
                    if (existProductOnNextStorage.getName().equals(productOnPreviousStorage.getName())
                            && existProductOnNextStorage.getArticle().equals(productOnPreviousStorage.getArticle())) {
                        existProductOnNextStorage.setAmount(existProductOnNextStorage.getAmount() + productOnPreviousStorage.getAmount());
                        productServiceImpl.save(existProductOnNextStorage);
                    } else {
                        productOnPreviousStorage.setStorage(nextStorage);
                        productServiceImpl.save(productOnPreviousStorage);
                    }
                }
            }));
        }
        return movingBetweenStoragesModel;
    }
}
