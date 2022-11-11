package ru.mystorage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.mystorage.entities.Product;
import ru.mystorage.exceptions.MyStorageException;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.repositories.ProductRepository;
import ru.mystorage.services.IProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final StorageService storageService;


    @Override
    public ResponseModel add(Product product) {
        try {
            var existProduct = productRepository.findById(product.getId());
            if (existProduct.isPresent()) {
                throw new MyStorageException("Такой товар уже существует", 405);
            }
            productRepository.save(product);
            return new ResponseModel(200, "Склад успешно добавлен");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyStorageException("Внутренняя ошибка сервиса", 500);
        }
    }

    @Override
    public Product get(Integer id) {
        try {
            var existProduct = productRepository.findById(id);
            if (existProduct.isPresent()) {
                return existProduct.get();
            } else {
                throw new MyStorageException("Такого товара не существует", 404);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyStorageException("Внутренняя ошибка сервиса", 500);
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            var products = productRepository.findAll();
            if (CollectionUtils.isEmpty(products)) {
                throw new MyStorageException("Товары не найдены", 404);
            } else {
                return products;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyStorageException("Внутренняя ошибка сераиса", 500);
        }
    }

    @Override
    public ResponseModel delete(Integer id) {
        var product = get(id);
        productRepository.delete(product);
        return new ResponseModel(200, "Информация о товаре успешно удалена");
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
                var storage = storageService.get(product.getStorage().getId());
                existProduct.setStorage(storage);
            }
            productRepository.save(existProduct);
            return existProduct;
        } else {
            throw new MyStorageException("Такого товара не существует", 404);
        }
    }
}
