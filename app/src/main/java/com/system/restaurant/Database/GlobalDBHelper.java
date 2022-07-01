package com.system.restaurant.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GlobalDBHelper extends SQLiteOpenHelper {

    public  static final String dataBaseName="database79.db";
    public static final String Table1="category";
    public static  final String Table2="food";
    public static  final String Table3="user";
    public static  final String Table4="tableCh";
    public static final  String Table5="booking";
    public static final  String Table6="cart";


   public GlobalDBHelper(Context context){
       super (context,dataBaseName,null,1);

   }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TableCategory="create table "+Table1+"(id integer primary key AUTOINCREMENT, name TEXT, image BLOB);";

        db.execSQL(TableCategory);


        String TableFood="create table "+Table2+"(id_food integer primary key AUTOINCREMENT, name TEXT," +
                "image BLOB,price NUMERIC,details text, id_category integer ," +
                "FOREIGN KEY (id_category) REFERENCES "+Table1+"(id))";

        db.execSQL(TableFood);

        String TableUser="create table "+Table3+"(email TEXT primary key," +
                "  password TEXT ,fname TEXT,lname TEXT," +
                "phonenum TEXT,image BLOB);";

        db.execSQL(TableUser);

        String TableTable="create table " +Table4+" (id integer primary key AUTOINCREMENT,"+
                "area TEXT,limitchair integer);";
        db.execSQL(TableTable);

        String TableBooking="create table "+Table5+"(booking_id integer primary key AUTOINCREMENT" +
                ",id_table integer ,email_user TEXT,time TEXT," +
                "FOREIGN KEY (email_user) REFERENCES "+Table3+"(email)," +
                "FOREIGN KEY (id_table) REFERENCES "+Table4+"(id));";
        db.execSQL(TableBooking);

        String TableCart="create table "+Table6+"(id integer primary key AUTOINCREMENT ,emailuser TEXT,food_id integer," +
                "countfood integer , FOREIGN KEY (emailuser) REFERENCES "+Table3+"(email) );" ;



        db.execSQL(TableCart);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table1);
        db.execSQL("DROP TABLE IF EXISTS "+Table2);
        db.execSQL("DROP TABLE IF EXISTS "+Table3);
        db.execSQL("DROP TABLE IF EXISTS "+Table4);
        db.execSQL("DROP TABLE IF EXISTS "+Table5);
        db.execSQL("DROP TABLE IF EXISTS "+Table6);

        onCreate(db);
    }

}
