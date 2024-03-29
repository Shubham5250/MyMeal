package com.example.fooddeliveryapp.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {

    // ArrayList => Dynamic Array
    ArrayList<CategoryDomain>categoryDomains;


    // ===== CategoryAdaptor Constructor =====
    public CategoryAdaptor(ArrayList<CategoryDomain> categoryDomains){
        this.categoryDomains = categoryDomains;
    }


    // ====== onCreateViewHolder only creates a new view holder when there are no existing view holders which the RecyclerView can reuse. =====
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // ==== Inflate layout viewHolder_category to parent ====
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        // ==== The first argument takes the layout file which is needed to be inflated. The second argument is the root of this newly inflated layout.====


        // ==== ViewHolder ==> ViewHolder is a type of helper class that allows us to draw the UI for individual items on the screen. =====
        return new ViewHolder(inflate);
    }


    // ===== This method should update the contents of the itemView to reflect the item at the given position. ======
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holdr, int position) {

        holdr.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl = "pizza_clipart";
                holdr.mainLayout.setBackground(ContextCompat.getDrawable(holdr.itemView.getContext(),R.drawable.cat_background1));

                break;
            }
            case 1:{
                picUrl = "veg_meal";
                holdr.mainLayout.setBackground(ContextCompat.getDrawable(holdr.itemView.getContext(),R.drawable.cat_background4));
                break;

            }
            case 2:{
                picUrl = "soft_drinks";
                holdr.mainLayout.setBackground(ContextCompat.getDrawable(holdr.itemView.getContext(),R.drawable.cat_background3));
                break;

            }
            case 3:{
                picUrl = "panveggies";
                holdr.mainLayout.setBackground(ContextCompat.getDrawable(holdr.itemView.getContext(),R.drawable.cat_background2));
                break;

            }
            case 4:{
                picUrl = "donut";
                holdr.mainLayout.setBackground(ContextCompat.getDrawable(holdr.itemView.getContext(),R.drawable.cat_background5));
                break;

            }
            case 5:{
                picUrl = "burger_clipart";
                holdr.mainLayout.setBackground(ContextCompat.getDrawable(holdr.itemView.getContext(),R.drawable.cat_background6));
                break;

            }
        }

        int drawableResourceId = holdr.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holdr.itemView.getContext().getPackageName());

        // ==== Glide is an Image Loader Library for Android developed by bumptech and is a library that is recommended by Google. ====
        Glide.with(holdr.itemView.getContext())
                .load(drawableResourceId)
                .into(holdr.categoryPic);

    }


    // ==== get the itemcount of categoryDomain ArrayList ====
    @Override
    public int getItemCount() {

        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
