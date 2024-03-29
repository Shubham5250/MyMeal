package com.example.fooddeliveryapp;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fooddeliveryapp.Adaptor.CategoryAdaptor;
import com.example.fooddeliveryapp.Adaptor.PopularAdaptor;
import com.example.fooddeliveryapp.Adaptor.RestaurantsAdapter;
import com.example.fooddeliveryapp.Adaptor.SliderAdapter;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Domain.RestaurantDomain;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList, recyclerViewRestaurants;

    private DatabaseReference databaseReference;

    private LinearLayout restaurants_layout;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    TextView welcometxt;
    private FirebaseUser user;
    private String userId;

    TextView restaurant_txt;

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ConstraintLayout mainContent = findViewById(R.id.toggleMainContent);
        viewPager2 = findViewById(R.id.viewPagerImageSlider);
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

        TextView filter_txt = findViewById(R.id.filter_txt);
        final int[] checkfilter = {1};
        filter_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkfilter[0] ==1 ) {
                    filter_txt.setBackgroundResource(R.drawable.txt_selector);
                    checkfilter[0] = 0;
                }
                else{

                    filter_txt.setBackgroundResource(R.drawable.filter_bg);
                    checkfilter[0] =1;
                }
                showBottomSheetDialogFilter();
            }
        });



        restaurant_txt = findViewById(R.id.restaurants_txt);
        restaurants_layout = findViewById(R.id.restaurants_layout);
        restaurant_txt.setOnClickListener(new View.OnClickListener() {
            int check = 1;

            @Override
            public void onClick(View v) {
                if(check ==1 ) {
                    mainContent.setVisibility(GONE);
                    restaurants_layout.setVisibility(View.VISIBLE);
                    restaurant_txt.setBackgroundResource(R.drawable.txt_selector);
                    check = 0;
                }
                else{
                    mainContent.setVisibility(View.VISIBLE);
                    restaurants_layout.setVisibility(GONE);
                    restaurant_txt.setBackgroundResource(R.drawable.filter_bg);
                    check =1;
                }
            }
        });
        List<SliderItem> sliderItems = new ArrayList<>();

        sliderItems.add(new SliderItem(R.drawable.newuser));
        sliderItems.add(new SliderItem(R.drawable.dealofday));
        sliderItems.add(new SliderItem(R.drawable.restomania));
        sliderItems.add(new SliderItem(R.drawable.specialoffer));
        sliderItems.add(new SliderItem(R.drawable.refer));


        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(SliderRunnable);
                sliderHandler.postDelayed(SliderRunnable,2500);
            }
        });
        // ========  Calling the functions declared in the class  =======
        recyclerViewCategoryList();
        recyclerViewPopularList();
        bottomNavigation();
        restaurants_BottomSheetFragment();
    }

    private void showBottomSheetDialogFilter() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.filter_bottom_sheet_dialog);

        radioGroup = bottomSheetDialog.findViewById(R.id.radioGroup);
        TextView button = bottomSheetDialog.findViewById(R.id.applybtn);
        TextView clearAll = bottomSheetDialog.findViewById(R.id.clearAll);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = bottomSheetDialog.findViewById(radioId);

                bottomSheetDialog.dismiss();
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
            }
        });
        bottomSheetDialog.show();

    }


    private void restaurants_BottomSheetFragment(){

        // ======creating VERTICAL linearlayput of restaurants =====
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
//        recyclerViewRestaurants = findViewById(R.id.rescyclerView_Restaurants);
         recyclerViewRestaurants = findViewById(R.id.recyclerView_restaurants);

        recyclerViewRestaurants.setLayoutManager(linearLayoutManager);

        ArrayList<RestaurantDomain> restaurantDomainArrayList = new ArrayList<>();

        restaurantDomainArrayList.add(new RestaurantDomain("Non veg Zaika - The Biryani House","resto1","Non veg","nonvegpng","Within 1 km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Milan Pure Veg Restaurant","resto2","Pure veg","pureveg","Within 1.5 km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Vaishali - Shahi Restaurant","resto3","Pure veg","pureveg","Within 2 km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Anna's - South Indian Restaurant","resto4","Pure veg","pureveg","Within 900 m"));
        restaurantDomainArrayList.add(new RestaurantDomain("Cafe Barista - Resto & Bar","resto5","Pure veg","pureveg","Within 1km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Empire Restaurant","resto6","Pure veg","pureveg","Within 500 m"));
        restaurantDomainArrayList.add(new RestaurantDomain("Bikkgane Biryani","resto7","Non veg","nonvegpng","Within 3 km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Khana Khazana","resto8","Pure veg","pureveg","Within 1.2 km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Iceland Fruits & Desserts","resto9","Pure veg","pureveg","Within 2.5 km"));
        restaurantDomainArrayList.add(new RestaurantDomain("Punjab Sweet Corner","resto10","Pure veg","pureveg","Within 600 m"));


        RecyclerView.Adapter adapter_restaurants = new RestaurantsAdapter(restaurantDomainArrayList);
        recyclerViewRestaurants.setAdapter(adapter_restaurants);


    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
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
        popularList.add(new FoodDomain("Garlic Bread", "garlic_bread_popular", "Garlic bread consists of bread, topped with garlic and olive oil or butter and may include additional herbs, such as oregano or chives. It is then either grilled until toasted or baked in a conventional or bread oven.", 79.00));

        // === Adapter acts as a bridge between the UI component(ListView , GridView) and data sources(ArrayList, HashMap) ===
        adapter = new PopularAdaptor(popularList);
        recyclerViewPopularList.setAdapter(adapter);
        // === here it is binding recycleview and arraylist ===

    }

    private Runnable SliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(SliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(SliderRunnable, 2500);
    }


}