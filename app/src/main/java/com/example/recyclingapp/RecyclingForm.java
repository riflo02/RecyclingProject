package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RecyclingForm extends AppCompatActivity {
    RadioGroup rg = findViewById(R.id.materialRG);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_form);

        RadioButton r1 = new RadioButton(this);
        RadioButton r2 = new RadioButton(this);
        RadioButton r3 = new RadioButton(this);
        RadioButton r4 = new RadioButton(this);

        r1.setText("Aluminium");
        r1.setId(View.generateViewId());
        r2.setText("Glass");
        r2.setId(View.generateViewId());
        r3.setText("Paper");
        r3.setId(View.generateViewId());
        r4.setText("Plastic");
        r4.setId(View.generateViewId());

        rg.addView(r1);
        rg.addView(r2);
        rg.addView(r3);
        rg.addView(r4);
    }
}