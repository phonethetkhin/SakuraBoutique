package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {




    public static List<String> AddURL()
 {
     List<String> URLList=new ArrayList<>();

     URLList.add("https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp");
     URLList.add("https://ae01.alicdn.com/kf/HTB1Shb_MVXXXXb2XXXXq6xXFXXXg/men-jacket-men-s-coat-fashion-clothes-hot-sale-autumn-overcoat-outwear-Free-shipping-wholesale-retail.jpg_640x640.jpg");
     URLList.add("https://cdn2.iconfinder.com/data/icons/pick-a-dress/900/dress-dresses-fashion-clothes-clothing-silhouette-shadow-15-512.png");
     URLList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEkpgW2ZqR2cQyqlejf5nlUqWsUbjmRnfCUI0hHG4o4tN6znZj6w&s");
     URLList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEkpgW2ZqR2cQyqlejf5nlUqWsUbjmRnfCUI0hHG4o4tN6znZj6w&s");

     return URLList;
 }


}
