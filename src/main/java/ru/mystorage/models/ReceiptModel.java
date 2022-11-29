package ru.mystorage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
//Класс описывающий продажу или поступление товара
public class ReceiptModel {
    private Integer number;
    private String storageName;
    private List<ProductModel> products;
}
