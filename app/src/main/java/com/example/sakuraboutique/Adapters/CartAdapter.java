package com.example.sakuraboutique.Adapters;

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

import com.example.sakuraboutique.Interfaces.DataTransferInterface;
import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<ProductCartModel> productCartModelList;
    int TotalPrice;
    int Count1 = 0, Count2 = 0;
    int quantity = 0;
    int TotalQuantity = 0;
    int FinalTotalPrice = 0;
    DataTransferInterface dtInterface;


    public CartAdapter(List<ProductCartModel> productCartModelList, DataTransferInterface dtInterface) {
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
        Picasso.get().load(productCartModelList.get(position).getURLs()).into(holder.imgCartPhoto);
        holder.tvName.setText(productCartModelList.get(position).getProductName());
        holder.tvSize.setText(productCartModelList.get(position).getSize());
        quantity = productCartModelList.get(position).getQuantity();


        TotalPrice = (productCartModelList.get(position).getPrice() * quantity);
        holder.tvPrice.setText(TotalPrice + "");
        productCartModelList.get(position).setTotalPrice(TotalPrice);
        holder.etQuantity.setText(productCartModelList.get(position).getQuantity() + "");
        holder.etQuantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                try {
                    if (holder.etQuantity != null) {
                        quantity = Integer.parseInt(holder.etQuantity.getText().toString());

                        TotalPrice = (productCartModelList.get(position).getPrice() * quantity);
                        holder.tvPrice.setText(TotalPrice + "");

                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        holder.imgbtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.etQuantity.getText().toString());
                Count1 = quantity;
                ++Count1;
                ++Count2;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.blink);
                holder.imgbtnPlus.startAnimation(myFadeInAnimation);
                TotalPrice = ((productCartModelList.get(position).getPrice()) * Count1);
                holder.tvPrice.setText(TotalPrice + "");

                holder.etQuantity.setText(Count1 + "");
                TotalQuantity = Calculate1(Count2);

                productCartModelList.get(position).setTotalPrice(TotalPrice);
                TotalPrice=CalculatePrice();

                dtInterface.setValues(TotalQuantity,TotalPrice);


            }
        });
        holder.imgbtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.etQuantity.getText().toString());
                Count1 = quantity;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.blink);
                holder.imgbtnMinus.startAnimation(myFadeInAnimation);
                if (Count1 != 1) {
                    --Count1;
                    --Count2;


                }

                holder.etQuantity.setText(Count1 + "");
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
        ImageView imgCartPhoto;
        TextView tvName, tvPrice, tvSize;
        ImageButton imgbtnPlus, imgbtnMinus;
        EditText etQuantity;

        public ViewHolder(@NonNull View v) {
            super(v);
            imgCartPhoto = v.findViewById(R.id.imgCartPhoto);
            tvName = v.findViewById(R.id.tvName);
            tvPrice = v.findViewById(R.id.tvPrice);
            tvSize = v.findViewById(R.id.tvSizeCart);
            imgbtnPlus = v.findViewById(R.id.imgbtnPlus);
            imgbtnMinus = v.findViewById(R.id.imgbtnMinus);
            etQuantity = v.findViewById(R.id.etQuantity);
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
