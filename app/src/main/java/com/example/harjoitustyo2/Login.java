package com.example.harjoitustyo2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView password;
    TextView username;
    Button register, login;
    Context context;

    DatabaseHelper databaseHelper;
    Hashing hashing;
    User user;
    

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = Login.this;
        username = findViewById(R.id.address);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login  = findViewById(R.id.login);
        databaseHelper = new DatabaseHelper(this);
        hashing = new Hashing();

        login.setOnClickListener(v -> {
            final String usernameCheck = username.getText().toString();
            final String passwordCheck = password.getText().toString();

            String finalCheckPassword;

            if (usernameCheck.isEmpty() || passwordCheck.isEmpty()){
                Toast.makeText(Login.this, "Enter username and password!", Toast.LENGTH_SHORT).show();
            } else {
                if (databaseHelper.checkUsername(usernameCheck)) {
                    user = new User(context, usernameCheck);
                    byte[] byteConverter = user.getSaltId();

                    finalCheckPassword = hashing.getSecurePassword(passwordCheck, byteConverter);

                    if (databaseHelper.isLoginValid(usernameCheck, finalCheckPassword)) {
                        Intent intent = new Intent(Login.this, MainPage.class);
                        intent.putExtra("Username", username.getText().toString());
                        startActivity(intent);

                        Toast.makeText(Login.this, "Login is succesful!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Login.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "   User does not exist! \nDo you want to register?", Toast.LENGTH_SHORT).show();
                }
            }
        });



        register.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, RegisterActivity.class);
            startActivityForResult(intent, 1);
        });

    }
    

}