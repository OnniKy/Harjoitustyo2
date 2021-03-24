package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity{

    UserManager userManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userManager = (UserManager) getIntent().getSerializableExtra("UserManager");

        userManager.checkUsername();

    }
}