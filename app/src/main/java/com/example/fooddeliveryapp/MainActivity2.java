package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fooddeliveryapp.Adaptor.CategoryAdaptor;
import com.example.fooddeliveryapp.Adaptor.PopularAdaptor;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        
        recyclerViewCategoryList();
        recyclerViewPopularList();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartPng);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout infobtn = findViewById(R.id.infobtn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, CartList.class));
                Animatoo.INSTANCE.animateZoom(MainActivity2.this);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity2.class));
            }
        });

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, info_activity.class));
                Animatoo.INSTANCE.animateShrink(MainActivity2.this);
            }
        });
    }

    private void recyclerViewCategoryList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);


        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "pizza_clipart"));
        category.add(new CategoryDomain("Veg meal", "veg_meal"));

        category.add(new CategoryDomain("Soft drinks", "soft_drinks"));
        category.add(new CategoryDomain("Pan veggies", "panveggies"));

        category.add(new CategoryDomain("Donut", "donut"));

        category.add(new CategoryDomain("Burger", "burger_clipart"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);


    }


    private void recyclerViewPopularList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recycleView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        ArrayList<FoodDomain> popularList = new ArrayList<>();
        popularList.add(new FoodDomain("Pepperoni Pizza", "pepperoni_pizza","Pepperoni is an American variety of spicy salami made from cured pork and beef seasoned with paprika or other chili pepper. Thinly sliced pepperoni is one of the most popular pizza toppings in American pizzerias.",  9.76));
        popularList.add(new FoodDomain("Cheese Burger", "cheese_burger","A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the cooking hamburger patty shortly before serving, which allows the cheese to melt.", 8.79));

        popularList.add(new FoodDomain("Vegetable Pizza", "vegetable_pizza",
                "Fresh tomatoes, onions, arugula, kale, eggplants, bell peppers, spinach, zucchini, mushrooms and more. They all make flavorsome vegetarian pizza toppings. ", 8.52));


        adapter = new PopularAdaptor(popularList);
        recyclerViewPopularList.setAdapter(adapter);


    }
}