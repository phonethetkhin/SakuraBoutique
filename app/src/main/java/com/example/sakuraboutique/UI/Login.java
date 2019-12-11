package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sakuraboutique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {
    TextInputEditText tietemail, tietPassword;
    Button btnLogIn, btnRegister, btnSigninasGuest, btnResetPassword;
    Toolbar tbToolbar;
    FirebaseAuth mAuth;

    private void InitializeViews() {
        tbToolbar = findViewById(R.id.tbToolbar);
        tietemail = findViewById(R.id.tietemail);
        tietPassword = findViewById(R.id.tietPassword);
        btnLogIn = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnSigninasGuest = findViewById(R.id.btnSigninAsGuest);
        btnResetPassword = findViewById(R.id.btnResetPassword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitializeViews();
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Log In" + " </font>"));
        mAuth=FirebaseAuth.getInstance();


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

            }
        });



        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=tietemail.getText().toString();
                String password=tietPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
                    tietemail.setError("Enter Email");

                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please Enter Password!", Toast.LENGTH_SHORT).show();
                    tietPassword.setError("Enter Email");

                }
                else
                {
                    final SweetAlertDialog pDialog = new SweetAlertDialog(Login.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                pDialog.setTitleText("Logged in Successfully!").changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                Intent i=new Intent(Login.this,ComfirmOrder.class);
                                startActivity(i);
                            }
                            else
                            {
                                pDialog.setTitleText("Username or Password Incorret!").changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                    });
                }
            }
        });

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
