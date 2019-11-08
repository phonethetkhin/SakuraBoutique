package com.example.sakuraboutique.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.sakuraboutique.Models.CategoryModel;
import com.example.sakuraboutique.Models.ProductModel;
import com.example.sakuraboutique.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
private static List<CategoryModel> CategoryModelList=new ArrayList<>();
private static List<ProductModel> ProductModelList=new ArrayList<>();
   private static List<String> SizeList=new ArrayList<>();
   private static List<String> ColorList=new ArrayList<>();


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
    public static List<String> AddURL2()
    {
        List<String> URLList2=new ArrayList<>();

        URLList2.add("https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp");
        URLList2.add("https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp");
        URLList2.add("https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp");
        URLList2.add("https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp");
        URLList2.add("https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp");

        return URLList2;
    }




    public static List<CategoryModel> AddCategoryData()
    {
        CategoryModelList.add(new CategoryModel("New Arrivals","https://cdn.shopify.com/s/files/1/0028/4560/5988/products/DongCMY-Short-Burgundy-Prom-dresses-2019-new-arrival-fashion-Sexy-Mermaid-Party-Women-Performance-Dress_778x.jpg?v=1563239132"));
        CategoryModelList.add(new CategoryModel("Tops","https://5.imimg.com/data5/JW/FR/MY-30877293/gfgbffh-500x500.jpg"));
        CategoryModelList.add(new CategoryModel("Bottoms","https://s7d9.scene7.com/is/image/Chaps/medium/3074071619699402_001.jpg"));
        CategoryModelList.add(new CategoryModel("Dresses","https://media-api.xogrp.com/images/6eab309b-6d91-4e22-a4f6-1adeb1a9336c~rs_515.h"));
        CategoryModelList.add(new CategoryModel("Traditional Dresses","https://www.marry.com.mm/wp-content/uploads/users/527216/2018/01/23/HH4.jpg"));
        CategoryModelList.add(new CategoryModel("Shoes & Bags","https://www.dhresource.com/0x0/f2/albu/g6/M00/80/A5/rBVaSFt-J8WAb5mHAApa-5t-lmo550.jpg"));
        CategoryModelList.add(new CategoryModel("Accessories","https://www.billini.com/media/catalog/category/EARRINGS.jpg"));
        CategoryModelList.add(new CategoryModel("Men's Fashion","https://www.next.co.uk/nxtcms/resource/image/1891218/portrait_ratio1x1/525/525/3297a1d11424bf65037b6b8d15d6b6ee/dB/g83-mb-sh193231-lm-026.jpg"));
    return CategoryModelList;
    }

    public static List<ProductModel> AddProductData()
    {
        ProductModelList.add(new ProductModel(1,13000,"https://static-01.daraz.pk/p/d09981b024fd95e1ec0c4835423492aa.jpg_340x340q80.jpg_.webp","Black Dress"));
        ProductModelList.add(new ProductModel(2,14000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa7Mnr9TxHo0nBgwkXcr3uRBQKp0eGJ1YNHVHlyJcNXvw50Sc6&s","Grey Dress"));
        ProductModelList.add(new ProductModel(3,15000,"https://i0.wp.com/www.diyfashioncrafts.com/wp-content/uploads/2019/02/a.png?resize=640%2C960","Black Dress"));
        ProductModelList.add(new ProductModel(4,16000,"https://sc02.alicdn.com/kf/HTB1Fmm9wC8YBeNkSnb4q6yevFXas/Fashion-2017-trending-clothes-women-off-shoulder.jpg","White Dress"));
        ProductModelList.add(new ProductModel(5,17000,"https://sc01.alicdn.com/kf/HTB11v_PrIIrBKNjSZK9q6ygoVXau/229630413/HTB11v_PrIIrBKNjSZK9q6ygoVXau.jpg_.webp","Pink Dress"));

        ProductModelList.add(new ProductModel(6,13000,"https://i.pinimg.com/originals/61/d7/3a/61d73a00ba135a3fdb15b6c6790fd729.jpg","Yellow Dress"));
        ProductModelList.add(new ProductModel(7,14000,"https://www.dhresource.com/0x0/f2/albu/g6/M01/25/5B/rBVaSFqilROAXTElAAOEelFrim4637.jpg","Grey Dress"));
        ProductModelList.add(new ProductModel(8,15000,"https://qph.fs.quoracdn.net/main-qimg-2e30501851f6ec5df0e958cffe3c19c5.webp","Black Dress"));
        ProductModelList.add(new ProductModel(9,16000,"https://images-na.ssl-images-amazon.com/images/I/61P%2Bzz8SqpL._UX425_.jpg","White Dress"));
        ProductModelList.add(new ProductModel(10,17000,"https://www.hatfielddaniels.co.uk/images/Free%20Shipping/Women%20s%20Trending%20Fashion%20A-Lab%20Saleh%20Kitty%20Elbow%20Patch%20Blue%20Hoodie%20-%208317547%207196.jpg","Pink Dress"));

        ProductModelList.add(new ProductModel(11,13000,"https://cdn-img-2.wanelo.com/p/2f5/287/58a/11176ff2cda582a580a3c3e/x354-q80.jpg","Yellow Dress"));
        ProductModelList.add(new ProductModel(12,14000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4A68Nw4FTnrHJQTHJOYmYmdjBHwwUBCgl3eLe0zDODV3wWUkE3g&s","Grey Dress"));
        ProductModelList.add(new ProductModel(13,15000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4A68Nw4FTnrHJQTHJOYmYmdjBHwwUBCgl3eLe0zDODV3wWUkE3g&s","Black Dress"));
        ProductModelList.add(new ProductModel(14,16000,"https://1.bp.blogspot.com/-SXypwlYYhtE/XMnOUJgtD7I/AAAAAAAAAAM/hX09NPqD7-osX1l99MxSzt9oWTXngC0RwCLcBGAs/s1600/trending%2Bfashion%2Bfor%2Bwomen.jpg","White Dress"));
        ProductModelList.add(new ProductModel(15,17000,"https://i.pinimg.com/736x/e0/f1/0e/e0f10e9130492c308dde89ff0725a7a4.jpg","Pink Dress"));

        ProductModelList.add(new ProductModel(16,13000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4A68Nw4FTnrHJQTHJOYmYmdjBHwwUBCgl3eLe0zDODV3wWUkE3g&s","Yellow Dress"));
        ProductModelList.add(new ProductModel(17,14000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREDSMHU21QXVgmL83SjslXFqNz1CR6y3ZF8Ml5qoJwmTLa75vw&s","Grey Dress"));
        ProductModelList.add(new ProductModel(18,15000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnRTuc-Mz7HIUVg5O911ZBbrohZVjpoVI7dwvyWKAzlGS0mcLdxA&s","Black Dress"));
        ProductModelList.add(new ProductModel(19,16000,"https://www.hatfielddaniels.co.uk/images/Free%20Shipping/Women%20s%20Trending%20Fashion%20A-Lab%20Saleh%20Kitty%20Elbow%20Patch%20Blue%20Hoodie%20-%208317547%207196.jpg","White Dress"));
        ProductModelList.add(new ProductModel(20,17000,"https://i.pinimg.com/originals/54/51/29/5451295e63b722193939f6899e177324.jpg","Pink Dress"));

        ProductModelList.add(new ProductModel(21,13000,"https://images-na.ssl-images-amazon.com/images/I/61QOSESry2L._UY606_.jpg","Yellow Dress"));
        ProductModelList.add(new ProductModel(22,14000,"https://www.cafefikann.nu/images/image/Womens%20Dresses/Clothing%20-%20Black%20Elegant%203%204%20Sleeves%20Mesh%20Floral%20Print%20Chiffon%20Dress%20For%20Women-20%2085%20Trending%20Fashion%20Es%20M%20BLACK%20-%2012695790%208180.jpg","Grey Dress"));
        ProductModelList.add(new ProductModel(23,15000,"https://shopforaurelia.com/pub/media/catalog/product/cache/be0ecb69ee46c79a2232306733289cad/h/t/httpsshopforaurelia.compubmediaimport19fea10598_700037_1.jpg","Black Dress"));
        ProductModelList.add(new ProductModel(24,16000,"https://pantaloons.imgix.net/img/app/product/2/270931-1086092.jpg?w=412&auto=format","White Dress"));
        ProductModelList.add(new ProductModel(25,17000,"https://i.pinimg.com/474x/79/5b/6d/795b6dc80964b38ab78ce2689156faa0.jpg","Pink Dress"));

        ProductModelList.add(new ProductModel(26,13000,"https://nishuhossain.com/wp-content/uploads/2019/05/NishuHossain-A-Complete-Guide-of-Spring-Summer-2019-Fashion-Trends-.jpg","Yellow Dress"));
        ProductModelList.add(new ProductModel(27,14000,"https://images-na.ssl-images-amazon.com/images/I/614dLJ509ML._UX385_.jpg","Grey Dress"));
        ProductModelList.add(new ProductModel(28,15000,"https://i0.wp.com/glamisse.com/wp-content/uploads/2019/03/Trends-Summer-Fashion-2019-for-Women-21.jpg?w=224&h=411&ssl=1","Black Dress"));
        ProductModelList.add(new ProductModel(29,16000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4A68Nw4FTnrHJQTHJOYmYmdjBHwwUBCgl3eLe0zDODV3wWUkE3g&s","White Dress"));
        ProductModelList.add(new ProductModel(30,17000,"https://cdn.shopify.com/s/files/1/0040/4049/1075/products/product-image-931650091_grande.jpg?v=1555849253","Pink Dress"));
        return ProductModelList;
    }
    public static List<String> AddSizeData()
    {
        SizeList.add("S");
        SizeList.add("M");
        SizeList.add("L");
        SizeList.add("XL");
        SizeList.add("XXL");
        SizeList.add("Free");
        return SizeList;
    }
    public static List<String> AddColorData()
    {
    ColorList.add("#000000");
    ColorList.add("#FFFFFF");
    ColorList.add("#ff80ab");
    ColorList.add("#80000000");
    ColorList.add("#bdbdbd");
    ColorList.add("#42a5f5");
    ColorList.add("#29b6f6");
    ColorList.add("#26c6da");
    ColorList.add("#26a69a");
    ColorList.add("#66bb6a");
    ColorList.add("#9ccc65");
    ColorList.add("#ab47bc");
    ColorList.add("#ec407a");
    ColorList.add("#ef5350");
    return ColorList;

    }

}
