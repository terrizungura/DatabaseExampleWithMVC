package com.example.ceta.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceta.R;
import com.example.ceta.model.DatabaseHelper;

public class AddProjectActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private TextView title;
    private String project, province;
    private int ID;

    EditText IDin, projectIn, provinceIn;
    Button btnSaveProj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproject_layout);
        IDin = (EditText)findViewById(R.id.IDin);
        projectIn = (EditText)findViewById(R.id.projectIn);
        provinceIn = (EditText)findViewById(R.id.provinceIn);
        btnSaveProj = (Button)findViewById(R.id.btnSaveProj);

        btnSaveProj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project=projectIn.getText().toString();
                province=provinceIn.getText().toString();
                ID=Integer.valueOf(IDin.getText().toString());

                if ((project.length()!=0)&&(province.length()!=0)&&(ID!=0)){
                    AddProj(project,ID,province);
                    IDin.setText("");
                    projectIn.setText("");
                    provinceIn.setText("");
                }else{
                    showToast("Fill all fields with data");
                }

            }
        });
    }



    public void AddProj(String project, int id, String province){
        boolean insertData = mDatabaseHelper.addProjects(project,id,province);
        if (insertData){
            showToast("Project Successfully entered");
        } else{
            showToast("Something went wrong");
        }
    }

    private void showToast(String project_successfully_entered) {
        Toast.makeText(AddProjectActivity.this, project_successfully_entered, Toast.LENGTH_SHORT).show();
    }


}
