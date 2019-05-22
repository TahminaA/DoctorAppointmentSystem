package com.example.doctorappointmentsystem;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Receptin3 extends AppCompatActivity implements View.OnClickListener {
    MyDatabaseHelper myDatabaseHelper;
    private EditText nameEditText,addressEditText,diseaseEditText,numberEditText,genderEdiText,idEditText;
    private Button addButton,viewButton,updateButton,cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptin3);
        myDatabaseHelper= new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= myDatabaseHelper.getWritableDatabase();
        nameEditText=(EditText)findViewById(R.id.name);
        addressEditText=(EditText)findViewById(R.id.address);
        diseaseEditText=(EditText)findViewById(R.id.disease);
        numberEditText=(EditText)findViewById(R.id.number);
        genderEdiText=(EditText)findViewById(R.id.gender);
        idEditText=(EditText)findViewById(R.id.id);
        addButton=(Button)findViewById(R.id.app1);
        viewButton=(Button)findViewById(R.id.app2);
        updateButton=(Button)findViewById(R.id.app3);
        cancelButton=(Button)findViewById(R.id.app4);
        addButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String disease = diseaseEditText.getText().toString();
        String phoneNumber = numberEditText.getText().toString();
        String gender = genderEdiText.getText().toString();
        String id = idEditText.getText().toString();

        if (view.getId() == R.id.app1) {
            long rowId = myDatabaseHelper.insertData(name, address, disease, phoneNumber, gender);
            if (rowId == -1) {
                Toast.makeText(getApplicationContext(), " Unsuccessful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Row " + rowId + " is successfully insert", Toast.LENGTH_LONG).show();
            }
        }


        if(view.getId()==R.id.app2) {
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
        else if(view.getId()==R.id.app3){
            Boolean isupdated=myDatabaseHelper.updateData(id,name,address,disease,phoneNumber,gender);
            if(isupdated==true){
                Toast.makeText(getApplicationContext()," Data is updated",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext()," Data is not updated",Toast.LENGTH_LONG).show();
            }
        }
        if(view.getId()==R.id.app4){
            int value=myDatabaseHelper.deleteData(id);
            if (value>0){
                Toast.makeText(getApplicationContext()," Data is Deleted",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext()," Data is not Deleted",Toast.LENGTH_LONG).show();
            }
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


