package com.tusharpatil.ecommapp.models.categories;

public class Category {
    private int id, parentCategory;
    private String name;

    public Category() {
    }

    public Category(int id, String name, int parentCategory) {
        this.id = id;
        this.parentCategory = parentCategory;
        this.name = name;
    }

    public Category(int id, int parentCategory) {
        this.id = id;
        this.parentCategory = parentCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(int parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentCategory=" + parentCategory +
                ", name='" + name + '\'' +
                '}';
    }
}
