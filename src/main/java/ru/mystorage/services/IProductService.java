package ru.mystorage.services;

import ru.mystorage.entities.Product;
import ru.mystorage.entities.Storage;
import ru.mystorage.models.ProductModelWithStorage;
import ru.mystorage.models.ResponseModel;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product add(ProductModelWithStorage productModel);

    Product get(Integer id);

    Optional<Product> getByNameAndArticle(String name, String article);

    List<Product> getAllByStorage(Storage storage);

    Product getByStorage(Storage storage);

    void save(Product product);

    void saveAll(List<Product> products);

    List<Product> getAll();

    ResponseModel delete(Integer id);

    Product edit(Product product);
}
