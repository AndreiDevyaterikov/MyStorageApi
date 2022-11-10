package ru.mystorage.models;

import lombok.Data;

import java.util.List;

@Data
public class Moving {
    private Integer number;
    private Storage fromStorage;
    private Storage toStorage;
    private List<Product> productList;
}
