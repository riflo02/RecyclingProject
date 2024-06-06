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
    private final String myIP = "192.168.1.142";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_form);
        Intent intent = getIntent();
        String usrname = intent.getStringExtra("Username");

        ImageButton cancelButton = findViewById(R.id.cancelBtn);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button subButton = findViewById(R.id.submitBtn);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText quantity = findViewById(R.id.quantityInput);
                String quantityStr = quantity.getText().toString();
                Spinner materialSpinner = findViewById(R.id.materialSpinner);
                String selectedMaterial = materialSpinner.getSelectedItem().toString();

                if (!quantityStr.isEmpty()) {
                    Intent intent = new Intent(RecyclingForm.this,SignInAdmin.class);
                    intent.putExtra("username",usrname);
                    intent.putExtra("material",selectedMaterial);
                    intent.putExtra("quantity", quantityStr);
                    intent.putExtra("boolean", "true");
                    startActivity(intent);

                } else {
                    Toast.makeText(RecyclingForm.this, "Please enter the quantity.", Toast.LENGTH_SHORT).show();
                }

            }


        });

    }

}
