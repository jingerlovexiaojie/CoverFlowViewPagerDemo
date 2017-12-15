package com.example.nala.coverflowviewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/lisdye2/article/details/52315008
 * 自定义View 之利用ViewPager 实现画廊效果（滑动放大缩小）
 *
 */

public class CoverFlowViewPager extends RelativeLayout implements OnPageSelectListener {
    private final ViewPager mViewPager;
    private List<View> mViewList = new ArrayList<>();
    private CoverFlowAdapter mAdapter;

    public CoverFlowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.widget_cover_flow, this);
        mViewPager = (ViewPager) findViewById(R.id.vp_cover_flow);
        init();
    }

    private void init() {
        mAdapter = new CoverFlowAdapter(mViewList, getContext());
        mAdapter.setOnPageSelectListener(this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(mAdapter);
        mViewPager.setOffscreenPageLimit(5);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return mViewPager.dispatchTouchEvent(motionEvent);
            }
        });
    }

    public void setViewList(List<View> list) {
        if (list == null) {
            return;
        }
        mViewList.clear();
        for (View view : list) {
            FrameLayout layout = new FrameLayout(getContext());
            layout.setPadding(CoverFlowAdapter.sWidthPadding, CoverFlowAdapter.sHeightPadding, CoverFlowAdapter.sWidthPadding, CoverFlowAdapter.sHeightPadding);
            layout.addView(view);
            mViewList.add(layout);
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void select(int position) {

    }
}
