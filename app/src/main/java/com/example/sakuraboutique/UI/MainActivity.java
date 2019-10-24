package com.example.sakuraboutique.UI;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Adapters.CategoryAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter;
import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class MainActivity extends AppCompatActivity {


private RecyclerView rvMain;
Toolbar tbMain;
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


        rvMain.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false));
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(new CategoryAdapter(categoryModelList));
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
        List<String> URLs=MainViewModel.AddURL();

        svImageSlider.setSliderAdapter(new SlideAdapter(MainActivity.this,URLs));
        svImageSlider.setCircularHandlerEnabled(true);

        svImageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        svImageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        svImageSlider.setCircularHandlerEnabled(true);

        svImageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        svImageSlider.startAutoCycle();




    }

}
