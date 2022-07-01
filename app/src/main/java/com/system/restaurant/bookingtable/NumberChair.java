package com.system.restaurant.bookingtable;

public class NumberChair {
   private boolean checked;
   private int number;

    public NumberChair(boolean checked,int number) {
        this.checked = checked;
        this.number=number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
