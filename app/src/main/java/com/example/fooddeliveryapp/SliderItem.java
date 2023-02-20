package com.example.fooddeliveryapp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

public class SliderItem {
    class SliderViewHolder extends RecyclerView.ViewHolder{

        public RoundedImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlider);

        }
    }
}
