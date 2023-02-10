package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Helper.ManagementCart;

public class ShowDetailsActivity extends AppCompatActivity {

    private TextView titleText, feeTxt;
    private TextView minusTxt, plusTxt, numberTxt,addToCartTxt,descriptionText;
    private ImageView picFood;
    private FoodDomain object;
    private ManagementCart managementCart;


    int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);


        managementCart  = new ManagementCart(this);
        initView();
        getBundle();

    }


    private void getBundle() {

        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleText.setText(object.getTitle());
        feeTxt.setText("$ "+object.getFee());
        descriptionText.setText(object.getDescription());
        numberTxt.setText(String.valueOf(numberOrder));


        plusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder+1;
                numberTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder = numberOrder-1;

                }
                numberTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
                Toast.makeText(ShowDetailsActivity.this, "Added to your cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {

        addToCartTxt = findViewById(R.id.addToCartTxt);
        titleText = findViewById(R.id.titleText);
        feeTxt = findViewById(R.id.priceTxt);
        minusTxt = findViewById(R.id.minustxt);
        descriptionText = findViewById(R.id.description);
        plusTxt = findViewById(R.id.plusTxt);
        numberTxt = findViewById(R.id.numberTxt);
        picFood =  findViewById(R.id.picFood);

    }
}