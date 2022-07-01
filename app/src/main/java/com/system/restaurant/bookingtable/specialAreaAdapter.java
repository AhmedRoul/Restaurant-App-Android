package com.system.restaurant.bookingtable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.system.restaurant.R;

import java.util.ArrayList;
import java.util.List;

public class specialAreaAdapter extends ArrayAdapter<specialArea> {

    public specialAreaAdapter(@NonNull Context context, @NonNull ArrayList<specialArea> SpecialArea) {
        super(context, 0, SpecialArea);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item_special_area, parent, false);
        }

         ImageView imageViewFlag = view.findViewById(R.id.icon_special_area);
         TextView textViewName = view.findViewById(R.id.textview_special_area);


         if(getItem(position).getImage()!=0)
         imageViewFlag.setImageResource(getItem(position).getImage());

         textViewName.setText(getItem(position).getText());


        return view;
    }
}
