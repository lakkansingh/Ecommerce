package com.e.ecommerce.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.ArrayList;

public class ProductModel implements Serializable {
    private boolean isChecked;
    private String search;
    private int id;
    private String stock_quantity;
    private int parent_id;
    private String name;
    private String price;
    private String sale_price;
    private String stock_status;
    private int quantity;
    private String description;
    private String regular_price;
    private int page;
    private int per_page;
    @SerializedName("images")
    private ArrayList<ImagesModel> imagesModelArrayList;
    /*@SerializedName("categories")
    private ArrayList<CategoriesModel> categoriesModels;*/
    private String on_sale;

    public ProductModel() {
    }

    public ProductModel(String products) {
    }

   /* public ArrayList<CategoriesModel> getCategoriesModels() {
        return categoriesModels;
    }

    public void setCategoriesModels(ArrayList<CategoriesModel> categoriesModels) {
        this.categoriesModels = categoriesModels;
    }*/

    public String getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(String stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ArrayList<ImagesModel> getImagesModelArrayList() {
        return imagesModelArrayList;
    }

    public void setImagesModelArrayList(ArrayList<ImagesModel> imagesModelArrayList) {
        this.imagesModelArrayList = imagesModelArrayList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   /* {
        "product_id": 103,
            "quantity": 2
    }*/

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "product_id=" + id +
                ", quantity=" + quantity +
                '}';
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOn_sale() {
        return on_sale;
    }

    public void setOn_sale(String on_sale) {
        this.on_sale = on_sale;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    /**
     * Creating 10 dummy content for list.
     *
     * @param itemCount
     * @return
     */
    public  ArrayList<ProductModel> createProducts(int itemCount) {
        ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            ProductModel productModel = new ProductModel(name + (itemCount == 0 ?
                    (itemCount + 1 + i) : (itemCount + i)));
            productModelArrayList.add(productModel);
        }
        return productModelArrayList;
    }
}
