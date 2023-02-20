package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fooddeliveryapp.Adaptor.CategoryAdaptor;
import com.example.fooddeliveryapp.Adaptor.PopularAdaptor;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    private DatabaseReference databaseReference;

    TextView welcometxt;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        welcometxt = findViewById(R.id.welcometxt);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");


        databaseReference.child(String.valueOf(userId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);

                if(userProfile != null){

                    String name = userProfile.user_name;

                    welcometxt.setText("Hi, " + name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//
//            // Check if user's email is verified
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();
//                                welcometxt.setText("Hi, " + name);
//
//        }

        // ========  Calling the functions declared in the class  =======
        recyclerViewCategoryList();
        recyclerViewPopularList();
        bottomNavigation();
    }







    // ========  Bottom Navigation Menu Intent Activity  =======
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartPng);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout infobtn = findViewById(R.id.infobtn);
        LinearLayout profilebtn = findViewById(R.id.profilebtn);

        // === click listener on CART button ===
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, CartList.class));
                Animatoo.INSTANCE.animateZoom(MainActivity2.this);
            }
        });

        // === click listener on HOME button ===
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity2.class));
            }
        });

        // === click listener on INFO button ===
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, info_activity.class));
                Animatoo.INSTANCE.animateShrink(MainActivity2.this);
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, profile.class));
            }
        });
    }

    // ======== CategoryList function for adding elements to the linearlayout.HORIZONTAL ========
    private void recyclerViewCategoryList() {


        // === creating horizontal linearlayout of category list ===
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        // === creating ArrayList named category of CategoryDomain ===
        ArrayList<CategoryDomain> category = new ArrayList<>();

        // === adding elements to the ArrayList ===
        category.add(new CategoryDomain("Pizza", "pizza_clipart"));
        category.add(new CategoryDomain("Veg meal", "veg_meal"));
        category.add(new CategoryDomain("Soft drinks", "soft_drinks"));
        category.add(new CategoryDomain("Pan veggies", "panveggies"));
        category.add(new CategoryDomain("Donut", "donut"));
        category.add(new CategoryDomain("Burger", "burger_clipart"));

        // === Adapter acts as a bridge between the UI component(ListView , GridView) and data sources(ArrayList, HashMap) ===
        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
        // === here it is binding recycleview and arraylist ===


    }


    // ======== PopularList function for adding elements to the linearlayout.HORIZONTAL ========

    private void recyclerViewPopularList() {

        // === creating horizontal linearlayout of popular list ===
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recycleView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        // === creating ArrayList named popularList of FoodDomain ===
        ArrayList<FoodDomain> popularList = new ArrayList<>();

        // === adding elements to the ArrayList ===
        popularList.add(new FoodDomain("Pepperoni Pizza", "pepperoni_pizza","Pepperoni is an American variety of spicy salami made from cured pork and beef seasoned with paprika or other chili pepper. Thinly sliced pepperoni is one of the most popular pizza toppings in American pizzerias.",  129.00));
        popularList.add(new FoodDomain("Cheese Burger", "cheese_burger","A cheeseburger is a hamburger topped with cheese. Traditionally, the slice of cheese is placed on top of the meat patty. The cheese is usually added to the cooking hamburger patty shortly before serving, which allows the cheese to melt.", 99.00));
        popularList.add(new FoodDomain("Vegetable Pizza", "vegetable_pizza",
                "Fresh tomatoes, onions, arugula, kale, eggplants, bell peppers, spinach, zucchini, mushrooms and more. They all make flavorsome vegetarian pizza toppings. ", 109.00));

        // === Adapter acts as a bridge between the UI component(ListView , GridView) and data sources(ArrayList, HashMap) ===
        adapter = new PopularAdaptor(popularList);
        recyclerViewPopularList.setAdapter(adapter);
        // === here it is binding recycleview and arraylist ===

    }
}