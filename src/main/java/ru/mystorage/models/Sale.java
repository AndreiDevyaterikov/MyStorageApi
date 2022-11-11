package ru.mystorage.models;

import lombok.Data;
import ru.mystorage.entities.Product;
import ru.mystorage.entities.Storage;

import java.util.List;

@Data
public class Sale {
    private Integer number;
    private Storage storage;
    private List<Product> productList;
}
