package com.e.ecommerce.model;

import java.io.Serializable;

public class ImagesModel implements Serializable {
    private int id;
    private String src;
    private String name;

    public ImagesModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
