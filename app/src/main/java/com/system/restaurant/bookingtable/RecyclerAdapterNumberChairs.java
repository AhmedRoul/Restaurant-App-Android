package com.system.restaurant.bookingtable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.system.restaurant.R;

import java.util.ArrayList;

public class RecyclerAdapterNumberChairs extends RecyclerView.Adapter<RecyclerAdapterNumberChairs.ViewHolder> {


    private static ArrayList<NumberChair> numbers;
    private static int lastPostion;
    private  Context context;


    public RecyclerAdapterNumberChairs(ArrayList<NumberChair> numbers,Context context){
        this.numbers=numbers;
        this.lastPostion=0;
        this.context=context;
        numbers.get(lastPostion).setChecked(true);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
         ViewHolder(View view){
             super(view);
             radioButton=view.findViewById(R.id.radioButtonRecycler);

              radioButton.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         numbers.get(lastPostion).setChecked(false);
                         numbers.get(getAdapterPosition()).setChecked(true);
                         notifyItemChanged(getAdapterPosition());

                         notifyItemChanged(lastPostion);
                         lastPostion=getAdapterPosition();
                         }
                 });

         }



    }
    @NonNull
    @Override
    public RecyclerAdapterNumberChairs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.numchair_radbttn_reyclerview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterNumberChairs.ViewHolder holder, int position) {

        holder.radioButton.setText(String.valueOf(numbers.get(position).getNumber()));
        holder.radioButton.setChecked(numbers.get(position).isChecked());


    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
    public static int getLastPostion(){
        return lastPostion;
    }
    public static ArrayList<NumberChair> getArraylistNumberChair(){
        return numbers;
    }
}
