package com.example.recyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
 String ip = "192.168.56.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        TextView signupText = findViewById(R.id.SignUp_txt);

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
                ArrayList<String[]> uList = new ArrayList<>();
                String url= "http://"+ip+"/fetching.php";
                try {
                    OkHttpHandler okHttpHandler = new OkHttpHandler();
                    uList = okHttpHandler.fetching(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                EditText usernameInput = findViewById(R.id.uname_input);
                String username = usernameInput.getText().toString();
                EditText passwordInput = findViewById(R.id.password);
                String password = passwordInput.getText().toString();
                for(int i=0;i<uList.size();i++){
                    if(uList.get(i)[0].equals(username) && uList.get(i)[1].equals(password)){
                        Intent intent = new Intent(SignInActivity.this, UserMainPageActivity.class);
                        startActivity(intent);
                        break;
                    }
                }

            }
        });
    }
}