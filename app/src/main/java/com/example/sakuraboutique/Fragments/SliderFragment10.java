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
public class SliderFragment10 extends Fragment {
ImageView imgSliderPhoto10;

    public SliderFragment10() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment10, container, false);
        imgSliderPhoto10=v.findViewById(R.id.imgSliderphoto10);
        imgSliderPhoto10.setImageResource(R.drawable.dress5);
        return v;
    }

}
