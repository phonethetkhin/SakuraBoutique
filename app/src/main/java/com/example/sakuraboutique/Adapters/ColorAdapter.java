package com.example.sakuraboutique.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.R;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    List<com.example.sakuraboutique.Models.Color> colorlist ;
    Context context;
    private int selectedPos = RecyclerView.NO_POSITION;


    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }

    private onRecyclerViewItemClickListener mItemClickListener;

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public ColorAdapter(List<com.example.sakuraboutique.Models.Color> colorlist, Context context) {
        this.colorlist = colorlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.colorlistitem, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder h, final int position) {
        h.itemView.setSelected(selectedPos == position);
        h.cvCardView.setBackgroundColor(Color.parseColor("#"+colorlist.get(position).getColorCode()));

        if (selectedPos == position) {

            h.imgColor.setImageResource(R.drawable.correct);
        } else {
            h.imgColor.setImageDrawable(null);
            h.cvCardView.setBackgroundColor(Color.parseColor("#"+colorlist.get(position).getColorCode()));


        }


    }

    @Override
    public int getItemCount() {
        return colorlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgColor;
        CardView cvCardView;

        public ViewHolder(@NonNull View v) {
            super(v);
            imgColor = v.findViewById(R.id.imgColor);
            imgColor.setOnClickListener(this);
            cvCardView=v.findViewById(R.id.cvCardView);
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
