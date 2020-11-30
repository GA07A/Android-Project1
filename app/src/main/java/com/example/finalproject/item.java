package com.example.finalproject;

public class item {
    private String tital;
    private String name;
    private int image;

    public item(String tital, String name, int image) {
        this.tital = tital;
        this.name = name;
        this.image = image;
    }

    public String getTital() {
        return tital;
    }

    public void setTital(String tital) {
        this.tital = tital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
