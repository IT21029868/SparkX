package com.example.sparkx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sparkx.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    EditText txt_password;
    EditText txt_phone;
    Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        onCreateObjects();
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Phone = txt_phone.getText().toString();
                String Password = txt_password.getText().toString();
                System.out.println("Phone"+Phone);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(Phone);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("Snapshot"+snapshot);
                        if (snapshot.hasChildren()) {
                            // String PhoneNumber = snapshot.child("contact").getValue().toString();
                            // String email = snapshot.child("email").getValue().toString();
                            String fullName = snapshot.child("fullName").getValue().toString();
                            String password = snapshot.child("password").getValue().toString();
                            String userType = snapshot.child("userType").getValue().toString();

                            if(password.equals(Password))
                            {

                                if(userType.equals("Customer"))
                                {
                                    Intent intent =new Intent(getApplicationContext(),UserDashboardActivity.class);
                                    startActivity(intent);

                                }
                                else if(userType.equals("Guide"))
                                {
                                    Intent intent =new Intent(getApplicationContext(),UserDashboardActivity.class);
                                    startActivity(intent);
                                }
                                else if(userType.equals("Admin"))
                                {
                                    Intent intent =new Intent(getApplicationContext(),AdminDashBoardActivity.class);
                                    startActivity(intent);
                                }
                            }
                            else
                            {
                                Toast.makeText(SignInActivity.this, "Password  Incorrect !!!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignInActivity.this, "Phone Number  Incorrect !!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    private void onCreateObjects() {
        txt_password = findViewById(R.id.txt_password);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        btn_signIn = findViewById(R.id.btn_signIn);
    }
}