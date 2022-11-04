package com.example.sparkx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelRVadaptor extends RecyclerView.Adapter<HotelRVadaptor.ViewHolder> {
    int lastPos = -1;
    private ArrayList<HotelRvModel> hotelRvModelsarr;
    private Context context;
    private HotelClickInterface hotelClickInterface;

    public HotelRVadaptor(ArrayList<HotelRvModel> hotelRvModelsarr, Context context, HotelClickInterface hotelClickInterface) {
        this.hotelRvModelsarr = hotelRvModelsarr;
        this.context = context;
        this.hotelClickInterface = hotelClickInterface;


    }

    @NonNull
    @Override
    public HotelRVadaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.hotel_rv_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelRVadaptor.ViewHolder holder, int position) {
        HotelRvModel hotelRvModel = hotelRvModelsarr.get(position);
        holder.hotelName.setText(hotelRvModel.getHotelName());
        holder.price.setText(hotelRvModel.getPrice());
        Picasso.get().load(hotelRvModel.getImage()).into(holder.hotelIv);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotelClickInterface.onHotelClick(holder.getAdapterPosition());
            }
        });


    }

    private void setAnimation(View itemView,int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return hotelRvModelsarr.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView hotelName,price;
        private ImageView hotelIv;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            hotelName = itemView.findViewById(R.id.hotelname);
            price = itemView.findViewById(R.id.price);
            hotelIv = itemView.findViewById(R.id.Iview);
        }
    }
    public interface HotelClickInterface{
        void onHotelClick(int position);
    }

}
