package com.example.sakuraboutique.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
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
private int price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tvItem=findViewById(R.id.tvItem);
        tvTotalPrice=findViewById(R.id.tvTotalPrice);
        rvCartItem=findViewById(R.id.rvCartItem);


        //getolddata
productCartModelList=getIntent().getParcelableArrayListExtra("ProductList");
tvItem.setText("Total Item ("+productCartModelList.size()+")");
price=getIntent().getIntExtra("Price",0);

tvTotalPrice.setText("Total Price ("+price+")");



        rvCartItem.setLayoutManager(new LinearLayoutManager(CartActivity.this,RecyclerView.VERTICAL,false));
        rvCartItem.setHasFixedSize(true);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(CartActivity.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(CartActivity.this, R.drawable.horizontal_divider));

        rvCartItem.addItemDecoration(itemDecorator);
        rvCartItem.setAdapter(new CartAdapter(productCartModelList));
    }
}
