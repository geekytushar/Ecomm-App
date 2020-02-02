package com.tusharpatil.ecommapp.activities.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.tusharpatil.ecommapp.R;
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

public class SplashActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        db = new DatabaseHelper(this);
//        for (Category category : db.getAllCategories()) {
//            Log.d("TAGG8", "Id ==>" + category.getId() + " :: Parent ==>" + category.getParentCategory() + " :: Name ==>" + category.getName());
//        }
//        for (Product product : db.getAllProducts()) {
//            Log.d("TAGG11", "Id ==>" + product.toString());
//        }
//        for (Variant variant : db.getAllVariants()) {
//            Log.d("TAGG12", "Id ==>" + variant.getId() + " :: Name ==>" + variant.getPrice());
//        }

        VolleyLog.DEBUG = true;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.GET, "https://jsonblob.com/api/jsonBlob/7a640a9d-443a-11ea-9fc2-d347e19004ba", new Response.Listener<String>() {

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
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(strReq);
    }
}
