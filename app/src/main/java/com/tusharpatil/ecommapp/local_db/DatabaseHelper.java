package com.tusharpatil.ecommapp.local_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tusharpatil.ecommapp.models.categories.Category;
import com.tusharpatil.ecommapp.models.categories.Product;
import com.tusharpatil.ecommapp.models.categories.Variant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ecomm_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesDB.CREATE_TABLE);
        db.execSQL(ProductsDB.CREATE_TABLE);
        db.execSQL(VariantsDB.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesDB.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProductsDB.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VariantsDB.TABLE_NAME);
        onCreate(db);
    }

    public void insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CategoriesDB.COLUMN_ID, category.getId());
        values.put(CategoriesDB.COLUMN_NAME, category.getName());
        values.put(CategoriesDB.COLUMN_PARENT_CATEGORY, category.getParentCategory());
        db.insert(CategoriesDB.TABLE_NAME, null, values);
        db.close();
    }

    public void updateParentCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CategoriesDB.COLUMN_PARENT_CATEGORY, category.getParentCategory());
        db.update(CategoriesDB.TABLE_NAME, values, CategoriesDB.COLUMN_ID + " = ?",
                new String[]{String.valueOf(category.getId())});
    }

    public List<Category> getCategory(int categoryId) {
        List<Category> categories = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + CategoriesDB.TABLE_NAME + " WHERE " + CategoriesDB.COLUMN_PARENT_CATEGORY + "='" + categoryId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(CategoriesDB.COLUMN_ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(CategoriesDB.COLUMN_NAME)));
                category.setParentCategory(cursor.getInt(cursor.getColumnIndex(CategoriesDB.COLUMN_PARENT_CATEGORY)));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        db.close();
        return categories;
    }

    public void insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductsDB.COLUMN_ID, product.getId());
        values.put(ProductsDB.COLUMN_NAME, product.getName());
        values.put(ProductsDB.COLUMN_TAX_NAME, product.getTaxName());
        values.put(ProductsDB.COLUMN_TAX_VALUE, product.getTaxValue());
        values.put(ProductsDB.COLUMN_CATEGORY_ID, product.getCategoryId());
        values.put(ProductsDB.COLUMN_DATE_ADDED, product.getDateAdded().substring(0, 19).replace("T", " "));
        db.insert(ProductsDB.TABLE_NAME, null, values);
        db.close();
    }

    public List<Product> getProducts(int categoryId) {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + ProductsDB.TABLE_NAME + " WHERE " + ProductsDB.COLUMN_CATEGORY_ID + "='" + categoryId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_NAME)));
                product.setTaxName(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_TAX_NAME)));
                product.setTaxValue(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_TAX_VALUE)));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_CATEGORY_ID)));
                product.setViewCount(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_VIEW_COUNT)));
                product.setOrderCount(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_ORDER_COUNT)));
                product.setShares(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_SHARES)));
                product.setDateAdded(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_DATE_ADDED)));
                products.add(product);
            } while (cursor.moveToNext());
        }
        db.close();
        return products;
    }

    public List<Product> getSortedProducts(int categoryId, int type) {
        List<Product> products = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + ProductsDB.TABLE_NAME + " WHERE " + ProductsDB.COLUMN_CATEGORY_ID + "='" + categoryId + "'";
        switch (type) {
            case 1:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_VIEW_COUNT + " ASC");
                break;
            case 2:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_VIEW_COUNT + " DESC");
                break;
            case 3:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_ORDER_COUNT + " ASC");
                break;
            case 4:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_ORDER_COUNT + " DESC");
                break;
            case 5:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_SHARES + " ASC");
                break;
            case 6:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_SHARES + " DESC");
                break;
            case 7:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_DATE_ADDED + " DESC");
                break;
            case 8:
                selectQuery = selectQuery.concat(" ORDER BY " + ProductsDB.COLUMN_DATE_ADDED + " ASC");
                break;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_NAME)));
                product.setTaxName(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_TAX_NAME)));
                product.setTaxValue(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_TAX_VALUE)));
                product.setCategoryId(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_CATEGORY_ID)));
                product.setViewCount(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_VIEW_COUNT)));
                product.setOrderCount(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_ORDER_COUNT)));
                product.setShares(cursor.getInt(cursor.getColumnIndex(ProductsDB.COLUMN_SHARES)));
                product.setDateAdded(cursor.getString(cursor.getColumnIndex(ProductsDB.COLUMN_DATE_ADDED)));
                products.add(product);
            } while (cursor.moveToNext());
        }
        db.close();
        return products;
    }

    public void insertVariant(Variant variant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VariantsDB.COLUMN_ID, variant.getId());
        values.put(VariantsDB.COLUMN_PRODUCT_ID, variant.getProduct_id());
        values.put(VariantsDB.COLUMN_PRICE, variant.getPrice());
        values.put(VariantsDB.COLUMN_COLOR, variant.getColor());
        values.put(VariantsDB.COLUMN_SIZE, variant.getSize());
        db.insert(VariantsDB.TABLE_NAME, null, values);
        db.close();
    }

    public List<Variant> getAllVariants(int productId) {
        List<Variant> variants = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + VariantsDB.TABLE_NAME + " WHERE " + VariantsDB.COLUMN_PRODUCT_ID + "='" + productId + "'";
        ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Variant variant = new Variant();
                variant.setId(cursor.getInt(cursor.getColumnIndex(VariantsDB.COLUMN_ID)));
                variant.setProduct_id(cursor.getInt(cursor.getColumnIndex(VariantsDB.COLUMN_PRODUCT_ID)));
                variant.setSize(cursor.getString(cursor.getColumnIndex(VariantsDB.COLUMN_SIZE)));
                variant.setColor(cursor.getString(cursor.getColumnIndex(VariantsDB.COLUMN_COLOR)));
                variant.setPrice(cursor.getInt(cursor.getColumnIndex(VariantsDB.COLUMN_PRICE)));
                variants.add(variant);
            } while (cursor.moveToNext());
        }
        db.close();
        return variants;
    }

    public void updateProductCount(Product product, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        switch (type) {
            case 1:
                values.put(ProductsDB.COLUMN_VIEW_COUNT, product.getViewCount());
                db.update(ProductsDB.TABLE_NAME, values, ProductsDB.COLUMN_ID + " = ?",
                        new String[]{String.valueOf(product.getId())});
                break;
            case 2:
                values.put(ProductsDB.COLUMN_ORDER_COUNT, product.getOrderCount());
                db.update(ProductsDB.TABLE_NAME, values, ProductsDB.COLUMN_ID + " = ?",
                        new String[]{String.valueOf(product.getId())});
                break;
            case 3:
                values.put(ProductsDB.COLUMN_SHARES, product.getShares());
                db.update(ProductsDB.TABLE_NAME, values, ProductsDB.COLUMN_ID + " = ?",
                        new String[]{String.valueOf(product.getId())});
                break;
        }
    }
}
