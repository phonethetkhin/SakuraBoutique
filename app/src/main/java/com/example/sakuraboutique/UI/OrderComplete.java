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
import android.widget.ImageView;

import com.example.sakuraboutique.R;

public class OrderComplete extends AppCompatActivity {
ImageView imgSuccessShopping;
Button btnConfirmOrder;
Toolbar tbInclude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        imgSuccessShopping=findViewById(R.id.imgSuccessShopping);
        btnConfirmOrder=findViewById(R.id.btnConfirmOrder);
        tbInclude=findViewById(R.id.tbToolbar);
        setSupportActionBar(tbInclude);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Order Completed"+" </font>"));




        imgSuccessShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderComplete.this,MainActivity.class);
                startActivity(i);

            }
        });
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderComplete.this,MainActivity.class);
                finish();
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

    @Override
    public void onBackPressed() {
        Intent i=new Intent(OrderComplete.this,MainActivity.class);
        startActivity(i);
    }
}
