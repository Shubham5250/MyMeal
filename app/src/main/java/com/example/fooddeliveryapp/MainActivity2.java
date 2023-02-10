package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fooddeliveryapp.Adaptor.CategoryAdaptor;
import com.example.fooddeliveryapp.Adaptor.PopularAdaptor;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.FoodDomain;

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
        popularList.add(new FoodDomain("Pepperoni Pizza", "pepperoni_pizza","slices pepperoni, mozzerella, fresh oregano, ground black pepper, pizza sauce","9.76"));
        popularList.add(new FoodDomain("Cheese Burger", "cheese_burger","beef, tomato,lattuce","8.79"));

        popularList.add(new FoodDomain("Vegetable Pizza", "vegetable_pizza","olive oil, vegetable oil, cherry tomatoes","8.52"));


        adapter = new PopularAdaptor(popularList);
        recyclerViewPopularList.setAdapter(adapter);


    }
}