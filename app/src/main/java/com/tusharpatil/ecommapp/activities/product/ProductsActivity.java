package com.tusharpatil.ecommapp.activities.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.activities.product_details.ProductDetailsActivity;
import com.tusharpatil.ecommapp.adapters.ProductAdapter;
import com.tusharpatil.ecommapp.local_db.DatabaseHelper;
import com.tusharpatil.ecommapp.models.categories.Product;

import java.util.List;

public class ProductsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DatabaseHelper db;
    private ListView listView;
    private ProductAdapter productAdapter;
    private List<Product> products;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        int categoryId = getIntent().getIntExtra("category_id", 0);
        String categoryName = getIntent().getStringExtra("category_name");
        getSupportActionBar().setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.products);
        db = new DatabaseHelper(this);
        products = db.getProducts(categoryId);
        showProducts();
    }

    private void showProducts() {
        productAdapter = new ProductAdapter(this, products);
        listView.setAdapter(productAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
        intent.putExtra("product_id", products.get(position).getId());
        intent.putExtra("product_name", products.get(position).getName());
        intent.putExtra("product_tax_name", products.get(position).getTaxName());
        intent.putExtra("product_tax_value", products.get(position).getTaxValue());
        intent.putExtra("product_view_count", products.get(position).getViewCount());
        intent.putExtra("product_order_count", products.get(position).getOrderCount());
        intent.putExtra("product_shares", products.get(position).getShares());
        intent.putExtra("product_date_added", products.get(position).getDateAdded());
        intent.putExtra("product_category_id", products.get(position).getCategoryId());
        startActivity(intent);
    }
}
