package com.tusharpatil.ecommapp.activities.product_details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tusharpatil.ecommapp.utils.Config;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        int productId = getIntent().getIntExtra(Config.PRODUCT_ID_KEY, 0);
        if (productId == 0) {
            Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            return;
        }
        String productName = getIntent().getStringExtra(Config.PRODUCT_NAME_KEY);
        String taxName = getIntent().getStringExtra(Config.PRODUCT_TAX_NAME_KEY);
        String taxValue = getIntent().getStringExtra(Config.PRODUCT_TAX_VALUE_KEY);
        int viewCount = getIntent().getIntExtra(Config.PRODUCT_VIEW_COUNT_KEY, 0);
        int orderCount = getIntent().getIntExtra(Config.PRODUCT_ORDER_COUNT_KEY, 0);
        int shares = getIntent().getIntExtra(Config.PRODUCT_SHARES_KEY, 0);
        int categoryId = getIntent().getIntExtra(Config.PRODUCT_CATEGORY_ID_KEY, 0);
        String dateAdded = getIntent().getStringExtra(Config.PRODUCT_DATE_ADDED_KEY);

        getSupportActionBar().setTitle(productName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        DatabaseHelper db = new DatabaseHelper(this);
        init(db, productId, productName, taxName, taxValue, viewCount, orderCount, shares, categoryId, dateAdded);
    }

    private void init(DatabaseHelper db, int productId, String productName, String taxName, String taxValue, int viewCount, int orderCount, int shares, int categoryId, String dateAdded) {
        TextView textName = findViewById(R.id.textName);
        textName.setText("Name: " + productName);
        TextView textTaxName = findViewById(R.id.textTaxName);
        textTaxName.setText("Tax: " + taxName);
        TextView textTaxValue = findViewById(R.id.textTaxValue);
        textTaxValue.setText("Tax(%): " + taxValue);
        TextView textViewCount = findViewById(R.id.textViewCount);
        textViewCount.setText("Views: " + String.valueOf(viewCount));
        TextView textOrderCount = findViewById(R.id.textOrderCount);
        textOrderCount.setText("Orders: " + String.valueOf(orderCount));
        TextView textShares = findViewById(R.id.textShares);
        textShares.setText("Shares: " + String.valueOf(shares));
        TextView textDateAdded = findViewById(R.id.textDateAdded);
        textDateAdded.setText("Added On: " + dateAdded);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
        recyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        VariantAdapter variantAdapter = new VariantAdapter(db.getAllVariants(productId));
        recyclerView.setAdapter(variantAdapter);
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
}
