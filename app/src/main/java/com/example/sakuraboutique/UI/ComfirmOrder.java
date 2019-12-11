package com.example.sakuraboutique.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sakuraboutique.CartDB.CartDB;
import com.example.sakuraboutique.Models.OrderModel;
import com.example.sakuraboutique.Models.ProductCartModel;
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

public class ComfirmOrder extends AppCompatActivity {
Toolbar tbToolbar;
Button btnConfirm;
TextInputEditText tietuserName,tietAddress,tietPhonenumber;
TextView tvTotalPrice,tvUserTotalItem,tvUserOrderItem;
DatabaseReference databaseReference;
FirebaseUser firebaseUser;
SharedPreferences pref;
int TotalPrice,TotalQuantity,Item;
FirebaseDatabase firebaseDatabase;
List<ProductCartModel> productCartModelList;
CartDB db=new CartDB(ComfirmOrder.this);
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

        //getcurrent time
        final String orderDate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        //setting color to toolbar
        setSupportActionBar(tbToolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>"+"Confirm Your Order"+" </font>"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        //calling prefvalue
        pref = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        TotalPrice=pref.getInt("TotalPrice",0);
        TotalQuantity=pref.getInt("TotalQuantity",0);
        Item=pref.getInt("Item",0);


        //instaiate and use firebaseauth
            firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

            final String uid=firebaseUser.getUid();


if(uid!=null) {
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
        productCartModelList=db.getCartItemList();
                List<OrderModel> orderModelList=new ArrayList<>();
        for(int i=0;i<productCartModelList.size();i++) {
            OrderModel orderModel = new OrderModel(
                    tietuserName.getText().toString(),
                    tietAddress.getText().toString(),
                    tietPhonenumber.getText().toString(),
                    productCartModelList.get(i).getSize(),
                    productCartModelList.get(i).getColor(),
                    String.valueOf(productCartModelList.get(i).getQuantity()),
                    String.valueOf(TotalPrice),
                    String.valueOf(TotalQuantity),
                    String.valueOf(productCartModelList.size()),
                    String.valueOf(productCartModelList.get(i).getProductId()),
                    orderDate
            );
            orderModelList.add(orderModel);

        }


        if (uid != null) {

String key=FirebaseDatabase.getInstance().getReference("Orders").push().getKey();

            FirebaseDatabase.getInstance().getReference()
                    .child(key).child(uid)
                    .setValue(orderModelList).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull final Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ComfirmOrder.this, "Ordered Successfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ComfirmOrder.this, OrderComplete.class);
                        startActivity(i);
                    } else {
                        task.getException();
                    }
                }
            });
        }
    }
});




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
