package com.system.restaurant.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.*;
import android.graphics.Bitmap;

public class FoodDBHelper extends GlobalDBHelper {
    public static final String TableName=GlobalDBHelper.Table2;
    public  static final String COLUMN1="id_food";
    public  static final String COLUMN2="name";
    public  static final String COLUMN3="image";
    public static final String COLUMN4="price";
    public static final String COLUMN5="details";
    public static final String COLUMN6="id_category";
    Context context;

    public FoodDBHelper(Context context){
        super(context);
        this.context=context;
    }
    public int getIDCategory(String Name){
        CategoryDBHelper categoryDBHelper=new CategoryDBHelper(context);
        return categoryDBHelper.getIdCategory(Name);
    }

    public boolean insert (String name , Bitmap image, float price, String details, int id){
        byte [] imageByte=convertBitmapByteArray.getBytes(image);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COLUMN2,name);
        contentValues.put(COLUMN3,imageByte);
        contentValues.put(COLUMN4,price);
        contentValues.put(COLUMN5,details);
        contentValues.put(COLUMN6,id);
        boolean checked=db.insert("food", null, contentValues) != -1;
        db.close();
        return checked;
    }
    public boolean insert (String name , Bitmap image,float price,String details,String categoryName){
        return insert(name,image,price,details,getIDCategory(categoryName));

    }

    public Cursor fetchAllFood(){
        SQLiteDatabase db=getReadableDatabase();
        String [] rowDetails={COLUMN1,COLUMN2,COLUMN3,COLUMN4,COLUMN5,COLUMN6};
        Cursor cursor= db.query(TableName,rowDetails,null,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
    public Cursor getFood(int id){
        SQLiteDatabase db=getReadableDatabase();
        String [] rowDetails={COLUMN1,COLUMN2,COLUMN3,COLUMN4,COLUMN5,COLUMN6};
        Cursor cursor= db.query(TableName,rowDetails,COLUMN1+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }





}
