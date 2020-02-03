package com.tusharpatil.ecommapp.activities.category;

import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tusharpatil.ecommapp.BuildConfig;
import com.tusharpatil.ecommapp.R;
import com.tusharpatil.ecommapp.adapters.CategoryAdapter;
import com.tusharpatil.ecommapp.adapters.DividerItemDecoration;
import com.tusharpatil.ecommapp.local_db.DatabaseHelper;
import com.tusharpatil.ecommapp.models.ChildParentId;
import com.tusharpatil.ecommapp.models.ProductDataResponsePojo;
import com.tusharpatil.ecommapp.models.categories.Categories;
import com.tusharpatil.ecommapp.models.categories.Category;
import com.tusharpatil.ecommapp.models.categories.Product;
import com.tusharpatil.ecommapp.models.categories.Products;
import com.tusharpatil.ecommapp.models.categories.Variant;
import com.tusharpatil.ecommapp.models.categories.Variants;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.Callback {
    private DatabaseHelper db;
    private int categoryId;
    private String title;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        categoryId = getIntent().getIntExtra("category_id", 0);
        title = getIntent().getStringExtra("category_name");

        init();
    }

    private void init() {
        LinearLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider_drawable);
        recyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        categoryAdapter = new CategoryAdapter(new ArrayList<Category>());
        checkStack();
    }

    private void getProductData() {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.GET, BuildConfig.BASE_URL + "/api/jsonBlob/7a640a9d-443a-11ea-9fc2-d347e19004ba", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                ProductDataResponsePojo productDataResponsePojo = new Gson().fromJson(response, ProductDataResponsePojo.class);
                ArrayList<ChildParentId> childParentIds = new ArrayList<>();
                for (Categories category : productDataResponsePojo.getCategories()) {
                    db.insertCategory(new Category(category.getId(), category.getName().trim(), 0));
                    for (int childId : category.getChild_categories())
                        childParentIds.add(new ChildParentId(category.getId(), childId));
                    for (Products product : category.getProducts()) {
                        db.insertProduct(new Product(product.getId(), category.getId(), product.getName(), product.getTax().getName(), product.getTax().getValue(), product.getDate_added()));
                        for (Variants variant : product.getVariants())
                            db.insertVariant(new Variant(variant.getId(), product.getId(), variant.getPrice(), variant.getColor(), variant.getSize()));
                    }
                }
                for (ChildParentId childParentId : childParentIds)
                    db.updateParentCategory(new Category(childParentId.getChildId(), childParentId.getParentId()));
                for (com.tusharpatil.ecommapp.models.rankings.Products product : productDataResponsePojo.getRankings()[0].getProducts())
                    db.updateProductCount(new Product(product.getId(), product.getView_count(), 0, 0), 1);
                for (com.tusharpatil.ecommapp.models.rankings.Products product : productDataResponsePojo.getRankings()[1].getProducts())
                    db.updateProductCount(new Product(product.getId(), 0, product.getOrder_count(), 0), 2);
                for (com.tusharpatil.ecommapp.models.rankings.Products product : productDataResponsePojo.getRankings()[2].getProducts())
                    db.updateProductCount(new Product(product.getId(), 0, 0, product.getShares()), 3);
                showCategories(0);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                showCategories(0);
            }
        });
        mRequestQueue.add(strReq);
    }

    private void showCategories(int categoryId) {
        categoryAdapter.addItems(db.getCategory(categoryId));
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onEmptyViewRetryClick() {

    }

    private void checkStack() {
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);
        if (taskList.get(0).numActivities == 1 && taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            getSupportActionBar().setTitle("Categories");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
            getProductData();
        } else {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            showCategories(categoryId);
        }
    }
}
