package ru.mystorage.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel {
    private String name;
    private String article;
    private Integer amount;
    private BigDecimal price;
}
