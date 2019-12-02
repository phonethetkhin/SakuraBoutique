package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import com.example.sakuraboutique.R;

public class PrivacyPolicy extends AppCompatActivity {
    Toolbar tbToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        tbToolbar=findViewById(R.id.tbToolbar);
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Terms and Conditions"+" </font>"));



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
