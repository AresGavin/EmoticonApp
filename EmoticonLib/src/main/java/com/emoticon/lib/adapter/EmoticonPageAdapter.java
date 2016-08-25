package com.emoticon.lib.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：Gavin 时间：2016/8/16.
 * 描述：
 */
public class EmoticonPageAdapter extends PagerAdapter {

    private List<View> mViews;

    public EmoticonPageAdapter(List<View> list) {
        this.mViews = list;
    }

    @Override
    public int getCount() {
        return mViews == null ? 0 : mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            // 解决View只能滑动两屏的方法
            ViewGroup parent = (ViewGroup) mViews.get(position).getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            container.addView(mViews.get(position), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView(mViews.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
