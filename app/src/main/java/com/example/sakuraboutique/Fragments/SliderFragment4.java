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
public class SliderFragment4 extends Fragment {
ImageView imgSliderPhoto4;

    public SliderFragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment4, container, false);
        imgSliderPhoto4=v.findViewById(R.id.imgSliderphoto4);
        imgSliderPhoto4.setImageResource(R.drawable.dress4);
        return v;

    }

}
