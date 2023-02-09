package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fooddeliveryapp.Adaptor.CategoryAdaptor;
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


        ArrayList<CategoryDomain> popularList = new ArrayList<>();
        popularList.add(new FoodDomain("Pizza", "pizza_clipart","gngf"));
        popularList.add(new CategoryDomain("Veg meal", "veg_meal"));

        popularList.add(new CategoryDomain("Soft drinks", "soft_drinks"));
        popularList.add(new CategoryDomain("Pan veggies", "panveggies"));

        popularList.add(new CategoryDomain("Donut", "donut"));

        popularList.add(new CategoryDomain("Burger", "burger_clipart"));

        adapter = new CategoryAdaptor(popularList);
        recyclerViewPopularList.setAdapter(adapter);


    }
}