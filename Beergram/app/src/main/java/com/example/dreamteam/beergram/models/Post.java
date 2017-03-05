package com.example.dreamteam.beergram.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private String id;
    private double longitude;
    private double latitude;
    private String postTime;

    public Post(double longitude, double latitude, String email){
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.postTime = formatter.format(new Date());
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
