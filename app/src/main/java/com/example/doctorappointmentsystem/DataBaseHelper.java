package com.example.doctorappointmentsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "userdetails.db";
        private static final String TABLE_NAME = "user_details";
        private static final int DATABASE_VERSION = 3;
        private static final String ID = "Id";
        private static final String Name = "Name";
        private static final String Address = "Address";
        private static final String UserName = "UserName";
        private static final String Password = "Password";
        private static final String CREATE_TABLE = "Create TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Name + " VARCHAR(20) NOT NULL," + Address+ " VARCHAR(20)," + UserName + " INTEGER(20),"+Password+" INTEGER(20) NOT NULL);";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;



        public DataBaseHelper(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                Toast.makeText(context, "onCreate method is called ", Toast.LENGTH_LONG).show();
                sqLiteDatabase.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context, "Exception: " + e, Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                Toast.makeText(context, "onUpgrade method is called ", Toast.LENGTH_LONG).show();
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);

            } catch (Exception e) {
                Toast.makeText(context, "Exception: " + e, Toast.LENGTH_LONG).show();
            }


        }

    public long insertData(UserDetails userDetails){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Name,userDetails.getName());
        contentValues.put(Address,userDetails.getAddress());
        contentValues.put(UserName,userDetails.getUsername());
        contentValues.put(Password,userDetails.getPassword());

        long rowId=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;

        }
      public  Boolean findPassword(String username,String password){
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
            Boolean result=false;

            if (cursor.getCount()==0)
            {
                Toast.makeText(context,"No data is found",Toast.LENGTH_LONG).show();
            }
           else {
               while (cursor.moveToNext()) {
                   String name = cursor.getString(3);
                   String passwrd = cursor.getString(4);
                   if (name.equals(username) && passwrd.equals(password)) {
                       result =true;
                       break;
                   }
               }

           }
           return result;
}
}




