package com.example.sakuraboutique.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.sakuraboutique.Adapters.CategoryAdapter;
import com.example.sakuraboutique.Adapters.ViewPagerAdapter;
import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.example.sakuraboutique.ViewPagerTransformation;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

private static final int SLIDER_TIMER=3000;
private int page=0;
private boolean isCountDownTimerActive=false;
private Handler handler;
private ViewPager vpMain;
private DotsIndicator dotsIndicator;
private RecyclerView rvMain;
private ViewPagerTransformation viewPagerTransformation=new ViewPagerTransformation();
List<CategoryModel> categoryModelList;
CircleImageView topIcon,bottomIcon,dressIcon,traditionaldressIcon,shoesandbagIcon,accessoriesIcon,menfashionIcon;


private final Runnable r=new Runnable() {
    @Override
    public void run() {
if(!isCountDownTimerActive)
{
    AutomateSlider();
}
handler.postDelayed(r,1000);
    }
};
private void InitializeViews()
{
    vpMain=findViewById(R.id.vpMain);
    dotsIndicator=findViewById(R.id.dots_indicator);
    rvMain=findViewById(R.id.rvMain);
    topIcon=findViewById(R.id.topicon);
    bottomIcon=findViewById(R.id.bottomicon);
    dressIcon=findViewById(R.id.dressicon);
    traditionaldressIcon=findViewById(R.id.traditionalicon);
    shoesandbagIcon=findViewById(R.id.shoeandbagicon);
    accessoriesIcon=findViewById(R.id.accessories);
    menfashionIcon=findViewById(R.id.menfashion);
}

    @Override
    protected void onResume() {
        super.onResume();
        AutomateSlider();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
InitializeViews();
getSupportActionBar().setTitle("Home Page");
        categoryModelList=MainViewModel.AddData();
        vpMain.setPageTransformer(true,viewPagerTransformation);

        handler=new Handler();
        handler.postDelayed(r,1000);
        r.run();


        rvMain.setLayoutManager(new GridLayoutManager(MainActivity.this,2, GridLayoutManager.VERTICAL,false));
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(new CategoryAdapter(categoryModelList));

        vpMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
if(position==0)
{
page=0;
}
else if(position==1)
{
page=1;
}
else if(position==2)
{
page=2;
}
else if(position==3)
{
    page=3;
}

else if(position==4)
{
    page=4;
}

else if(position==5)
{
    page=5;
}

else if(position==6)
{
    page=6;
}

else if(position==7)
{
    page=7;
}

else if(position==8)
{
    page=8;
}

else if(position==9)
{
    page=9;
}
else if(position==10)
{
    page=10;
}



    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
        dotsIndicator.setViewPager(vpMain);
        ButtonClicks();

    }

    private void AutomateSlider()
    {
        isCountDownTimerActive=true;
        new CountDownTimer(SLIDER_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
int nextSlider=page+1;
if(nextSlider==10)
{
    nextSlider=0;
}
vpMain.setCurrentItem(nextSlider);
isCountDownTimerActive=false;
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(r);
    }
    private void ButtonClicks()
    {
        topIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Tops");

                startActivity(i);
            }
        });

        bottomIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Bottoms");

                startActivity(i);
            }
        });
        dressIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Dresses");

                startActivity(i);
            }
        });
        traditionaldressIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Traditional Dresses");

                startActivity(i);
            }
        });
        shoesandbagIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Shoes & bags");

                startActivity(i);
            }
        });
        accessoriesIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Accessories");

                startActivity(i);
            }
        });
        menfashionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ProductView.class);
                i.putExtra("Keyword","Men's Fashion");

                startActivity(i);
            }
        });
    }
}
