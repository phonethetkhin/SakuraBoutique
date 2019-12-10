package com.example.sakuraboutique.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sakuraboutique.Adapters.CategoryAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter;
import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.Retrofit.Apicalls;
import com.example.sakuraboutique.Retrofit.RetrofitObj;
import com.example.sakuraboutique.ViewModels.CategoryViewModel;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.nex3z.notificationbadge.NotificationBadge;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


private RecyclerView rvMain;
Toolbar tbMain;
    List<String> URLs=new ArrayList<>();
DrawerLayout dlMain;
    GifImageView gifNoInternet;
List<CategoryModel> categoryModelList;
SliderView svImageSlider;
ActionBarDrawerToggle toggle;
SwipeRefreshLayout srflMain;
    NotificationBadge notificationBadge;
    SharedPreferences pref;
    private int cartQuantity;
    NavigationView navigationView;
    CollapsingToolbarLayout ctblCollapsingtoolbar;
    AppBarLayout apAppBar;


private void InitializeViews()
{
    rvMain=findViewById(R.id.rvMain);
    tbMain=findViewById(R.id.tbMain);
    dlMain=findViewById(R.id.drawer_layout);
    navigationView=findViewById(R.id.nav_view);
        gifNoInternet=findViewById(R.id.gifNoInternet);
    svImageSlider=findViewById(R.id.svImageSlider);
    srflMain=findViewById(R.id.srflMain);
    apAppBar=findViewById(R.id.apAppBar);
    ctblCollapsingtoolbar=findViewById(R.id.ctblCollapsingtoolbar);
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeViews();
setSupportActionBar(tbMain);

getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toggle=(new ActionBarDrawerToggle(MainActivity.this,dlMain,R.string.open,R.string.close));
        dlMain.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

View header=navigationView.getHeaderView(0);
header.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }
});
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_tops:
                        Intent i=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        SharedPreferences.Editor myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 2);
                        myeditor.putString("CategoryName","Tops");

                        myeditor.commit();
                        startActivity(i);
                        break;

                    case R.id.nav_bottoms:
                        Intent intent=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 3);
                        myeditor.putString("CategoryName","Bottoms");

                        myeditor.commit();
                        startActivity(intent);
                        break;

                    case R.id.nav_dress:
                        Intent intent1=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                         myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 4);
                        myeditor.putString("CategoryName","Dresses");

                        myeditor.commit();
                        startActivity(intent1);
                        break;

                    case R.id.nav_traditionaldress:
                        Intent intent2=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 5);
                        myeditor.putString("CategoryName","Traditional Dresses");

                        myeditor.commit();
                        startActivity(intent2);
                        break;

                    case R.id.nav_shoesandbags:
                        Intent intent3=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 6);
                        myeditor.putString("CategoryName","Shoes & Bags");

                        myeditor.commit();
                        startActivity(intent3);
                        break;

                    case R.id.nav_accessories:
                        Intent intent4=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 7);
                        myeditor.putString("CategoryName","Accessories");

                        myeditor.commit();
                        startActivity(intent4);
                        break;

                    case R.id.nav_menfashion:
                        Intent intent5=new Intent(MainActivity.this,ProductView.class);
                        pref=getSharedPreferences("MY_PREF", MODE_PRIVATE);
                        myeditor = pref.edit();
                        myeditor.putInt("CategoryID", 8);
                        myeditor.putString("CategoryName","Men's Fashion");

                        myeditor.commit();
                        startActivity(intent5);
                        break;

                    case R.id.nav_cart:
                        Intent intent6=new Intent(MainActivity.this,CartActivity.class);
                        startActivity(intent6);
                        break;

                    case R.id.nav_aboutus:
                        Intent intent7=new Intent(MainActivity.this,AboutUs.class);
                        startActivity(intent7);
                        break;

                    case R.id.nav_contactus:
                        Intent intent8=new Intent(MainActivity.this,ContactUs.class);
                        startActivity(intent8);
                        break;
                }

                return false;
            }
        });
        rvMain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false));

        rvMain.setHasFixedSize(true);
        MainFunction();

        srflMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                    srflMain.setRefreshing(true);

                    MainFunction();

                srflMain.setRefreshing(false);
            }
        });


    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (ctblCollapsingtoolbar.getHeight() + verticalOffset < 6 * ViewCompat.getMinimumHeight(ctblCollapsingtoolbar)) {
            srflMain.setEnabled(false);
        } else {
            srflMain.setEnabled(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        apAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        apAppBar.removeOnOffsetChangedListener(this);
    }
    private void MainFunction()
    {
        svImageSlider.setCircularHandlerEnabled(true);


        svImageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP);
        svImageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        svImageSlider.setCircularHandlerEnabled(true);

        svImageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        svImageSlider.startAutoCycle();
        if(!Network())
        {
            svImageSlider.setVisibility(View.GONE);
            rvMain.setVisibility(View.GONE);
            gifNoInternet.setVisibility(View.VISIBLE);

            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
        else {
            CategoryViewModel viewModels = ViewModelProviders.of(this).get(CategoryViewModel.class);
            viewModels.getCategoryLivedata().observe(this, new Observer<List<CategoryModel>>() {
                @Override
                public void onChanged(List<CategoryModel> categoryModels) {
                    categoryModelList = categoryModels;
                    rvMain.setAdapter(new CategoryAdapter(categoryModelList));

                }
            });


            gifNoInternet.setVisibility(View.GONE);
            svImageSlider.setVisibility(View.VISIBLE);
            rvMain.setVisibility(View.VISIBLE);

            Apicalls apicalls= RetrofitObj.getRetrofit().create(Apicalls.class);
            Call<JsonArray> jsonArrayCall=apicalls.getURLs();
            jsonArrayCall.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    JsonArray jsonArray=response.body();
                    for(int i=0;i<jsonArray.size();i++)
                    {
                        String url=jsonArray.get(i).getAsString();
                        URLs.add(url);
                    }
                    svImageSlider.setSliderAdapter(new SlideAdapter(MainActivity.this, URLs));


                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {

                }
            });


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
//FrameLayout frameLayout= (FrameLayout) findViewById(R.id.flActionBar);


        final MenuItem menuItem =(MenuItem) menu.findItem(R.id.shoppingcart);
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

    @Override
    public void onBackPressed() {
    if(dlMain.isDrawerOpen(GravityCompat.START))
    {
        dlMain.closeDrawer(GravityCompat.START);
    }
        else
        {
            super.onBackPressed();
        }
    }

    /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        final MenuItem menuItem =(MenuItem) menu.findItem(R.id.shoppingcart);
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
    public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) MainActivity.this.getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {

            return true;
        }
    switch (item.getItemId())
    {


        case android.R.id.home:
            this.finish();
            break;

            case R.id.shoppingcart:
            Intent i=new Intent(MainActivity.this,CartActivity.class);
            startActivity(i);
            break;
    }
        return super.onOptionsItemSelected(item);
    }

}
