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
    private final String ip = "192.168.1.142";
    ArrayList<User> uList = new ArrayList<>();

    float salumKg = 0;
    float sglassKg = 0;
    float spaperKg = 0;
    float splasticKg = 0;
    int max = 0;
    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_top_stats);
        String url = "http://" + ip + "/fetching.php";


        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            uList = okHttpHandler.fetching(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (User user : uList) {
            if(user.getPoints()>max){
                max = user.getPoints();
                username = user.getUsername();
            }
                salumKg +=  user.getAluminiumKg();
                sglassKg += user.getGlassKg();
                spaperKg += user.getPaperKg();
                splasticKg += user.getPlasticKg();
        }

        TextView usernameView = findViewById(R.id.topUsername);
        TextView pointsView = findViewById(R.id.topPoints);

        usernameView.setText(username);
        pointsView.setText(Integer.toString(max));

        PieChart pChart = findViewById(R.id.pieChartAdmin);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(salumKg, "Aluminium"));
        entries.add(new PieEntry(sglassKg, "Glass"));
        entries.add(new PieEntry(spaperKg, "Paper"));
        entries.add(new PieEntry(splasticKg, "Plastic"));

        PieDataSet pieDataSet = new PieDataSet(entries, "Materials");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pChart.setData(pieData);
        pChart.animateY(1000);
        pChart.invalidate();



        //Log out Button Listener
        Button logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminTopStats.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}