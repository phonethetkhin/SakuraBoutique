package com.example.sakuraboutique.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.droidsonroids.gif.GifImageView;

public class ProductDetailed extends AppCompatActivity {
    private SliderView svImageSlider;
    private RecyclerView rvSize, rvColor;
    private List<Size> sizelist ;
    private List<String> photolist;
    private Toolbar tbToolbar;
    private int Count=1;
    private ImageButton imgbtnPlus, imgbtnMinus;
    private Button btnAddtoCart;
    SharedPreferences pref;
    private int CartCount=0;
    private int cartQuantity=0;
    private int TotalCount=0;
    private String ProdcutName;
    private int price;
    private int ProductID;
    private int quantity;
    private int TotalStockQuantity;

    private String size;
    private String color;
    private TextView tvName,tvQuantity,tvPrice,tvStockQuantity,tvProductDescription,tvSizeQuantity,tvColorQuantity,tvSelectColor,tvSelectSize,tvSelectQuantity,tvStockQuantityLabel,tvProductDescriptionLabel;
    private String url;
    private CartDB db=new CartDB(ProductDetailed.this);
    private ProductCartModel productCartModel;
    private ProductCartModel selectedproductcartmodel;
    private int sizequantity,colorquantity;
    private GifImageView gifNoInternet;
    private ProgressBar pbProgress;

    NotificationBadge notificationBadge;



