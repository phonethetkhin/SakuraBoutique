package com.example.sakuraboutique.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sakuraboutique.R;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
TextInputEditText tietUserName,tietPassword,tietEmail,tietDeliveryAddress,tietPhoneNumber;
Button btnSignUp,btnTermsandService,btnPolicy;

private void InitializeViews()
{
tietUserName=findViewById(R.id.tietUserName);
tietPassword=findViewById(R.id.tietPassword);
tietEmail=findViewById(R.id.tietEmail);
tietDeliveryAddress=findViewById(R.id.tietDeliveryaddress);
tietPhoneNumber=findViewById(R.id.tietPhonenumber);
btnSignUp=findViewById(R.id.btnSignup);
btnTermsandService=findViewById(R.id.btnTermofService);
btnPolicy=findViewById(R.id.btnPrivacyPolicy);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitializeViews();

        btnTermsandService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Register.this,PrivacyPolicy.class);
                startActivity(i);
            }
        });
        btnPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Register.this,PrivacyPolicy.class);
                startActivity(i);
            }
        });

    }
}
