package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.sakuraboutique.Adapters.ColorAdapter;
import com.example.sakuraboutique.Adapters.SizeAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter2;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductDetailed extends AppCompatActivity {
private SliderView svImageSlider;
private RecyclerView rvSize,rvColor;
private List<String> sizelist;
private List<String> colorlist;
  private List<String> photolist;
  private Toolbar tbToolbar;
  private String ProductName;
  private int Count;
  private EditText etQuantity;
  private CircleImageView cimgMinus,cimgPlus;

  private void InitializeViews()
  {
      svImageSlider=findViewById(R.id.svImageSlider);
      rvSize=findViewById(R.id.rvSize);
      rvColor=findViewById(R.id.rvColor);
      tbToolbar=findViewById(R.id.tbToolbar);
      etQuantity=findViewById(R.id.etQuantity);
      cimgMinus=findViewById(R.id.cimgMinus);
      cimgPlus=findViewById(R.id.cimgPlus);


  }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
      InitializeViews();
ProductName="Black Dress";

        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+ProductName+" </font>"));



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        //size rv
        sizelist=MainViewModel.AddSizeData();
        rvSize.setLayoutManager(new LinearLayoutManager(ProductDetailed.this,RecyclerView.HORIZONTAL,false));
        rvSize.setHasFixedSize(true);
        rvSize.setAdapter(new SizeAdapter(sizelist));

        //slider
       photolist=MainViewModel.AddURL2();
      svImageSlider.setSliderAdapter(new SlideAdapter2(ProductDetailed.this,photolist));

        //color rv
        colorlist=MainViewModel.AddColorData();
        rvColor.setLayoutManager(new LinearLayoutManager(ProductDetailed.this,RecyclerView.HORIZONTAL,false));
        rvColor.setHasFixedSize(true);
        rvColor.setAdapter(new ColorAdapter(colorlist));

        //counting
        Count=0;
        cimgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Count++;
            }
        });
        cimgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Count--;
            }
        });
        etQuantity.setText(Count+"");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
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
