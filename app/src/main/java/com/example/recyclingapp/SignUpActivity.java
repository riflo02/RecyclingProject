package com.example.recyclingapp;

import static com.example.recyclingapp.R.id.login_button;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        Button signUpButton = findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Πληροφορίες από τα κενά για τη δημιουργία χρήστη
                EditText emailInput = findViewById(R.id.email_input);
                EditText usernameInput = findViewById(R.id.username_input);
                EditText pass1Input = findViewById(R.id.password1_input);
                EditText pass2Input = findViewById(R.id.password2_input);


                String email = emailInput.getText().toString();
                String username = usernameInput.getText().toString();
                String pass1 = pass1Input.getText().toString();
                String pass2 = pass2Input.getText().toString();

                if(pass1.equals(pass2)){
                    //Άνοιγμα MainPage
                    Intent intent = new Intent(SignUpActivity.this, UserMainPageActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SignUpActivity.this, "The passwords do not match",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}