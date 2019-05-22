package com.example.doctorappointmentsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLogin extends AppCompatActivity implements View.OnClickListener{
    private Button signInButton,signUpButton;
    private EditText userName;
    private EditText Password;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        signInButton=(Button)findViewById(R.id.d1);
        signUpButton=(Button)findViewById(R.id.d2);
        userName=(EditText) findViewById(R.id.u);
        Password=(EditText) findViewById(R.id.pp);

        dataBaseHelper= new DataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase=dataBaseHelper.getWritableDatabase();
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        String username=userName.getText().toString();
        String password=Password.getText().toString();

        if (view.getId()== R.id.d1) {
            Boolean result= dataBaseHelper.findPassword(username,password);
            if(result==true){
            Intent intent = new Intent(DoctorLogin.this, ViewAppointment.class);
            startActivity(intent);
        }else{
                Toast.makeText(getApplicationContext(),"UserName ans password did not match",Toast.LENGTH_LONG).show();

            }
        }
        else if (view.getId() == R.id.d2) {
            Intent intent = new Intent(DoctorLogin.this,DoctorSignUp.class);
            startActivity(intent);

        }
        }

    }


