package com.tusharpatil.ecommapp.activities.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.activities.product_details.ProductDetailsActivity;
import com.tusharpatil.ecommapp.adapters.ProductAdapter;
import com.tusharpatil.ecommapp.local_db.DatabaseHelper;
import com.tusharpatil.ecommapp.models.categories.Product;
import com.tusharpatil.ecommapp.utils.Config;

import java.util.List;

public class ProductsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DatabaseHelper db;
    private int categoryId;
    private ProductAdapter productAdapter;
    private List<Product> products;
    private static final int MENU_ITEM_ITEM1 = 1;
    private static final int MENU_ITEM_ITEM2 = 2;
    private static final int MENU_ITEM_ITEM3 = 3;
    private static final int MENU_ITEM_ITEM4 = 4;
    private static final int MENU_ITEM_ITEM5 = 5;
    private static final int MENU_ITEM_ITEM6 = 6;
    private static final int MENU_ITEM_ITEM7 = 7;
    private static final int MENU_ITEM_ITEM8 = 8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        categoryId = getIntent().getIntExtra(Config.CATEGORY_ID_KEY, 0);
        if (categoryId == 0) {
            Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            return;
        }
        String categoryName = getIntent().getStringExtra(Config.CATEGORY_NAME_KEY);
        getSupportActionBar().setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView listView = (ListView) findViewById(R.id.products_list_view);
        db = new DatabaseHelper(this);
        products = db.getProducts(categoryId);
        showProducts(listView);
    }

    private void showProducts(ListView listView) {
        productAdapter = new ProductAdapter(this, products);
        listView.setAdapter(productAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, R.string.view_low_high);
        menu.add(Menu.NONE, MENU_ITEM_ITEM2, Menu.NONE, R.string.view_high_low);
        menu.add(Menu.NONE, MENU_ITEM_ITEM3, Menu.NONE, R.string.order_low_high);
        menu.add(Menu.NONE, MENU_ITEM_ITEM4, Menu.NONE, R.string.orders_high_low);
        menu.add(Menu.NONE, MENU_ITEM_ITEM5, Menu.NONE, R.string.shares_low_high);
        menu.add(Menu.NONE, MENU_ITEM_ITEM6, Menu.NONE, R.string.shares_high_low);
        menu.add(Menu.NONE, MENU_ITEM_ITEM7, Menu.NONE, R.string.latest);
        menu.add(Menu.NONE, MENU_ITEM_ITEM8, Menu.NONE, R.string.oldest);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case MENU_ITEM_ITEM1:
            case MENU_ITEM_ITEM2:
            case MENU_ITEM_ITEM3:
            case MENU_ITEM_ITEM4:
            case MENU_ITEM_ITEM5:
            case MENU_ITEM_ITEM6:
            case MENU_ITEM_ITEM7:
            case MENU_ITEM_ITEM8:
                products.clear();
                products.addAll(db.getSortedProducts(categoryId, item.getItemId()));
                productAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
        intent.putExtra(Config.PRODUCT_ID_KEY, products.get(position).getId());
        intent.putExtra(Config.PRODUCT_NAME_KEY, products.get(position).getName());
        intent.putExtra(Config.PRODUCT_TAX_NAME_KEY, products.get(position).getTaxName());
        intent.putExtra(Config.PRODUCT_TAX_VALUE_KEY, products.get(position).getTaxValue());
        intent.putExtra(Config.PRODUCT_VIEW_COUNT_KEY, products.get(position).getViewCount());
        intent.putExtra(Config.PRODUCT_ORDER_COUNT_KEY, products.get(position).getOrderCount());
        intent.putExtra(Config.PRODUCT_SHARES_KEY, products.get(position).getShares());
        intent.putExtra(Config.PRODUCT_DATE_ADDED_KEY, products.get(position).getDateAdded());
        intent.putExtra(Config.PRODUCT_CATEGORY_ID_KEY, products.get(position).getCategoryId());
        startActivity(intent);
    }
}
