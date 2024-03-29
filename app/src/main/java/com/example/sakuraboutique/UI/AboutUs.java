package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sakuraboutique.R;

public class AboutUs extends AppCompatActivity {
TextView tvContactUs,tvFindUsonFacebook;
Toolbar tbToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        tvContactUs=findViewById(R.id.tvContactUs);
        tvFindUsonFacebook=findViewById(R.id.tvFindUsonFacebook);

        tbToolbar=findViewById(R.id.tbToolbar);
setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "About Us" + " </font>"));
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AboutUs.this,ContactUs.class);
                startActivity(i);
            }
        });
        tvFindUsonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.facebook.com/Sakura-Boutique-433408814113660/";
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent i=new Intent(AboutUs.this,MainActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
