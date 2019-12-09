package com.example.sakuraboutique.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Adapters.ColorAdapter;
import com.example.sakuraboutique.Adapters.SizeAdapter;
import com.example.sakuraboutique.Adapters.SlideAdapter2;
import com.example.sakuraboutique.CartDB.CartDB;
import com.example.sakuraboutique.Models.Color;
import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.Models.ProductDetailedModel;
import com.example.sakuraboutique.Models.Size;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.ViewModels.MainViewModel;
import com.example.sakuraboutique.ViewModels.ProductDetailedViewModel;
import com.nex3z.notificationbadge.NotificationBadge;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailed extends AppCompatActivity {
    private SliderView svImageSlider;
    private RecyclerView rvSize, rvColor;
    private List<Size> sizelist ;
    private List<String> photolist;
    private Toolbar tbToolbar;
    private int Count=1;
    private EditText etQuantity;
    private ImageButton imgbtnPlus, imgbtnMinus;
    private Button AddtoCart;
    SharedPreferences pref;
    private int CartCount=0;
    private int cartQuantity=0;
    private int TotalCount=0;
    private String ProdcutName;
    private int price;
    private int ProductID;
    private int quantity;
    List<Color> colorlist = new ArrayList<>();

    private String size;
    private String color;
    private TextView tvName,tvPrice;
    private String url;
    private CartDB db=new CartDB(ProductDetailed.this);
    private ProductCartModel productCartModel;
    private ProductCartModel selectedproductcartmodel;


    NotificationBadge notificationBadge;



    private void InitializeViews() {
        svImageSlider = findViewById(R.id.svImageSlider);
        rvSize = findViewById(R.id.rvSize);
        rvColor = findViewById(R.id.rvColor);
        tbToolbar = findViewById(R.id.tbToolbar);
        etQuantity = findViewById(R.id.etQuantity);
        imgbtnPlus = findViewById(R.id.imgbtnPlus);
        imgbtnMinus = findViewById(R.id.imgbtnMinus);
        AddtoCart = findViewById(R.id.btnAddtoCart);
        tvName=findViewById(R.id.tvName);
        tvPrice=findViewById(R.id.tvPrice);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        InitializeViews();
        //getvaluefromproductview

        ProductID = getIntent().getIntExtra("ProductID", 0);
        ProdcutName = getIntent().getStringExtra("ProductName");
        price = getIntent().getIntExtra("Price", 0);
        size = getIntent().getStringExtra("Size");
        color = getIntent().getStringExtra("Color");
        url = getIntent().getStringExtra("URL");


        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + ProdcutName + " </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        ProductDetailedViewModel pdvm=ViewModelProviders.of(this).get(ProductDetailedViewModel.class);
        pdvm.getProductlivedatalist(ProductID).observe(this, new Observer<ProductDetailedModel>() {
            @Override
            public void onChanged(ProductDetailedModel productDetailedModel) {

                tvName.setText(productDetailedModel.getProductName());
                tvPrice.setText(productDetailedModel.getPrice()+"");

                //size rv
                sizelist=productDetailedModel.getSizes();
                rvSize.setLayoutManager(new LinearLayoutManager(ProductDetailed.this, RecyclerView.HORIZONTAL, false));
                rvSize.setHasFixedSize(true);
                SizeAdapter sAdapter = new SizeAdapter(sizelist);

                rvSize.setAdapter(sAdapter);
                sAdapter.setOnItemClickListener(new SizeAdapter.onRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClickListener(View view, int position) {
                        size = sizelist.get(position).getSizeName();
                    }
                });

                //slider
                photolist = productDetailedModel.getPhotos();
                svImageSlider.setSliderAdapter(new SlideAdapter2(ProductDetailed.this, photolist));

                //color rv
                for(int i=0;i<productDetailedModel.getSizes().size();i++)
                {
                    for(int k=0;k<productDetailedModel.getSizes().get(i).getColor().size();k++)
                    {

                        String ColorCode=productDetailedModel.getSizes().get(i).getColor().get(k).getColorCode();
                        String ColorName=productDetailedModel.getSizes().get(i).getColor().get(k).getColorName();
                        int Quantity=productDetailedModel.getSizes().get(i).getColor().get(k).getQuantity();
                        Color colormodel=new Color(ColorCode,ColorName,Quantity);
                        colorlist.add(colormodel);
                    }

                }

                rvColor.setLayoutManager(new LinearLayoutManager(ProductDetailed.this, RecyclerView.HORIZONTAL, false));
                rvColor.setHasFixedSize(true);
                ColorAdapter cAdapter = new ColorAdapter(colorlist,ProductDetailed.this);
                rvColor.setAdapter(cAdapter);
                cAdapter.setOnItemClickListener(new ColorAdapter.onRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClickListener(View view, int position) {
                        color = colorlist.get(position).getColorCode();
                    }
                });
            }
        });












        //counting

        etQuantity.setText(Count + "");
        imgbtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(etQuantity.getText().toString());
                Count = quantity;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(ProductDetailed.this, R.anim.blink);
                imgbtnPlus.startAnimation(myFadeInAnimation);
                ++Count;
                etQuantity.setText(Count + "");


            }
        });
        imgbtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(etQuantity.getText().toString());

                Count = quantity;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(ProductDetailed.this, R.anim.blink);
                imgbtnMinus.startAnimation(myFadeInAnimation);
                if (Count != 1) {
                    --Count;
                }

                etQuantity.setText(Count + "");

            }
        });



        //cart count
        AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(etQuantity.getText().toString());


                if (quantity <= 0) {
                    Toast.makeText(ProductDetailed.this, "You Selecting 0 Quantity of this product", Toast.LENGTH_SHORT).show();
                    etQuantity.setError("Quantity must be more than 0");
                } else if (size == null) {
                    Toast.makeText(ProductDetailed.this, "Please Choose a Size", Toast.LENGTH_SHORT).show();

                } else if (color == null) {
                    Toast.makeText(ProductDetailed.this, "Please Choose a Color", Toast.LENGTH_SHORT).show();
                } else {

                    ProductID = getIntent().getIntExtra("ProductID", 0);
                    productCartModel = db.getProductBaseonID(ProductID);
                    selectedproductcartmodel = new ProductCartModel(ProductID, price, quantity,price, ProdcutName, url, size, color);

                    if(productCartModel!=null) {
                        if (selectedproductcartmodel.getProductId() == productCartModel.getProductId()
                                || selectedproductcartmodel.getColor() == productCartModel.getColor()
                                || selectedproductcartmodel.getSize() == productCartModel.getSize())
                        {

                            Toast.makeText(ProductDetailed.this, "This Product is Already Added !!!", Toast.LENGTH_SHORT).show();
                        } else if (db.InsertCartItem(ProductID, ProdcutName, quantity, price, size, color, url,price)) {
                            Toast.makeText(ProductDetailed.this, "1 Product Added to Cart !!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ProductDetailed.this, CartActivity.class);

                            startActivity(i);
                            ++CartCount;
                            TotalCount = CartCount + cartQuantity;

                            pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                            SharedPreferences.Editor myeditor = pref.edit();
                            myeditor.putInt("Cart_Quantity", TotalCount);


                            myeditor.commit();
                            notificationBadge.setText(TotalCount + "");

                        }
                    }
                    else if (db.InsertCartItem(ProductID, ProdcutName, quantity, price, size, color, url,price)) {
                            Toast.makeText(ProductDetailed.this, "1 Product Added to Cart !!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ProductDetailed.this, CartActivity.class);

                            startActivity(i);
                            ++CartCount;
                            TotalCount = CartCount + cartQuantity;

                            pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                            SharedPreferences.Editor myeditor = pref.edit();
                            myeditor.putInt("Cart_Quantity", TotalCount);


                            myeditor.commit();
                            notificationBadge.setText(TotalCount + "");
                        }



                }
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
Intent intent2=new Intent(ProductDetailed.this,ProductView.class);
startActivity(intent2);
            break;
            case R.id.mainshoppingcart:
                Intent i=new Intent(ProductDetailed.this,CartActivity.class);
                startActivity(i);
                break;
            case R.id.HomeIcon:
                Intent intent=new Intent(ProductDetailed.this,MainActivity.class);
                startActivity(intent);
                break;



        }
        return super.onOptionsItemSelected(item);

    }


    /*@Override
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


return true;
    }*/
}
