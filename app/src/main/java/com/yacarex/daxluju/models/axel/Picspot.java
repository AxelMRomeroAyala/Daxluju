package com.yacarex.daxluju.models.axel;

import com.google.firebase.firestore.GeoPoint;

public class Picspot {

    private GeoPoint location;
    private String title;
    private String description;

    public Picspot() {
    }

    public Picspot(GeoPoint location, String title, String description) {
        this.location = location;
        this.title = title;
        this.description = description;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
