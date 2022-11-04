package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelList extends AppCompatActivity implements HotelRVadaptor.HotelClickInterface{

    private RecyclerView hotelRv;
    private ProgressBar loading;
    private FloatingActionButton btn;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private ArrayList<HotelRvModel> hotelRvModelsarr;
    private RelativeLayout bottomRL;
    private HotelRVadaptor hotelRVadaptor;

    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hotelRv = findViewById(R.id.hotels);
        loading = findViewById(R.id.loading);
        btn = findViewById(R.id.add);
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("Hotels");
        hotelRvModelsarr = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        bottomRL = findViewById(R.id.bottomSheet);
        hotelRVadaptor = new HotelRVadaptor(hotelRvModelsarr,this,  this);
        hotelRv.setLayoutManager(new LinearLayoutManager(this));
        hotelRv.setAdapter(hotelRVadaptor);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HotelList.this,Add_hotels.class));
            }


        });

        getAllhotels();





    }

    private  void getAllhotels(){
        hotelRvModelsarr.clear();

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loading.setVisibility(View.GONE);
                hotelRvModelsarr.add(snapshot.getValue(HotelRvModel.class));
                hotelRVadaptor.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loading.setVisibility(View.GONE);
                hotelRVadaptor.notifyDataSetChanged();



            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                loading.setVisibility(View.GONE);
                hotelRVadaptor.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loading.setVisibility(View.GONE);
                hotelRVadaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
    public void onHotelClick(int position){
        displayBottom(hotelRvModelsarr.get(position));
    }

    private  void displayBottom(HotelRvModel hotelRvModel){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet,bottomRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView hotelname = layout.findViewById(R.id.name);
        TextView description = layout.findViewById(R.id.description);
        TextView price = layout.findViewById(R.id.price);
        ImageView image = layout.findViewById(R.id.image);

        Button edit = layout.findViewById(R.id.edit);


        hotelname.setText(hotelRvModel.getHotelName());
        description.setText(hotelRvModel.getDescription());
        price.setText(hotelRvModel.getPrice());
        Picasso.get().load(hotelRvModel.getImage()).into(image);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(HotelList.this, EditHotels.class);
                i.putExtra("hotel",hotelRvModel);
                startActivity(i);

            }



        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.logout:
                Toast.makeText(this,"User logged out",Toast.LENGTH_SHORT).show();
                auth.signOut();
                Intent i = new Intent(HotelList.this,LoginAcitivty.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }


    }


}