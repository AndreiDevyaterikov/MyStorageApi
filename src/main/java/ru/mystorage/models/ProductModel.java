package ru.mystorage.models;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
@Setter
public class ProductModel {
    private String name;
    private String article;
    private Integer amount;
    private BigDecimal price;

    public ProductModel(String name, String article, Integer amount, BigDecimal price) {
        this.name = name;
        this.article = article;
        this.amount = amount;
        this.price = price;
    }
}
