package com.example.sakuraboutique.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sakuraboutique.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideAdapter2 extends SliderViewAdapter<SlideAdapter2.ViewHolder> {
    private Context context;
    private List<String> Urls;

    public SlideAdapter2(Context context, List<String> urls) {
        this.context = context;
        Urls = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sliderlayoutlistitem2,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Picasso.get().load(Urls.get(position)).placeholder(R.drawable.placeholder).into(viewHolder.imageViewBackground);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Urls.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        public ViewHolder(View v) {
            super(v);
            imageViewBackground = v.findViewById(R.id.imgPhoto);
            this.itemView = v;
        }
    }
}
