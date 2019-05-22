package com.example.doctorappointmentsystem;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceptionSignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText nameEditText,addressEditText,usernameEditText,passwordEditText;
    private Button confirm;
    UserDetails userDetails;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_sign_up);
        nameEditText=(EditText)findViewById(R.id.name);
        addressEditText=(EditText)findViewById(R.id.address);
        usernameEditText=(EditText)findViewById(R.id.uname);
        passwordEditText=(EditText)findViewById(R.id.p);
        confirm=(Button)findViewById(R.id.a1);
        confirm=(Button)findViewById(R.id.a1);
        dataBaseHelper=new DataBaseHelper(this);
        userDetails=new UserDetails();
        confirm.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String name=nameEditText.getText().toString();
        String address=addressEditText.getText().toString();
        String userName=usernameEditText.getText().toString();
        String password=passwordEditText.getText().toString();

        userDetails.setName(name);
        userDetails.setAddress(address);
        userDetails.setUsername(userName);
        userDetails.setPassword(password);

        long  rowId =dataBaseHelper.insertData(userDetails);
        if(rowId>0){
            Toast.makeText(getApplicationContext(),"Row "+rowId+" is Successfully inserted",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Row insertion Failed",Toast.LENGTH_LONG).show();
        }
    }
}
