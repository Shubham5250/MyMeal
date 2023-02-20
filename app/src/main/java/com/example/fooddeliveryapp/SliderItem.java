package com.example.fooddeliveryapp;

import android.media.Image;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

public class SliderItem {

    private int image;

    SliderItem(int image){
        this.image = image;
    }

    public int getImage(){
        return image;
    }
}
