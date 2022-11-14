package ru.mystorage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ReceiptOrSaleModel {
    private Integer number;
    private String storageName;
    private List<ProductModel> products;
}
