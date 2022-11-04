package com.example.sparkx;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText username,psw,conpwd;
    private Button regbtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username = findViewById(R.id.usernameedit);
        psw = findViewById(R.id.passwrodedit);
        conpwd = findViewById(R.id.conpassedit);
        regbtn = findViewById(R.id.regbutton);
        loadingPB=findViewById(R.id.loading);
        loginTV = findViewById(R.id.mssg);
        auth = FirebaseAuth.getInstance();
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegistrationActivity.this,LoginAcitivty.class);
                startActivity(i);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String userName = username.getText().toString();
                String pwd = psw.getText().toString();
                String cnpwd = conpwd.getText().toString();

                if(!pwd.equals(cnpwd)) {

                    Toast.makeText(RegistrationActivity.this,"Please check both password again",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(cnpwd)){
                    Toast.makeText(RegistrationActivity.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                    return;

                }

                auth.createUserWithEmailAndPassword(userName,pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegistrationActivity.this,"User Registered",Toast.LENGTH_SHORT).show();
                                    loadingPB.setVisibility(View.GONE);
                                    Intent i = new Intent(RegistrationActivity.this, AdminMenu.class);
                                    startActivity(i);


                                }
                                else{

                                    Toast.makeText(RegistrationActivity.this,"Failed to register",Toast.LENGTH_SHORT).show();
                                    loadingPB.setVisibility(View.GONE);


                                }
                            }
                        });



            }





        });


    }
}