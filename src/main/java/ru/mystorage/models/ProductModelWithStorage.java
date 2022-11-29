package ru.mystorage.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModelWithStorage extends ProductModel {
    public ProductModelWithStorage(String name, String article, Integer amount, BigDecimal price, Integer storageId) {
        super(name, article, amount, price);
        this.storageId = storageId;
    }

    private Integer storageId;
}
