
package com.example.doctorappointmentsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.b1);
        bt2=(Button)findViewById(R.id.b2);
        bt3=(Button)findViewById(R.id.b3);

    }


    public void Onclick(View view) {
        if (view.getId()== R.id.b1) {
            Intent intent = new Intent(MainActivity.this, DoctorLogin.class);
            startActivity(intent);
        } if (view.getId()== R.id.b3) {
            Intent intent = new Intent(MainActivity.this,OnlineLogin.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.b2) {
            Intent intent = new Intent(MainActivity.this, ReceptionLogin.class);
            startActivity(intent);

        }
    }

}



