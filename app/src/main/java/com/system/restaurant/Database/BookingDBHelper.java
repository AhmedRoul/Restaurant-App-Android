package com.system.restaurant.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BookingDBHelper extends GlobalDBHelper {

    public static final String TableName=GlobalDBHelper.Table5;
    public static final String COLUMN1="booking_id";
    public static final String COLUMN2="id_table";
    public static final String COLUMN3="email_user";
    public static final String COLUMN4="time";


    public BookingDBHelper(Context context) {
        super(context);
    }

    public boolean insert(String emailUser,int idtable,String time){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put(COLUMN2,idtable);
        row.put(COLUMN3,emailUser);
        row.put(COLUMN4,time);
        boolean checked=db.insert( TableName,null,row)!=-1;
        db.close();
        return checked;
    }
    public Cursor getBooking(String emailUser){
        SQLiteDatabase db = getReadableDatabase();
        String[] rowDetails={COLUMN2,COLUMN4};
        Cursor cursor=db.query(TableName,rowDetails,"email_user=?",new String[]{emailUser},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();
        return  cursor;
    }


}


