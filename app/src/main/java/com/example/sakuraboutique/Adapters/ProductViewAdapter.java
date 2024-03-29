package com.example.sakuraboutique.Adapters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.ProductDetailed;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ViewHolder>{
    List<ProductModel> ProductModelList;

    public ProductViewAdapter(List<ProductModel> productModelList) {
        ProductModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.productviewlistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
h.tvProductName.setText(ProductModelList.get(position).getProductName());
h.tvPrice.setText(ProductModelList.get(position).getPrice()+" MMK");
        Picasso.get().load(ProductModelList.get(position).getURL()).placeholder(R.drawable.placeholder).into(h.imgProductViewPhoto);


    }

    @Override
    public int getItemCount() {
        return ProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProductViewPhoto;
        TextView tvProductName,tvPrice;
         public ViewHolder(@NonNull View v) {
             super(v);
             v.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent i=new Intent(v.getContext(), ProductDetailed.class);
                     SharedPreferences pref = v.getContext().getSharedPreferences("MY_PREF", MODE_PRIVATE);
                     SharedPreferences.Editor myeditor = pref.edit();
                     myeditor.putInt("ProductID",ProductModelList.get(getAdapterPosition()).getProductID());
                     myeditor.putString("ProductName",ProductModelList.get(getAdapterPosition()).getProductName());

                     myeditor.commit();

                     i.putExtra("ProductName",ProductModelList.get(getAdapterPosition()).getProductName());
                        i.putExtra("Price",ProductModelList.get(getAdapterPosition()).getPrice());
                        i.putExtra("URL",ProductModelList.get(getAdapterPosition()).getURL());
                     v.getContext().startActivity(i);
                 }
             });
             imgProductViewPhoto=v.findViewById(R.id.imgProductViewPhoto);
             tvProductName=v.findViewById(R.id.tvProductName);
             tvPrice=v.findViewById(R.id.tvPrice);

         }
     }
}
