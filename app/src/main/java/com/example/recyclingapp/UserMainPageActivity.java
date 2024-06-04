package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserMainPageActivity extends AppCompatActivity {
    private String ip = "192.168.56.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
        User selectedUser = null;
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String url= "http://"+ip+"/fetching.php";


        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            ArrayList<User> uList = okHttpHandler.fetching(url);
            for (User user : uList) {
                if(user.getUsername().equals(username)){
                    selectedUser = user;
                    break;
                }

            }
        }
        catch (Exception e) {e.printStackTrace();}

        TextView editText = findViewById(R.id.nameText);
        editText.setText(selectedUser.getName());
        TextView pointsText = findViewById(R.id.pointsText);
        pointsText.setText("Total Points: "+ selectedUser.getPoints());

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
                intent.putExtra("Username",username);
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