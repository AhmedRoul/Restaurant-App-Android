package com.system.restaurant.bookingtable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.system.restaurant.R;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BookingTable extends AppCompatActivity {

    private RadioButton inSide,outSide;
    private Button Cancel ,Confirm;
    private TextView timeTextView;
    private RecyclerView recyclerView;
    private ArrayList<NumberChair> numberChairs;
    private int lastPostionNumberChairs;
    private  Spinner spinnerSpecialArea;
    private specialAreaAdapter SpecialAreaAdapter;
    private ArrayList<specialArea> specialAreas;
    private int Hour;
    private int Minute;
    private String A_PMeridiem; //AM ||PM
    private final  int numberLimit=10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_table);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_iconmonstr_arrow_64);
        actionBar.setDisplayHomeAsUpEnabled(true);



        declaration();
        Adapter();
        SpannerSpecialArea();
        setDefaultTime();
        dialogInterfaceBox();

        Pair<RadioButton,RadioButton> Button=ColorRadioButton(inSide,outSide);
        inSide=Button.first;outSide=Button.second;

         Button=ColorRadioButton(outSide,inSide);
        outSide=Button.first;inSide=Button.second;



    }
   private void dialogInterfaceBox(){
       Confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(BookingTable.this,R.style.DialogInterfaceBox);
               builder.setTitle("Confirm");
               builder.setMessage("5 EGP per seat  will be discount from your wallet\n\n If you agree, press OK ");
               builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                   //    Instant instant=new Instant(BookingTable.this,/**/);
                      // instant.

                       //Check Wallet
                       //if he have money  go to new activity

                       dialog.dismiss();
                   }
               });
               builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {

                   public void onClick(DialogInterface dialog, int which) {

                       dialog.dismiss();
                   }
               });
               AlertDialog alert = builder.create();
               alert.show();
           }
       });
   }
   private void setDefaultTime(){
        Calendar calendar = Calendar.getInstance();
        int hourCurrent= calendar.get(Calendar.HOUR_OF_DAY)+1%24;
        int minuteCurrent= calendar.get(Calendar.MINUTE);
        checkTime(hourCurrent,minuteCurrent);
        String APMcurrent="";
        Pair<Integer, String> pair=convertTime(hourCurrent);
        hourCurrent=pair.first;    APMcurrent=pair.second;
        if(minuteCurrent<10)
            timeTextView.setText(String.valueOf(hourCurrent)+":"+"0"+String.valueOf(minuteCurrent)+"\t"+APMcurrent);
        else
            timeTextView.setText(String.valueOf(hourCurrent)+":"+String.valueOf(minuteCurrent)+"\t"+APMcurrent);

        timeTextView.setTextSize(25);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimePicker();
            }
        });
    }

   private Pair<RadioButton,RadioButton> ColorRadioButton(RadioButton Button1 ,RadioButton Button2 ){
       Button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(Button1.isChecked()){
                   Button1.setTextColor(getResources().getColor(R.color.Booking_text_seat_area_on));
                   Button2.setTextColor(getResources().getColor(R.color.Booking_text_seat_area_off));

               }
               else {
                   Button1.setTextColor(getResources().getColor(R.color.Booking_text_seat_area_off));
                   Button2.setTextColor(getResources().getColor(R.color.Booking_text_seat_area_on));
               }
           }
       });
       return  new Pair<RadioButton, RadioButton>(Button1,Button2);
   }

    private void declaration(){
        inSide=findViewById(R.id.inside_button);
        outSide=findViewById(R.id.outside_button);
        Cancel=findViewById(R.id.booking_cancel_button);
        Confirm=findViewById(R.id.booking_confirm_button);
        recyclerView=findViewById(R.id.recyclerview_number_chair);
        spinnerSpecialArea=findViewById(R.id.special_area_spinner);
        timeTextView=findViewById(R.id.time_text_view);


        A_PMeridiem="";

        numberChairs=new ArrayList<>();
        for(int i=1;i<=numberLimit;i++)
        numberChairs.add(new NumberChair(false,i));

        specialAreas=new ArrayList<>();
        specialAreas.add(new specialArea("Special Area",0));
        specialAreas.add(new specialArea("Unknow1",R.drawable.ic_cancel));
        specialAreas.add(new specialArea("Unknow2",R.drawable.ic_cancel));
        specialAreas.add(new specialArea("Unknow3",R.drawable.ic_cancel));

    }
    private void Adapter(){
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new RecyclerAdapterNumberChairs(numberChairs,this);

        recyclerView.setAdapter(adapter);



        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        lastPostionNumberChairs=RecyclerAdapterNumberChairs.getLastPostion();
                        numberChairs=RecyclerAdapterNumberChairs.getArraylistNumberChair();
                    }
                });
            }
        }, 0, 3000);

    }
    private void SpannerSpecialArea(){
        SpecialAreaAdapter=new specialAreaAdapter(this,specialAreas);
        spinnerSpecialArea.setAdapter(SpecialAreaAdapter);
    }
    private void popTimePicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Hour=hourOfDay;
                Minute=minute;
                checkTime(Hour,Minute);
                Pair<Integer, String> pair = convertTime(Hour);
                Hour=pair.first;    A_PMeridiem=pair.second;

                if(minute==0)
                    timeTextView.setText(String.valueOf(Hour)+":"+String.valueOf(Minute)+String.valueOf(Minute)+"\t"+A_PMeridiem);
                else
                    timeTextView.setText(String.valueOf(Hour)+":"+String.valueOf(Minute)+"\t"+A_PMeridiem);
                timeTextView.setTextSize(25);

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this/*,R.style.MyTimePicker,*/, onTimeSetListener, Hour, Minute, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();

    }
    private  void   checkTime(int hour,int minute){
        Calendar calendar = Calendar.getInstance();
        int hourCurrent= calendar.get(Calendar.HOUR_OF_DAY);
        int minuteCurrent= calendar.get(Calendar.MINUTE);

        if((hour>1&&hour<13)){
            //restaurant close
            timeTextView.setTextColor(Color.parseColor("#ff0000"));
            Toast.makeText(BookingTable.this,"restaurant close",Toast.LENGTH_LONG).show();
        }

        else if((hour<1)&&(minute>20&&minute<59) ){
            //restaurant will closed
            timeTextView.setTextColor(Color.parseColor("#ff0000"));
            Toast.makeText(BookingTable.this,"restaurant will closed",Toast.LENGTH_LONG).show();
        }

        else if((hourCurrent>hour )||((hourCurrent==hour)&&minute<minuteCurrent)){
            // error in time
            timeTextView.setTextColor(Color.parseColor("#ff0000"));
            Toast.makeText(BookingTable.this,"error in time typing again",Toast.LENGTH_LONG).show();
        }

        else {
            timeTextView.setTextColor(getResources().getColor(R.color.black));
        }



    }

    private   Pair<Integer, String> convertTime(int Hour1){
       String APM="";
        if (Hour1 > 12) {
            Hour1 -= 12;
            APM = "PM";
        } else if (Hour1 == 0) {
            APM = "AM";
        } else if (Hour1 == 12){
            APM = "PM";
        }else{
            APM = "AM";
        }
         return new Pair<Integer, String>(Hour1,APM);
    }

}
