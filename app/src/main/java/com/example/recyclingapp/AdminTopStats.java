package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AdminTopStats extends AppCompatActivity {
    private final String myIP = "192.168.1.142"; // IP address of the server
    ArrayList<User> uList = new ArrayList<>(); // List to store user data
    float salumKg = 0; // Total aluminium weight
    float sglassKg = 0; // Total glass weight
    float spaperKg = 0; // Total paper weight
    float splasticKg = 0; // Total plastic weight
    int max = 0; // Maximum points
    String username = ""; // Username of the user with maximum points
    PieDataSet pieDataSet; // Data set for the pie chart
    ArrayList<PieEntry> entries; // Entries for the pie chart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_top_stats);
        String url = "http://" + myIP + "/fetching.php"; // URL to fetch user data

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler(); // Create an instance of OkHttpHandler
            uList = okHttpHandler.fetching(url); // Fetch user data from the server
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Calculate the total weights and find the user with the maximum points
        for (User user : uList) {
            if (user.getPoints() > max) {
                max = user.getPoints();
                username = user.getUsername();
            }
            salumKg += user.getAluminiumKg();
            sglassKg += user.getGlassKg();
            spaperKg += user.getPaperKg();
            splasticKg += user.getPlasticKg();
        }

        // Display the username and points of the top user
        TextView usernameView = findViewById(R.id.topUsername);
        TextView pointsView = findViewById(R.id.topPoints);
        usernameView.setText(username);
        pointsView.setText(Integer.toString(max));

        // Set up the pie chart to display the total weights of materials
        PieChart pChart = findViewById(R.id.pieChartAdmin);
        entries = new ArrayList<>();
        entries.add(new PieEntry(salumKg, "Aluminium"));
        entries.add(new PieEntry(sglassKg, "Glass"));
        entries.add(new PieEntry(spaperKg, "Paper"));
        entries.add(new PieEntry(splasticKg, "Plastic"));

        pieDataSet = new PieDataSet(entries, "Materials");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pChart.setData(pieData);
        pChart.animateY(1000); // Animate the pie chart
        pChart.invalidate(); // Refresh the pie chart

        // Set up the log out button to navigate back to the main activity
        Button logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTopStats.this, MainActivity.class);
                startActivity(intent); // Start the MainActivity
            }
        });
    }
}