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
public class SliderFragment5 extends Fragment {
ImageView imgSliderPhoto5;

    public SliderFragment5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment5, container, false);
        imgSliderPhoto5=v.findViewById(R.id.imgSliderphoto5);
        imgSliderPhoto5.setImageResource(R.drawable.dress5);
        return v;
    }

}
