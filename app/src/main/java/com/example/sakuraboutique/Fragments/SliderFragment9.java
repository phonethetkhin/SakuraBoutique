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
public class SliderFragment9 extends Fragment {
ImageView imgSliderPhoto9;

    public SliderFragment9() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_slider_fragment9, container, false);
        imgSliderPhoto9=v.findViewById(R.id.imgSliderphoto9);
        imgSliderPhoto9.setImageResource(R.drawable.dress4);
        return v;    }

}
