package com.tusharpatil.ecommapp.models.rankings;

public class Products {
    private int id;

    private int view_count, order_count, shares;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", view_count = " + view_count + "]";
    }
}
