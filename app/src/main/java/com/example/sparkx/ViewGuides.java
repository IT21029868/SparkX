package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class ViewGuides extends AppCompatActivity {

    ListView myListview2;
    List<GuideBookings> guideBookingsList;

    DatabaseReference guidebookingdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_guides);

        getSupportActionBar().setTitle("View Stays");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar)));


        myListview2 = findViewById(R.id.myListView2);
        guideBookingsList = new ArrayList<>();

        guidebookingdb = FirebaseDatabase.getInstance().getReference("GuideBookings");

        guidebookingdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                guideBookingsList.clear();

                for(DataSnapshot guideDatasnap : dataSnapshot.getChildren()){

                    GuideBookings guides = guideDatasnap.getValue(GuideBookings.class);

                    guideBookingsList.add(guides);
                }

                ListAdapter2 adapter = new ListAdapter2(ViewGuides.this,guideBookingsList);
                myListview2.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myListview2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GuideBookings guides = guideBookingsList.get(position);
                showUpdateDialog(guides.getId(),guides.getName2());

                return false;
            }
        });
    }

    private void showUpdateDialog(final String id,String name2) {


        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog2, null);

        mDialog.setView(mDialogView);

        final EditText etUpdateName = mDialogView.findViewById(R.id.updatename2);
        final EditText etUpdateEmail = mDialogView.findViewById(R.id.updateemail2);
        final EditText etUpdatedate = mDialogView.findViewById(R.id.updatdate2);
        final EditText etUpdateDays = mDialogView.findViewById(R.id.updatedays1);
        final EditText etUpdateDests = mDialogView.findViewById(R.id.updatedest1);
        Button btnUpdate = mDialogView.findViewById(R.id.update2);
        Button btnDelete = mDialogView.findViewById(R.id.delete2);


        mDialog.setTitle("Updating " + name2 + " record");

        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newName = etUpdateName.getText().toString();
                String newEmail = etUpdateEmail.getText().toString();
                String newDate = etUpdatedate.getText().toString();
                String newDays = etUpdateDays.getText().toString();
                String newdests = etUpdateDests.getText().toString();


                updateData(id, newName, newEmail, newDate, newDays, newdests);

                Toast.makeText(ViewGuides.this, "Record Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(id);

                alertDialog.dismiss();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String id){
        //create reference to database
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("GuideBookings").child(id);
        //we referencing child here because we will be delete one record not whole data data in database
        //we will use generic Task here so lets do it..

        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void avoid) {
                showToast("Deleted");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting record");
            }
        });
    }

    private void updateData(String id, String name2, String email2, String date2, String day,String dest){

        //creating database reference
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("GuideBookings").child(id);
        GuideBookings guides = new GuideBookings(id, name2,email2,date2,day,dest);
        DbRef.setValue(guides);
    }

}
