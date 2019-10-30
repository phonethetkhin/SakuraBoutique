package com.example.sakuraboutique.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sakuraboutique.Adapters.CartAdapter;
import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.Models.ProductDetailModel;
import com.example.sakuraboutique.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {
private TextView tvItem,tvTotalPrice;
private RecyclerView rvCartItem;
private List<ProductCartModel> productCartModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tvItem=findViewById(R.id.tvItem);
        tvTotalPrice=findViewById(R.id.tvTotalPrice);
        rvCartItem=findViewById(R.id.rvCartItem);


        //getolddata
productCartModelList=getIntent().getParcelableArrayListExtra("ProductList");



        rvCartItem.setLayoutManager(new LinearLayoutManager(CartActivity.this,RecyclerView.VERTICAL,false));
        rvCartItem.setHasFixedSize(true);
        rvCartItem.setAdapter(new CartAdapter(productCartModelList));
    }
}
