package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    ConstraintLayout logout;

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profie);

        logout = findViewById(R.id.logout);
        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userId = user.getUid();

        final TextView name_display = (TextView) findViewById(R.id.name_display);
        final TextView email_display = (TextView) findViewById(R.id.email_display);
        final TextView phone_display = (TextView) findViewById(R.id.phone_display);

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);

                if(userProfile != null){

                    String name = userProfile.user_name;
                    String email = userProfile.user_email;
                    String phone = userProfile.user_phone;

                    name_display.setText(name);
                    email_display.setText(email);
                    phone_display.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Something wrong!", Toast.LENGTH_SHORT).show();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profile.this, register_user.class));
            }
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.cartPng);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout infobtn = findViewById(R.id.infobtn);
        LinearLayout profilebtn = findViewById(R.id.profilebtn);

        // === click listener on CART button ===
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, CartList.class));
                Animatoo.INSTANCE.animateZoom(profile.this);
            }
        });

        // === click listener on HOME button ===
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, MainActivity2.class));
            }
        });

        // === click listener on INFO button ===
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, info_activity.class));
                Animatoo.INSTANCE.animateShrink(profile.this);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(profile.this, profile.class));
            }
        });


    }
}