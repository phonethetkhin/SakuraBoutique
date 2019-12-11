package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;
import com.example.sakuraboutique.Adapters.ProductViewAdapter;
import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.example.sakuraboutique.ViewModels.ProductViewModel;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.droidsonroids.gif.GifImageView;

public class ProductView extends AppCompatActivity {
RecyclerView rvProductView;
private String CategoryName;
private int CategoryID;
Toolbar tbToolbar;
private List<ProductModel> productModelList=new ArrayList<>();
SharedPreferences pref;
SwipeRefreshLayout srflRefresh;
 GifImageView gifNoInternet;
private int cartQuantity;
ProgressBar pbProgress;
    private NotificationBadge notificationBadge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        rvProductView=findViewById(R.id.rvProductView);
        tbToolbar=findViewById(R.id.tbToolbar);
        srflRefresh=findViewById(R.id.srflRefresh);
        gifNoInternet=findViewById(R.id.gifNoInternet);
        pbProgress=findViewById(R.id.pbProgress);


    }

    @Override
    protected void onResume() {
        super.onResume();
        pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        CategoryID = pref.getInt("CategoryID", 1);
        CategoryName = pref.getString("CategoryName", "");

        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + CategoryName + " </font>"));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);



        MainFunction();
        srflRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srflRefresh.setRefreshing(true);
                MainFunction();
                srflRefresh.setRefreshing(false);
            }
        });







    }
    public void MainFunction() {
        if (Network())
        {
            pbProgress.setVisibility(View.GONE);


            pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
            CategoryID = pref.getInt("CategoryID", 1);
            CategoryName = pref.getString("CategoryName", "");
            gifNoInternet.setVisibility(View.GONE);
            rvProductView.setVisibility(View.VISIBLE);
            ProductViewModel viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
            viewModel.getProductlivedatalist(CategoryID).observe(this, new Observer<List<ProductModel>>() {
                @Override
                public void onChanged(List<ProductModel> productModels) {

                    if (productModels == null) {
                        Toast.makeText(ProductView.this, "There is no item match your searches!", Toast.LENGTH_SHORT).show();
                    } else {
                        productModelList = productModels;
                        GridLayoutManager glm = new GridLayoutManager(ProductView.this, 2, GridLayoutManager.VERTICAL, false);
                        rvProductView.setLayoutManager(glm);
                        rvProductView.setHasFixedSize(true);
                        Drawable horizontalDivider = ContextCompat.getDrawable(ProductView.this, R.drawable.horizontal_divider);
                        Drawable verticalDivider = ContextCompat.getDrawable(ProductView.this, R.drawable.horizontal_divider);
                        rvProductView.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 2));
                        rvProductView.setAdapter(new ProductViewAdapter(productModelList));
                    }
                }
            });
        }
        else
        {
            Toast.makeText(ProductView.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();

            pbProgress.setVisibility(View.VISIBLE);

            gifNoInternet.setVisibility(View.VISIBLE);
            rvProductView.setVisibility(View.GONE);
            Toast.makeText(this, "Check Your Connection!", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) ProductView.this.getSystemService(ProductView.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

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
Intent intent=new Intent(ProductView.this,MainActivity.class);
startActivity(intent);
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
