package com.example.doctorappointmentsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewAppointment extends AppCompatActivity implements View.OnClickListener {
    MyDatabaseHelper myDatabaseHelper;
    private Button vButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        myDatabaseHelper= new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= myDatabaseHelper.getWritableDatabase();
        vButton=(Button)findViewById(R.id.vbuuton);
        vButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.vbuuton) {
            Cursor cursor = myDatabaseHelper.displaydata();
            if (cursor.getCount() == 0) {
                showData("Error", "No Data Found");
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append("ID: " + cursor.getString(0) + "\n\n");
                stringBuffer.append("Name: " + cursor.getString(1) + "\n\n");
                stringBuffer.append("Address: " + cursor.getString(2) + "\n\n");
                stringBuffer.append("Disease: " + cursor.getString(3) + "\n\n");
                stringBuffer.append("PhoneNumber: " + cursor.getString(4) + "\n\n");
                stringBuffer.append("Gender: " + cursor.getString(5) + "\n\n");
            }
            showData("Patient Details",stringBuffer.toString());

    }
}

    private void showData(String title, String resultSet) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(resultSet);
        builder.setCancelable(true);
        builder.show();
    }
}
