package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToCart extends AppCompatActivity {

    EditText etname, etemail, etphone, etdate, etnight, etcount, etchef;
    Button btnsubmit;

    DatabaseReference bookingDB;


    private Button button34;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        etname = findViewById(R.id.name1);
        etemail = findViewById(R.id.email1);
        etphone = findViewById(R.id.phone1);
        etdate = findViewById(R.id.date1);
        etnight = findViewById(R.id.nights1);
        etcount = findViewById(R.id.people1);
        etchef = findViewById(R.id.chef1);
        btnsubmit= findViewById(R.id.submit1);

        bookingDB = FirebaseDatabase.getInstance().getReference().child("HotelBookings");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHotelBookingData();
            }
        });




        getSupportActionBar().setTitle("Add To Cart");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        button34 = findViewById(R.id.button34);
        button34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindAStay();
            }
        });
    }
    public void openFindAStay(){
        Intent intent = new Intent(this, FindAStay.class);
        startActivity(intent);
    }

    private void insertHotelBookingData(){

        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String date = etdate.getText().toString();
        String night = etnight.getText().toString();
        String count = etcount.getText().toString();
        String chef = etchef.getText().toString();

        HotelBookings hotel1 = new HotelBookings(name,email,phone,
                date,night,count,chef);

        bookingDB.push().setValue(hotel1);
        etname.setText("");
        etemail.setText("");
        etphone.setText("");
        etdate.setText("");
        etnight.setText("");
        etcount.setText("");
        etchef.setText("");
        Toast.makeText(this,"Added to Travel Cart",Toast.LENGTH_SHORT).show();


    }

}