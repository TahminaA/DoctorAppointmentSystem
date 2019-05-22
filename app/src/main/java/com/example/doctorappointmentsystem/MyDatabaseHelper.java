package com.example.doctorappointmentsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Patient.db";
    private static final String TABLE_NAME="patient_deatils";
    private static final int DATABASE_VERSION=2;
    private static final String ID ="_id";
    private static final String Name ="Name";
    private static final String Address ="Address";
    private static final String Disease ="Disease";
    private static final String PhoneNumber ="Number";
    private static final String Gender= "Gender";
    private static final String CREATE_TABLE="Create TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Name+" VARCHAR(20),"+Address+" VARCHAR(20),"+Disease+" VARCHAR(20),"+PhoneNumber+" INTEGER,"+Gender+" VARCHAR(20));";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String DISPLAY_DATA= "SELECT * FROM "+TABLE_NAME;
    private Context context;


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            Toast.makeText(context,"onCreate method is called ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            Toast.makeText(context,"onUpgrade method is called ",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }


    }
    public long insertData(String name,String address,String disease,String phoneNumber,String gender){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Name,name);
        contentValues.put(Address,address);
        contentValues.put(Disease,disease);
        contentValues.put(PhoneNumber,phoneNumber);
        contentValues.put(Gender,gender);
        long rowId= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor displaydata(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DISPLAY_DATA,null);
        return cursor;
    }
    public boolean updateData(String id,String name,String address,String disease,String phoneNumber,String gender){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(Name,name);
        contentValues.put(Address,address);
        contentValues.put(Disease,disease);
        contentValues.put(PhoneNumber,phoneNumber);
        contentValues.put(Gender,gender);
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+"= ?",new String[]{id});
        return true;
    }
    public int deleteData(String id){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }
}


