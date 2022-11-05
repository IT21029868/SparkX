package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sparkx.Models.UserModel;
import com.example.sparkx.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    Button btn_signup;
    EditText txt_fullname, txt_email, txt_contact, txt_password;
    Spinner spinner_type;
    String selectedItem;
    String userType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        onCreateObjects();

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = spinner_type.getSelectedItem().toString();
                if (selectedItem.equals("Customer")) {
                    userType = "Customer";
                } else if (selectedItem.equals("Guide")) {
                    userType = "Guide";
                } else {
                    Toast.makeText(SignUpActivity.this, "Please Select Valid Type !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSignUp();
            }
        });
    }

    private void onCreateObjects() {

        txt_fullname = findViewById(R.id.txt_fullname);
        txt_email = findViewById(R.id.txt_email);
        txt_contact = findViewById(R.id.txt_contact);
        txt_password = findViewById(R.id.txt_password);
        spinner_type = findViewById(R.id.spinner_type);
        btn_signup = findViewById(R.id.btm_signup);
    }
    private void performSignUp()
    {
        String Email=txt_email.getText().toString();
        String FullName=txt_fullname.getText().toString();
        String Contact=txt_contact.getText().toString();
        String Password=txt_password.getText().toString();
        if(FullName.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Full Name ",Toast.LENGTH_LONG);
        }
        else if(Email.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_LONG);
        }
        else if(Contact.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Contact Number ",Toast.LENGTH_LONG);
        }
        else if(Password.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Password ",Toast.LENGTH_LONG);
        }
        else if(userType.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Full Name ",Toast.LENGTH_LONG);
        }
        else
        {
            DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child("users").child(Contact);
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChildren())
                    {
                        Toast.makeText(SignUpActivity.this, "This Phone Number Already Exists!!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        FirebaseDatabase rootNode;
                        DatabaseReference reference;
                        rootNode=FirebaseDatabase.getInstance();
                        reference=rootNode.getReference("users");
                        UserModel user=new UserModel(FullName,Contact,Password,Email,userType);
                        reference.child(Contact).setValue(user);
                        Toast.makeText(SignUpActivity.this, "Successfully Registered !!", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



    }
}
