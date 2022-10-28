package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddToCart2 extends AppCompatActivity {

    private Button button36;
    private Button button60;

    EditText etname2, etemail2, etdate2, etdays, etdestination;
    Button btnsubmit2;

    DatabaseReference bookingDB2;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart2);

        etname2 = findViewById(R.id.name2);
        etemail2= findViewById(R.id.email2);
        etdate2 = findViewById(R.id.date2);
        etdays = findViewById(R.id.days);
        etdestination = findViewById(R.id.destination1);
        btnsubmit2= findViewById(R.id.submit2);

        bookingDB2 = FirebaseDatabase.getInstance().getReference("GuideBookings");

        btnsubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertGuideBookingData();
            }
        });

        getSupportActionBar().setTitle("Add Guide To Travel Cart");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);


        button36 = findViewById(R.id.button36);
        button36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBookAGuide();
            }
        });

        button60 = findViewById(R.id.button60);
        button60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTravelCart();
            }
        });
    }

    public void openBookAGuide(){
        Intent intent = new Intent(this, BookAGuide.class);
        startActivity(intent);
    }

    public void openTravelCart(){
        Intent intent = new Intent(this, TravelCart.class);
        startActivity(intent);
    }
    private void insertGuideBookingData(){

        String name2 = etname2.getText().toString();
        String email2 = etemail2.getText().toString();
        String date2 = etdate2.getText().toString();
        String day = etdays.getText().toString();
        String dest = etdestination.getText().toString();



        boolean check =validateinfor(name2,email2,date2,day,dest);

        if(check==true){
            Toast.makeText(this,"Added to Travel Cart",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Please the check data again", Toast.LENGTH_SHORT).show();
        }

        String id = bookingDB2.push().getKey();
        GuideBookings guide1 = new GuideBookings(id,name2,email2,date2,day,dest);
        assert  id != null;
        bookingDB2.child(id).setValue(guide1);
        etname2.setText("");
        etemail2.setText("");
        etdate2.setText("");
        etdays.setText("");
        etdestination.setText("");
        //Toast.makeText(this,"Added to Travel Cart",Toast.LENGTH_SHORT).show();


    }
    private Boolean validateinfor(String name2, String email2, String date2, String day, String dest) {

        if(name2.length()==0){
            etname2.requestFocus();
            etname2.setError("Field cannot be empty");
            return false;
        }
        /*else if(!name2.matches("[a-zA-Z]+")){
            etname2.requestFocus();
            etname2.setError("Enter only alphabetical character");
            return false;
        }*/
        else if(email2.length()==0){
            etemail2.requestFocus();
            etemail2.setError("Field cannot be empty");
            return false;
        }
        /*else if(!email2.matches("[a-zA-Z]+@[a-z]+\\.+[a-z]+")){
            etemail2.requestFocus();
            etemail2.setError("Enter valid email");
            return false;
        }*/
        else if(date2.length()==0){
            etdate2.requestFocus();
            etdate2.setError("Field cannot be empty");
        }
        else if(day.length()==0){
            etdays.requestFocus();
            etdays.setError("Field cannot be empty");
        }
        else if(dest.length()==0){
            etdestination.requestFocus();
            etdestination.setError("Field cannot be empty");
        }
        else{
            return true;
        }
        return null;
    }

}