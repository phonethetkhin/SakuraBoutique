package com.example.sakuraboutique.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Adapters.CartAdapter;
import com.example.sakuraboutique.CartDB.CartDB;
import com.example.sakuraboutique.Interfaces.DataTransferInterface;
import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class CartActivity extends AppCompatActivity  implements DataTransferInterface {
private TextView tvItem,tvTotalPrice,tvCartTotal,tvTotalQuantity,tvCartEmpty,tvCartTotalLabel,tvTotalQuantityLabel,tvPriceDetailLabel;
private ImageView imgEmptyCart;
private RecyclerView rvCartItem;
private List<ProductCartModel> productCartModelList;
private CartDB db=new CartDB(CartActivity.this);
private CartAdapter cartAdapter;
private Button btnEmptyCart,btnCheckout,btnStartShopping;
private View divider2,divider3;

private int FinalTotalPrice,TotalQuantity;
private Toolbar toolbar;
private SharedPreferences pref;
FirebaseAuth mAuth;
FirebaseUser firebaseUser;
private int EnterKey;
int ProductID;


private void InitializeViews()
{
    tvItem=findViewById(R.id.tvItem);
    tvTotalPrice=findViewById(R.id.tvTotalPrice);
    rvCartItem=findViewById(R.id.rvCartItem);
    toolbar=findViewById(R.id.tbToolbar);
    tvCartTotal=findViewById(R.id.tvCartTotal);
    tvTotalQuantity=findViewById(R.id.tvTotalQuantity);
    btnEmptyCart=findViewById(R.id.btnEmptyCart);
    btnCheckout=findViewById(R.id.btnCheckout);
    btnStartShopping=findViewById(R.id.btnStartShopping);
    tvCartEmpty=findViewById(R.id.tvEmptyCart);
    imgEmptyCart=findViewById(R.id.imgEmptyCart);
    divider2=findViewById(R.id.divider2);
    divider3=findViewById(R.id.divider3);
    tvCartTotalLabel=findViewById(R.id.tvCartTotalLabel);
    tvTotalQuantityLabel=findViewById(R.id.tvTotalQuantityLabel);
    tvPriceDetailLabel=findViewById(R.id.tvPriceDetail);



}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        InitializeViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>" + "Shopping Cart" + " </font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

//checkenterkey

        EnterKey=getIntent().getIntExtra("EnterKey",0);

        //getitem


        imgEmptyCart.setImageResource(R.drawable.cart);


        //getolddata
        productCartModelList = db.getCartItemList();


        tvItem.setText("Total Item (" + productCartModelList.size() + ")");


        if (productCartModelList.size() == 0) {
            btnStartShopping.setVisibility(View.VISIBLE);
            imgEmptyCart.setVisibility(View.VISIBLE);
            tvCartEmpty.setVisibility(View.VISIBLE);
            rvCartItem.setVisibility(View.GONE);
            tvCartTotal.setVisibility(View.GONE);
            tvTotalQuantity.setVisibility(View.GONE);
            tvCartTotalLabel.setVisibility(View.GONE);
            tvPriceDetailLabel.setVisibility(View.GONE);
            tvTotalQuantityLabel.setVisibility(View.GONE);
            divider2.setVisibility(View.GONE);
            divider3.setVisibility(View.GONE);
            btnEmptyCart.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.GONE);
        } else {
            btnStartShopping.setVisibility(View.GONE);

            imgEmptyCart.setVisibility(View.GONE);
            tvCartEmpty.setVisibility(View.GONE);
            rvCartItem.setVisibility(View.VISIBLE);
            tvCartTotal.setVisibility(View.VISIBLE);
            tvTotalQuantity.setVisibility(View.VISIBLE);
            tvCartTotalLabel.setVisibility(View.VISIBLE);
            tvPriceDetailLabel.setVisibility(View.VISIBLE);
            tvTotalQuantityLabel.setVisibility(View.VISIBLE);
            divider2.setVisibility(View.VISIBLE);
            divider3.setVisibility(View.VISIBLE);
            btnEmptyCart.setVisibility(View.VISIBLE);
            btnCheckout.setVisibility(View.VISIBLE);

        }


        rvCartItem.setLayoutManager(new LinearLayoutManager(CartActivity.this, RecyclerView.VERTICAL, false));
        rvCartItem.setHasFixedSize(true);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(CartActivity.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(CartActivity.this, R.drawable.horizontal_divider));

        rvCartItem.addItemDecoration(itemDecorator);
        cartAdapter = new CartAdapter(productCartModelList,this,this);

        rvCartItem.setAdapter(cartAdapter);

        FinalTotalPrice=cartAdapter.Calculate();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser!=null)
                {

                    Intent i=new Intent(CartActivity.this,ComfirmOrder.class);

                    startActivity(i);



                }
                else {
                    Intent i2 = new Intent(CartActivity.this, Login.class);
                    i2.putExtra("Key",1);

                    startActivity(i2);
                }
            }
        });

        tvTotalPrice.setText("Total Price (" + FinalTotalPrice + ")");
        tvCartTotal.setText(FinalTotalPrice + "");
        tvTotalQuantity.setText(TotalQuantity+ "");
        btnStartShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnEmptyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(CartActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("To Empty the Cart!")
                        .setConfirmText("Yes!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {
                                db.EmptyCart();
                                productCartModelList.clear();
                                cartAdapter.notifyDataSetChanged();
                                FinalTotalPrice = 0;
                                tvTotalPrice.setText("Total Price (" + FinalTotalPrice + ")");
                                tvCartTotal.setText(FinalTotalPrice + "");
                                tvItem.setText("Total Item (" + productCartModelList.size() + ")");
                                btnStartShopping.setVisibility(View.VISIBLE);
                                imgEmptyCart.setVisibility(View.VISIBLE);
                                tvCartEmpty.setVisibility(View.VISIBLE);
                                rvCartItem.setVisibility(View.GONE);
                                tvCartTotal.setVisibility(View.GONE);
                                tvTotalQuantity.setVisibility(View.GONE);
                                tvCartTotalLabel.setVisibility(View.GONE);
                                tvPriceDetailLabel.setVisibility(View.GONE);
                                tvTotalQuantityLabel.setVisibility(View.GONE);
                                divider2.setVisibility(View.GONE);
                                divider3.setVisibility(View.GONE);
                                btnEmptyCart.setVisibility(View.GONE);
                                btnCheckout.setVisibility(View.GONE);
                                tvTotalQuantity.setText(productCartModelList.size() + "");
                                pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                                SharedPreferences.Editor myeditor = pref.edit();
                                myeditor.clear();
                                myeditor.apply();

                                sDialog
                                        .setTitleText("Successfully Empty!")
                                        .setContentText("Your Cart is Empty!Start Shopping Now!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sDialog.dismissWithAnimation();

                                            }
                                        })

                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                sDialog.findViewById(R.id.cancel_button).setVisibility(View.GONE);



                            }

                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });



    }
    public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) CartActivity.this.getSystemService(CartActivity.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:


                   onBackPressed();



                break;

            case R.id.HomeIcon:
                Intent intent=new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
                break;



        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (EnterKey == 1) {
            Intent i = new Intent(CartActivity.this, MainActivity.class);
            startActivity(i);
        } else if (EnterKey == 2) {
            Intent i2 = new Intent(CartActivity.this, ProductView.class);
            startActivity(i2);
        }
        else
        {
            this.finish();
        }
    }

    @Override
    public void setValues(int TotalQuantity, int TotalPrice) {
tvTotalQuantity.setText(TotalQuantity+"");
tvTotalPrice.setText(TotalPrice+"");
tvCartTotal.setText(TotalPrice+"");
        SharedPreferences preferences=getSharedPreferences("MY_PREF", MODE_PRIVATE);
        SharedPreferences.Editor myeditor = preferences.edit();
        myeditor.putInt("TotalQuantity", TotalQuantity);
        myeditor.putInt("Item",productCartModelList.size());
        myeditor.putInt("TotalPrice",TotalPrice);
        myeditor.commit();
    }


}
