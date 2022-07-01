package com.system.restaurant.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CartDBHelper extends GlobalDBHelper {
    public static final String TableName=GlobalDBHelper.Table6;
    public static final String COLUMN1="emailuser";
    public static final String COLUMN2="food_id";
    public static final String COLUMN3="countfood";

    public CartDBHelper(Context context) {
        super(context);
    }
    public boolean insert(String emailUser,int idFood,int countfood){

        SQLiteDatabase db=getWritableDatabase();
          ContentValues row=new ContentValues();
        row.put(COLUMN1,emailUser);
        row.put(COLUMN2,idFood);
        row.put(COLUMN3,countfood);


        boolean check=db.insert(TableName,null,row) !=-1;
        db.close();
        return check;
    }

    public Cursor getCart(String emailUser){
        SQLiteDatabase db = getReadableDatabase();
        String[] rowDetails={COLUMN2,COLUMN3};
        Cursor cursor=db.query(TableName,rowDetails,"emailuser=?",new String[]{emailUser},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();
        return  cursor;
    }


}
