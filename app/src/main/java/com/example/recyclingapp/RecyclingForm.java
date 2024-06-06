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
    private final String myIP = "192.168.56.1";

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
                    try {
                        int quantityKg = Integer.parseInt(quantityStr);
                        int pointsToAdd = quantityKg * 5;
                        String url = "http://" + myIP + "/update.php?Username=" + usrname + "&Points=" + pointsToAdd +
                                "&Quantity=" +quantityKg + "&Material=" + selectedMaterial;

                        try {
                            OkHttpHandler okHttpHandler = new OkHttpHandler();
                            okHttpHandler.update(url);
                            Toast.makeText(getApplicationContext(), "Points updated successfully",
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(RecyclingForm.this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RecyclingForm.this, "Please enter the quantity.", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(RecyclingForm.this,UserMainPageActivity.class);
                intent.putExtra("username",usrname);
                startActivity(intent);
            }


        });

    }

}
