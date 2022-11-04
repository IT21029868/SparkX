package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginAcitivty extends AppCompatActivity {

    private TextInputEditText username,psw;
    private Button regbtn;
    private ProgressBar loadingPB;
    private TextView regTV;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivty);

        username = findViewById(R.id.usernameedit);
        psw = findViewById(R.id.passwrodedit);
        regbtn = findViewById(R.id.regbutton);
        loadingPB=findViewById(R.id.loading);
        regTV = findViewById(R.id.mssg);
        auth = FirebaseAuth.getInstance();

        regTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginAcitivty.this,RegistrationActivity.class);
                startActivity(i);
            }
        });

        regbtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String pwd = psw.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginAcitivty.this,"Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    auth.signInWithEmailAndPassword(userName,pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        loadingPB.setVisibility(View.GONE);
                                        Toast.makeText(LoginAcitivty.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(LoginAcitivty.this, AdminMenu.class);
                                        startActivity(i);

                                    }else{
                                        loadingPB.setVisibility((View.GONE));
                                        Toast.makeText(LoginAcitivty.this,"Fail to login",Toast.LENGTH_SHORT).show();
                                    }

                                }

                            });

                }






            }
        }));





    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null){
            Intent i = new Intent(LoginAcitivty.this, HotelList.class);
            startActivity(i);
            this.finish();
        }

    }
}