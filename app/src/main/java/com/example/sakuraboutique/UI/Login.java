package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Button;

import com.example.sakuraboutique.R;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
TextInputEditText tietUserName,tietPassword;
Button btnLogIn,btnRegister,btnSigninasGuest,btnResetPassword;
Toolbar tbToolbar;
private void InitializeViews()
{
    tbToolbar=findViewById(R.id.tbToolbar);
    tietUserName=findViewById(R.id.tietUserName);
    tietPassword=findViewById(R.id.tietPassword);
    btnLogIn=findViewById(R.id.btnLogin);
    btnRegister=findViewById(R.id.btnRegister);
    btnSigninasGuest=findViewById(R.id.btnSigninAsGuest);
    btnResetPassword=findViewById(R.id.btnResetPassword);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitializeViews();
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Log In" + " </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

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