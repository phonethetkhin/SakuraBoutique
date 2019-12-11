package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakuraboutique.Models.UserModel;
import com.example.sakuraboutique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class Register extends AppCompatActivity {
TextInputEditText tietUserName,tietPassword, tietCpassword,tietEmail,tietDeliveryAddress,tietPhoneNumber;
Button btnSignUp,btnTermsandService,btnPolicy;
Toolbar tbToolbar;
ImageView imgUserName,imgEmail,imgPassword,imgCPassword,imgDeliveryaddress,imgPhonenumber;
CircleImageView cimgLogo;
FirebaseDatabase firebaseDatabase;
TextView tvtermslabel,tvPolicyalbel;
TextInputLayout textInputLayout7,textInputLayout8;
    DatabaseReference myRef;
    FirebaseAuth firebaseAuth;
    GifImageView gifNoInternet;
    ProgressBar pbProgress;
    SwipeRefreshLayout srflRefresh;

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
cimgLogo=findViewById(R.id.cimgLogo);
    imgUserName=findViewById(R.id.imgUserName);
    imgEmail=findViewById(R.id.imgEmail);
    imgPassword=findViewById(R.id.imgPassword);
    imgCPassword=findViewById(R.id.imgCPassword);
    imgDeliveryaddress=findViewById(R.id.imgDeliveryaddress);
    imgPhonenumber=findViewById(R.id.imgPhonenumber);
    tvtermslabel=findViewById(R.id.tvtermslabel);
    tvPolicyalbel=findViewById(R.id.tvPolicyalbel);
    textInputLayout7=findViewById(R.id.textInputLayout7);
    textInputLayout8=findViewById(R.id.textInputLayout8);
    gifNoInternet=findViewById(R.id.gifNoInternet);
    pbProgress=findViewById(R.id.pbProgress);
    srflRefresh=findViewById(R.id.srflRefresh);


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

        MainFunction();
        srflRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srflRefresh.setRefreshing(true);
                MainFunction();
                srflRefresh.setRefreshing(false);
            }
        });    }
    public void MainFunction()
    {

        if(Network()) {
            gifNoInternet.setVisibility(View.GONE);
            pbProgress.setVisibility(View.GONE);
            tietUserName.setVisibility(View.VISIBLE);
            tietPassword.setVisibility(View.VISIBLE);
            tietCpassword.setVisibility(View.VISIBLE);
            tietEmail.setVisibility(View.VISIBLE);
            tietDeliveryAddress.setVisibility(View.VISIBLE);
            tietPhoneNumber.setVisibility(View.VISIBLE);
            btnSignUp.setVisibility(View.VISIBLE);
            btnTermsandService.setVisibility(View.VISIBLE);
            btnPolicy.setVisibility(View.VISIBLE);
            cimgLogo.setVisibility(View.VISIBLE);
            imgUserName.setVisibility(View.VISIBLE);
            imgEmail.setVisibility(View.VISIBLE);
            imgPassword.setVisibility(View.VISIBLE);
            imgCPassword.setVisibility(View.VISIBLE);
            imgDeliveryaddress.setVisibility(View.VISIBLE);
            imgPhonenumber.setVisibility(View.VISIBLE);
            tvtermslabel.setVisibility(View.VISIBLE);
            tvPolicyalbel.setVisibility(View.VISIBLE);
            textInputLayout7.setVisibility(View.VISIBLE);
            textInputLayout8.setVisibility(View.VISIBLE);
            firebaseAuth = FirebaseAuth.getInstance();


            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Network())
                    {
                        Toast.makeText(Register.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
                        gifNoInternet.setVisibility(View.VISIBLE);
                        pbProgress.setVisibility(View.VISIBLE);
                        tietUserName.setVisibility(View.GONE);
                        tietPassword.setVisibility(View.GONE);
                        tietCpassword.setVisibility(View.GONE);
                        tietEmail.setVisibility(View.GONE);
                        tietDeliveryAddress.setVisibility(View.GONE);
                        tietPhoneNumber.setVisibility(View.GONE);
                        btnSignUp.setVisibility(View.GONE);
                        btnTermsandService.setVisibility(View.GONE);
                        btnPolicy.setVisibility(View.GONE);
                        cimgLogo.setVisibility(View.GONE);
                        imgUserName.setVisibility(View.GONE);
                        imgEmail.setVisibility(View.GONE);
                        imgPassword.setVisibility(View.GONE);
                        imgCPassword.setVisibility(View.GONE);
                        imgDeliveryaddress.setVisibility(View.GONE);
                        imgPhonenumber.setVisibility(View.GONE);
                        tvtermslabel.setVisibility(View.GONE);
                        tvPolicyalbel.setVisibility(View.GONE);
                        textInputLayout7.setVisibility(View.GONE);
                        textInputLayout8.setVisibility(View.GONE);
                    }
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
                        final SweetAlertDialog pDialog = new SweetAlertDialog(Register.this, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("Loading");
                        pDialog.setCancelable(false);
                        pDialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
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
                                                        pDialog.setTitleText("Register Successfully!").changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                        Toast.makeText(Register.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                                                        Intent i = new Intent(Register.this, ComfirmOrder.class);
                                                        startActivity(i);
                                                    } else {
                                                        task.getException();
                                                    }
                                                }
                                            });


                                        } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            pDialog.setTitleText("Email Exist!").changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                            Toast.makeText(Register.this, "This Email is Already Registered!", Toast.LENGTH_SHORT).show();
                                        } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                            pDialog.setTitleText("Weak Password!").changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                            Toast.makeText(Register.this, "Password Too Weak!", Toast.LENGTH_SHORT).show();
                                        } else {

                                            pDialog.setTitleText("").setContentText("Error!").changeAlertType(SweetAlertDialog.ERROR_TYPE);


                                            try {
                                                throw task.getException();
                                            } catch (FirebaseException e) {
                                                e.printStackTrace();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }


                                    }
                                });

                    }
                }
            });
        }
        else
        {
            Toast.makeText(Register.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();

            gifNoInternet.setVisibility(View.VISIBLE);
            pbProgress.setVisibility(View.VISIBLE);
            tietUserName.setVisibility(View.GONE);
            tietPassword.setVisibility(View.GONE);
            tietCpassword.setVisibility(View.GONE);
            tietEmail.setVisibility(View.GONE);
            tietDeliveryAddress.setVisibility(View.GONE);
            tietPhoneNumber.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);
            btnTermsandService.setVisibility(View.GONE);
            btnPolicy.setVisibility(View.GONE);
            cimgLogo.setVisibility(View.GONE);
            imgUserName.setVisibility(View.GONE);
            imgEmail.setVisibility(View.GONE);
            imgPassword.setVisibility(View.GONE);
            imgCPassword.setVisibility(View.GONE);
            imgDeliveryaddress.setVisibility(View.GONE);
            imgPhonenumber.setVisibility(View.GONE);
            tvtermslabel.setVisibility(View.GONE);
            tvPolicyalbel.setVisibility(View.GONE);
            textInputLayout7.setVisibility(View.GONE);
            textInputLayout8.setVisibility(View.GONE);
        }
    }

    public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) Register.this.getSystemService(Register.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

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
