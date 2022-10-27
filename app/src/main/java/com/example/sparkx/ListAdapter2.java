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

public class ListAdapter2 extends ArrayAdapter {

    private Activity mContext;
    List<GuideBookings> guideBookingsList;


    public ListAdapter2(Activity mContext, List<GuideBookings> guideBookingsList){
       super(mContext,R.layout.list_item2,guideBookingsList);
       this.mContext = mContext;
       this.guideBookingsList= guideBookingsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item2,null,true);

        TextView tvName1 = listItemView.findViewById(R.id.tvName1);
        TextView tvEmail1 = listItemView.findViewById(R.id.tvEmail1);
        TextView tvDate1 = listItemView.findViewById(R.id.tvDate1);
        TextView tvDays1 = listItemView.findViewById(R.id.tvDays1);
        TextView tvDest1= listItemView.findViewById(R.id.tvDate1);

        GuideBookings guides = guideBookingsList.get(position);

        tvName1.setText(guides.getName2());
        tvEmail1.setText(guides.getEmail2());
        tvDate1.setText(guides.getDate2());
        tvDays1.setText(guides.getDay());
        tvDest1.setText(guides.getDest());

        return listItemView;





    }
}
