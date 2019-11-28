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

public class ContactUs extends AppCompatActivity {
Toolbar toolbar;
TextView tvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        toolbar=findViewById(R.id.tbToolbar);
        tvLocation=findViewById(R.id.tvLocation);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Contact Us" + " </font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
tvLocation.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String map = "http://maps.google.co.in/maps?q=" + "4th floor, 53 21st St, Yangon, Myanmar (Burma)";
        Intent i = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(map));
        startActivity(i);    }
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
