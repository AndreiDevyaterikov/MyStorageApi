package ru.mystorage.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.ProductController;
import ru.mystorage.entities.Product;
import ru.mystorage.models.ProductModelWithStorage;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public Product add(ProductModelWithStorage productModel) {
        return productService.add(productModel);
    }

    @Override
    public Product get(Integer id) {
        return productService.get(id);
    }

    @Override
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    public ResponseModel delete(Integer id) {
        return productService.delete(id);
    }

    @Override
    public Product edit(Product product) {
        return productService.edit(product);
    }
}
