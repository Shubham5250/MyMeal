package com.example.fooddeliveryapp;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class register_user extends AppCompatActivity {

    public static String PREF_NAME = "MyPrefFile";
    // VARIABLES AND THEIR TYPES ====>>>>>
    TextView signup,loginbtn,hasaccounttxt, donthaveacc;
    LinearLayout signupuserlayout, loginuserlayout;

    //registration form ====>
    EditText signup_username,signup_userphone,signup_useremail,signup_userpassword,signup_userconfirmpassword;
    Button signup_btn_register;

    //login form ====>
    EditText login_email,login_password;
    Button login_user_btn;

    String  emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    ProgressDialog progressDialogLogin;

    public static String PREFS_NAME = "MyPrefsFile";

    FirebaseAuth mAuth;
    FirebaseAuth mAuth1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        mAuth1 = FirebaseAuth.getInstance();
        // initialising the variables =====>>>>>>
        signup = findViewById(R.id.signup_btn);
        loginbtn = findViewById(R.id.login_btnuser);
        signupuserlayout = findViewById(R.id.signupuser_layout);
        loginuserlayout = findViewById(R.id.loginuser_layout);
        hasaccounttxt = findViewById(R.id.hasaccounttxt);
        donthaveacc = findViewById(R.id.donthaveacc);

        progressDialog = new ProgressDialog(this);
        progressDialogLogin = new ProgressDialog(this);

        signup_username = (EditText) findViewById(R.id.signup_username);
        signup_userphone = (EditText) findViewById(R.id.signup_userphone);
        signup_useremail = (EditText) findViewById(R.id.signup_useremail);
        signup_userpassword = (EditText) findViewById(R.id.signup_userpassword);
        signup_userconfirmpassword = (EditText) findViewById(R.id.signup_userconfirmpassword);
        signup_btn_register = findViewById(R.id.signup_btn_register);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_user_btn = findViewById(R.id.login_user_btn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(GONE);
                loginuserlayout.setVisibility(View.VISIBLE);
                hasaccounttxt.setVisibility(GONE);
                donthaveacc.setVisibility(View.VISIBLE);
                loginbtn.setTextColor(getResources().getColor(R.color.login_signuptext));
                signup.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(View.VISIBLE);
                loginuserlayout.setVisibility(GONE);
                donthaveacc.setVisibility(GONE);
                hasaccounttxt.setVisibility(View.VISIBLE);
                loginbtn.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
                signup.setTextColor(getResources().getColor(R.color.login_signuptext));
            }
        });

        hasaccounttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(GONE);
                loginuserlayout.setVisibility(View.VISIBLE);
                hasaccounttxt.setVisibility(GONE);
                donthaveacc.setVisibility(View.VISIBLE);
                loginbtn.setTextColor(getResources().getColor(R.color.login_signuptext));
                signup.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
            }
        });

        donthaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupuserlayout.setVisibility(View.VISIBLE);
                loginuserlayout.setVisibility(GONE);
                hasaccounttxt.setVisibility(View.VISIBLE);
                donthaveacc.setVisibility(GONE);
                loginbtn.setTextColor(getResources().getColor(R.color.defaultloginsignuptext));
                signup.setTextColor(getResources().getColor(R.color.login_signuptext));
            }
        });

        signup_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(register_user.PREFS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("hasRegistered",true);
                editor.apply();
                registerUser();


            }
        });

        login_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(register_user.PREF_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("hasLoggedIn",true);
                editor.apply();
                loginUser();

            }
        });
    }

    private void registerUser(){

        // create validation -> so that user does not submit the empty form
        // get the values -> convert it to string -> and save it in variables
        String user_name = signup_username.getText().toString();
        String user_phone = signup_userphone.getText().toString();
        String user_email = signup_useremail.getText().toString();
        String user_password = signup_userpassword.getText().toString().trim();
        String user_confirm_password = signup_userconfirmpassword.getText().toString().trim();

        // if... statements to validate these inputs --->

        if(user_name.isEmpty())
        {
            signup_username.setError("Name is required");
            signup_username.requestFocus();
            return;
        }

        else if(user_phone.isEmpty())
        {
            signup_userphone.setError("Phone number is required");
            signup_userphone.requestFocus();
            return;
        }

        else if(user_email.isEmpty())
        {
            signup_useremail.setError("Email is required");
            signup_useremail.requestFocus();
            return;
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(user_email).matches())
        {
            signup_useremail.setError("Invalid email input");
            signup_useremail.requestFocus();
            return;
        }

        else if(user_password.isEmpty())
        {
            signup_userpassword.setError("Password required");
            signup_useremail.requestFocus();
            return;
        }

        else if(user_password.length() < 6)
        {
            signup_userpassword.setError("Password with atleast 6 characters is required");
            signup_userpassword.requestFocus();
            return;
        }

        if(!user_password.matches(user_confirm_password))
        {
            signup_userconfirmpassword.setError("Password should match the above password");
            signup_userconfirmpassword.requestFocus();
            return;
        }


        // now we need to create one "user" class so that we can create its object to store the input data into it
        // and send that object to the firebase

        // mAuth is an object for firebase authentication

        mAuth.createUserWithEmailAndPassword(user_email, user_password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {       // check if user has been already registered
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //create an user object so that we that store the user info in a realtime database
                            user user = new user(user_name, user_phone, user_email);

                            // firebase database object
                            // users => collection of objects
                            // add realtime database to your project
                            progressDialog.setMessage("Please while we register you!");
                            progressDialog.setTitle("Authorizing...");
                            progressDialog.setCanceledOnTouchOutside(true);
                            progressDialog.show();
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())      //we get the registered users id and set it here, so that we can correspond this object(i.e user) to the registered user
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task2) {

                                            if(task2.isSuccessful()){
                                                hideProgress();
                                                sendUserToNextActivity();
                                            }
                                            else{

                                                hideProgress();
                                                Toast.makeText(register_user.this, ""+task2.getException(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else{

                            hideProgress();
                            Toast.makeText(register_user.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void loginUser(){

        final String loggedUser_email = login_email.getText().toString();
        final String loggedUser_password = login_password.getText().toString();

        if(loggedUser_email.isEmpty())
        {
            login_email.setError("Email is required");
            login_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(loggedUser_email).matches())
        {
            login_email.setError("Invalid email input");
            login_email.requestFocus();
            return;
        }
        if(loggedUser_password.isEmpty())
        {
            login_password.setError("Password is required");
            login_password.requestFocus();
            return;
        }
        if(loggedUser_password.length()<6)
        {
                login_password.setError("Password with atleast 6 characters is required");
        }

        progressDialogLogin.setMessage("Logging you in");
        progressDialogLogin.setTitle("Authorizing...");
        progressDialogLogin.setCanceledOnTouchOutside(true);
        progressDialogLogin.show();
        mAuth1.signInWithEmailAndPassword(loggedUser_email, loggedUser_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task1) {
                if(task1.isSuccessful()){




                    hideProgressLogin();
                    sendUserToNextActivity();

                }
                else{
                    hideProgressLogin();
                    Toast.makeText(register_user.this, ""+task1.getException(), Toast.LENGTH_SHORT).show();

                }
            }

        });



    }

    private void sendUserToNextActivity() {
        Intent i = new Intent(register_user.this, MainActivity2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    public void hideProgress() {
        if(progressDialog != null) {
            if(progressDialog.isShowing()) { //check if dialog is showing.

                //get the Context object that was used to great the dialog
                Context context = ((ContextWrapper)progressDialog.getContext()).getBaseContext();

                //if the Context used here was an activity AND it hasn't been finished or destroyed
                //then dismiss it
                if(context instanceof Activity) {
                    if(!((Activity)context).isFinishing() && !((Activity)context).isDestroyed())
                        progressDialog.dismiss();
                } else //if the Context used wasnt an Activity, then dismiss it too
                    progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }



    public void hideProgressLogin() {
        if(progressDialogLogin != null) {
            if(progressDialogLogin.isShowing()) { //check if dialog is showing.

                //get the Context object that was used to great the dialog
                Context context = ((ContextWrapper)progressDialogLogin.getContext()).getBaseContext();

                //if the Context used here was an activity AND it hasn't been finished or destroyed
                //then dismiss it
                if(context instanceof Activity) {
                    if(!((Activity)context).isFinishing() && !((Activity)context).isDestroyed())
                        progressDialogLogin.dismiss();
                } else //if the Context used wasnt an Activity, then dismiss it too
                    progressDialogLogin.dismiss();
            }
            progressDialogLogin = null;
        }
    }


}