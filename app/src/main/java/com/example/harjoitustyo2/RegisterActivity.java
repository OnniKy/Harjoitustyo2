package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity{

    UserManager userManager = null;
    EditText name, email, password, municipality, height, weight;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        municipality = findViewById(R.id.editMunicipality);
        height = findViewById(R.id.editHeight);
        weight = findViewById(R.id.editWeight);
        spinner = findViewById(R.id.spinnerGender);



        userManager = (UserManager) getIntent().getSerializableExtra("UserManager");


    }
}