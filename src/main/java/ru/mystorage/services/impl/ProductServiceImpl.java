package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mystorage.constants.Constants;
import ru.mystorage.entities.Product;
import ru.mystorage.entities.Storage;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ProductModelWithStorage;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.repositories.ProductRepository;
import ru.mystorage.services.ProductService;
import ru.mystorage.services.StorageService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StorageService storageService;


    @Override
    public Product add(ProductModelWithStorage productModel) {
        var existStorage = storageService.getById(productModel.getStorageId());
        var existProductOpt = productRepository.findByNameAndArticle(
                productModel.getName(),
                productModel.getArticle()
        );
        if (existProductOpt.isPresent()) {
            var existProduct = existProductOpt.get();
            var message = String.format(Constants.PRODUCT_ALREADY_EXIST, existProduct.getName(),
                    existProduct.getArticle());
            log.info(message);
            throw new MyStorageException(message, 405);
        } else {
            var newProduct = Product.builder()
                    .name(productModel.getName())
                    .article(productModel.getArticle())
                    .amount(productModel.getAmount())
                    .lastBuyPrice(productModel.getPrice())
                    .storage(existStorage)
                    .build();
            productRepository.save(newProduct);
            return newProduct;
        }
    }

    @Override
    public Product get(Integer id) {
        var existProduct = productRepository.findById(id);
        if (existProduct.isPresent()) {
            return existProduct.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_PRODUCT_WITH_ID, id);
            log.info(message);
            throw new MyStorageException(message, 404);
        }
    }

    @Override
    public Optional<Product> getByNameAndArticle(String name, String article) {
        return productRepository.findByNameAndArticle(name, article);
    }

    @Override
    public Optional<Product> getByNameAndArticleAndStorage(String name, String article, Storage storage) {
        return productRepository.findByNameAndArticleAndStorage(name, article, storage);
    }


    @Override
    public List<Product> getAllByStorage(Storage storage) {
        return productRepository.findAllByStorage(storage);
    }

    @Override
    public Product getByStorage(Storage storage) {
        var existProduct = productRepository.findByStorage(storage);
        if (existProduct.isPresent()) {
            return existProduct.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_PRODUCT_ON_STORAGE, storage.getName());
            log.info(message);
            throw new MyStorageException(message, 404);
        }
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ResponseModel delete(Integer id) {
        var product = get(id);
        productRepository.delete(product);
        var message = String.format(Constants.PRODUCT_HAS_BEEN_DELETED, product.getName());
        return new ResponseModel(200, message);
    }

    @Override
    public Product edit(Product product) {
        var existProductOpt = productRepository.findById(product.getId());
        if (existProductOpt.isPresent()) {
            var existProduct = existProductOpt.get();
            if (product.getArticle() != null) {
                existProduct.setArticle(product.getArticle());
            }
            if (product.getName() != null) {
                existProduct.setName(product.getName());
            }
            if (product.getLastBuyPrice() != null) {
                existProduct.setLastBuyPrice(product.getLastBuyPrice());
            }
            if (product.getLastSellPrice() != null) {
                existProduct.setLastSellPrice(product.getLastSellPrice());
            }
            if (product.getAmount() != null) {
                existProduct.setAmount(product.getAmount());
            }
            if (product.getStorage() != null) {
                var storage = storageService.getById(product.getStorage().getId());
                existProduct.setStorage(storage);
            }
            productRepository.save(existProduct);
            return existProduct;
        } else {
            var message = String.format(Constants.NOT_FOUND_PRODUCT_WITH_ID, product.getId());
            log.info(message);
            throw new MyStorageException(message, 404);
        }
    }
}
