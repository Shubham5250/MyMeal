package com.example.fooddeliveryapp.Helper;

import android.content.Context;

import com.example.fooddeliveryapp.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){

        ArrayList<FoodDomain> listFood = getListCart();
        boolean alreadyExists= false;

        int n=0;
        for(int i = 0; i<listFood.size();i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                alreadyExists = true;
                n = i;
                break;
            }
        }

        if(alreadyExists){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }
        else{
            listFood.add(item);
        }

    }

    private ArrayList<FoodDomain> getListCart() {

        return tinyDB.getListObject("CartList");
    }

}
