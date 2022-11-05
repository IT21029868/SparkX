package com.example.sparkx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sparkx.R;

public class MainActivity extends AppCompatActivity {
    Button btn_signup,btn_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateObjects();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void onCreateObjects()
    {
        btn_signup= findViewById(R.id.btn_signUp);
        btn_signin=findViewById(R.id.btn_signIn);

    }
}