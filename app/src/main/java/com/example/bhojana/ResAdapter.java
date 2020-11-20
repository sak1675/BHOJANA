package com.example.bhojana;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.Viewholder> {


    Context context;
    ArrayList<Restaurant> restaurant;
    public ResAdapter(Context c, ArrayList<Restaurant> list){
        context = c;
        restaurant = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.restaurantdetail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int i) {
        holder.restaurant_name.setText(restaurant.get(i).getRestaurant_name());
        holder.restaurant_address.setText(restaurant.get(i).getRestaurant_address());
        holder.restaurant_type.setText(restaurant.get(i).getRestaurant_type());
        holder.restaurant_rating.setText(restaurant.get(i).getRestaurant_rating());
        holder.restaurant_time.setText(restaurant.get(i).getRestaurant_time());
        holder.restaurant_service.setText(restaurant.get(i).getRestaurant_service());

        final String getRestaurant_name = restaurant.get(i).getRestaurant_name();



        holder.itemView.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(context,BookingActivity.class);
                        intent.putExtra("task", getRestaurant_name);
                        context.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return restaurant.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView restaurant_name,restaurant_address, restaurant_type, restaurant_rating, restaurant_time, restaurant_service;
        public Viewholder( View itemView) {

            super(itemView);
            restaurant_name = (TextView) itemView.findViewById(R.id.restaurant_name);
            restaurant_address = (TextView) itemView.findViewById(R.id.restaurant_address);
            restaurant_type = (TextView) itemView.findViewById(R.id.restaurant_type);
            restaurant_rating = (TextView) itemView.findViewById(R.id.restaurant_rating);
            restaurant_time = (TextView) itemView.findViewById(R.id.restaurant_time);
            restaurant_service = (TextView) itemView.findViewById(R.id.restaurant_service);

        }
    }

}
