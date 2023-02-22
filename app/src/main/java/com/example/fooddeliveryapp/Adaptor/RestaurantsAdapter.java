package com.example.fooddeliveryapp.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.RestaurantDomain;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    ArrayList<RestaurantDomain> restaurantDomains;


    public RestaurantsAdapter(ArrayList<RestaurantDomain> restaurantDomains){
        this.restaurantDomains = restaurantDomains;
    }



    @NonNull
    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_restaurants,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsAdapter.ViewHolder holder, int position) {

        holder.title.setText(restaurantDomains.get(position).getTitle());
        holder.typeVeg.setText(restaurantDomains.get(position).getTypeVeg());
        holder.distance.setText(restaurantDomains.get(position).getDistance());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(restaurantDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        int drawableResourceId2 = holder.itemView.getContext().getResources().getIdentifier(restaurantDomains.get(position).getVegSquarePic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId2)
                .into(holder.vegSquarePic);
    }

    @Override
    public int getItemCount() {
        return restaurantDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, distance, typeVeg;
        ImageView pic, vegSquarePic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.restoTitle);
            distance = itemView.findViewById(R.id.distance);
            typeVeg = itemView.findViewById(R.id.typeVeg);
            pic = itemView.findViewById(R.id.resto_pic);
            vegSquarePic = itemView.findViewById(R.id.typeVegPIc);

        }
    }

}


