package com.tusharpatil.ecommapp.activities.product;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.local_db.DatabaseHelper;

public class ProductsActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        int categoryId = getIntent().getIntExtra("id", 0);
        String categoryName = getIntent().getStringExtra("category_name");
        getSupportActionBar().setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHelper(this);
        showProducts(categoryId);
    }

    private void showProducts(int categoryId) {
//        categoryAdapter.addItems(db.getCategory(categoryId));
//        recyclerView.setAdapter(categoryAdapter);
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
