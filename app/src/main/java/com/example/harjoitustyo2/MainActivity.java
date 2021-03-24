package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView password;
    TextView address;
    UserManager userManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = (TextView) findViewById(R.id.address);
        password = (TextView) findViewById(R.id.password);

        userManager = UserManager.getInstance();

    }

    public void signIn(View v){
        userManager.checkUsername(address.getText().toString());
        userManager.checkPassword();
    }

    public void register(View v){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        intent.putExtra("UserManager", userManager);
        startActivityForResult(intent, 1);

    }

}