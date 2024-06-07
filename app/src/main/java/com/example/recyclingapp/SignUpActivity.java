package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    private final String myIP = "192.168.1.142"; // IP address of the server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize the sign-up button and login text view
        Button signUpButton = findViewById(R.id.signup_button);
        TextView loginText = findViewById(R.id.logIn_txt);

        // Set a click listener on the login text to navigate to the SignInActivity
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent); // Start the SignInActivity
            }
        });

        // Set a click listener on the sign-up button to handle user registration
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from the sign-up form fields
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

                // Check if the passwords match
                if (pass1.equals(pass2)) {
                    // Create a URL for the user registration request
                    String url = "http://" + myIP + "/logHistory.php?Name=" + name +
                            "&Email=" + email + "&Username=" + username + "&Password=" + pass1 +
                            "&Points=0&AluminiumKg=0&GlassKg=0&PaperKg=0&PlasticKg=0";
                    try {
                        OkHttpHandler okHttpHandler = new OkHttpHandler(); // Create a new OkHttpHandler
                        okHttpHandler.logHistory(url); // Send the registration request
                        // Show a success message
                        Toast.makeText(getApplicationContext(), "User registered successfully",
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace(); // Print the stack trace in case of an exception
                    }
                    // Navigate to UserMainPageActivity and pass the username
                    Intent intent = new Intent(SignUpActivity.this, UserMainPageActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent); // Start the UserMainPageActivity
                } else {
                    // Show an error message if the passwords do not match
                    Toast.makeText(SignUpActivity.this, "The passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}