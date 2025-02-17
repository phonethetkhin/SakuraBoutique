package com.example.sakuraboutique.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sakuraboutique.Models.SliderURLModel;
import com.example.sakuraboutique.R;
import com.example.sakuraboutique.UI.ProductDetailed;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideAdapter extends SliderViewAdapter<SlideAdapter.ViewHolder> {
    private Context context;
    private List<SliderURLModel> Urls;

    public SlideAdapter(Context context, List<SliderURLModel> urls) {
        this.context = context;
        this.Urls = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliderlayoutlistitem, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Picasso.get().load(Urls.get(position).getURL()).placeholder(R.drawable.placeholder).into(viewHolder.imageViewBackground);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("MY_PREF", MODE_PRIVATE);
                SharedPreferences.Editor myeditor = pref.edit();
                myeditor.putInt("ProductID", Urls.get(position).getProductID());
                myeditor.putString("ProductName", Urls.get(position).getProductName());

                myeditor.commit();
                Intent i = new Intent(v.getContext(), ProductDetailed.class);
                v.getContext().startActivity(i);
            }
        });

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Urls.size() + 1;
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), ProductDetailed.class);
                    v.getContext().startActivity(i);
                }
            });
            imageViewBackground = v.findViewById(R.id.imgPhoto);
            this.itemView = v;
        }
    }
}
