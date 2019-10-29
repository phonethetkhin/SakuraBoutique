package com.example.sakuraboutique.UI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Adapters.CategoryAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter;
import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.nex3z.notificationbadge.NotificationBadge;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


private RecyclerView rvMain;
Toolbar tbMain;
    List<String> URLs=new ArrayList<>();
DrawerLayout dlMain;
List<CategoryModel> categoryModelList;
SliderView svImageSlider;
ActionBarDrawerToggle toggle;


private void InitializeViews()
{
    rvMain=findViewById(R.id.rvMain);
    tbMain=findViewById(R.id.tbMain);
    dlMain=findViewById(R.id.dlMain);


    svImageSlider=findViewById(R.id.svImageSlider);
}

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeViews();
setSupportActionBar(tbMain);

getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toggle=(new ActionBarDrawerToggle(MainActivity.this,dlMain,R.string.open,R.string.close));

toggle.syncState();
categoryModelList = MainViewModel.AddCategoryData();
        URLs.clear();
        URLs=MainViewModel.AddURL();

        svImageSlider.setSliderAdapter(new SlideAdapter(MainActivity.this,URLs));
        svImageSlider.setCircularHandlerEnabled(true);


        svImageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svImageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        svImageSlider.setCircularHandlerEnabled(true);

        svImageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        svImageSlider.startAutoCycle();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        rvMain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false));
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(new CategoryAdapter(categoryModelList));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
//FrameLayout frameLayout= (FrameLayout) findViewById(R.id.flActionBar);


        MenuItem menuItem =(MenuItem) menu.findItem(R.id.shoppingcart);
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
            //
            break;
        case R.id.app_bar_search:
            break;

    }
        return super.onOptionsItemSelected(item);
    }
}
