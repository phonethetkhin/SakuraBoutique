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
public class SliderFragment3 extends Fragment {

ImageView imgSliderPhoto3;
    public SliderFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment3, container, false);
        imgSliderPhoto3=v.findViewById(R.id.imgSliderphoto3);
        imgSliderPhoto3.setImageResource(R.drawable.dress3);
        return v;    }

}
