package com.example.sakuraboutique.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.ProductDetailed;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
List<ProductCartModel> productCartModelList;
int TotalPrice;
    int Count=0;
    int quantity=0;


    public CartAdapter(List<ProductCartModel> productCartModelList) {
        this.productCartModelList = productCartModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlistitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Picasso.get().load(productCartModelList.get(position).getURLs()).into(holder.imgCartPhoto);
        holder.tvName.setText(productCartModelList.get(position).getProductName());
        holder.tvPrice.setText(productCartModelList.get(position).getPrice()+"");
        holder.tvSize.setText(productCartModelList.get(position).getSize());
        holder.etQuantity.setText(Count + "");
        holder.imgbtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.etQuantity.getText().toString());
                Count = quantity;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.blink);
                holder.imgbtnPlus.startAnimation(myFadeInAnimation);
                ++Count;
                holder.etQuantity.setText(Count + "");


            }
        });
        holder.imgbtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.etQuantity.getText().toString());

                Count = quantity;
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.blink);
                holder.imgbtnMinus.startAnimation(myFadeInAnimation);
                if (Count != 0) {
                    --Count;
                }

                holder.etQuantity.setText(Count + "");

            }
        });


    }

    @Override
    public int getItemCount() {
        return productCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCartPhoto;
        TextView tvName,tvPrice,tvSize;
        ImageButton imgbtnPlus,imgbtnMinus;
        EditText etQuantity;
        public ViewHolder(@NonNull View v) {
            super(v);
            imgCartPhoto=v.findViewById(R.id.imgCartPhoto);
            tvName=v.findViewById(R.id.tvName);
            tvPrice=v.findViewById(R.id.tvPrice);
            tvSize=v.findViewById(R.id.tvSizeCart);
            imgbtnPlus=v.findViewById(R.id.imgbtnPlus);
            imgbtnMinus=v.findViewById(R.id.imgbtnMinus);
            etQuantity=v.findViewById(R.id.etQuantity);
        }
    }
    public int Calculate()
    {
        TotalPrice=0;
        for(int i=0;i<productCartModelList.size();i++)
        {
            TotalPrice+=productCartModelList.get(i).getPrice();

        }
        return TotalPrice;
    }

}
