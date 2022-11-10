package ru.mystorage.models;

import lombok.Data;

import java.util.List;

@Data
public class Entrance {
    private Integer number;
    private Storage storage;
    private List<Product> productList;
}
