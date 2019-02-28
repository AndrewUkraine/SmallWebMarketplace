package com.marketplace.project.entities.enums;

public enum CategoryTypes {

    ELECTRONIKS("Ноутбуки и компьютеры"),
    MOTORS("Транспорт"),
    FASHION("Одежда"),
    HOME_AND_GARDEN("Дом и Сад"),
    SPORTING_GOODS("Товары для спорта");

    private String categoryType;

     CategoryTypes(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryType() {
        return categoryType;
    }
}
