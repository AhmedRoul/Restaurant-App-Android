package com.system.restaurant.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

public class UserDBHelper  extends  GlobalDBHelper{
    public static final String TableName=GlobalDBHelper.Table3;

    public static final String COLUMN1="email";
    public static final String COLUMN2="password";
    public static final String COLUMN3="fname";
    public static final String COLUMN4="lname";
    public static final String COLUMN5="phonenum";
    public static final String COLUMN6="image";

    public UserDBHelper(Context context) {
        super(context);

    }
    public boolean insert(String email,String password, String fname, String lname, String phonenum,Bitmap image){
        SQLiteDatabase db=getWritableDatabase();
        byte [] imageByte =convertBitmapByteArray.getBytes(image);

        ContentValues row=new ContentValues();
        row.put(COLUMN1,email);
        row.put(COLUMN2,password);
        row.put(COLUMN3,fname);
        row.put(COLUMN4,lname);
        row.put(COLUMN5,phonenum);
        row.put(COLUMN6,imageByte);

        boolean check=db.insert(TableName,null,row) !=-1;
        db.close();
        return check;

    }

    public Bitmap getImage(String email){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor=db.query(TableName,new String[]{COLUMN6},"email=?",new String[]{email},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();

        return convertBitmapByteArray.getImage(cursor.getBlob(0));
    }



}
