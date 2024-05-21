package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Button signUpButton = findViewById(R.id.signup_button);
        TextView loginText = findViewById(R.id.logIn_txt);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Πληροφορίες από τα κενά για τη δημιουργία χρήστη
                EditText nameInput = findViewById(R.id.name_input);
                EditText emailInput = findViewById(R.id.email_input);
                EditText usernameInput = findViewById(R.id.username_input);
                EditText pass1Input = findViewById(R.id.password1_input);
                EditText pass2Input = findViewById(R.id.password2_input);

                String name = nameInput.getText().toString();
                String email = emailInput.getText().toString();
                String username = usernameInput.getText().toString();
                String pass1 = pass1Input.getText().toString();
                String pass2 = pass2Input.getText().toString();

                if(pass1.equals(pass2)){
                    new User(name, email,username,pass1);
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