package com.e.ecommerce.model;

import com.google.gson.annotations.SerializedName;

public class ProductCategoryModel {
    private int id;
    private boolean hide_empty;
    private String name;
    private String slug;
    private int parent;
    private String description;
    private int per_page;
    private int page;
    private String stock_status;
    @SerializedName("image")
    private ImagesModel imagesModel;

    /*@SerializedName("images")
    private ArrayList<ImageSrcModel> imagesModelArrayList;

    public ArrayList<ImageSrcModel> getImagesModelArrayList() {
        return imagesModelArrayList;
    }

    public void setImagesModelArrayList(ArrayList<ImageSrcModel> imagesModelArrayList) {
        this.imagesModelArrayList = imagesModelArrayList;
    }*/

    public ProductCategoryModel() {
    }

    public ImagesModel getImagesModel() {
        return imagesModel;
    }

    public void setImagesModel(ImagesModel imagesModel) {
        this.imagesModel = imagesModel;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isHide_empty() {
        return hide_empty;
    }

    public void setHide_empty(boolean hide_empty) {
        this.hide_empty = hide_empty;
    }

    public String getStock_status() {
        return stock_status;
    }

    public void setStock_status(String stock_status) {
        this.stock_status = stock_status;
    }

    /*{
        "id": 44,
            "name": "Silk",
            "slug": "silk",
            "parent": 0,
            "description": "",
            "display": "default",
            "image": {
        "id": 1259,
                "date_created": "2018-11-21T07:43:08",
                "date_created_gmt": "2018-11-21T07:43:08",
                "date_modified": "2018-11-21T07:43:26",
                "date_modified_gmt": "2018-11-21T07:43:26",
                "src": "http://fem.4devlab.net/wp-content/uploads/2018/11/Banarasi-Saree.jpg",
                "name": "Banarasi-Saree",
                "alt": "Banarasi-Saree"
    },
        "menu_order": 0,
            "count": 4,
            "_links": {
        "self": [
        {
            "href": "http://fem.4devlab.net/wp-json/wc/v3/products/categories/44"
        }
    ],
        "collection": [
        {
            "href": "http://fem.4devlab.net/wp-json/wc/v3/products/categories"
        }
    ]
    }
    }*/
}
