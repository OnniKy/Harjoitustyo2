
package com.example.harjoitustyo2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdatePassword extends AppCompatActivity {

    TextView nameView, emailView;
    EditText password, newPassword1, newPassword2;
    Button submit, back;
    Context context;

    String username;

    DatabaseHelper databaseHelper;
    Hashing hashing = new Hashing();
    User user;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        nameView = findViewById(R.id.nameView);
        emailView = findViewById(R.id.emailView);
        password = findViewById(R.id.editPassword);
        newPassword1 = findViewById(R.id.newPassword);
        newPassword2 = findViewById(R.id.newPassword2);
        context = UpdatePassword.this;
        submit = findViewById(R.id.update);
        back = findViewById(R.id.backButton);

        username = getIntent().getStringExtra("Username");

        databaseHelper = new DatabaseHelper(context);

        nameView.setText(databaseHelper.getName(username));
        emailView.setText(username);

        submit.setOnClickListener(v -> this.updatePassword());

        back.setOnClickListener(v -> {
            Intent intent = new Intent(UpdatePassword.this, MainPage.class);
            intent.putExtra("Username", username);
            startActivity(intent);
        });

    }


    // Updates password to the database
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void updatePassword(){
        String finalCheckPassword;
        String password_;

        final String passwordCheck = password.getText().toString();
        user = new User(context, username);
        byte[] byteConverter = user.getSaltId();

        finalCheckPassword = hashing.getSecurePassword(passwordCheck, byteConverter);

        if (databaseHelper.isLoginValid(username, finalCheckPassword)) {
            boolean result = checkPasswords();
            if (result) {
                String password = newPassword1.getText().toString();
                if (isValidPassword(password)){
                    password_ = hashing.getSecurePassword(password, byteConverter);
                    databaseHelper.updatePassword(password_, username);
                    Toast.makeText(this, "Password updated succesfully!", Toast.LENGTH_SHORT).show();
                    
                } else {
                    Toast.makeText(this, "Invalid password. Password must contain at least 12 letters including big letter, small letter and special character.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "New passwords doesn't match!", Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText(UpdatePassword.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean checkPasswords (){
         return newPassword1.getText().toString().equals(newPassword2.getText().toString());
        }


    public boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

}

