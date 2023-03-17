package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.fooddeliveryapp.Adaptor.CartListAdapator;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.razorpay.Checkout;

import org.w3c.dom.Text;

public class CartList extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    LottieAnimationView anim;
    TextView totalItemFee, discountText, taxText, deliveryChargesText,totalText,emptyTxt, checkout;

    private double tax;
    ScrollView scrollView3;

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        userId = user.getUid();



        initView();
        initList();
        calculateCart();
        bottomNavigation();
        showProceedForPaymentBottomSheetDialog();
    }



    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartPng);
        LinearLayout homeBtn = findViewById(R.id.homebtn);
        LinearLayout infobtn = findViewById(R.id.infobtn);
        LinearLayout profilebtn = findViewById(R.id.profilebtn);



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

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartList.this, info_activity.class));
            }
        });

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartList.this, profile.class));
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

        totalItemFee.setText("₹"+ itemTotal);
        taxText.setText("₹"+ tax);
        deliveryChargesText.setText("₹"+ delivery);
        totalText.setText("₹"+ total);

    }

    private void showProceedForPaymentBottomSheetDialog(){
        final BottomSheetDialog bottomSheetDialogProceedToPayment = new BottomSheetDialog(this);
        bottomSheetDialogProceedToPayment.setContentView(R.layout.proceed_to_payment);
        checkout = findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView proceed_to_pay = bottomSheetDialogProceedToPayment.findViewById(R.id.proceed_to_pay);

                //<------------- Bottom Sheet Fragment Details (show()) ------------>

                EditText flatno_buildingname = (EditText) bottomSheetDialogProceedToPayment.findViewById(R.id.flatno_buildingname);
                EditText streetname_area = (EditText) bottomSheetDialogProceedToPayment.findViewById(R.id.streetname_area);
                EditText landmark = (EditText) bottomSheetDialogProceedToPayment.findViewById(R.id.landmark);

                double delivery = 10;
                double total = Math.round((managementCart.getTotalFee()+ tax + delivery)*100)/100;
                TextView cart_amount =  bottomSheetDialogProceedToPayment.findViewById(R.id.cart_amount);
                cart_amount.setText("₹" + total);
                final TextView customer_name_bill = (TextView) bottomSheetDialogProceedToPayment.findViewById(R.id.customer_name_bill);
                final TextView user_phone = (TextView) bottomSheetDialogProceedToPayment.findViewById(R.id.user_phone);
                databaseReference.child(String.valueOf(userId)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user userProfile = snapshot.getValue(user.class);

                        if(userProfile != null){

                            String name = userProfile.user_name;
                            String phone = userProfile.user_phone;

                            customer_name_bill.setText(name);
                            user_phone.setText(phone);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CartList.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                    }
                });

                bottomSheetDialogProceedToPayment.show();




                // <------------ PAYMENT GATEWAY ---------->

                //object of the Razorpay dependency
                // we are preloading libraries

                proceed_to_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String flatno_addr = flatno_buildingname.getText().toString();
                        String street_addr = streetname_area.getText().toString();
                        String landmark_addr = landmark.getText().toString();

                        if(flatno_addr.isEmpty()){
                            flatno_buildingname.setError("Flat number required");
                            flatno_buildingname.requestFocus();
                            return;
                        }
                        else if(street_addr.isEmpty()){
                            streetname_area.setError("Street name required");
                            streetname_area.requestFocus();
                            return;

                        }
                        else if(landmark_addr.isEmpty()){
                            landmark.setError("Landmark required");
                            landmark.requestFocus();
                            return;

                        }
                        Intent intent = new Intent(CartList.this, redirection_splash.class);
                        startActivity(intent);

                    }
                });

            }
        });
    }



}