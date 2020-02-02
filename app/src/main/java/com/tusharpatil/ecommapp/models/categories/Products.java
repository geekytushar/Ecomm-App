package com.tusharpatil.ecommapp.models.categories;

public class Products {
    private String date_added;

    private String name;

    private Tax tax;

    private int id;

    private Variants[] variants;

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Variants[] getVariants() {
        return variants;
    }

    public void setVariants(Variants[] variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "ClassPojo [date_added = " + date_added + ", name = " + name + ", tax = " + tax + ", id = " + id + ", variants = " + variants + "]";
    }
}
