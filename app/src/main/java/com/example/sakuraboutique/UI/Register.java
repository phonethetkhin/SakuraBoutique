package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sakuraboutique.R;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
TextInputEditText tietUserName,tietPassword,tietEmail,tietDeliveryAddress,tietPhoneNumber;
Button btnSignUp,btnTermsandService,btnPolicy;
Toolbar tbToolbar;

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
tbToolbar=findViewById(R.id.tbToolbar);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitializeViews();

        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Register" + " </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

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
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Register.this,ComfirmOrder.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId())
    {
        case android.R.id.home:
            this.finish();
            break;
    }
        return super.onOptionsItemSelected(item);
    }
}
