package com.system.restaurant.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TableDBHelper  extends  GlobalDBHelper{
    public static final String TableName=GlobalDBHelper.Table4;
    public static final String COLUMN1="id";
    public static final String COLUMN2="area";
    public static final String COLUMN3="limitchair";


    public TableDBHelper(Context context) {
        super(context);
    }
    public boolean insert(String area,int limitchair){

        SQLiteDatabase db=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put(COLUMN2,area);
        row.put(COLUMN3,limitchair);

        boolean checked=db.insert( TableName,null,row)!=-1;
       db.close();
       return checked;
    }
    public Cursor fetchAllTable(){
        SQLiteDatabase db=getReadableDatabase();
        String [] rowDetails={COLUMN1,COLUMN2,COLUMN3};
        Cursor cursor= db.query(TableName,rowDetails,null,null,null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
 /*   public int getLimitChair(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select " + COLUMN2 + " from " + TableName + " where id=" + id, null);
        if(cursor!=null)
            cursor.moveToFirst();
        db.close();
        return  cursor.getInt(0);
    }*/
}
