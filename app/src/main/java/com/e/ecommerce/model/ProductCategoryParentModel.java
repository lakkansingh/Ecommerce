package com.e.ecommerce.model;

import java.util.ArrayList;

public class ProductCategoryParentModel {
    private int parentId;
    private String title;
    private ArrayList<ProductCategoryModel> productCategoryItemModels;
    private boolean isDataFetched;

    public ProductCategoryParentModel(String title, ArrayList<ProductCategoryModel> productCategoryItemModels) {
        this.title = title;
        this.productCategoryItemModels = productCategoryItemModels;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isDataFetched() {
        return isDataFetched;
    }

    public void setDataFetched(boolean dataFetched) {
        isDataFetched = dataFetched;
    }

    public ProductCategoryParentModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ProductCategoryModel> getProductCategoryItemModels() {
        return productCategoryItemModels;
    }

    public void setProductCategoryItemModels(ArrayList<ProductCategoryModel> productCategoryItemModels) {
        this.productCategoryItemModels = productCategoryItemModels;
    }
}
