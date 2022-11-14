package ru.mystorage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mystorage.controllers.api.ProductControllerApi;
import ru.mystorage.entities.Product;
import ru.mystorage.models.ProductModel;
import ru.mystorage.models.ResponseModel;
import ru.mystorage.services.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {

    private final IProductService productService;

    @Override
    public Product add(ProductModel productModel) {
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
