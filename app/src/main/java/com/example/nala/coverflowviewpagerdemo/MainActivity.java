package com.example.nala.coverflowviewpagerdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CoverFlowViewPager mCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCover = (CoverFlowViewPager) findViewById(R.id.cover);

        List<View> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundColor(Color.parseColor("#" + getRandColorCode()));
            list.add(imageView);
        }
        mCover.setViewList(list);
    }

    public static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;

        return r+g+b;
    }
}
