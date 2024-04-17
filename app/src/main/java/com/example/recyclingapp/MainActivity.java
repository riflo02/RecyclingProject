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
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.rg_user_admin);
        RadioButton userButton = new RadioButton(this);
        userButton.setId(View.generateViewId());
        userButton.setText("User");

        RadioButton adminButton = new RadioButton(this);
        adminButton.setId(View.generateViewId());
        adminButton.setText("Administrator");

        rg.addView(userButton);
        rg.addView(adminButton);

    }
    public void approveRecordAndRewardPoints(View v){

    }
}