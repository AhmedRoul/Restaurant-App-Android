package com.system.restaurant.Menu;

import android.graphics.Bitmap;

public class Food {
    private Bitmap image;
    private String Name;
    private int ID;
    private float price;

    public Food(Bitmap image, String name, int ID, float price) {
        this.image = image;
        Name = name;
        this.ID = ID;
        this.price = price;
    }

    public Bitmap getBitmap() {
        return image;
    }

    public void setBitmap(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
