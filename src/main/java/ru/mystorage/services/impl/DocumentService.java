package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ProductModelWithStorage;
import ru.mystorage.models.ReceiptOrSaleModel;
import ru.mystorage.services.IDocumentService;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentService implements IDocumentService {

    private final StorageService storageService;
    private final ProductService productService;

    @Override
    public ReceiptOrSaleModel addNewReceipt(ReceiptOrSaleModel receiptOrSaleModel) {

        var existStorage = storageService.getByName(receiptOrSaleModel.getStorageName());
        var productsOnStorage = productService.getAllByStorage(existStorage);
        var products = receiptOrSaleModel.getProducts();

        if (Objects.isNull(products) || products.isEmpty()) {
            throw new MyStorageException("Вы не указали товары для поступления", 400);
        }

        if (productsOnStorage.isEmpty()) {
            products.forEach(productModel -> productService.add(
                    new ProductModelWithStorage(
                            productModel.getName(),
                            productModel.getArticle(),
                            productModel.getAmount(),
                            productModel.getPrice(),
                            existStorage.getName()
                    )
            ));

        } else {
            products.forEach(product -> {
                var productOnStorageOpt = productService.getByNameAndArticle(product.getName(), product.getArticle());
                if (productOnStorageOpt.isPresent()) {
                    var productOnStorage = productOnStorageOpt.get();
                    productOnStorage.setAmount(productOnStorage.getAmount() + product.getAmount());
                    productOnStorage.setLastBuyPrice(product.getPrice());
                    productService.save(productOnStorage);
                } else {
                    productService.add(
                            new ProductModelWithStorage(
                                    product.getName(),
                                    product.getArticle(),
                                    product.getAmount(),
                                    product.getPrice(),
                                    existStorage.getName()
                            )
                    );
                }


            });
        }
        return receiptOrSaleModel;
    }

    @Override
    public ReceiptOrSaleModel addNewSale(ReceiptOrSaleModel receiptOrSaleModel) {
        var existStorage = storageService.getByName(receiptOrSaleModel.getStorageName());

        var productsOnStorage = productService.getAllByStorage(existStorage);

        if (productsOnStorage.isEmpty()) {
            throw new MyStorageException(String.format("На указаном складе: %s, отсутствуют товары",
                    existStorage.getName()), 404);
        } else {
            var products = receiptOrSaleModel.getProducts();

            if (Objects.isNull(products) || products.isEmpty()) {
                throw new MyStorageException("Вы не указали товары для продажи", 400);
            }

            for (var product : products) {
                var productOnStorageOpt = productService.getByNameAndArticle(product.getName(), product.getArticle());
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
                    productService.save(productOnStorage);
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

        var previousStorage = storageService.getByName(movingBetweenStoragesModel.getFromStorageName());
        var nextStorage = storageService.getByName(movingBetweenStoragesModel.getToStorageName());
        var productsOnNextStorage = productService.getAllByStorage(nextStorage);
        var productsOnPreviousStorage = productService.getAllByStorage(previousStorage);
        var movingProducts = movingBetweenStoragesModel.getProducts();

        //если на новом складе нет таких товаров
        if (productsOnNextStorage.isEmpty()) {
            movingProducts.forEach(movingProduct -> {
                var productOnPreviousStorageOpt = productService.getByNameAndArticle(movingProduct.getName(),
                        movingProduct.getArticle());
                //если на предыдущем складе есть товар, который мы хотим переместить
                if (productOnPreviousStorageOpt.isPresent()) {
                    var productOnPreviousStorage = productOnPreviousStorageOpt.get();
                    productOnPreviousStorage.setAmount(productOnPreviousStorage.getAmount() - movingProduct.getAmount());
                    productService.add(new ProductModelWithStorage(
                            movingProduct.getName(),
                            movingProduct.getArticle(),
                            movingProduct.getAmount(),
                            movingProduct.getPrice(),
                            nextStorage.getName()
                    ));
                    productService.save(productOnPreviousStorage);
                } else {
                    throw new MyStorageException(String.format("Не найдено товара %s для перемещения на склад %s",
                            movingProduct.getName(), nextStorage.getName()), 404);
                }
            });
        } else {
            productsOnNextStorage.forEach(existProductOnNextStorage -> {
                movingProducts.forEach(movingProduct -> {
                    //если перемещаемый товар найден среди товаров на новом складе
                    if (movingProduct.getName().equals(existProductOnNextStorage.getName())
                            && movingProduct.getArticle().equals(existProductOnNextStorage.getArticle())) {

                    }
                });
            });
        }

        return movingBetweenStoragesModel;
    }
}
