package com.example.sakuraboutique.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.ProductView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylistitem,parent,false);
ViewHolder holder=new ViewHolder(v);
return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(categoryModelList.get(position).getCategoryName());
        Picasso.get().load(categoryModelList.get(position).getPhotoURL()).into(holder.imgCategoryPhoto);


        holder.cvCategory.setPreventCornerOverlap(true);
holder.cvCategory.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(v.getContext(), ProductView.class);
        i.putExtra("CategoryName",categoryModelList.get(position).getCategoryName());

        v.getContext().startActivity(i);
    }
});

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName;
        ImageView imgCategoryPhoto;
        CardView cvCategory;
        public ViewHolder(@NonNull View v) {
            super(v);
            cvCategory=v.findViewById(R.id.cvCategory);
            tvCategoryName=v.findViewById(R.id.tvCategoryName);
            imgCategoryPhoto=v.findViewById(R.id.imgCategoryPhoto);


        }
    }
}
