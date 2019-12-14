package com.example.sakuraboutique.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sakuraboutique.CartDB.CartDB;
import com.example.sakuraboutique.Models.OrderModel;
import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.Models.Items;
import com.example.sakuraboutique.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class ComfirmOrder extends AppCompatActivity {
Toolbar tbToolbar;
Button btnConfirm;
TextInputEditText tietuserName,tietAddress,tietPhonenumber;
TextView tvTotalPrice,tvUserTotalItem,tvUserOrderItem,tvConfirmOrder,tvOrderItemLabel,tvTotalPriceLabel,tvTotalItemLabel,tvPhoneLabel,tvNameLabel,tvDeliLabel;
DatabaseReference databaseReference;
FirebaseUser firebaseUser;
SharedPreferences pref;
int TotalPrice,TotalQuantity,Item;
FirebaseDatabase firebaseDatabase;
ImageView imgTotalItem,imgOrderItem,imgTotalPrice,imgPhone,imgDeliIcon,imgUserIcon;
List<ProductCartModel> productCartModelList;
CartDB db=new CartDB(ComfirmOrder.this);
GifImageView gifNoInternet;
ProgressBar pbProgress;
CircleImageView cimgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm_order);
        tbToolbar=findViewById(R.id.tbToolbar);
        btnConfirm=findViewById(R.id.btnConfirmOrder);
        tietuserName=findViewById(R.id.tietUserName);
        tietAddress=findViewById(R.id.tietAddress);
        tietPhonenumber=findViewById(R.id.tietPhonenumber);
        tvUserTotalItem=findViewById(R.id.tvUserTotalItem);
        tvTotalPrice=findViewById(R.id.tvTotalPrice);
        tvUserOrderItem=findViewById(R.id.tvUserOrderItem);
        gifNoInternet =findViewById(R.id.gifNoInternet);

        pbProgress=findViewById(R.id.pbProgress);

        tvConfirmOrder=findViewById(R.id.tvConfirmOrder);
        tvTotalPriceLabel=findViewById(R.id.tvTotalPriceLabel);
        imgTotalItem=findViewById(R.id.imgTotalItem);
        imgOrderItem=findViewById(R.id.imgOrderItem);
        imgTotalPrice=findViewById(R.id.imgTotalPrice);
        tvOrderItemLabel=findViewById(R.id.tvOrderItemLabel);
        tvTotalItemLabel=findViewById(R.id.tvTotalItemLabel);
        tvPhoneLabel=findViewById(R.id.tvPhoneLabel);
        imgPhone=findViewById(R.id.imgPhone);
        imgDeliIcon=findViewById(R.id.imgDeliIcon);
        tvNameLabel=findViewById(R.id.tvNameLabel);
        imgUserIcon=findViewById(R.id.imgUserIcon);
        cimgLogo=findViewById(R.id.cimgLogo);
        tvDeliLabel=findViewById(R.id.tvDeliLabel);

        //setting color to toolbar
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Confirm Your Order"+" </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

