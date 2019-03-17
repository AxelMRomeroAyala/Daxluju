package com.yacarex.daxluju;

public class PerritoModel {

    private String name;
    private String image;

    public PerritoModel(String name, String image){
        this.setName(name);
        this.setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
