package com.example.fooddeliveryapp;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class info_activity extends AppCompatActivity {

    TextView answer1, answer2, answer3, answer4;
    ImageView arrQ1, arrQ2, arrQ3, arrQ4;
    ImageView imageView5, imageView6, imageView7, imageView8;
    LinearLayout homeBtn,infobtn, profilebtn;
    LinearLayout question1, question2, question3, question4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        arrQ1 = findViewById(R.id.arrQ1);
        arrQ2 = findViewById(R.id.arrQ2);
        arrQ3 = findViewById(R.id.arrQ3);
        arrQ4 = findViewById(R.id.arrQ4);


        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        homeBtn = findViewById(R.id.homebtn);
        infobtn = findViewById(R.id.infobtn);
        profilebtn = findViewById(R.id.profilebtn);


        question1 = findViewById(R.id.question1);

        arrQ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrQ1.setVisibility(GONE);
                imageView5.setVisibility(View.VISIBLE);

                answer1.setVisibility(View.VISIBLE);
            }
        });

        arrQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrQ2.setVisibility(GONE);
                imageView6.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
            }
        });

        arrQ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrQ3.setVisibility(GONE);
                imageView7.setVisibility(View.VISIBLE);

                answer3.setVisibility(View.VISIBLE);
            }
        });

        arrQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrQ4.setVisibility(GONE);
                imageView8.setVisibility(View.VISIBLE);

                answer4.setVisibility(View.VISIBLE);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView5.setVisibility(GONE);
                answer1.setVisibility(GONE);
                arrQ1.setVisibility(View.VISIBLE);

            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView6.setVisibility(GONE);
                answer2.setVisibility(GONE);
                arrQ2.setVisibility(View.VISIBLE);

            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView7.setVisibility(GONE);
                answer3.setVisibility(GONE);
                arrQ3.setVisibility(View.VISIBLE);


            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView8.setVisibility(GONE);
                answer4.setVisibility(GONE);
                arrQ4.setVisibility(View.VISIBLE);
            }
        });


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(info_activity.this, MainActivity2.class));
            }
        });

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(info_activity.this, info_activity.class));
            }
        });


        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(info_activity.this, profile.class));
            }
        });





    }









}

