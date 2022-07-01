package com.system.restaurant.Menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.system.restaurant.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReyclerAdapterCategory extends  RecyclerView.Adapter<ReyclerAdapterCategory.ViewHolderCategory> {

    private static ArrayList<Category> categoryArrayList;
    private Context context;
    public ReyclerAdapterCategory(ArrayList<Category> categoryArrayList, Context context){
        this.categoryArrayList=categoryArrayList;
        this.context=context;

    }
    public class ViewHolderCategory extends RecyclerView.ViewHolder{
        private CircleImageView image; //category
        private TextView numberFood;
        private TextView name;
        private LinearLayout linearLayout;





        public ViewHolderCategory(@NonNull View itemView) {
            super(itemView);
            //image=itemView.findViewById(R.id.image_category_template);
           // numberFood=itemView.findViewById(R.id.number_food_category_template);
            //name=itemView.findViewById(R.id.name_category_template);
            //linearLayout =itemView.findViewById(R.id.linear_layout_category_template);
        }


    }


    @NonNull
    @Override
    public ReyclerAdapterCategory.ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_special_area,parent,false);
    return new ViewHolderCategory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReyclerAdapterCategory.ViewHolderCategory holder, int position) {
        Category category=categoryArrayList.get(position);

        holder.image.setImageBitmap(category.getImage());
        holder.numberFood.setText(String.valueOf(category.getNumberFood()));
        holder.name.setText(category.getName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to food of category
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }



}
