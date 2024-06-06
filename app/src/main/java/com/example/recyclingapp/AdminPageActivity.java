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
    private final String myIP = "192.168.1.142";

    private RadioGroup radioGroupApproval;
    private RadioButton radioButtonApprove;
    private RadioButton radioButtonReject;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Intent intent1 = getIntent();
        String usrname = intent1.getStringExtra("username");
        String material = intent1.getStringExtra("material");
        String quantityStr = intent1.getStringExtra("quantity");
        TextView textRequest = findViewById(R.id.textRequestFromUser);
        TextView materialView = findViewById(R.id.materialView);
        TextView quantityView = findViewById(R.id.quantityView);
        textRequest.setText("The request was submitted by: "+ usrname);
        materialView.setText("Material: "+material);
        quantityView.setText("Quantity: "+quantityStr);




        Button automaticPoints = findViewById(R.id.buttonAutoAssignPoints);

        radioGroupApproval = findViewById(R.id.radioGroupApproval);
        radioButtonApprove = findViewById(R.id.radioButtonApprove);
        radioButtonReject = findViewById(R.id.radioButtonReject);
            automaticPoints.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {
                    int selectedId = radioGroupApproval.getCheckedRadioButtonId();
                        if(selectedId == radioButtonApprove.getId()) {
                            try {
                                int quantityKg = Integer.parseInt(quantityStr);
                                int pointsToAdd = quantityKg * 5;
                                String url = "http://" + myIP + "/update.php?Username=" + usrname + "&Points=" + pointsToAdd +
                                "&Quantity=" + quantityKg + "&Material=" + material;
                                try {
                                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                                    okHttpHandler.update(url);
                                    Toast.makeText(getApplicationContext(), "Points updated successfully", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (NumberFormatException e) {
                            }

                        }
                        Intent intent2 = new Intent(AdminPageActivity.this,AdminTopStats.class);
                        startActivity(intent2);
                    }
            });
        }
}
