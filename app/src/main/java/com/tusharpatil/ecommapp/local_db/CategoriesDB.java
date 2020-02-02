package com.tusharpatil.ecommapp.local_db;

public class CategoriesDB {
    public static final String TABLE_NAME = "categories";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PARENT_CATEGORY = "parent_category";

    private int id, parentCategory;
    private String name;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_PARENT_CATEGORY + " INTEGER DEFAULT 0"
                    + ")";

    public CategoriesDB() {
    }

    public CategoriesDB(int id, String name, int parentCategory) {
        this.id = id;
        this.parentCategory = parentCategory;
        this.name = name;
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
}
