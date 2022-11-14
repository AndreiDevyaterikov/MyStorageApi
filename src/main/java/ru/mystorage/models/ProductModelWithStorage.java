package ru.mystorage.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModelWithStorage extends ProductModel {
    private String storageName;
}
