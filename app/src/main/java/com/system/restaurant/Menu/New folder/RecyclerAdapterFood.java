package com.system.restaurant.Menu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.system.restaurant.Database.CartDBHelper;
import com.system.restaurant.R;
import java.util.ArrayList;

public class RecyclerAdapterFood  extends RecyclerView.Adapter<RecyclerAdapterFood.ViewHolderFood> implements View.OnClickListener {

    static ArrayList<Food> foodArrayList;
    Context context;
    String EmailUser;
    String name, priceFloatString, priceIntString;
    int numID;

    public RecyclerAdapterFood(ArrayList<Food> foodArrayList , Context context,String EmailUser){
        this.foodArrayList=foodArrayList;
        this.context=context;
        this.EmailUser=EmailUser;
        priceFloatString=name=priceIntString="";
        numID=0;
    }

    public class ViewHolderFood extends RecyclerView.ViewHolder {

        private TextView priceInteger;
        private TextView priceFloat;
        private TextView nameFood;
        private RatingBar ratingBar;
        private ImageView imageFood;
        private ImageButton shoppingCart;


        public ViewHolderFood(@NonNull View itemView) {
            super(itemView);
            priceInteger = itemView.findViewById(R.id.price_integer_template_food);
            priceFloat = itemView.findViewById(R.id.price_float_template_food);
            nameFood = itemView.findViewById(R.id.name_template_food);
            ratingBar = itemView.findViewById(R.id.ratingbar_template_food);
            imageFood = itemView.findViewById(R.id.imageView_template_food);
            shoppingCart = itemView.findViewById(R.id.shopping_cart_template_food);

        }
    }


    @NonNull
    @Override
    public RecyclerAdapterFood.ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.template_item_food,
                parent,false);
        return new ViewHolderFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterFood.ViewHolderFood holder, int position) {
        Food food=foodArrayList.get(position);
        int priceInt =(int)food.getPrice();
        float pricefloat=(food.getPrice()-priceInt)*100;
         int price= (int) pricefloat;


        holder.priceInteger.setText(String.valueOf(priceInt));
        if(price==0)
            holder.priceFloat.setText(".00");
        else
            holder.priceFloat.setText("."+String.valueOf(price));

        holder.nameFood.setText(food.getName());
        holder.ratingBar.setRating(4);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            Drawable drawableProgress = DrawableCompat.wrap( holder.ratingBar.getIndeterminateDrawable());
            DrawableCompat.setTint(drawableProgress, ContextCompat.getColor(context, android.R.color.holo_green_light));
            holder.ratingBar.setIndeterminateDrawable(DrawableCompat.unwrap(drawableProgress));

        } else {
            holder.ratingBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, android.R.color.holo_green_light), PorterDuff.Mode.SRC_IN);
        }
        holder.imageFood.setImageBitmap(food.getBitmap());
        name=food.getName();
        priceFloatString= holder.priceFloat.getText().toString();
        priceIntString= holder.priceInteger.getText().toString();
        holder.shoppingCart.setOnClickListener(this::onClick);

        numID=food.getID();
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }
    public static ArrayList<Food> getArrayList(){
        return  foodArrayList;
    }

    //dialog
    @Override
    public void onClick(View v) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertdialog_mount_food);


        TextView priceIntegerDialog=dialog.findViewById(R.id.price_integer_dialog_food);
        TextView  priceFloatDialog=dialog.findViewById(R.id.price_float_dialog_food);
        TextView  nameFoodDialog=dialog.findViewById(R.id.name_dialog_food);
        TextView numberOfItem= dialog.findViewById(R.id.number_dialog_food);
        ImageButton plus= dialog.findViewById(R.id.plus_dialog_food);
        ImageButton minus= dialog.findViewById(R.id.minus_dialog_food);
        Button Cancel= dialog.findViewById(R.id.cancel_dialog_button);
        Button addCart= dialog.findViewById(R.id.add_cart_dialog_button);

        nameFoodDialog.setText(name);
        priceFloatDialog.setText(priceFloatString);
        priceIntegerDialog.setText(priceIntString);


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(context,"Cancel Order",Toast.LENGTH_LONG).show();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number=Integer.parseInt(numberOfItem.getText().toString())+1;
                numberOfItem.setText(String.valueOf(number));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number=Integer.parseInt(numberOfItem.getText().toString())-1;
                if (number==0)
                {
                    dialog.dismiss();
                    Toast.makeText(context,"Cancel Order",Toast.LENGTH_LONG).show();
                }
                else
                    numberOfItem.setText(String.valueOf(number));

            }
        });

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert to data base

                CartDBHelper database=new CartDBHelper(context);
                database.insert(EmailUser,numID,Integer.parseInt(numberOfItem.getText().toString()));
                Toast.makeText(context,"insert to data base",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });


        dialog.show();
    }


}
