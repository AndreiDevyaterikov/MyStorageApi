package ru.mystorage.models;

import lombok.Data;

import java.util.List;

@Data
public class MovingBetweenStoragesModel {
    private Integer number;
    private String fromStorageName;
    private String toStorageName;
    private List<ProductModel> products;
}
