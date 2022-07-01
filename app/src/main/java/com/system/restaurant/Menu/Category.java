package com.system.restaurant.Menu;

import android.graphics.Bitmap;

public class Category {
    private int ID;
    private String name;
    private Bitmap image;
    private int  numberFood;

    public Category(int ID, String name, Bitmap image, int numberFood) {
        this.ID = ID;
        this.name = name;
        this.image = image;
        this.numberFood = numberFood;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberFood() {
        return numberFood;
    }

    public void setNumberFood(int numberFood) {
        this.numberFood = numberFood;
    }
}
