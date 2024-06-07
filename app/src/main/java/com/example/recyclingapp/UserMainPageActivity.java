package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserMainPageActivity extends AppCompatActivity {
    private String myIP = "192.168.1.142"; // IP address of the server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        User selectedUser = null; // Initialize the selectedUser to null
        Intent intent = getIntent();
        String username = intent.getStringExtra("username"); // Get the username from the Intent
        String url = "http://" + myIP + "/fetching.php"; // URL to fetch user data

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler(); // Create a new OkHttpHandler
            ArrayList<User> uList = okHttpHandler.fetching(url); // Fetch the user list from the server

            // Iterate through the user list to find the selected user by username
            for (User user : uList) {
                if (user.getUsername().equals(username)) {
                    selectedUser = user;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace in case of an exception
        }

        // Display the user's name and total points on the TextViews
        TextView editText = findViewById(R.id.nameText);
        editText.setText(selectedUser.getName());
        TextView pointsText = findViewById(R.id.pointsText);
        pointsText.setText("Total Points: " + selectedUser.getPoints());

        // LogOut Button Listener
        Button logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to MainActivity when the logout button is clicked
                Intent intent = new Intent(UserMainPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Registration Form Button Listener
        Button regFormButton = findViewById(R.id.registrationFormButton);
        regFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the RecyclingForm activity and pass the username
                Intent intent = new Intent(UserMainPageActivity.this, RecyclingForm.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });

        // Statistics Button Listener
        Button statisticsButton = findViewById(R.id.statButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Statistics activity and pass the username
                Intent intent = new Intent(UserMainPageActivity.this, Statistics.class);
                intent.putExtra("Username", username);
                startActivity(intent);
            }
        });

        // Set the progress of the ProgressBar based on the user's points
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(selectedUser.getPoints() % 50);
        progressBar.setMax(50);
    }
}