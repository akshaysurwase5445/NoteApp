package com.example.ashish.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    private static final String databasename="info.db";
    private static final int databaseVersion=1;
    private static final String tablename="info_table";
    //private Context context;


    public DatabaseHelper2(Context context) {
        super(context,databasename, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE "+tablename+"(name TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query="DROP TABLE IF EXISTS "+tablename;
        db.execSQL(query);
        onCreate(db);

    }

    public void saveData(String name){

        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);


        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.insert(tablename,null,contentValues);

    }

    public StringBuffer getData(){

        String query="SELECT * FROM "+tablename;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);

        StringBuffer stringBuffer=new StringBuffer();

        if(cursor !=null)
        {
            cursor.moveToFirst();
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                stringBuffer.append("Name: "+name+ "\n");


                stringBuffer.append("____________________________________________\n");

            }while (cursor.moveToNext());

            cursor.close();
        }
        return stringBuffer;
    }

    public String searchName(String name){
        String searchquery="SELECT name FROM "+tablename+" WHERE name LIKE '"+name+"%'";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(searchquery,null);
        String resultcity="NA";

        if (cursor!=null)
        {
            do {
                cursor.moveToFirst();
                resultcity=cursor.getString(cursor.getColumnIndex("city"));

            }while (cursor.moveToNext());
            cursor.close();
        }
        return resultcity;
    }

    public void deletedata(String name){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(tablename,"name = '"+name+"'",null);
    }
/*
    public void updatedata(String name,String city,int number){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("city",city);
        contentValues.put("number",number);

        sqLiteDatabase.update(tablename,contentValues,"name = '"+name+"'",null);
    }
*/

}
