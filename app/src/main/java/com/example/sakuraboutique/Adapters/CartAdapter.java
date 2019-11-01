package com.example.sakuraboutique.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Models.ProductCartModel;
import com.example.sakuraboutique.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
List<ProductCartModel> productCartModelList;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(productCartModelList.get(position).getURLs()).into(holder.imgCartPhoto);
        holder.tvName.setText(productCartModelList.get(position).getProductName());
        holder.tvPrice.setText(productCartModelList.get(position).getPrice()+"");
        holder.tvSize.setText(productCartModelList.get(position).getSize());

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
}
