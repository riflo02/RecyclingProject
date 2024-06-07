package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AdminPageActivity extends AppCompatActivity {
    private final String myIP = "192.168.1.142"; // IP address of the server
    private RadioGroup radioGroupApproval; // RadioGroup for approval/rejection
    private RadioButton radioButtonApprove; // RadioButton for approval
    private RadioButton radioButtonReject; // RadioButton for rejection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        // Retrieve data from the intent
        Intent intent1 = getIntent();
        String usrname = intent1.getStringExtra("username");
        String material = intent1.getStringExtra("material");
        String quantityStr = intent1.getStringExtra("quantity");

        // Set the text views with the retrieved data
        TextView textRequest = findViewById(R.id.textRequestFromUser);
        TextView materialView = findViewById(R.id.materialView);
        TextView quantityView = findViewById(R.id.quantityView);
        textRequest.setText("The request was submitted by: " + usrname);
        materialView.setText("Material: " + material);
        quantityView.setText("Quantity: " + quantityStr);

        // Initialize the automatic points button
        Button automaticPoints = findViewById(R.id.buttonAutoAssignPoints);

        // Initialize the RadioGroup and RadioButtons
        radioGroupApproval = findViewById(R.id.radioGroupApproval);
        radioButtonApprove = findViewById(R.id.radioButtonApprove);
        radioButtonReject = findViewById(R.id.radioButtonReject);

        // Set the onClickListener for the automatic points button
        automaticPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupApproval.getCheckedRadioButtonId(); // Get the selected RadioButton ID

                if (selectedId == radioButtonApprove.getId()) { // If the approve button is selected
                    try {
                        Toast.makeText(AdminPageActivity.this, "Points are updated successfully!", Toast.LENGTH_LONG).show();
                        int quantityKg = Integer.parseInt(quantityStr); // Convert quantity to integer
                        int pointsToAdd = quantityKg * 5; // Calculate points to add

                        // Construct the URL for updating points
                        String url = "http://" + myIP + "/update.php?Username=" + usrname + "&Points=" + pointsToAdd +
                                "&Quantity=" + quantityKg + "&Material=" + material;
                        try {
                            OkHttpHandler okHttpHandler = new OkHttpHandler(); // Create an instance of OkHttpHandler
                            okHttpHandler.update(url); // Call the update method with the constructed URL

                            // Start the AdminTopStats activity
                            Intent intent2 = new Intent(AdminPageActivity.this, AdminTopStats.class);
                            startActivity(intent2);
                        } catch (Exception e) {
                            e.printStackTrace(); // Print the stack trace if an exception occurs
                        }
                    } catch (NumberFormatException e) {
                        // Handle the exception if quantityStr cannot be parsed to an integer
                    }
                } else if (selectedId == radioButtonReject.getId()) { // If the reject button is selected
                    Toast.makeText(AdminPageActivity.this, "The form was rejected!", Toast.LENGTH_LONG).show();

                    // Start the AdminTopStats activity
                    Intent intent2 = new Intent(AdminPageActivity.this, AdminTopStats.class);
                    startActivity(intent2);
                } else {
                    // If neither approve nor reject is selected, show a toast message
                    Toast.makeText(AdminPageActivity.this, "Please Approve or Reject form!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}