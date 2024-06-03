package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecyclingForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_form);

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
                TextInputEditText quantity = findViewById(R.id.quantityInput);
                EditText uName = findViewById(R.id.uname_input);
                String usrName = uName.getText().toString();
                String quantityStr = quantity.getText().toString();
                if (!quantityStr.isEmpty()) {
                    try {
                        int quantityKg = Integer.parseInt(quantityStr);
                        new UpdatePointsTask().execute(usrName, String.valueOf(quantityKg));
                    } catch (NumberFormatException e) {
                        Toast.makeText(RecyclingForm.this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RecyclingForm.this, "Please enter the quantity.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class UpdatePointsTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            String username = params[0];
            int quantityKg = Integer.parseInt(params[1]);
            int pointsToAdd = quantityKg / 5;
            updatePoints(username, pointsToAdd);
            return pointsToAdd;
        }

        @Override
        protected void onPostExecute(Integer pointsAdded) {
            super.onPostExecute(pointsAdded);
            Toast.makeText(RecyclingForm.this, "Points updated successfully.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePoints(String username, int quantityKg) {
        // Σύνδεση με τη βάση δεδομένων
        String url = "jdbc:mysql://localhost:3306/RecyclingProject";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE Users SET Points = Points + ? WHERE Username = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, quantityKg);
                statement.setString(2, username);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Points updated successfully.");
                } else {
                    System.out.println("User does not exists.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}