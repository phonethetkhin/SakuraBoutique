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
    int index = -1;

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
        holder.tvSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                notifyDataSetChanged();
                Intent i=new Intent(v.getContext(), ProductDetailed.class);
                i.putExtra("Size",sizelist.get(position));
                v.getContext().startActivity(i);
            }
        });
        if(index==position){
            holder.tvSize.setTextColor(Color.parseColor("#ff80ab"));
        }else{
            holder.tvSize.setTextColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public int getItemCount() {
        return sizelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSize;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvSize=v.findViewById(R.id.tvSize);
        }
    }
}
