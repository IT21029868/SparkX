package com.example.sparkx;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GuideIRVadaptor extends RecyclerView.Adapter<GuideIRVadaptor.ViewHolder>{
    int lastPos = -1;
    private ArrayList<TourguideRvModel> tourguideRvModelarr;
    private Context context;
    private GuideClickInterface guideClickInterface;

    public GuideIRVadaptor(ArrayList<TourguideRvModel> tourguideRvModelarr, Context context, GuideClickInterface guideClickInterface) {
        this.tourguideRvModelarr = tourguideRvModelarr;
        this.context = context;
        this.guideClickInterface = guideClickInterface;
    }

    @NonNull
    @Override
    public GuideIRVadaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.guide_rv_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideIRVadaptor.ViewHolder holder, int position) {
        TourguideRvModel tourguideRvModel = tourguideRvModelarr.get(position);
        holder.guideName.setText(tourguideRvModel.getName());
        holder.id.setText(tourguideRvModel.getCid());
        holder.phoneNo.setText(tourguideRvModel.getPhoneNo());
        holder.gmail.setText(tourguideRvModel.getGmail());

        //Picasso.get().load(tourguideRvModel.getImage()).into(holder.hotelIv);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideClickInterface.onHotelClick(holder.getAdapterPosition());
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
        return tourguideRvModelarr.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView guideName,id,phoneNo,gmail;
        //private ImageView hotelIv;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            guideName = itemView.findViewById(R.id.guidename);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            id = itemView.findViewById(R.id.id);
            gmail = itemView.findViewById(R.id.gmail);

        }
    }





    public interface GuideClickInterface{
        void onHotelClick(int position);
    }


}
