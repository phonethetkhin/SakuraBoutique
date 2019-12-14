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
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sakuraboutique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class Login extends AppCompatActivity {
    TextInputEditText tietemail, tietPassword;
    TextView tvLabel1,tvLabel3;
    Button btnLogIn, btnRegister, btnSigninasGuest, btnResetPassword;
    Toolbar tbToolbar;
    CircleImageView cimgLogo;
    TextInputLayout textInputLayout6;
    GifImageView gifNoInternet;
    FirebaseAuth mAuth;
    ProgressBar pbProgress;
    int Key;

    private void InitializeViews() {
        tbToolbar = findViewById(R.id.tbToolbar);
        tietemail = findViewById(R.id.tietemail);
        tietPassword = findViewById(R.id.tietPassword);
        btnLogIn = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnSigninasGuest = findViewById(R.id.btnSigninAsGuest);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        cimgLogo=findViewById(R.id.cimgLogo);
        tvLabel1=findViewById(R.id.tvLabel1);
        tvLabel3=findViewById(R.id.tvLabel3);
        gifNoInternet=findViewById(R.id.gifNoInternet);
        pbProgress=findViewById(R.id.pbProgress);
        textInputLayout6=findViewById(R.id.textInputLayout6);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitializeViews();
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Log In" + " </font>"));
        Key=getIntent().getIntExtra("Key",0);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        btnSigninasGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Key==1)
                {
                    Intent i=new Intent(Login.this,ComfirmOrder.class);
                    startActivity(i);
                }
                else
                {
                     SweetAlertDialog pDialog = new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE);
                     pDialog.setTitleText("Sign In as Guest Only Available When Checkout Orders");
                     pDialog.show();

                }

            }
        });

        MainFunction();


    }
    public void MainFunction() {
        if (Network()) {
            gifNoInternet.setVisibility(View.GONE);
            pbProgress.setVisibility(View.GONE);
            tietemail.setVisibility(View.VISIBLE);
            tietPassword.setVisibility(View.VISIBLE);
            btnLogIn.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);
            btnSigninasGuest.setVisibility(View.VISIBLE);
            btnResetPassword.setVisibility(View.VISIBLE);
            cimgLogo.setVisibility(View.VISIBLE);
            tvLabel1.setVisibility(View.VISIBLE);
            tvLabel3.setVisibility(View.VISIBLE);
            textInputLayout6.setVisibility(View.VISIBLE);
            mAuth = FirebaseAuth.getInstance();

            btnLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!Network()) {
                        Toast.makeText(Login.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();

                        gifNoInternet.setVisibility(View.VISIBLE);
                        pbProgress.setVisibility(View.VISIBLE);
                        tietemail.setVisibility(View.GONE);
                        tietPassword.setVisibility(View.GONE);
                        btnLogIn.setVisibility(View.GONE);
                        btnRegister.setVisibility(View.GONE);
                        btnSigninasGuest.setVisibility(View.GONE);
                        btnResetPassword.setVisibility(View.GONE);
                        cimgLogo.setVisibility(View.GONE);
                        tvLabel1.setVisibility(View.GONE);
                        tvLabel3.setVisibility(View.GONE);
                        textInputLayout6.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Check Your Internet!", Toast.LENGTH_SHORT).show();
                    } else {
                        String email = tietemail.getText().toString();
                        String password = tietPassword.getText().toString();
                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(Login.this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
                            tietemail.setError("Enter Email");

                        }
                        if (TextUtils.isEmpty(password)) {
                            Toast.makeText(Login.this, "Please Enter Password!", Toast.LENGTH_SHORT).show();
                            tietPassword.setError("Enter Email");

                        } else {
                            final SweetAlertDialog pDialog = new SweetAlertDialog(Login.this, SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setTitleText("Loading");
                            pDialog.setCancelable(false);
                            pDialog.show();
                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        pDialog.setTitleText("Logged in Successfully!").changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                pDialog.dismissWithAnimation();
                                                Intent i=new Intent(Login.this,MainActivity.class);

                                                startActivity(i);
                                                finish();

                                            }
                                        });
                                    }

                                    else {
                                        pDialog.setTitleText("Username or Password Incorret!").changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                        Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }

                            });
                        }
                    }
                }
            });


        }
        else
        {
            Toast.makeText(Login.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();

            gifNoInternet.setVisibility(View.VISIBLE);
            pbProgress.setVisibility(View.VISIBLE);
            tietemail.setVisibility(View.GONE);
            tietPassword.setVisibility(View.GONE);
            btnLogIn.setVisibility(View.GONE);
            btnRegister.setVisibility(View.GONE);
            btnSigninasGuest.setVisibility(View.GONE);
            btnResetPassword.setVisibility(View.GONE);
            cimgLogo.setVisibility(View.GONE);
            tvLabel1.setVisibility(View.GONE);
            tvLabel3.setVisibility(View.GONE);
            textInputLayout6.setVisibility(View.GONE);

        }
    }
    public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) Login.this.getSystemService(Login.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
           this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
