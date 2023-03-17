package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class redirection_splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1900;

    TextView redirecting_txt;
    LottieAnimationView redirection_splash, bottom_celebration, order_placed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirection_splash);

        redirecting_txt = findViewById(R.id.redirecting_txt);
        redirection_splash = findViewById(R.id.redirecting_lottie_anim);
        bottom_celebration = findViewById(R.id.bottom_celebration);
        order_placed = findViewById(R.id.order_placed);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirecting_txt.setText("Order Placed successfully!");
                redirection_splash.setVisibility(View.GONE);
                bottom_celebration.setVisibility(View.VISIBLE);
                order_placed.setVisibility(View.VISIBLE);

            }
        },SPLASH_TIME_OUT);
    }
}