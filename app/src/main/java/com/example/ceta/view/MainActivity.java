package com.example.ceta.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceta.controller.AddProjectActivity;
import com.example.ceta.R;
import com.example.ceta.model.DatabaseHelper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private TextView title;
    private String name, surname, DOB;
    private int cell=0, age=0;
    private int mYear, mMonth, mDay;

    EditText FName, SName, phoneNumber, ageIn, DOBin;
    Button saveBtn, btnAddProj, btnViewPple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        btnViewPple = (Button)findViewById(R.id.btnViewPple);
        btnAddProj = (Button)findViewById(R.id.btnAddProj);
        FName =(EditText)findViewById(R.id.FName);
        SName=(EditText)findViewById(R.id.SName);
        phoneNumber=(EditText)findViewById(R.id.phoneNumber);
        ageIn=(EditText)findViewById(R.id.ageIn);
        DOBin=(EditText)findViewById(R.id.DOBin);
        DOBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickAdate();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=FName.getText().toString();
                surname=SName.getText().toString();
                cell=Integer.valueOf(phoneNumber.getText().toString());
                age=Integer.valueOf(ageIn.getText().toString());
                DOB=DOBin.getText().toString();

                if ((name.length()!=0)&&(surname.length()!=0)&&(age!=0)&&(cell!=0)){
                    AddData(name,surname,cell,DOB,age);
                    FName.setText("");
                    SName.setText("");
                    phoneNumber.setText("");
                    ageIn.setText("");
                    DOBin.setText("");
                }else{
                    showToast("Fill all fields with data");
                }

            }
        });

        btnViewPple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });


        btnAddProj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String name, String surname, int cell, String DOB, int age){
        boolean insertData = mDatabaseHelper.addData(name,surname,cell,DOB,age);
        if (insertData){
            showToast("Data Successfully Inserted");
        } else{
            showToast("Something went wrong");
        }
    }



    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    public void pickAdate(){
        final Calendar cal = Calendar.getInstance();
        mYear=cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog listener = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DOBin.setText(dayOfMonth+"/" + (month + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        listener.show();

    }

}
