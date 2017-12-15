package com.example.nala.coverflowviewpagerdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Nala on 2017/11/29.
 */

public class CoverFlowAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    public static int sWidthPadding;
    public static int sHeightPadding;
    private List<View> mViewList;
    //滑动监听的回调接口
    private OnPageSelectListener listener;
    private Context context;

    public CoverFlowAdapter(List<View> mViewList, Context context) {
        this.mViewList = mViewList;
        this.context = context;
        //设置Paddin值
        sWidthPadding = dp2px(24);
        sHeightPadding = dp2px(32);
    }

    private int dp2px(int dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewList.get(position);
        container.addView(mViewList.get(position));
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mViewList.size() > 0 && position < mViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            int outHeightPadding = (int) (positionOffset * sHeightPadding);
            int outWidthPadding = (int) (positionOffset * sWidthPadding);
            // 从0滑动到一时，此时position = 0，其应该是缩小的，符合
            mViewList.get(position).setPadding(outWidthPadding, outHeightPadding, outWidthPadding, outHeightPadding);

            // position+1 为即将显示的页面，越来越大
            if (position < mViewList.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding);
                int inHeightPadding = (int) ((1 - positionOffset) * sHeightPadding);
                mViewList.get(position + 1).setPadding(inWidthPadding, inHeightPadding, inWidthPadding, inHeightPadding);
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.listener = listener;
    }


}
