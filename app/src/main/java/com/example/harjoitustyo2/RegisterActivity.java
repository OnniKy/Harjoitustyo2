package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity{

    Context context;
    ArrayList<String> genderList;
    UserManager userManager = null;
    EditText name, email, password, municipality, height, weight, birthyear;
    Spinner spinner;
    String gender;

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
        birthyear = findViewById(R.id.editBirthyear);
        spinner = findViewById(R.id.spinnerGender);

        userManager = (UserManager) getIntent().getSerializableExtra("UserManager");

        initializeSpinner();

        Button button = (Button) findViewById(R.id.registerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRegisteration();
            }
        });


    }

    public void initializeSpinner(){
        System.out.println("MOI");

        genderList = new ArrayList<String>();
        genderList.add("Choose gender");
        genderList.add("Male");
        genderList.add("Female");

        System.out.println("MOI");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void makeRegisteration(){
        gender = spinner.getSelectedItem().toString();
        userManager.createUser(height.getText().toString(), weight.getText().toString(), birthyear.getText().toString(), municipality.getText().toString(), email.getText().toString(), name.getText().toString(), gender);
    }


}