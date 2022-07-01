package com.system.restaurant.Menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.system.restaurant.R;
import java.util.ArrayList;


public class ActivityFood extends AppCompatActivity {

    private String EmailUser ="ahmed123456";
    private RecyclerView recyclerView;
    private ArrayList<Food> foodArrayList;
    private ImageButton recyclerMode,gridMode;
    private String modeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        declaration();

        setDataFood();
        if(modeView.equals("recyclerview")) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),RecyclerView.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            Adapter();
        }
        Mode();


    }

    private void declaration(){
        recyclerView=findViewById(R.id.recyclerview_food);
        foodArrayList=new ArrayList<>();
        recyclerMode=findViewById(R.id.recycler_view_mode_food);
        gridMode=findViewById(R.id.grid_view_mode_food);

        modeView="recyclerview";



    }
    private void Mode(){
        recyclerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modeView.equals("gridview")){
                    LinearLayoutManager layoutManager=new LinearLayoutManager(recyclerView.getContext(),RecyclerView.VERTICAL,false);
                    recyclerView.setLayoutManager(layoutManager);
                    Adapter();
                    recyclerView.setPadding(20,20,20,20);
                    modeView="recyclerview";

                }
            }
        });
        gridMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modeView.equals("recyclerview")){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ActivityFood.this,2,GridLayoutManager.VERTICAL,false);

                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setPadding(0,30,0,20);

                    Adapter();
                    modeView="gridview";

                }

            }
        });


    }

    private void Adapter(){

        RecyclerView.Adapter adapter=new RecyclerAdapterFood(foodArrayList ,this,EmailUser);
        recyclerView.setAdapter(adapter);
    }
    private void setDataFood(){

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.chairs);
        foodArrayList.add(new Food(icon,"Ahmed",105,115));
        foodArrayList.add(new Food(icon,"24daed",115,180));
        foodArrayList.add(new Food(icon,"Ahmedsaasas",104,160));
        foodArrayList.add(new Food(icon,"Ahmedsasa",185,141));
        foodArrayList.add(new Food(icon,"Ahmedsaas",145,1014.5f));

    }
    public void showPopupFilter(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        popup.inflate(R.menu.menu_filter);
        popup.show();
    }
}
