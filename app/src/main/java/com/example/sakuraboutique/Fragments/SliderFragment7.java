package com.example.sakuraboutique.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sakuraboutique.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SliderFragment7 extends Fragment {
ImageView imgSliderPhoto7;

    public SliderFragment7() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment7, container, false);
        imgSliderPhoto7=v.findViewById(R.id.imgSliderphoto7);
        imgSliderPhoto7.setImageResource(R.drawable.dress2);
        return v;    }

}
