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

                String url = "http://" + ip + "/fetching.php"; // Προσαρμόστε το URL

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpHandler okHttpHandler = new OkHttpHandler();
                            ArrayList<User> uList = okHttpHandler.fetching(url);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    boolean userFound = false;
                                    for (User user : uList) {
                                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                            Intent intent = new Intent(SignInActivity.this, UserMainPageActivity.class);
                                            intent.putExtra("name", user.getName());
                                            intent.putExtra("email", user.getEmail());
                                            intent.putExtra("username", user.getUsername());
                                            startActivity(intent);
                                            userFound = true;
                                            break;
                                        }
                                    }
                                    if (!userFound) {
                                        Toast.makeText(SignInActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignInActivity.this, "Error connecting to server", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}
