package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AdminPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

         Button backButton = findViewById(R.id.log_out_button);
         backButton.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
       // Epistrofi
             Intent intent = new Intent(AdminPageActivity.this,MainActivity.class);
            startActivity(intent);
          }
         });

    }
}