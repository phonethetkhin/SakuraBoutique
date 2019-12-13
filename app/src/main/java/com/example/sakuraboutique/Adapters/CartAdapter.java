package com.example.sakuraboutique.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.CartDB.CartDB;
import com.example.sakuraboutique.Interfaces.DataTransferInterface;
import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.CartActivity;
import com.example.sakuraboutique.UI.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<ProductCartModel> productCartModelList;
    Context context;
    int TotalPrice;
    int Count1 = 0, Count2 = 0;
    int quantity = 0;
    int TotalQuantity = 0;
    int FinalTotalPrice = 0;
    DataTransferInterface dtInterface;


    public CartAdapter(List<ProductCartModel> productCartModelList,Context context,DataTransferInterface dtInterface) {
        this.context=context;
        this.productCartModelList = productCartModelList;
        this.dtInterface = dtInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlistitem, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Picasso.get().load(productCartModelList.get(position).getURLs()).placeholder(R.drawable.placeholder).into(holder.imgCartPhoto);
        holder.tvName.setText(productCartModelList.get(position).getProductName());
        holder.tvSize.setText(" - "+productCartModelList.get(position).getSize());
        holder.imgColor.setBackgroundColor(Color.parseColor("#"+productCartModelList.get(position).getColor()));
        holder.imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
               if(productCartModelList.size()==1)
               {
                   new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                           .setTitleText("Last Item in the Cart")
                           .setContentText("You Want to Remove?")
                           .setConfirmText("Yes!")
                           .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                               @Override
                               public void onClick(final SweetAlertDialog sDialog) {
                                   CartDB db=new CartDB(v.getContext());

                                   ProductCartModel pcm=db.checkExistingProduct(productCartModelList.get(position).getProductId(),productCartModelList.get(position).getSize(),productCartModelList.get(position).getColor());

                                   db.DeleteCartItem(pcm.getProductId(), pcm.getSize(), pcm.getColor());
                                   productCartModelList.remove(position);
                                   notifyDataSetChanged();

                                   SharedPreferences pref = v.getContext().getSharedPreferences("MY_PREF", MODE_PRIVATE);

                                   int  cartQuantity=pref.getInt("Cart_Quantity",0);
                                   int cqty=cartQuantity-1;

                                   SharedPreferences.Editor myeditor = pref.edit();
                                   myeditor.putInt("Cart_Quantity", cqty);


                                   myeditor.apply();

                                   sDialog
                                           .setTitleText("Cart is Clear! Let's Shop Again!")
                                           .setContentText("")
                                           .setConfirmText("OK")
                                           .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                               @Override
                                               public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Intent i=new Intent(v.getContext(), MainActivity.class);
                                            v.getContext().startActivity(i);
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
               else
               {
                   new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                           .setTitleText("Are you sure?")
                           .setContentText("To Remove this Item!")
                           .setConfirmText("Yes!")
                           .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                               @Override
                               public void onClick(final SweetAlertDialog sDialog) {
                                   CartDB db=new CartDB(v.getContext());

                                   ProductCartModel pcm=db.checkExistingProduct(productCartModelList.get(position).getProductId(),productCartModelList.get(position).getSize(),productCartModelList.get(position).getColor());

                                   db.DeleteCartItem(pcm.getProductId(), pcm.getSize(), pcm.getColor());
                                   productCartModelList.remove(position);
                                   notifyDataSetChanged();

                                   SharedPreferences pref = v.getContext().getSharedPreferences("MY_PREF", MODE_PRIVATE);

                                   int  cartQuantity=pref.getInt("Cart_Quantity",0);
                                   int cqty=cartQuantity-1;

                                   SharedPreferences.Editor myeditor = pref.edit();
                                   myeditor.putInt("Cart_Quantity", cqty);


                                   myeditor.apply();

                                   sDialog
                                           .setTitleText("1 Item Removed!")
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
            }
        });

        quantity = productCartModelList.get(position).getQuantity();


        TotalPrice = (productCartModelList.get(position).getPrice() * quantity);
        holder.tvPrice.setText(TotalPrice + "");
        productCartModelList.get(position).setTotalPrice(TotalPrice);
        holder.tvQuantity.setText(productCartModelList.get(position).getQuantity() + "");


        holder.imgbtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.tvQuantity.getText().toString());
                Count1 = quantity;
                ++Count1;
                ++Count2;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.blink);
                holder.imgbtnPlus.startAnimation(myFadeInAnimation);
                TotalPrice = ((productCartModelList.get(position).getPrice()) * Count1);
                holder.tvPrice.setText(TotalPrice + "");

                holder.tvQuantity.setText(Count1 + "");
                TotalQuantity = Calculate1(Count2);

                productCartModelList.get(position).setTotalPrice(TotalPrice);
                TotalPrice=CalculatePrice();

                dtInterface.setValues(TotalQuantity,TotalPrice);


            }
        });
        holder.imgbtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                quantity = Integer.parseInt(holder.tvQuantity.getText().toString());
                Count1 = quantity;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.blink);
                holder.imgbtnMinus.startAnimation(myFadeInAnimation);
                if (Count1 != 1) {
                    --Count1;
                    --Count2;
                }
                else if(productCartModelList.size()==1)
                {
                    new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Last Item in the Cart")
                            .setContentText("You Want to Remove?")
                            .setConfirmText("Yes!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(final SweetAlertDialog sDialog) {
                                    CartDB db=new CartDB(v.getContext());

                                    ProductCartModel pcm=db.checkExistingProduct(productCartModelList.get(position).getProductId(),productCartModelList.get(position).getSize(),productCartModelList.get(position).getColor());

                                    db.DeleteCartItem(pcm.getProductId(), pcm.getSize(), pcm.getColor());
                                    productCartModelList.remove(position);
                                    notifyDataSetChanged();

                                    SharedPreferences pref = v.getContext().getSharedPreferences("MY_PREF", MODE_PRIVATE);

                                    int  cartQuantity=pref.getInt("Cart_Quantity",0);
                                    int cqty=cartQuantity-1;

                                    SharedPreferences.Editor myeditor = pref.edit();
                                    myeditor.putInt("Cart_Quantity", cqty);


                                    myeditor.apply();

                                    sDialog
                                            .setTitleText("Cart is Clear! Let's Shop Again!")
                                            .setContentText("")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    Intent i=new Intent(v.getContext(), MainActivity.class);
                                                    v.getContext().startActivity(i);
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

                else
                {
                    new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("You About To Remove")
                            .setContentText("Last Quantity of This Item, Remove this Item?")
                            .setConfirmText("Yes!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(final SweetAlertDialog sDialog) {
                                    CartDB db=new CartDB(v.getContext());

                                    ProductCartModel pcm=db.checkExistingProduct(productCartModelList.get(position).getProductId(),productCartModelList.get(position).getSize(),productCartModelList.get(position).getColor());

                                    db.DeleteCartItem(pcm.getProductId(), pcm.getSize(), pcm.getColor());
                                    productCartModelList.remove(position);
                                    notifyDataSetChanged();

                                    SharedPreferences pref = v.getContext().getSharedPreferences("MY_PREF", MODE_PRIVATE);

                                    int  cartQuantity=pref.getInt("Cart_Quantity",0);
                                    int cqty=cartQuantity-1;

                                    SharedPreferences.Editor myeditor = pref.edit();
                                    myeditor.putInt("Cart_Quantity", cqty);


                                    myeditor.apply();

                                    sDialog
                                            .setTitleText("Item Removed!")
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

                holder.tvQuantity.setText(Count1 + "");
                TotalPrice = ((productCartModelList.get(position).getPrice()) * Count1);
                holder.tvPrice.setText(TotalPrice + "");
                TotalQuantity = Calculate1(Count2);
                productCartModelList.get(position).setTotalPrice(TotalPrice);

                TotalPrice=CalculatePrice();
                dtInterface.setValues(TotalQuantity,TotalPrice);


            }
        });
        TotalQuantity = Calculate1(Count2);
TotalPrice=Calculate();



        dtInterface.setValues(TotalQuantity,TotalPrice);


    }

    @Override
    public int getItemCount() {
        return productCartModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartPhoto,imgMore,imgColor;
        TextView tvName, tvPrice, tvSize,tvQuantity;
        ImageButton imgbtnPlus, imgbtnMinus;

        public ViewHolder(@NonNull View v) {
            super(v);
            imgCartPhoto = v.findViewById(R.id.imgCartPhoto);
            tvName = v.findViewById(R.id.tvName);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvSize = v.findViewById(R.id.tvSizeCart);
            imgbtnPlus = v.findViewById(R.id.imgbtnPlus);
            imgbtnMinus = v.findViewById(R.id.imgbtnMinus);
            tvQuantity = v.findViewById(R.id.tvQuantity);
            imgMore=v.findViewById(R.id.imgmore);
            imgColor=v.findViewById(R.id.imgColor);
            tvQuantity=v.findViewById(R.id.tvQuantity);
        }
    }

    public int Calculate1(int Count) {

        for (int i = 0; i < productCartModelList.size(); i++) {

            int totalquantity = 0;
            totalquantity = CalculateQuantity();
            TotalQuantity = Count + totalquantity;
        }
        return TotalQuantity;

    }
    public int CalculatePrice()
    {            int totalprice=0;
for(int i=0;i<productCartModelList.size();i++)
{
    totalprice+=productCartModelList.get(i).getTotalPrice();

}
        return totalprice;
    }

    public int Calculate() {

        int totalPrice = 0;
        for (int i = 0; i < productCartModelList.size(); i++) {
            totalPrice += productCartModelList.get(i).getPrice() * productCartModelList.get(i).getQuantity();
        }

        return totalPrice;
    }

    public int CalculateQuantity() {

        int totalquantity = 0;
        for (int i = 0; i < productCartModelList.size(); i++) {
            totalquantity += productCartModelList.get(i).getQuantity();
        }

        return totalquantity;

    }
}

