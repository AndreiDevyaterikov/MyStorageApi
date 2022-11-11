package ru.mystorage.models;

import lombok.Data;

import java.util.List;

@Data
public class ReceiptOrSaleModel {
    private Integer number;
    private String storageName;
    private List<ProductModel> products;
}
