package com.example.harjoitustyo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity{

    Context context;
    EditText name, email, password, municipality, height, weight, birthyear;
    Button register, cancel;
    RadioGroup gender;
    DatabaseHelper databaseHelper;
    JSONFileControl jsonFileControl;

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
        gender = findViewById(R.id.gender);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        jsonFileControl = new JSONFileControl();


        databaseHelper = new DatabaseHelper(this);


        // Get back to login page
        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });


        // Registeration
        register.setOnClickListener(this::onClick);

    }

    // Required strong login password
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void onClick(View v) {

        RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());

        if (email.length() > 0 && name.length() > 0 && password.length() > 0 && municipality.length() > 0 && height.length() > 0 && weight.length() > 0 && checkedBtn != null && birthyear.length() > 0) {
            String nameValue = name.getText().toString();
            String usernameValue = email.getText().toString();
            String passwordValue = password.getText().toString();
            String municipalityValue = municipality.getText().toString();
            int heightValue = Integer.parseInt(height.getText().toString());
            int weightValue = Integer.parseInt(weight.getText().toString());
            String genderValue = checkedBtn.getText().toString();
            int birthyearValue = Integer.parseInt(birthyear.getText().toString());



            if (isValidPassword(passwordValue) && passwordValue.length() >= 12) {

                boolean usercheckResult = databaseHelper.checkUsername(usernameValue);
                if (!usercheckResult) {

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("username", usernameValue);
                    contentValues.put("name", nameValue);
                    contentValues.put("password", passwordValue);
                    contentValues.put("municipality", municipalityValue);
                    contentValues.put("height", heightValue);
                    contentValues.put("weight", weightValue);
                    contentValues.put("gender", genderValue);
                    contentValues.put("birthyear", birthyearValue);

                    //Adding first weight to Weight Data File
                    String weightValue1 = String.valueOf(weightValue);
                    jsonFileControl.writeLogWeight(weightValue1, context, usernameValue);

                    //TEE SE TÄHÄN

                    databaseHelper.insertUser(contentValues);
                    Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, MainPage.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(RegisterActivity.this, "Username already exists. \nPlease sign in.", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(RegisterActivity.this, "Invalid password. Password must contain at least 12 letters including big letter, small letter and special character.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(RegisterActivity.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
        }

    }
}