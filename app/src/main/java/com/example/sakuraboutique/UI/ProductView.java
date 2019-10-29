package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


        MenuItem menuItem =(MenuItem) menu.findItem(R.id.mainshoppingcart);
        View actionView=(View) MenuItemCompat.getActionView(menuItem);
        NotificationBadge notificationBadge=(NotificationBadge) actionView.findViewById(R.id.badge);
        notificationBadge.setText("5");
        return true;
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
