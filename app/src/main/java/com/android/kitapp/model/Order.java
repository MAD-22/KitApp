package com.android.kitapp.model;

public class Order {
    private String key;
    private String name;
    private String price;
    private String quantity;
    private String date;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order() {
    }

    public Order(String name, String price, String quantity, String date) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }
}
