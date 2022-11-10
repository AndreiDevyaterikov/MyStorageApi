package ru.mystorage.models;

import lombok.Data;

import java.util.List;

@Data
public class Sale {
    private Integer number;
    private Storage storage;
    private List<Product> productList;
}
