package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditHotels extends AppCompatActivity {

    private TextInputEditText hotelname,description,price,image;
    private Button editbtn;
    private Button deletebtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private String hotelId;
    private HotelRvModel hotelRvModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        hotelname = findViewById(R.id.hotelname);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        image = findViewById(R.id.image);
        editbtn = findViewById(R.id.updatebtn);
        deletebtn = findViewById(R.id.deletebtn);
        loadingPB = findViewById(R.id.loading);
        db = FirebaseDatabase.getInstance();

        hotelRvModel = getIntent().getParcelableExtra( "hotel");
        if(hotelRvModel!=null){
            hotelname.setText(hotelRvModel.getHotelName());
            description.setText(hotelRvModel.getDescription());
            price.setText(hotelRvModel.getPrice());
            image.setText(hotelRvModel.getImage());
            hotelId = hotelRvModel.getHotelId();


        }

        //dbref = db.getReference().child("Hotels").child(hotelId);

        dbref = db.getReference().child("Hotels").child(hotelId);


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility((View.VISIBLE));

                String hotelName = hotelname.getText().toString();
                String des = description.getText().toString();
                String pri = price.getText().toString();
                String img = image.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("hotelName",hotelName);
                map.put("description",des);
                map.put("price",pri);
                map.put("image",img);
                map.put("hotelId",hotelId);

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        dbref.updateChildren(map);
                        Toast.makeText(EditHotels.this,"Hotel Updated...",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditHotels.this, HotelList.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(EditHotels.this,"Fail to update hotel...",Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });


        deletebtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteHotel();

            }
        }));

    }

    private void deleteHotel(){
        dbref.removeValue();
        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditHotels.this, HotelList.class));



    }
}