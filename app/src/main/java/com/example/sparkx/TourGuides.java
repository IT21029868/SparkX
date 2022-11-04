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

import java.util.ArrayList;

public class TourGuides extends AppCompatActivity implements GuideIRVadaptor.GuideClickInterface {

    private RecyclerView guidesRv;
    private ProgressBar loading;
    private FloatingActionButton btn;
    private FirebaseDatabase db;
    private DatabaseReference dbref;
    private ArrayList<TourguideRvModel> tourguideRvModels;
    private RelativeLayout bottom;
    private GuideIRVadaptor guideIRVadaptor;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guides);
        auth = FirebaseAuth.getInstance();

        guidesRv = findViewById(R.id.guides);
        loading = findViewById(R.id.loading);
        btn = findViewById(R.id.add);
        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("Guides");
        tourguideRvModels = new ArrayList<>();
        bottom = findViewById(R.id.bottom_tour);
        guideIRVadaptor = new GuideIRVadaptor(tourguideRvModels, this, this);
        guidesRv.setLayoutManager(new LinearLayoutManager(this));
        guidesRv.setAdapter(guideIRVadaptor);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TourGuides.this, AddTourguides.class));


            }
        });

        getAllguides();


    }

    private void getAllguides() {
        tourguideRvModels.clear();

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loading.setVisibility(View.GONE);
                tourguideRvModels.add(snapshot.getValue(TourguideRvModel.class));
                guideIRVadaptor.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loading.setVisibility(View.GONE);
                guideIRVadaptor.notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                loading.setVisibility(View.GONE);
                guideIRVadaptor.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                loading.setVisibility(View.GONE);
                guideIRVadaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

    @Override
    public void onHotelClick(int position) {

        displayBottom(tourguideRvModels.get(position));

    }

    private void displayBottom(TourguideRvModel tourguideRvModel) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottoms_tourguide, bottom);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView name = layout.findViewById(R.id.name);
        TextView phone = layout.findViewById(R.id.phoneNo);
        TextView email = layout.findViewById(R.id.email);


        Button edit = layout.findViewById(R.id.editbtn);


        name.setText(tourguideRvModel.getName());
        phone.setText(tourguideRvModel.getPhoneNo());
        email.setText(tourguideRvModel.getGmail());


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(TourGuides.this, EditTourGuide.class);
                i.putExtra("guide", tourguideRvModel);
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
                Intent i = new Intent(TourGuides.this,LoginAcitivty.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }


    }


}