MainFunction();



    }
    public void MainFunction() {
        if (Network()) {
            gifNoInternet.setVisibility(View.GONE);
            pbProgress.setVisibility(View.GONE);
            tvOrderItemLabel.setVisibility(View.VISIBLE);
            tvUserOrderItem.setVisibility(View.VISIBLE);
            tvConfirmOrder.setVisibility(View.VISIBLE);
            tvTotalPriceLabel.setVisibility(View.VISIBLE);
            imgTotalItem.setVisibility(View.VISIBLE);
            imgOrderItem.setVisibility(View.VISIBLE);
            imgTotalPrice.setVisibility(View.VISIBLE);
            tvTotalPriceLabel.setVisibility(View.VISIBLE);
            tvTotalPrice.setVisibility(View.VISIBLE);
            tvUserTotalItem.setVisibility(View.VISIBLE);
            tvTotalItemLabel.setVisibility(View.VISIBLE);
            tietPhonenumber.setVisibility(View.VISIBLE);
            tvPhoneLabel.setVisibility(View.VISIBLE);
            imgPhone.setVisibility(View.VISIBLE);
            tietAddress.setVisibility(View.VISIBLE);
            imgDeliIcon.setVisibility(View.VISIBLE);
            tietuserName.setVisibility(View.VISIBLE);
            imgUserIcon.setVisibility(View.VISIBLE);
            tvNameLabel.setVisibility(View.VISIBLE);
            btnConfirm.setVisibility(View.VISIBLE);
            cimgLogo.setVisibility(View.VISIBLE);
            tvDeliLabel.setVisibility(View.VISIBLE);


            //getcurrent time
            final String orderDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


            //calling prefvalue
            pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
            TotalPrice = pref.getInt("TotalPrice", 0);
            TotalQuantity = pref.getInt("TotalQuantity", 0);
            Item = pref.getInt("Item", 0);


            //instaiate and use firebaseauth
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            final String uid = firebaseUser.getUid();


            if (firebaseUser != null) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String username = dataSnapshot.child(uid).child("fullName").getValue(String.class);
                        String address = dataSnapshot.child(uid).child("address").getValue(String.class);
                        String phonenumber = dataSnapshot.child(uid).child("phoneNumber").getValue(String.class);

                        tietuserName.setText(username);
                        tietAddress.setText(address);
                        tietPhonenumber.setText(phonenumber);
                        tvUserTotalItem.setText(TotalQuantity + "/" + Item);
                        tvTotalPrice.setText(TotalPrice + "");
                        tvUserOrderItem.setText(TotalQuantity + "");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final SweetAlertDialog pDialog = new SweetAlertDialog(ComfirmOrder.this, SweetAlertDialog.PROGRESS_TYPE);
                   /* pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.setCancelable(false);*/

                    pDialog.show();

                    productCartModelList = db.getCartItemList();
                    List<Items> itemsList = new ArrayList<>();
                    for (int i = 0; i < productCartModelList.size(); i++) {
                        Items items = new Items(
                                String.valueOf(productCartModelList.get(i).getProductId()),
                                productCartModelList.get(i).getColor(),
                                productCartModelList.get(i).getSize(),
                                String.valueOf(productCartModelList.get(i).getQuantity())
                        );
                        itemsList.add(items);
                    }
                    OrderModel orderModel = new OrderModel(
                            tietuserName.getText().toString(),
                            tietAddress.getText().toString(),
                            tietPhonenumber.getText().toString(),
                            orderDate,
                            String.valueOf(productCartModelList.size()),
                            String.valueOf(TotalQuantity),
                            String.valueOf(TotalPrice),
                            itemsList
                    );

                    if (firebaseUser != null) {

                        String key = FirebaseDatabase.getInstance().getReference().push().getKey();

                        FirebaseDatabase.getInstance().getReference("Orders")
                                .child(key).child(uid)
                                .setValue(orderModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull final Task<Void> task) {
                                if (task.isSuccessful()) {
                                    pDialog.setTitleText("Ordered Successfully!").changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Intent i = new Intent(ComfirmOrder.this, OrderComplete.class);
                                            startActivity(i);


                                            finish();
                                        }
                                    });


                                } else {
                                    pDialog.setTitleText("Error").changeAlertType(SweetAlertDialog.ERROR_TYPE);

                                    Toast.makeText(ComfirmOrder.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
        }
        else
        {
            Toast.makeText(ComfirmOrder.this, "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();

            gifNoInternet.setVisibility(View.VISIBLE);
            pbProgress.setVisibility(View.VISIBLE);
            tvOrderItemLabel.setVisibility(View.GONE);
            tvUserOrderItem.setVisibility(View.GONE);
            tvConfirmOrder.setVisibility(View.GONE);
            tvTotalPriceLabel.setVisibility(View.GONE);
            imgTotalItem.setVisibility(View.GONE);
            imgOrderItem.setVisibility(View.GONE);
            imgTotalPrice.setVisibility(View.GONE);
            tvTotalPriceLabel.setVisibility(View.GONE);
            tvTotalPrice.setVisibility(View.GONE);
            tvUserTotalItem.setVisibility(View.GONE);
            tvTotalItemLabel.setVisibility(View.GONE);
            tietPhonenumber.setVisibility(View.GONE);
            tvPhoneLabel.setVisibility(View.GONE);
            imgPhone.setVisibility(View.GONE);
            tietAddress.setVisibility(View.GONE);
            imgDeliIcon.setVisibility(View.GONE);
            tietuserName.setVisibility(View.GONE);
            imgUserIcon.setVisibility(View.GONE);
            tvNameLabel.setVisibility(View.GONE);
            btnConfirm.setVisibility(View.GONE);
            cimgLogo.setVisibility(View.GONE);
            tvDeliLabel.setVisibility(View.GONE);



        }
    }
    public boolean Network()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) ComfirmOrder.this.getSystemService(ComfirmOrder.this.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

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
