package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mystorage.constants.Constants;
import ru.mystorage.entities.Product;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.MovingBetweenStoragesModel;
import ru.mystorage.models.ReceiptModel;
import ru.mystorage.services.DocumentService;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final StorageServiceImpl storageServiceImpl;
    private final ProductServiceImpl productService;

    @Override
    public ReceiptModel addNewReceipt(ReceiptModel receiptModel) {

        var existStorage = storageServiceImpl.getByName(receiptModel.getStorageName());
        var productsOnStorage = productService.getAllByStorage(existStorage);
        var products = receiptModel.getProducts();

        if (Objects.isNull(products) || products.isEmpty()) {
            throw new MyStorageException(Constants.NOT_SET_PRODUCTS_FOR_RECEIPT, 400);
        }

        if (productsOnStorage.isEmpty()) {
            products.forEach(productModel -> productService.save(Product.builder()
                    .name(productModel.getName())
                    .article(productModel.getArticle())
                    .amount(productModel.getAmount())
                    .lastBuyPrice(productModel.getPrice())
                    .storage(existStorage)
                    .build()));

        } else {
            products.forEach(product -> {
                var productOnStorageOpt = productService.getByNameAndArticle(product.getName(),
                        product.getArticle());
                if (productOnStorageOpt.isPresent()) {
                    var productOnStorage = productOnStorageOpt.get();
                    productOnStorage.setAmount(productOnStorage.getAmount() + product.getAmount());
                    productOnStorage.setLastBuyPrice(product.getPrice());
                    productService.save(productOnStorage);
                } else {
                    productService.save(Product.builder()
                            .name(product.getName())
                            .article(product.getArticle())
                            .amount(product.getAmount())
                            .lastBuyPrice(product.getPrice())
                            .storage(existStorage)
                            .build());
                }
            });
        }
        return receiptModel;
    }

    @Override
    public ReceiptModel addNewSale(ReceiptModel receiptModel) {
        var existStorage = storageServiceImpl.getByName(receiptModel.getStorageName());

        var productsOnStorage = productService.getAllByStorage(existStorage);

        if (productsOnStorage.isEmpty()) {
            var message = String.format(Constants.NOT_EXISTS_PRODUCTS_ON_STORAGE, existStorage.getName());
            log.info(message);
            throw new MyStorageException(message, 404);
        } else {
            var products = receiptModel.getProducts();

            if (Objects.isNull(products) || products.isEmpty()) {
                throw new MyStorageException(Constants.NOT_SET_PRODUCTS_FOR_SALE, 400);
            }

            for (var product : products) {
                var productOnStorageOpt = productService.getByNameAndArticle(product.getName(),
                        product.getArticle());
                if (productOnStorageOpt.isPresent()) {
                    var productOnStorage = productOnStorageOpt.get();
                    var sellAmount = product.getAmount();
                    if (sellAmount > productOnStorage.getAmount()) {
                        var message = String.format(Constants.CAN_NOT_SELL_MORE_PRODUCTS_THAN_EXISTS,
                                productOnStorage.getName(), productOnStorage.getAmount(), product.getAmount());
                        log.info(message);
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
                    var message = String.format(Constants.NOT_FOUND_PRODUCT_WITH_NAME_AND_ARTICLE,
                            product.getName(), product.getArticle());
                    log.info(message);
                    throw new MyStorageException(message, 404);
                }
            }
            return receiptModel;
        }
    }

    @Override
    public MovingBetweenStoragesModel addNewMoving(MovingBetweenStoragesModel movingBetweenStoragesModel) {
        var previousStorage = storageServiceImpl.getByName(movingBetweenStoragesModel.getFromStorageName());
        var nextStorage = storageServiceImpl.getByName(movingBetweenStoragesModel.getToStorageName());
        var productsOnNextStorage = productService.getAllByStorage(nextStorage);
        var movingProducts = movingBetweenStoragesModel.getProducts();

        //если на новом складе нет товаров
        if (productsOnNextStorage.isEmpty()) {
            movingProducts.forEach(movingProduct -> {
                var productOnPreviousStorageOpt = productService.getByNameAndArticle(
                        movingProduct.getName(),
                        movingProduct.getArticle()
                        );
                //если на предыдущем складе есть товар, который мы хотим переместить
                if (productOnPreviousStorageOpt.isPresent()) {
                    var productOnPreviousStorage = productOnPreviousStorageOpt.get();
                    productOnPreviousStorage.setAmount(productOnPreviousStorage.getAmount() - movingProduct.getAmount());
                    productService.save(Product.builder()
                            .name(productOnPreviousStorage.getName())
                            .article(productOnPreviousStorage.getArticle())
                            .amount(movingProduct.getAmount())
                            .lastBuyPrice(productOnPreviousStorage.getLastBuyPrice())
                            .lastSellPrice(productOnPreviousStorage.getLastSellPrice())
                            .storage(nextStorage)
                            .build());
                    productService.save(productOnPreviousStorage);
                } else {
                    var message = String.format(Constants.NOT_FOUND_PRODUCT_TO_MOVE_TO_STORAGE,
                            movingProduct.getName(), nextStorage.getName());
                    log.info(message);
                    throw new MyStorageException(message, 404);
                }
            });
        } else {
            movingProducts.forEach(movingProduct -> productsOnNextStorage.forEach(existProductOnNextStorage -> {
                var productOnPreviousStorageOpt = productService.getByNameAndArticleAndStorage(
                        movingProduct.getName(), movingProduct.getArticle(), previousStorage);
                if (productOnPreviousStorageOpt.isPresent()) {
                    var productOnPreviousStorage = productOnPreviousStorageOpt.get();
                    if (existProductOnNextStorage.getName().equals(productOnPreviousStorage.getName())
                            && existProductOnNextStorage.getArticle().equals(productOnPreviousStorage.getArticle())) {
                        existProductOnNextStorage.setAmount(existProductOnNextStorage.getAmount()
                                + productOnPreviousStorage.getAmount());
                        productService.save(existProductOnNextStorage);
                    } else {
                        productOnPreviousStorage.setStorage(nextStorage);
                        productService.save(productOnPreviousStorage);
                    }
                }
            }));
        }
        return movingBetweenStoragesModel;
    }
}
