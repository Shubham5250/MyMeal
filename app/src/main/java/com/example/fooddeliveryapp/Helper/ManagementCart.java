package com.example.fooddeliveryapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemListener;

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
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Item added to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {

        return tinyDB.getListObject("CartList");

    }

    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemListener changeNumberItemListener){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();


    }

    public void minusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemListener changeNumberItemListener){
        if(listFood.get(position).getNumberInCart() == 1){
            listFood.remove(position);
        }
        else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);

        }

        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomain> listFood = getListCart();
        double fee = 0;
        for(int i = 0; i < listFood.size(); i++ ){
            fee = fee+(listFood.get(i).getFee() *listFood.get(i).getNumberInCart());

        }
        return fee;
    }



}
