package com.tusharpatil.ecommapp.models.categories;

public class Variant {
    private int id, price, product_id;
    private String color, size;

    public Variant() {
    }

    public Variant(int id, int product_id, int price, String color, String size) {
        this.id = id;
        this.price = price;
        this.product_id = product_id;
        this.color = color;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
