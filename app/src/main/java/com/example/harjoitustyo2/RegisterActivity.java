package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity{

    Context context = null;
    Spinner spinner;
    ArrayList genderList = new ArrayList();
    UserManager userManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;

        userManager = (UserManager) getIntent().getSerializableExtra("UserManager");
        spinner = (Spinner) findViewById(R.id.spinnerGender);

    }

    public void utilizeUI(){
        genderList.add("Select gender");
        genderList.add("Male");
        genderList.add("Female");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}