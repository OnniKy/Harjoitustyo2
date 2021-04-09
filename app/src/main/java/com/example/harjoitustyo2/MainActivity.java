package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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


        System.out.println("Sijainti kirjastossa: " + context.getFilesDir());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if (databaseHelper.isLoginValid(usernameValue, passwordValue)){
                    User user = new User(usernameValue);
                    Intent intent = new Intent(MainActivity.this, MainPage.class);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Login is succesful!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(MainActivity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
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


}