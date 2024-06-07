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
    String myIP = "192.168.1.142"; // IP address of the server
    ArrayList<User> uList; // List to store user data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView signupText = findViewById(R.id.SignUp_txt); // TextView for sign-up option
        String url = "http://" + myIP + "/fetching.php"; // URL to fetch user data

        // Set a click listener on the sign-up text to navigate to the SignUpActivity
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent); // Start the SignUpActivity
            }
        });

        // Initialize the login button and set a click listener for user login
        Button logIn = findViewById(R.id.login_button);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from the login form fields
                EditText usernameInput = findViewById(R.id.uname_input);
                String username = usernameInput.getText().toString();
                EditText passwordInput = findViewById(R.id.password);
                String password = passwordInput.getText().toString();

                String url = "http://" + myIP + "/fetching.php"; // Adjust the URL if needed

                // Create a new thread to handle network operations
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpHandler okHttpHandler = new OkHttpHandler(); // Create a new OkHttpHandler
                            ArrayList<User> uList = okHttpHandler.fetching(url); // Fetch the user list from the server

                            // Run the UI-related code on the main thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    boolean userFound = false;
                                    // Iterate through the user list to find a match for the username and password
                                    for (User user : uList) {
                                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                            Intent intent = new Intent(SignInActivity.this, UserMainPageActivity.class);
                                            intent.putExtra("username", user.getUsername()); // Pass the username to the next activity
                                            startActivity(intent); // Start the UserMainPageActivity
                                            userFound = true;
                                            break;
                                        }
                                    }
                                    // Show a toast message if the username or password is invalid
                                    if (!userFound) {
                                        Toast.makeText(SignInActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            // Show a toast message if there's an error connecting to the server
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SignInActivity.this, "Error connecting to server", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start(); // Start the thread
            }
        });
    }
}