    private void InitializeViews() {
        svImageSlider = findViewById(R.id.svImageSlider);
        rvSize = findViewById(R.id.rvSize);
        rvColor = findViewById(R.id.rvColor);
        tbToolbar = findViewById(R.id.tbToolbar);
        tvQuantity = findViewById(R.id.tvQuantity);
        imgbtnPlus = findViewById(R.id.imgbtnPlus);
        imgbtnMinus = findViewById(R.id.imgbtnMinus);
        btnAddtoCart = findViewById(R.id.btnAddtoCart);
        tvName=findViewById(R.id.tvName);
        tvPrice=findViewById(R.id.tvPrice);
        tvStockQuantity=findViewById(R.id.tvStockQuantity);
        tvProductDescription=findViewById(R.id.tvProductDescription);
        tvSizeQuantity=findViewById(R.id.tvSizeQuantity);
        tvColorQuantity=findViewById(R.id.tvColorQuantity);
        gifNoInternet=findViewById(R.id.gifNoInternet);
        pbProgress=findViewById(R.id.pbProgress);
        tvSelectColor=findViewById(R.id.tvSelectColor);
        tvSelectQuantity=findViewById(R.id.tvSelectQuantity);
        tvSelectSize=findViewById(R.id.tvSelectSize);
        tvStockQuantityLabel=findViewById(R.id.tvStockQuantityLabel);
        tvProductDescriptionLabel=findViewById(R.id.tvProductDescriptionLabel);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        InitializeViews();
        //getvaluefromproductview
        pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        ProductID = pref.getInt("ProductID", 0);

        pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        ProdcutName = pref.getString("ProductName", "");

        price = getIntent().getIntExtra("Price", 0);
        size = getIntent().getStringExtra("Size");
        color = getIntent().getStringExtra("Color");
        url = getIntent().getStringExtra("URL");
        svImageSlider.setCircularHandlerEnabled(true);


        svImageSlider.setIndicatorAnimation(IndicatorAnimations.SWAP);
        svImageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        svImageSlider.setCircularHandlerEnabled(true);

        svImageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        svImageSlider.startAutoCycle();

        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + ProdcutName + " </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        MainFunction();


    }
    public void MainFunction()
    {
        if (Network()) {
            gifNoInternet.setVisibility(View.GONE);
            pbProgress.setVisibility(View.GONE);
            svImageSlider.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            tvPrice.setVisibility(View.VISIBLE);
            tvProductDescription.setVisibility(View.VISIBLE);
            tvStockQuantity.setVisibility(View.VISIBLE);
            rvColor.setVisibility(View.VISIBLE);
            rvSize.setVisibility(View.VISIBLE);
            tvSelectSize.setVisibility(View.VISIBLE);
            tvSelectQuantity.setVisibility(View.VISIBLE);
            tvSelectColor.setVisibility(View.VISIBLE);
            tvSizeQuantity.setVisibility(View.VISIBLE);
            tvColorQuantity.setVisibility(View.VISIBLE);
            imgbtnPlus.setVisibility(View.VISIBLE);
            imgbtnMinus.setVisibility(View.VISIBLE);
            btnAddtoCart.setVisibility(View.VISIBLE);
            tvStockQuantityLabel.setVisibility(View.VISIBLE);
            tvProductDescriptionLabel.setVisibility(View.VISIBLE);
            tvQuantity.setVisibility(View.VISIBLE);



            final ProductDetailedViewModel pdvm = ViewModelProviders.of(this).get(ProductDetailedViewModel.class);
            pdvm.getProductlivedatalist(ProductID).observe(this, new Observer<ProductDetailedModel>() {
                @Override
                public void onChanged(ProductDetailedModel productDetailedModel) {

                    tvName.setText(productDetailedModel.getProductName());
                    tvPrice.setText(productDetailedModel.getPrice() + "");
                    tvProductDescription.setText(productDetailedModel.getProductDescription());


                    //slider
                    photolist = productDetailedModel.getPhotos();
                    svImageSlider.setSliderAdapter(new SlideAdapter2(ProductDetailed.this, photolist));

                    //size rv
                    sizelist = productDetailedModel.getSizes();
                    for (int i = 0; i < sizelist.size(); i++) {
                        for (int k = 0; k < sizelist.get(i).getColor().size(); k++) {
                            TotalStockQuantity += sizelist.get(i).getColor().get(k).getQuantity();
                        }
                    }
                    tvStockQuantity.setText(TotalStockQuantity + "");
                    tvSizeQuantity.setText("(" + TotalStockQuantity + ") Stock Available");
                    tvColorQuantity.setText("(" + TotalStockQuantity + ") Stock Available");


                    rvSize.setLayoutManager(new LinearLayoutManager(ProductDetailed.this, RecyclerView.HORIZONTAL, false));
                    rvSize.setHasFixedSize(true);
                    SizeAdapter sAdapter = new SizeAdapter(sizelist);

                    rvSize.setAdapter(sAdapter);
                    sAdapter.setOnItemClickListener(new SizeAdapter.onRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClickListener(View view, final int position) {
                            size = sizelist.get(position).getSizeName();
                            sizequantity = 0;
                            final List<Color> colorlist = new ArrayList<>();


                            for (int i = 0; i < sizelist.get(position).getColor().size(); i++) {
                                String ColorCode = sizelist.get(position).getColor().get(i).getColorCode();
                                String ColorName = sizelist.get(position).getColor().get(i).getColorName();
                                sizequantity += sizelist.get(position).getColor().get(i).getQuantity();
                                tvColorQuantity.setText("(" + sizequantity + ") Stock Available");


                                Color colormodel = new Color(ColorCode, ColorName, sizequantity);
                                colorlist.add(colormodel);
                                rvColor.setLayoutManager(new LinearLayoutManager(ProductDetailed.this, RecyclerView.HORIZONTAL, false));
                                rvColor.setHasFixedSize(true);
                                ColorAdapter cAdapter = new ColorAdapter(colorlist, ProductDetailed.this);
                                rvColor.setAdapter(cAdapter);
                                cAdapter.setOnItemClickListener(new ColorAdapter.onRecyclerViewItemClickListener() {
                                    @Override
                                    public void onItemClickListener(View view, int position2) {
                                        color = colorlist.get(position2).getColorCode();

                                        colorquantity = sizelist.get(position).getColor().get(position2).getQuantity();

                                        tvColorQuantity.setText("(" + colorquantity + ") Stock Available");

                                    }
                                });

                            }

                            tvSizeQuantity.setText("(" + sizequantity + ") Stock Available");
                        }
                    });
                }
            });

            //counting
            tvQuantity.setText(Count + "");
            imgbtnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity = Integer.parseInt(tvQuantity.getText().toString());
                    Count = quantity;
                    Animation myFadeInAnimation = AnimationUtils.loadAnimation(ProductDetailed.this, R.anim.blink);
                    imgbtnPlus.startAnimation(myFadeInAnimation);
                    ++Count;
                    tvQuantity.setText(Count + "");


                }
            });
            imgbtnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity = Integer.parseInt(tvQuantity.getText().toString());

                    Count = quantity;
                    Animation myFadeInAnimation = AnimationUtils.loadAnimation(ProductDetailed.this, R.anim.blink);
                    imgbtnMinus.startAnimation(myFadeInAnimation);
                    if (Count != 1) {
                        --Count;
                    }

                    tvQuantity.setText(Count + "");

                }
            });


            //cart count
            btnAddtoCart.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    quantity = Integer.parseInt(tvQuantity.getText().toString());


                    if (size == null)
                    {
                        Toast.makeText(ProductDetailed.this, "Please Choose a Size", Toast.LENGTH_SHORT).show();

                    }

                    else if (color == null)
                    {
                        Toast.makeText(ProductDetailed.this, "Please Choose a Color", Toast.LENGTH_SHORT).show();
                    }

                    else {

                        productCartModel = db.checkExistingProduct(ProductID,size,color);
                        selectedproductcartmodel = new ProductCartModel(ProductID, price, quantity, price, ProdcutName, url, size, color);


                        if (productCartModel != null)
                        {

                             SweetAlertDialog pDialog = new SweetAlertDialog(ProductDetailed.this, SweetAlertDialog.ERROR_TYPE);
                            pDialog.setTitleText("Already Added!");

                            pDialog.show();
                            Toast.makeText(ProductDetailed.this, "This Product is Already Added to cart, try different Size or Color!", Toast.LENGTH_SHORT).show();


                        }
                            else if (db.InsertCartItem(ProductID, ProdcutName, quantity, price, size, color, url, price))
                            {
                            final SweetAlertDialog pDialog = new SweetAlertDialog(ProductDetailed.this, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText("1 Product Added to Cart!");
                            pDialog.setContentText("Do You Wanna Watch Your Cart Items?");
                            pDialog.setCancelText("No");
                            pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
pDialog.cancel();
                                }
                            });
                            pDialog.setConfirmText("Yes");

                         pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                             @Override
                             public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent i=new Intent(ProductDetailed.this,CartActivity.class);
                                        i.putExtra("EnterKey",3);
                                        startActivity(i);
                                        pDialog.cancel();
                             }
                         });

                            pDialog.show();

                            ++CartCount;
                                    TotalCount = CartCount + cartQuantity;

                                    pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                                    SharedPreferences.Editor myeditor = pref.edit();
                                    myeditor.putInt("Cart_Quantity", TotalCount);


                                    myeditor.apply();
                                    notificationBadge.setText(TotalCount + "");

                                }


                        else {
                            Toast.makeText(ProductDetailed.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }


                }

            });
        }
        else
        {
            Toast.makeText(ProductDetailed.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();

            gifNoInternet.setVisibility(View.VISIBLE);
            pbProgress.setVisibility(View.VISIBLE);
            svImageSlider.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            tvPrice.setVisibility(View.GONE);
            tvProductDescription.setVisibility(View.GONE);
            tvStockQuantity.setVisibility(View.GONE);
            rvColor.setVisibility(View.GONE);
            rvSize.setVisibility(View.GONE);
            tvSelectSize.setVisibility(View.GONE);
            tvSelectQuantity.setVisibility(View.GONE);
            tvSelectColor.setVisibility(View.GONE);
            tvSizeQuantity.setVisibility(View.GONE);
            tvColorQuantity.setVisibility(View.GONE);
            imgbtnPlus.setVisibility(View.GONE);
            imgbtnMinus.setVisibility(View.GONE);
            btnAddtoCart.setVisibility(View.GONE);
            tvStockQuantityLabel.setVisibility(View.GONE);
            tvProductDescriptionLabel.setVisibility(View.GONE);
            tvQuantity.setVisibility(View.GONE);
        }
    }

public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) ProductDetailed.this.getSystemService(ProductDetailed.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

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
        onBackPressed();
            break;
            case R.id.mainshoppingcart:
                Intent i=new Intent(ProductDetailed.this,CartActivity.class);
                i.putExtra("EnterKey",4);
                startActivity(i);
                break;
            case R.id.HomeIcon:
                Intent intent=new Intent(ProductDetailed.this,MainActivity.class);
                startActivity(intent);
                break;



        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        Intent intent2=new Intent(ProductDetailed.this,ProductView.class);
        startActivity(intent2);
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
