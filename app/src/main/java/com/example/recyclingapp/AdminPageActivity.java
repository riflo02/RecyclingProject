package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

         Button backButton = findViewById(R.id.backAdminButton);
         backButton.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {
            // Epistrofi
             Intent intent = new Intent(AdminPageActivity.this, AdminPageActivity.class);
            startActivity(intent);
          }
         });

    }
}