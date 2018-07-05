package com.example.ashish.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;           // extends to sqliteopenhelper

public class SQLlitehelper extends SQLiteOpenHelper {
    public static final String DATABASE_Name= "student.db";    //add this public variable
    public static final String Table_Name= "student_tabe";    // press alt+enter to copy paste te line
    public static final String Col_1= "ID";
    public static final String Col_2= "name";
    public static final String Col_3= "surname";
    public static final String Col_4= "marks";
    public SQLlitehelper(Context context) {           // 1.click the build warning and add implement 2.alt+enter on sqliteopen
        super(context, DATABASE_Name, null, 1 );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //          Table name   variable name  name  type  'primary key if it is'      name type name  type
        db.execSQL("create table "+Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,MARK INTEGER  )"); //db.sql= class name



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name);
        onCreate(db);

    }

    public boolean insertdata(String name,String surname,String marks){     //making it return the date we insert into the table
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues ;
        contentValues = new ContentValues();
        contentValues.put(Col_2,name);
        contentValues.put(Col_3,surname);
        contentValues.put(Col_4,marks);
        long result = sqLiteDatabase.insert(Table_Name, null,contentValues);
        if (result==1)
            return false;
        else
            return true;
    }

    public Cursor getAlldata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+Table_Name, null);
        return res;
    }
}
