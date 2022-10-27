package com.example.sparkx;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<HotelBookings> hotel1List;


    public ListAdapter(Activity mContext, List<HotelBookings> hotel1List){

        super(mContext, R.layout.list_item1,hotel1List);
        this.mContext= mContext;
        this.hotel1List= hotel1List;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item1,null,true);

        TextView tvName= listItemView.findViewById(R.id.tvName);
        TextView tvEmail= listItemView.findViewById(R.id.tvEmail);
        TextView tvPhone= listItemView.findViewById(R.id.tvPhone);
        TextView tvCheckin= listItemView.findViewById(R.id.tvCheckin);
        TextView tvnight= listItemView.findViewById(R.id.tvnight);
        TextView tvcount= listItemView.findViewById(R.id.tvcount);
        TextView tvChef= listItemView.findViewById(R.id.tvChef);

        HotelBookings hotels = hotel1List.get(position);

        tvName.setText(hotels.getName());
        tvEmail.setText(hotels.getEmail());
        tvPhone.setText(hotels.getPhone());
        tvCheckin.setText(hotels.getDate());
        tvnight.setText(hotels.getNight());
        tvcount.setText(hotels.getCount());
        tvChef.setText(hotels.getChef());



       return listItemView;
       // return super.getView(position, convertView, parent);
    }
}
