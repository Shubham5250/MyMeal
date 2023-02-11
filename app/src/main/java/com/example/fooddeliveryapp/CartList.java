package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fooddeliveryapp.Adaptor.CartListAdapator;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartList extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalItemFee, discountText, taxText, deliveryChargesText,totalText,emptyTxt;
    private double tax;
    private ScrollView scrollView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);


        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartPng);
        LinearLayout homeBtn = findViewById(R.id.homebtn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartList.this, CartList.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartList.this, MainActivity2.class));
            }
        });
    }

    private void initView() {

        recyclerViewList = findViewById(R.id.recycleViewList2);
        totalItemFee = findViewById(R.id.totalItemFee);
        taxText = findViewById(R.id.taxText);
        discountText = findViewById(R.id.discountText);
        deliveryChargesText = findViewById(R.id.deliveryChargesText);
        totalText = findViewById(R.id.totalText);
        scrollView3 = findViewById(R.id.scrollView3);
        emptyTxt = findViewById(R.id.emptyTxt);


    }


    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapator(managementCart.getListCart(),  this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView3.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView3.setVisibility(View.VISIBLE);

        }

    }

    private void calculateCart(){
        double percentTax= 0.02;
        double delivery = 10;

        tax = Math.round(managementCart.getTotalFee()*percentTax*100)/100;
        double total = Math.round((managementCart.getTotalFee()+ tax + delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;


        totalItemFee.setText("$"+ itemTotal);
        taxText.setText("$"+ tax);
        deliveryChargesText.setText("$"+ delivery);
        totalText.setText("$"+total);
    }


}