package com.tusharpatil.ecommapp.models.categories;

public class Categories {
    private String name;

    private int id;

    private int[] child_categories;

    private Products[] products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getChild_categories() {
        return child_categories;
    }

    public void setChild_categories(int[] child_categories) {
        this.child_categories = child_categories;
    }

    public Products[] getProducts() {
        return products;
    }

    public void setProducts(Products[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ClassPojo [name = " + name + ", id = " + id + ", child_categories = " + child_categories.toString() + ", products = " + products + "]";
    }
}
