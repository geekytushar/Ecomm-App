package com.tusharpatil.ecommapp.activities.product_details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.adapters.DividerItemDecoration;
import com.tusharpatil.ecommapp.adapters.VariantAdapter;
import com.tusharpatil.ecommapp.local_db.DatabaseHelper;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView textName, textTaxName, textTaxValue, textCategoryName, textViewCount, textOrderCount, textShares, textDateAdded;
    private int productId;
    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private VariantAdapter variantAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productId = getIntent().getIntExtra("product_id", 0);
        String productName = getIntent().getStringExtra("product_name");
        String taxName = getIntent().getStringExtra("product_tax_name");
        String taxValue = getIntent().getStringExtra("product_tax_value");
        int viewCount = getIntent().getIntExtra("product_view_count", 0);
        int orderCount = getIntent().getIntExtra("product_order_count", 0);
        int shares = getIntent().getIntExtra("product_shares", 0);
        int categoryId = getIntent().getIntExtra("product_category_id", 0);
        String dateAdded = getIntent().getStringExtra("product_date_added");

        getSupportActionBar().setTitle(productName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        db = new DatabaseHelper(this);
        init(productName, taxName, taxValue, viewCount, orderCount, shares, categoryId, dateAdded);
    }

    private void init(String productName, String taxName, String taxValue, int viewCount, int orderCount, int shares, int categoryId, String dateAdded) {
        textName = findViewById(R.id.textName);
        textName.setText(productName);
        textTaxName = findViewById(R.id.textTaxName);
        textTaxName.setText(taxName);
        textTaxValue = findViewById(R.id.textTaxValue);
        textTaxValue.setText(taxValue);
        textCategoryName = findViewById(R.id.textCategoryName);
        textCategoryName.setText(String.valueOf(categoryId));
        textViewCount = findViewById(R.id.textViewCount);
        textViewCount.setText(String.valueOf(viewCount));
        textOrderCount = findViewById(R.id.textOrderCount);
        textOrderCount.setText(String.valueOf(orderCount));
        textShares = findViewById(R.id.textShares);
        textShares.setText(String.valueOf(shares));
        textDateAdded = findViewById(R.id.textDateAdded);
        textDateAdded.setText(dateAdded);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
        recyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        variantAdapter = new VariantAdapter(db.getAllVariants(productId));
        recyclerView.setAdapter(variantAdapter);
    }
}
