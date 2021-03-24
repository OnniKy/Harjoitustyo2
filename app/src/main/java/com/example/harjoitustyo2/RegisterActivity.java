package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity{

    Context context;
    ArrayList<String> genderList;
    UserManager userManager = null;
    EditText name, email, password, municipality, height, weight;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;

        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        municipality = findViewById(R.id.editMunicipality);
        height = findViewById(R.id.editHeight);
        weight = findViewById(R.id.editWeight);
        spinner = findViewById(R.id.spinnerGender);

        userManager = (UserManager) getIntent().getSerializableExtra("UserManager");


    }

    public void initializeUI(){
        genderList.add("Choose gender");
        genderList.add("Male");
        genderList.add("Female");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}