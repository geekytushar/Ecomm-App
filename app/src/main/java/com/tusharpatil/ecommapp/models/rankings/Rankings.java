package com.tusharpatil.ecommapp.models.rankings;

public class Rankings {
    private String ranking;

    private Products[] products;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public Products[] getProducts() {
        return products;
    }

    public void setProducts(Products[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ClassPojo [ranking = " + ranking + ", products = " + products + "]";
    }
}
