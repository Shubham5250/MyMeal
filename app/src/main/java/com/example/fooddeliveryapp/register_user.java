package com.example.fooddeliveryapp;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_user extends AppCompatActivity {

    TextView signup,loginbtn,hasaccounttxt, donthaveacc;
    LinearLayout signupuserlayout, loginuserlayout;

    //registration form ====>
    EditText signup_username,signup_userphone,signup_useremail,signup_userpassword,signup_userconfirmpassword;
    Button signup_btn_register;

    //login form ====>
    EditText login_email,login_password;
    Button login_user_btn;

    String  emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    private static final String EXTRA_NAME = "com.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        signup = findViewById(R.id.signup_btn);
        loginbtn = findViewById(R.id.login_btnuser);
        signupuserlayout = findViewById(R.id.signupuser_layout);
        loginuserlayout = findViewById(R.id.loginuser_layout);
        hasaccounttxt = findViewById(R.id.hasaccounttxt);
        donthaveacc = findViewById(R.id.donthaveacc);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(GONE);
                loginuserlayout.setVisibility(View.VISIBLE);
                hasaccounttxt.setVisibility(GONE);
                donthaveacc.setVisibility(View.VISIBLE);
                loginbtn.setTextColor(getResources().getColor(R.color.login_signuptext));
                signup.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(View.VISIBLE);
                loginuserlayout.setVisibility(GONE);
                donthaveacc.setVisibility(GONE);
                hasaccounttxt.setVisibility(View.VISIBLE);
                loginbtn.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
                signup.setTextColor(getResources().getColor(R.color.login_signuptext));
            }
        });

        hasaccounttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(GONE);
                loginuserlayout.setVisibility(View.VISIBLE);
                hasaccounttxt.setVisibility(GONE);
                donthaveacc.setVisibility(View.VISIBLE);
                loginbtn.setTextColor(getResources().getColor(R.color.login_signuptext));
                signup.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
            }
        });

        donthaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(View.VISIBLE);
                loginuserlayout.setVisibility(GONE);
                hasaccounttxt.setVisibility(View.VISIBLE);
                donthaveacc.setVisibility(GONE);
                loginbtn.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
                signup.setTextColor(getResources().getColor(R.color.login_signuptext));
            }
        });
    }
}