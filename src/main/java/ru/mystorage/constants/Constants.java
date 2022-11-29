package ru.mystorage.constants;

public class Constants {
    public static final String NOT_FOUND_STORAGE_WITH_ID = "Не существует такого склада с id %d";
    public static final String STORAGE_ALREADY_EXIST = "Склад с навазнием %s уже существует";
    public static final String PRODUCT_ALREADY_EXIST = "Склад с навазнием %s и артиклем %s уже существует";
    public static final String NOT_FOUND_STORAGE_WITH_NAME = "Не существует такого склада с названием %s";
    public static final String STORAGE_HAS_BEEN_DELETED = "Склад с названием %s был удален";
    public static final String NOT_SET_PRODUCTS_FOR_RECEIPT = "Вы не указали товары для поступления";
    public static final String NOT_EXISTS_PRODUCTS_ON_STORAGE = "На указаном складе: %s, отсутствуют товары";
    public static final String NOT_SET_PRODUCTS_FOR_SALE = "Вы не указали товары для продажи";
    public static final String CAN_NOT_SELL_MORE_PRODUCTS_THAN_EXISTS= "Нельзя продать товара %s больше чем есть на" +
            " складе. Кол-во на складе: %s, кол-во на продажу: %s";
    public static final String NOT_FOUND_PRODUCT_WITH_NAME_AND_ARTICLE = "Товар с именем %s и артикулом %s не найден" +
            " для продажи";
    public static final String NOT_FOUND_PRODUCT_TO_MOVE_TO_STORAGE = "Не найдено товара %s для перемещения на склад" +
            " %s";
    public static final String NOT_FOUND_PRODUCT_WITH_ID = "Товара с id %d не сущетсвует";
    public static final String NOT_FOUND_PRODUCT_ON_STORAGE = "Товара на складе %s не существует";
    public static final String PRODUCT_HAS_BEEN_DELETED = "Информация о товаре %s успешно удалена";
    public static final String NOT_SET_NEW_STORAGE_NAME = "Вы не указали новое имя склада";
}
