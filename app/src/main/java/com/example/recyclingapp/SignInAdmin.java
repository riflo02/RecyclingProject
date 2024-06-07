package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_admin);

        // Get the Intent and retrieve the boolean string from the previous activity
        Intent intent = getIntent();
        String check = intent.getStringExtra("boolean");

        // Initialize the login button
        Button logInButton = findViewById(R.id.LogAdmin);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the admin name and password input from the EditText fields
                EditText adminName = findViewById(R.id.uname_inputAdmin);
                String nameInput = adminName.getText().toString();
                EditText pass = findViewById(R.id.passwordAdmin);
                String passInput = pass.getText().toString();

                // Check if the entered credentials match the admin credentials
                if (nameInput.equals("Admin") && passInput.equals("abcdeAdmin123!")) {
                    if (check.equals("true")) {
                        // If check is true, get additional data from the Intent and start AdminPageActivity
                        String username = intent.getStringExtra("username");
                        String material = intent.getStringExtra("material");
                        String quantityStr = intent.getStringExtra("quantity");
                        Intent intent1 = new Intent(SignInAdmin.this, AdminPageActivity.class);
                        intent1.putExtra("username", username);
                        intent1.putExtra("material", material);
                        intent1.putExtra("quantity", quantityStr);
                        startActivity(intent1);
                    } else {
                        // If check is false, start AdminTopStats activity
                        Intent intent2 = new Intent(SignInAdmin.this, AdminTopStats.class);
                        startActivity(intent2);
                    }
                } else {
                    // Show a toast message if the credentials are incorrect
                    Toast.makeText(SignInAdmin.this, nameInput, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}