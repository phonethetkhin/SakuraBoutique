package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sakuraboutique.Models.UserModel;
import com.example.sakuraboutique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
TextInputEditText tietUserName,tietPassword, tietCpassword,tietEmail,tietDeliveryAddress,tietPhoneNumber;
Button btnSignUp,btnTermsandService,btnPolicy;
Toolbar tbToolbar;
FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    FirebaseAuth firebaseAuth;
    ProgressBar pbProgressBar;

private void InitializeViews()
{
tietUserName=findViewById(R.id.tietUserName);
tietPassword=findViewById(R.id.tietPassword);
tietCpassword=findViewById(R.id.tietCPassword);
tietEmail=findViewById(R.id.tietEmail);
tietDeliveryAddress=findViewById(R.id.tietDeliveryaddress);
tietPhoneNumber=findViewById(R.id.tietPhonenumber);
btnSignUp=findViewById(R.id.btnSignup);
btnTermsandService=findViewById(R.id.btnTermofService);
btnPolicy=findViewById(R.id.btnPrivacyPolicy);
tbToolbar=findViewById(R.id.tbToolbar);
pbProgressBar=findViewById(R.id.pbProgressBar);

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitializeViews();
pbProgressBar.setVisibility(View.GONE);
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Register" + " </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        btnTermsandService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, PrivacyPolicy.class);
                startActivity(i);
            }
        });
        btnPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, PrivacyPolicy.class);
                startActivity(i);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FullName = tietUserName.getText().toString();
                final String Email = tietEmail.getText().toString();
                final String Password = tietPassword.getText().toString();
                String CPassword = tietCpassword.getText().toString();
                final String Address = tietDeliveryAddress.getText().toString();
                final String PhoneNumber = tietPhoneNumber.getText().toString();


                if (TextUtils.isEmpty(FullName)) {
                    Toast.makeText(Register.this, "Please Enter Full Name!", Toast.LENGTH_SHORT).show();
                    tietUserName.setError("Enter Email");
                }
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Register.this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
                    tietEmail.setError("Enter Email");

                }
                if (TextUtils.isEmpty(Password)) {
                    Toast.makeText(Register.this, "Please Enter Password!", Toast.LENGTH_SHORT).show();
                    tietPassword.setError("Enter Email");

                }
                if (TextUtils.isEmpty(CPassword)) {
                    Toast.makeText(Register.this, "Please Confirm Password!", Toast.LENGTH_SHORT).show();
                    tietCpassword.setError("Enter Email");

                }

                if (TextUtils.isEmpty(Address)) {
                    Toast.makeText(Register.this, "Please Enter Address!", Toast.LENGTH_SHORT).show();
                    tietUserName.setError("Enter Email");

                }
                if (TextUtils.isEmpty(tietPhoneNumber.getText().toString())) {
                    Toast.makeText(Register.this, "Please Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    tietPhoneNumber.setError("Enter Email");

                } else if (!Password.equals(CPassword)) {
                    Toast.makeText(Register.this, "Your Passwords Don't Match!", Toast.LENGTH_SHORT).show();
                    tietPassword.setError("Not Match!");
                    tietCpassword.setError("Not Match");

                } else {
                    pbProgressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {


                                        UserModel userModel = new UserModel(
                                                FullName,
                                                Password,
                                                Email,
                                                Address,
                                                PhoneNumber);

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull final Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Register.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                                                    pbProgressBar.setVisibility(View.GONE);
                                                } else {
                                                    task.getException();
                                                }
                                            }
                                        });


                                    }
                                    else
                                    {
                                        Log.d("error", String.valueOf(task.getResult()));
                                        Toast.makeText(Register.this, "Error", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                }
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
