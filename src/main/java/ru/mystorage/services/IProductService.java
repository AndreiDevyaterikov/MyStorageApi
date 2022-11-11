package ru.mystorage.services;

import ru.mystorage.entities.Product;
import ru.mystorage.models.ResponseModel;

import java.util.List;

public interface IProductService {
    ResponseModel add(Product product);
    Product get(Integer id);
    List<Product> getAll();
    ResponseModel delete(Integer id);
    Product edit(Product product);
}
