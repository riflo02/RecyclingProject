package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {
    private final String myIP = "192.168.1.142"; // IP address of the server
    ArrayList<User> uList = new ArrayList<>(); // List to store user data
    float alumKg; // Variable to store aluminium weight
    float glassKg; // Variable to store glass weight
    float paperKg; // Variable to store paper weight
    float plasticKg; // Variable to store plastic weight

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        String url = "http://" + myIP + "/fetching.php"; // URL to fetch user data

        // Back button listener to finish the activity and return to the previous screen
        Button back = findViewById(R.id.backToUser);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the current activity
            }
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username"); // Get the username from the Intent
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler(); // Create a new OkHttpHandler
            uList = okHttpHandler.fetching(url); // Fetch the user list from the server
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace in case of an exception
        }

        // Iterate through the user list to find the selected user by username
        for (User user : uList) {
            if (user.getUsername().equals(username)) {
                // Assign the weights of materials to the respective variables
                alumKg = user.getAluminiumKg();
                glassKg = user.getGlassKg();
                paperKg = user.getPaperKg();
                plasticKg = user.getPlasticKg();
            }
        }

        // Initialize the PieChart and set the data
        PieChart pChart = findViewById(R.id.pieChartUser);

        // Create a list of PieEntry objects with material weights and labels
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(alumKg, "Aluminium"));
        entries.add(new PieEntry(glassKg, "Glass"));
        entries.add(new PieEntry(paperKg, "Paper"));
        entries.add(new PieEntry(plasticKg, "Plastic"));

        // Create a PieDataSet and set its colors
        PieDataSet pieDataSet = new PieDataSet(entries, "Materials");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // Create PieData and set it to the PieChart
        PieData pieData = new PieData(pieDataSet);
        pChart.setData(pieData);
        pChart.animateY(1000); // Animate the PieChart for 1000 milliseconds
        pChart.invalidate(); // Refresh the PieChart
    }
}