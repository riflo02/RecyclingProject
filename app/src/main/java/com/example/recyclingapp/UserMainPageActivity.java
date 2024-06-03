package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserMainPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        Integer points = intent.getIntExtra("points",0);

        TextView editText = findViewById(R.id.nameText);
        editText.setText(name);
        TextView pointsText = findViewById(R.id.pointsText);
        pointsText.setText("Total Points: "+points);

        //LogOut Button Listener
        Button logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Επιστροφή στο MainActivity
                Intent intent = new Intent(UserMainPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Registration Form Button Listener
        Button regFormButton = findViewById(R.id.registrationFormButton);
        regFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainPageActivity.this, RecyclingForm.class);
                startActivity(intent);
            }
        });

        Button statisticsButton = findViewById(R.id.statButton);
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainPageActivity.this, Statistics.class);
                startActivity(intent);
            }
        });

    }



}