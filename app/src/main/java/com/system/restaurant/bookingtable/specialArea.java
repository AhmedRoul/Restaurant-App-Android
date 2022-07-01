package com.system.restaurant.bookingtable;

public class specialArea {
    private  String Text;
    private int image;

    public specialArea(String text, int image) {
        this.Text = text;
        this.image = image;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
