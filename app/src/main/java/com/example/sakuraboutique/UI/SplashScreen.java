package com.example.sakuraboutique.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.sakuraboutique.R;

public class SplashScreen extends AppCompatActivity {
private final int SPLASH_TIMER=6000;
ImageView imgLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imgLogo=findViewById(R.id.imgLogo);
imgLogo.setImageResource(R.mipmap.ic_launcher_round);
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
Intent i=new Intent(SplashScreen.this,MainActivity.class);
startActivity(i);
finish();

    }
},SPLASH_TIMER);

    }
}
