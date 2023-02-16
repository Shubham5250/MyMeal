package com.example.fooddeliveryapp;

public class user {

    public String user_name, user_phone, user_email;

    public user(){
        // empty constructor
        // can access these variables if create an empty object of the class
    }

    public user(String user_name, String user_phone, String user_email){
        // initialize variables
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_email = user_email;
    }

}
