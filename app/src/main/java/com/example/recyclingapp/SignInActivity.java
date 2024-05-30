package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
 String ip = "192.168.56.1";
    ArrayList<User> uList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TextView signupText = findViewById(R.id.SignUp_txt);
        String url= "http://"+ip+"/fetching.php";

        //Άνοιγμα παραθύρου για sign in
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        //Άνοιγμα userMainPage
        Button logIn = findViewById(R.id.login_button);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameInput = findViewById(R.id.uname_input);
                String username = usernameInput.getText().toString();
                EditText passwordInput = findViewById(R.id.password);
                String password = passwordInput.getText().toString();
                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    uList = okHttpHandler.fetching(url);
                    Toast.makeText(SignInActivity.this, "EN DOULEUEI",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(SignInActivity.this, uList.size(),Toast.LENGTH_SHORT).show();
                for(int i = 0; i< uList.size();i++){
                    Toast.makeText(SignInActivity.this, uList.get(i).getName() + uList.get(i).getUsername(),Toast.LENGTH_SHORT).show();
                    if(uList.get(i).isUsername(username) && uList.get(i).isPassword(password)){
                        Intent intent = new Intent(SignInActivity.this, UserMainPageActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
                Toast.makeText(SignInActivity.this, "There is no user",Toast.LENGTH_LONG).show();
            }
        });
    }
}
