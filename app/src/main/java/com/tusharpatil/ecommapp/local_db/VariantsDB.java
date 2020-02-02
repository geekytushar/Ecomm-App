package com.tusharpatil.ecommapp.local_db;

public class VariantsDB {
    public static final String TABLE_NAME = "variants";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PRODUCT_ID = "product_id";

    private int id, price, product_id;
    private String color, size;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_COLOR + " TEXT,"
                    + COLUMN_SIZE + " TEXT,"
                    + COLUMN_PRICE + " INTEGER,"
                    + COLUMN_PRODUCT_ID + " INTEGER"
                    + ")";

    public VariantsDB() {

    }

    public VariantsDB(int id, String color, String size, int price, int product_id) {
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
}
