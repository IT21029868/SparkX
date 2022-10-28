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

    private Button button50;

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


        button50 = findViewById(R.id.button50);
        button50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindAStay();
            }
        });

        button34 = findViewById(R.id.button34);
        button34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTravelCart();
            }
        });
    }
    public void openFindAStay(){
        Intent intent = new Intent(this, FindAStay.class);
        startActivity(intent);
    }
    public void openTravelCart(){
        Intent intent = new Intent(this, TravelCart.class);
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

        String id = bookingDB.push().getKey();

        boolean check =validateinfor(name,email,phone,date,night,count,chef);

        if(check==true){
            Toast.makeText(this,"Added to Travel Cart",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Please the check data again", Toast.LENGTH_SHORT).show();
        }
        HotelBookings hotel1 = new HotelBookings(id,name,email,phone,date,night,count,chef);
        assert id != null;
        bookingDB.child(id).setValue(hotel1);
        etname.setText("");
        etemail.setText("");
        etphone.setText("");
        etdate.setText("");
        etnight.setText("");
        etcount.setText("");
        etchef.setText("");
        //Toast.makeText(this,"Added to Travel Cart",Toast.LENGTH_SHORT).show();


    }

    private Boolean validateinfor(String name, String email, String phone, String date, String night, String count, String chef) {

        if(name.length()==0){
            etname.requestFocus();
            etname.setError("Field cannot be empty");
            return false;
        }
        /*else if(!name.matches("[a-zA-Z]+")){
            etname.requestFocus();
            etname.setError("Enter only alphabetical character");
            return false;
        }*/
        else if(email.length()==0){
            etemail.requestFocus();
            etemail.setError("Field cannot be empty");
            return false;
        }
       /* else if(!email.matches("[a-zA-Z]+@[a-z]+\\.+[a-z]+")){
            etemail.requestFocus();
            etemail.setError("Enter valid email");
            return false;
        }*/
        else if(phone.length()==0){
            etphone.requestFocus();
            etphone.setError("Field cannot be empty");
        }
        /*else if(!phone.matches("^[+][0-9]{10,13}$")){
            etphone.requestFocus();
            etphone.setError("Correct Format: +94xxxxxxxxx");
            return false;
        }*/
        else if(date.length()==0){
            etdate.requestFocus();
            etdate.setError("Field cannot be empty");
        }
        else if(night.length()==0){
            etnight.requestFocus();
            etnight.setError("Field cannot be empty");
        }
        else if(count.length()==0){
            etcount.requestFocus();
            etcount.setError("Field cannot be empty");
        }
        else if(chef.length()==0){
            etchef.requestFocus();
            etchef.setError("Field cannot be empty");
        }
        else{
            return true;
        }
        return null;
    }

}