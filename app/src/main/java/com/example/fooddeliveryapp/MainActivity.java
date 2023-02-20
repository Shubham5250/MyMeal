package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 900;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(register_user.PREFS_NAME,0);
                boolean hasRegistered = sharedPreferences.getBoolean("hasRegistered",false);


                Intent i;
                if(hasRegistered){

                    i = new Intent(MainActivity.this, MainActivity2.class);

                }
                else{
                    i = new Intent(MainActivity.this, register_user.class);
                }
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(register_user.PREF_NAME,0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);


                Intent i;
                if(hasLoggedIn){

                    i = new Intent(MainActivity.this, MainActivity2.class);

                }
                else{
                    i = new Intent(MainActivity.this, register_user.class);
                }
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
//

}