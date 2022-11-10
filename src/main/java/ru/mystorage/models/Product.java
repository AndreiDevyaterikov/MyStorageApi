package ru.mystorage.models;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Product {
    private String article;
    private String name;
    private BigDecimal lastBuyPrice;
    private BigDecimal lastSellPrice;
}
