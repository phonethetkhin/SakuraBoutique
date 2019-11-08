package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.example.sakuraboutique.Adapters.ProductViewAdapter;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

public class ProductView extends AppCompatActivity {
RecyclerView rvProductView;
private String CategoryName;
Toolbar tbToolbar;
private List<ProductModel> productModelList=new ArrayList<>();
SharedPreferences pref;
private int cartQuantity;
    private NotificationBadge notificationBadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        rvProductView=findViewById(R.id.rvProductView);
        tbToolbar=findViewById(R.id.tbToolbar);

        CategoryName = getIntent().getStringExtra("CategoryName");



            setSupportActionBar(tbToolbar);
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+CategoryName+" </font>"));



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);



        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
        rvProductView.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 2));
        productModelList= MainViewModel.AddProductData();

        rvProductView.setLayoutManager(new GridLayoutManager(ProductView.this,2, GridLayoutManager.VERTICAL,false));
        rvProductView.setHasFixedSize(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        productModelList.clear();
        productModelList=MainViewModel.AddProductData();
        rvProductView.setAdapter(new ProductViewAdapter(productModelList));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
//FrameLayout frameLayout= (FrameLayout) findViewById(R.id.flActionBar);


        final MenuItem menuItem =(MenuItem) menu.findItem(R.id.mainshoppingcart);
        final View actionView=(View) MenuItemCompat.getActionView(menuItem);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
         notificationBadge=(NotificationBadge) actionView.findViewById(R.id.badge);
        pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        cartQuantity=pref.getInt("Cart_Quantity",0);
        notificationBadge.setText(cartQuantity+"");
                return true;
    }
   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        final MenuItem menuItem =(MenuItem) menu.findItem(R.id.mainshoppingcart);
        final View actionView=(View) MenuItemCompat.getActionView(menuItem);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        notificationBadge=(NotificationBadge) actionView.findViewById(R.id.badge);

        pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        cartQuantity=pref.getInt("Cart_Quantity",0);
        notificationBadge.setText(cartQuantity+"");


        return super.onPrepareOptionsMenu(menu);
    }*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.mainshoppingcart:
                Intent i=new Intent(ProductView.this,CartActivity.class);
                startActivity(i);
                break;

            case R.id.HomeIcon:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
