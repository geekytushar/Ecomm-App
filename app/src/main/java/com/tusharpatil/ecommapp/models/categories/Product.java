package com.tusharpatil.ecommapp.models.categories;

public class Product {
    private int id, categoryId, viewCount, orderCount, shares;
    private String name, taxName, taxValue, dateAdded;

    public Product() {
    }

    public Product(int id, int categoryId, String name, String taxName, String taxValue, String dateAdded) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.taxName = taxName;
        this.taxValue = taxValue;
        this.dateAdded = dateAdded;
    }

    public Product(int id, int viewCount, int orderCount, int shares) {
        this.id = id;
        this.viewCount = viewCount;
        this.orderCount = orderCount;
        this.shares = shares;
    }

    public Product(int id, int categoryId, String name, String taxName, String taxValue, String dateAdded, int viewCount, int orderCount, int shares) {
        this.id = id;
        this.categoryId = categoryId;
        this.viewCount = viewCount;
        this.orderCount = orderCount;
        this.shares = shares;
        this.name = name;
        this.taxName = taxName;
        this.taxValue = taxValue;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(String taxValue) {
        this.taxValue = taxValue;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", viewCount=" + viewCount +
                ", orderCount=" + orderCount +
                ", shares=" + shares +
                ", name='" + name + '\'' +
                ", taxName='" + taxName + '\'' +
                ", taxValue='" + taxValue + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
