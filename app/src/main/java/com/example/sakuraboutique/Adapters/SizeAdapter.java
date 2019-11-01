package com.example.sakuraboutique.Adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.ProductDetailed;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder>{
    List<String> sizelist;
    private int selectedPos = RecyclerView.NO_POSITION;

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }
    private SizeAdapter.onRecyclerViewItemClickListener mItemClickListener;

    public void setOnItemClickListener(SizeAdapter.onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public SizeAdapter(List<String> sizelist) {
        this.sizelist = sizelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sizeliteitem,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.tvSize.setText(sizelist.get(position));
        holder.itemView.setSelected(selectedPos == position);
        if(selectedPos==position)
        {
            holder.tvSize.setTextColor(Color.parseColor("#ff80ab"));
        }
        else
        {
            holder.tvSize.setTextColor(Color.parseColor("#000000"));

        }

    }

    @Override
    public int getItemCount() {
        return sizelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        TextView tvSize;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvSize=v.findViewById(R.id.tvSize);
            tvSize.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClickListener(v, getAdapterPosition());
                notifyItemChanged(selectedPos);



                    selectedPos = getLayoutPosition();

                    notifyItemChanged(selectedPos);



            }
        }
    }
}
