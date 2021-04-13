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

public class MainActivity extends AppCompatActivity {
    Context context;
    TextView password;
    TextView username;
    Button register, login;
    DatabaseHelper databaseHelper;
    Hashing hashing;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        username = (TextView) findViewById(R.id.address);
        password = (TextView) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        login  = (Button) findViewById(R.id.login);
        databaseHelper = new DatabaseHelper(this);

        hashing = new Hashing();


        System.out.println("Sijainti kirjastossa: " + context.getFilesDir());

        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final String usernameCheck = username.getText().toString();
                final String passwordCheck = password.getText().toString();

                if (usernameCheck.isEmpty() || passwordCheck.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter username and password!", Toast.LENGTH_SHORT).show();
                } else {
                    user = new User(context, usernameCheck);
                    byte[] byteConverter = user.getSaltId();

                    String finalCheckPassword = hashing.getSecurePassword(passwordCheck, byteConverter);
                    System.out.println(finalCheckPassword);



                    if (databaseHelper.isLoginValid(usernameCheck, finalCheckPassword)) {
                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                        intent.putExtra("Username", username.getText().toString());
                        startActivity(intent);

                        Toast.makeText(MainActivity.this, "Login is succesful!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(MainActivity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void checkLogin(){

        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();

    }


}