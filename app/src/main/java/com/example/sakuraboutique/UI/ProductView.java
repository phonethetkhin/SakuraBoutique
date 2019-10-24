package com.example.sakuraboutique.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sakuraboutique.Adapters.ProductViewAdapter;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductView extends AppCompatActivity {
RecyclerView rvProductView;
private String CategoryName;
Toolbar tbToolbar;
private List<ProductModel> productModelList=new ArrayList<>();
private String keyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        rvProductView=findViewById(R.id.rvProductView);
        tbToolbar=findViewById(R.id.tbToolbar);

        keyword=getIntent().getStringExtra("Keyword");
        CategoryName = getIntent().getStringExtra("CategoryName");

        if(keyword==null) {
            setSupportActionBar(tbToolbar);
            getSupportActionBar().setTitle(CategoryName);

        }
        else {
            setSupportActionBar(tbToolbar);
            getSupportActionBar().setTitle(keyword);

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DividerItemDecoration rvdecoration=new DividerItemDecoration(ProductView.this,DividerItemDecoration.VERTICAL);
        rvdecoration.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        rvProductView.addItemDecoration(rvdecoration);
        DividerItemDecoration rvVertical=new DividerItemDecoration(ProductView.this,DividerItemDecoration.HORIZONTAL);
        rvVertical.setDrawable(getResources().getDrawable(R.drawable.divider_line));
        rvProductView.addItemDecoration(rvVertical);



        productModelList= MainViewModel.AddProductData();
        rvProductView.setLayoutManager(new GridLayoutManager(ProductView.this,2, GridLayoutManager.VERTICAL,false));
        rvProductView.setHasFixedSize(true);
        rvProductView.setAdapter(new ProductViewAdapter(productModelList));
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
