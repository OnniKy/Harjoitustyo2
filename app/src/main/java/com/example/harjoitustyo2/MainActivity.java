package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView password;
    TextView address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = (TextView) findViewById(R.id.password);
        address = (TextView) findViewById(R.id.address);

    }

    private void set(View v){
        System.out.println(password);

    }

}