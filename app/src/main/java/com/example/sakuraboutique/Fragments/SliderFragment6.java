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
public class SliderFragment6 extends Fragment {
ImageView imgSliderPhoto6;

    public SliderFragment6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment6, container, false);
        imgSliderPhoto6=v.findViewById(R.id.imgSliderphoto6);
        imgSliderPhoto6.setImageResource(R.drawable.dress1);
        return v;
    }

}
