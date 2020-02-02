package com.tusharpatil.ecommapp.models;

import com.tusharpatil.ecommapp.models.categories.Categories;
import com.tusharpatil.ecommapp.models.rankings.Rankings;

public class ProductDataResponsePojo {
    private Rankings[] rankings;

    private Categories[] categories;

    public Rankings[] getRankings() {
        return rankings;
    }

    public void setRankings(Rankings[] rankings) {
        this.rankings = rankings;
    }

    public Categories[] getCategories() {
        return categories;
    }

    public void setCategories(Categories[] categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ClassPojo [rankings = " + rankings.length + ", categories = " + categories.length + "]";
    }
}
