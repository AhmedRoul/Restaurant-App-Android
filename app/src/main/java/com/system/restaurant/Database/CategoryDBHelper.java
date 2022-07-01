package com.system.restaurant.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

public class CategoryDBHelper extends GlobalDBHelper{
    public static final String TableName=GlobalDBHelper.Table1;
    public  static final String COLUMN1="id";
    public  static final String COLUMN2="name";
    public  static final String COLUMN3="image";

    public CategoryDBHelper(Context context){
        super(context);

    }
    public boolean insert(String Name,Bitmap img) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row=new ContentValues();
        byte []Byte=convertBitmapByteArray.getBytes(img);
        row.put(COLUMN2,Name);
        row.put(COLUMN3,Byte);
        boolean check=  db.insert(TableName,null,row)!=-1;

        db.close();
      return  check;


    }

    public Cursor fetchAllCategory(){
        SQLiteDatabase db = getReadableDatabase();
        String[] rowDetails={COLUMN1,COLUMN2,COLUMN3};
        Cursor cursor=db.query(TableName,rowDetails,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public Cursor getCategory(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] rowDetails={COLUMN2,COLUMN3};
        Cursor cursor=db.query(TableName,rowDetails,"id=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();

        return  cursor;
    }
    public int getIdCategory(String name) {
        SQLiteDatabase db = getReadableDatabase();

        String[] rowDetails={COLUMN1};
        Cursor cursor=db.query(TableName,rowDetails,"name=?",new String[]{name},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();

        return  cursor.getInt(0);
    }



    public Bitmap getImage(int id){
        SQLiteDatabase db = getReadableDatabase();

        String[] selectionArgs = {String.valueOf(id)};
      //  Cursor cursor = db.rawQuery("select " + COLUMN3 + " from " + TableName + " where   id = ?",  new String[]{String.valueOf(id)});
        Cursor cursor= db.query(TableName, new String[]{COLUMN3}, COLUMN1 + " =?", new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();

        return convertBitmapByteArray.getImage(cursor.getBlob(0));
    }



}
