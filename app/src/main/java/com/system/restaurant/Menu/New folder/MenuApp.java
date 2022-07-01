package com.system.restaurant.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.system.restaurant.R;

import java.util.ArrayList;

public class MenuApp extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Category> categoryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_menu);
       // recyclerView=findViewById(R.id.recyclerview_category);
        categoryArrayList=new ArrayList<>();
        DataCategory();
        Adapter();


    }

    private void Adapter(){

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter=new ReyclerAdapterCategory(categoryArrayList ,this);
        recyclerView.setAdapter(adapter);

    }
    private void DataCategory(){

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.arrow_right);

        categoryArrayList.add(new Category(101,"sasasasa",icon,5));
        categoryArrayList.add(new Category(101,"assasa",icon,1));
        categoryArrayList.add(new Category(101,"hhhh",icon,2));
        categoryArrayList.add(new Category(101,"sasasasa",icon,5));
        categoryArrayList.add(new Category(101,"vvvvv",icon,9));
        categoryArrayList.add(new Category(101,"vccvvcv",icon,7));
        categoryArrayList.add(new Category(101,"vcvccv",icon,0));
    }
}
