package com.example.sakuraboutique.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sakuraboutique.Fragments.SliderFragment1;
import com.example.sakuraboutique.Fragments.SliderFragment10;
import com.example.sakuraboutique.Fragments.SliderFragment2;
import com.example.sakuraboutique.Fragments.SliderFragment3;
import com.example.sakuraboutique.Fragments.SliderFragment4;
import com.example.sakuraboutique.Fragments.SliderFragment5;
import com.example.sakuraboutique.Fragments.SliderFragment6;
import com.example.sakuraboutique.Fragments.SliderFragment7;
import com.example.sakuraboutique.Fragments.SliderFragment8;
import com.example.sakuraboutique.Fragments.SliderFragment9;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position==0)
        {
fragment=new SliderFragment1();
        }
        else if(position==1)
        {
            fragment=new SliderFragment2();

        }
        else if(position==2)
        {
            fragment=new SliderFragment3();

        }
        else if(position==3)
        {
            fragment=new SliderFragment4();

        }
        else if(position==4) {

            fragment = new SliderFragment5();
        }

        else if(position==5)
        {
            fragment=new SliderFragment6();

        }
        else if(position==6)
        {
            fragment=new SliderFragment7();

        }
        else if(position==7)
        {
            fragment=new SliderFragment8();

        }
        else if(position==8)
        {
            fragment=new SliderFragment9();

        }
        else if(position==9)
        {
            fragment=new SliderFragment10();

        }



        return fragment;
    }

    @Override
    public int getCount() {
        return 10;
    }
}
