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
public class SliderFragment2 extends Fragment {
ImageView imgSliderPhoto2;

    public SliderFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment2, container, false);
        imgSliderPhoto2=v.findViewById(R.id.imgSliderphoto2);
        imgSliderPhoto2.setImageResource(R.drawable.dress2);
        return v;    }

}
