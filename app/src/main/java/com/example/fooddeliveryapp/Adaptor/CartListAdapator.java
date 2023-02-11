package com.example.fooddeliveryapp.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.CartList;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemListener;
import com.example.fooddeliveryapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartListAdapator extends RecyclerView.Adapter<CartListAdapator.ViewHolder> {

    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartListAdapator(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemListener changeNumberItemListener){
        this.changeNumberItemListener = changeNumberItemListener;
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapator.ViewHolder holder, int position) {
        holder.titleTxt.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));

        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(holder.getAdapterPosition()).getPic(), "drawable", holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.picCart);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(foodDomains, holder.getAdapterPosition(), new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(foodDomains, holder.getAdapterPosition(), new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();

    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt,feeEachItem;
        ImageView picCart;
        TextView plusItem, minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt= itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            picCart  = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusItem);
            minusItem = itemView.findViewById(R.id.minusItem);
            totalEachItem = itemView.findViewById(R.id.totalFeeEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
