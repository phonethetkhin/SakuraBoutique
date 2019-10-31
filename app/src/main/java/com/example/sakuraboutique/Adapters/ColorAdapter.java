package com.example.sakuraboutique.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.ProductDetailed;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    List<String> colorlist;

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }
    private onRecyclerViewItemClickListener mItemClickListener;

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }



    public ColorAdapter(List<String> colorlist) {
        this.colorlist = colorlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.colorlistitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder h, final int position) {
        h.imgColor.setBackgroundColor(Color.parseColor(colorlist.get(position)));



    }

    @Override
    public int getItemCount() {
        return colorlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        ImageView imgColor;
        public ViewHolder(@NonNull View v) {
            super(v);
            imgColor=v.findViewById(R.id.imgColor);
        imgColor.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());

            }
        }
    }
}
