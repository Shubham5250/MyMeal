package com.example.fooddeliveryapp;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class register_user extends AppCompatActivity {

    TextView signup,loginbtn;
    LinearLayout signupuserlayout, loginuserlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        signup = findViewById(R.id.signup_btn);
        loginbtn = findViewById(R.id.login_btnuser);
        signupuserlayout = findViewById(R.id.signupuser_layout);
        loginuserlayout = findViewById(R.id.loginuser_layout);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(GONE);
                loginuserlayout.setVisibility(View.VISIBLE);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(View.VISIBLE);

                loginuserlayout.setVisibility(GONE);

            }
        });
    }
}