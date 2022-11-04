package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;

public class ViewStays extends AppCompatActivity {


    ListView myListview1;
    List<HotelBookings> hotelBookingsList;

    DatabaseReference hoteldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stays);


        myListview1 = findViewById(R.id.myListView1);
        hotelBookingsList = new ArrayList<>();

        hoteldb = FirebaseDatabase.getInstance().getReference("HotelBookings");

        hoteldb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotelBookingsList.clear();

                for (DataSnapshot hotelDatasnap : dataSnapshot.getChildren()) {

                    HotelBookings hotels = hotelDatasnap.getValue(HotelBookings.class);
                    hotelBookingsList.add(hotels);
                }

                ListAdapter adapter = new ListAdapter(ViewStays.this, hotelBookingsList);
                myListview1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getSupportActionBar().setTitle("View Guides");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));

        //set itemLong Listener on listview item

        myListview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                HotelBookings hotels = hotelBookingsList.get(position);
                showUpdateDialog(hotels.getId(),hotels.getName());

                return false;
            }
        });
    }

    private void showUpdateDialog(final String id,String name){

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog1,null);

        mDialog.setView(mDialogView);

       final EditText updatename1 = mDialogView.findViewById(R.id.updatename1);
        final EditText updateemail1 = mDialogView.findViewById(R.id.updateemail1);
        final EditText updatephone1 = mDialogView.findViewById(R.id.updatephone1);
        final EditText updatedate1 = mDialogView.findViewById(R.id.updatedate1);
        final EditText updatenights1 = mDialogView.findViewById(R.id.updatenights1);
        final EditText updatepeople1 = mDialogView.findViewById(R.id.updatepeople1);
        final EditText updatechef1 = mDialogView.findViewById(R.id.updatechef1);

        Button update1 = mDialogView.findViewById(R.id.update1);
        Button delete1 = mDialogView.findViewById(R.id.delete1);

        mDialog.setTitle("Updating " + name +" record");
        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newName = updatename1.getText().toString();
                String newEmail = updateemail1.getText().toString();
                String newPhone = updatephone1.getText().toString();
                String newDate = updatedate1.getText().toString();
                String newNight = updatenights1.getText().toString();
                String newCount = updatepeople1.getText().toString();
                String newChef = updatechef1.getText().toString();

                updateData(id,newName,newEmail,newPhone,newDate,newNight,newCount,newChef);

                Toast.makeText(ViewStays.this,"Record Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord(id);
                alertDialog.dismiss();
            }
        });

    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String id){
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("HotelBookings").child(id);

        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting record");
            }
        });

        }
  private void updateData(String id, String name, String email, String phone, String date,
                          String night, String count, String chef){


        DatabaseReference bdref = FirebaseDatabase.getInstance().getReference("HotelBookings").child(id);
        HotelBookings hotels = new HotelBookings(id,name,email,phone,date,night,count,chef);
        bdref.setValue(hotels);


  }
}