package com.example.sakuraboutique.UI;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Adapters.ColorAdapter;
import com.example.sakuraboutique.Adapters.SizeAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter2;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.nex3z.notificationbadge.NotificationBadge;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class ProductDetailed extends AppCompatActivity {
    private SliderView svImageSlider;
    private RecyclerView rvSize, rvColor;
    private List<String> sizelist;
    private List<String> colorlist;
    private List<String> photolist;
    private Toolbar tbToolbar;
    private String ProductName;
    private int Count;
    private EditText etQuantity;
    private ImageButton imgbtnPlus, imgbtnMinus;
    private Button AddtoCart;


    private void InitializeViews() {
        svImageSlider = findViewById(R.id.svImageSlider);
        rvSize = findViewById(R.id.rvSize);
        rvColor = findViewById(R.id.rvColor);
        tbToolbar = findViewById(R.id.tbToolbar);
        etQuantity = findViewById(R.id.etQuantity);
        imgbtnPlus = findViewById(R.id.imgbtnPlus);
        imgbtnMinus = findViewById(R.id.imgbtnMinus);
        AddtoCart = findViewById(R.id.btnAddtoCart);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        InitializeViews();
        ProductName = "Black Dress";

        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + ProductName + " </font>"));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        //size rv
        sizelist = MainViewModel.AddSizeData();
        rvSize.setLayoutManager(new LinearLayoutManager(ProductDetailed.this, RecyclerView.HORIZONTAL, false));
        rvSize.setHasFixedSize(true);
        rvSize.setAdapter(new SizeAdapter(sizelist));

        //slider
        photolist = MainViewModel.AddURL2();
        svImageSlider.setSliderAdapter(new SlideAdapter2(ProductDetailed.this, photolist));

        //color rv
        colorlist = MainViewModel.AddColorData();
        rvColor.setLayoutManager(new LinearLayoutManager(ProductDetailed.this, RecyclerView.HORIZONTAL, false));
        rvColor.setHasFixedSize(true);
        rvColor.setAdapter(new ColorAdapter(colorlist));

        //counting
        Count = 0;

        etQuantity.setText(Count + "");
        imgbtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(ProductDetailed.this, R.anim.blink);
                imgbtnPlus.startAnimation(myFadeInAnimation);
                ++Count;
                etQuantity.setText(Count + "");
            }
        });
        imgbtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(ProductDetailed.this, R.anim.blink);
                imgbtnMinus.startAnimation(myFadeInAnimation);
                if (Count != 0) {
                    --Count;
                }

                etQuantity.setText(Count + "");
            }
        });


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
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


}
