package com.example.harjoitustyo2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity{

    EditText name, email, password, height, weight, birthyear;
    Spinner municipality;
    Button register, cancel;
    RadioGroup gender;
    Context context;

    DatabaseHelper databaseHelper;
    JSONFileControl jsonFileControl;
    User user;
    Hashing hashing;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;
        name = findViewById(R.id.editName);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        municipality = findViewById(R.id.spinnerMunicipality);
        height = findViewById(R.id.editHeight);
        weight = findViewById(R.id.editWeight);
        birthyear = findViewById(R.id.editBirthyear);
        gender = findViewById(R.id.gender);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);
        hashing = new Hashing();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        jsonFileControl = new JSONFileControl();
        databaseHelper = new DatabaseHelper(this);

        utilizeSpinner();


        // Get back to login page
        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, Login.class);
            startActivity(intent);
        });


        // Registeration
        register.setOnClickListener(v ->{
            RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
            if (email.length() == 0){
                email.setError("Enter valid email!");
            }
            if (name.length() == 0){
                name.setError("Enter name!");
            }
            if (password.length() == 0){
                password.setError("Enter password!");
            }
            if (height.length() == 0){
                height.setError("Enter height!");
            }
            if (weight.length() == 0){
                weight.setError("Enter height!");
            }
            if (birthyear.length() == 0){
                birthyear.setError("Enter birth year!");
            }

            if (email.length() > 0 && name.length() > 0 && password.length() > 0 && height.length() > 0 && weight.length() > 0 && checkedBtn != null && birthyear.length() > 0) {
                String nameValue = name.getText().toString();
                String usernameValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String municipalityValue = municipality.getSelectedItem().toString();
                String heightValue = height.getText().toString();
                int weightValue = Integer.parseInt(weight.getText().toString());
                String genderValue = checkedBtn.getText().toString();
                int birthyearValue = Integer.parseInt(birthyear.getText().toString());
                System.out.println(municipalityValue);

                if (isValidPassword(passwordValue) && passwordValue.length() >= 12) {

                    boolean usercheckResult = databaseHelper.checkUsername(usernameValue);
                    if (!usercheckResult) {
                        byte[] salt;
                        String password_;
                        salt = Hashing.getSalt();
                        password_ = hashing.getSecurePassword(passwordValue, salt);

                        user = new User(salt, nameValue, usernameValue, password_, municipalityValue, genderValue, heightValue, weightValue, birthyearValue);
                        databaseHelper.insertUser(user, context);
                        Toast.makeText(RegisterActivity.this, "User registered!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, MainPage.class);
                        intent.putExtra("Username", usernameValue);
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


        });

    }

    // Ensure that the password follows the strong password requirements
    public boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    // Calls method to get municipalities from API and utilizes spinner
    public void utilizeSpinner(){
        JSONRequest jsonRequest = new JSONRequest();
        ArrayList<String> mlist = jsonRequest.getMunicipality();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        municipality.setAdapter(adapter);
    }

}