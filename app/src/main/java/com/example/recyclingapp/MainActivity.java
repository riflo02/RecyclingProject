package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_sign_up);

//        rg = findViewById(R.id.rg_user_admin);
//        RadioButton userButton = new RadioButton(this);
//        userButton.setId(View.generateViewId());
//        userButton.setText("User");
//        userButton.setTextColor(getColor(android.R.color.white));
//        userButton.setTextSize(20);
//
//        RadioButton adminButton = new RadioButton(this);
//        adminButton.setId(View.generateViewId());
//        adminButton.setText("Administrator");
//        adminButton.setTextColor(getColor(android.R.color.white));
//        adminButton.setTextSize(20);
//        rg.addView(userButton);
//        rg.addView(adminButton);

    }
    public void approveRecordAndRewardPoints(View v){

    }
}