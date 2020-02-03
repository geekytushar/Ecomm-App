package com.tusharpatil.ecommapp.local_db;

public class ProductsDB {
    public static final String TABLE_NAME = "products";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TAX_NAME = "tax_name";
    public static final String COLUMN_TAX_VALUE = "tax_value";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_VIEW_COUNT = "view_count";
    public static final String COLUMN_ORDER_COUNT = "order_count";
    public static final String COLUMN_SHARES = "shares";
    public static final String COLUMN_DATE_ADDED = "date_added";

    private int id, categoryId, viewCount, orderCount, shares;
    private String name, taxName, taxValue, dateAdded;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TAX_NAME + " TEXT,"
                    + COLUMN_TAX_VALUE + " TEXT,"
                    + COLUMN_CATEGORY_ID + " INTEGER,"
                    + COLUMN_VIEW_COUNT + " INTEGER DEFAULT 0,"
                    + COLUMN_ORDER_COUNT + " INTEGER DEFAULT 0,"
                    + COLUMN_SHARES + " INTEGER DEFAULT 0,"
                    + COLUMN_DATE_ADDED + " DATETIME"
                    + ")";

    public ProductsDB() {
    }

    public ProductsDB(int id, int categoryId, String name, String taxName, String taxValue, String dateAdded, int viewCount, int orderCount, int shares) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
}
