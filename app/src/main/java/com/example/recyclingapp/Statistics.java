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

    private final String ip = "192.168.1.142";
    ArrayList<User> uList = new ArrayList<>();

    float alumKg;
    float glassKg;
    float paperKg;
    float plasticKg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        String url = "http://" + ip + "/fetching.php";

        Button back = findViewById(R.id.backToUser);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");
        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            uList = okHttpHandler.fetching(url);
        } catch (Exception e) {
            e.printStackTrace();
        }



        for (User user : uList) {
            if (user.getUsername().equals(username)) {
                alumKg =  user.getAluminiumKg();
                glassKg = user.getGlassKg();
                paperKg = user.getPaperKg();
                plasticKg =user.getPlasticKg();
            }
        }


        PieChart pChart = findViewById(R.id.pieChartUser);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(alumKg, "Aluminium"));
        entries.add(new PieEntry(glassKg, "Glass"));
        entries.add(new PieEntry(paperKg, "Paper"));
        entries.add(new PieEntry(plasticKg, "Plastic"));

        PieDataSet pieDataSet = new PieDataSet(entries, "Materials");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pChart.setData(pieData);
        pChart.animateY(1000);
        pChart.invalidate();


    }
}
