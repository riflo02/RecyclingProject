package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecyclingForm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_form);

        // Get the username passed from the previous activity
        Intent intent = getIntent();
        String usrname = intent.getStringExtra("Username");

        // Set up the cancel button to finish the activity and return to the previous screen
        ImageButton cancelButton = findViewById(R.id.cancelBtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
            }
        });

        // Set up the submit button to handle the form submission
        Button subButton = findViewById(R.id.submitBtn);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the quantity input and the selected material from the spinner
                EditText quantity = findViewById(R.id.quantityInput);
                String quantityStr = quantity.getText().toString();
                Spinner materialSpinner = findViewById(R.id.materialSpinner);
                String selectedMaterial = materialSpinner.getSelectedItem().toString();

                // Check if the quantity is not empty
                if (!quantityStr.isEmpty()) {
                    // Show a toast message indicating that admin sign-in is required
                    Toast.makeText(RecyclingForm.this, "You have to Sign In as Administrator.", Toast.LENGTH_LONG).show();

                    // Create an intent to navigate to the SignInAdmin activity
                    Intent intent = new Intent(RecyclingForm.this, SignInAdmin.class);
                    intent.putExtra("username", usrname); // Pass the username
                    intent.putExtra("material", selectedMaterial); // Pass the selected material
                    intent.putExtra("quantity", quantityStr); // Pass the quantity
                    intent.putExtra("boolean", "true"); // Pass a boolean flag
                    startActivity(intent); // Start the SignInAdmin activity

                } else {
                    // Show a toast message if the quantity is empty
                    Toast.makeText(RecyclingForm.this, "Please enter the quantity.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}