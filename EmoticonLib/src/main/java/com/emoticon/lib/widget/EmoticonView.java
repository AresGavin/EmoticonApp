package com.emoticon.lib.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.emoticon.lib.R;
import com.emoticon.lib.adapter.EmoticonGridAdapter;
import com.emoticon.lib.adapter.EmoticonPageAdapter;
import com.emoticon.lib.model.EmoticonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Gavin 时间：2016/8/16.
 * 描述：
 */
public class EmoticonView extends LinearLayout {

    private Context mContext;

    private List<EmoticonBean> mEmoticonList;// 数据集合

    private ImageView[] mIndexImgSize;// 下标数组

    private int mViewPagerSize;// ViewPager页数

    private int mPageItemCount = 15;// 一页显示item个数

    private int mPageItemColumn = 5;// 一页显示Item列数

    private List<View> mViews;// 每页显示的GridView视图

    private WrapContentViewPager mViewPager;

    private LinearLayout mIndexGroup;// 用来装下标的Group

    private OnEmoticonItemListener mListener;

    public EmoticonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mEmoticonList = null;
        initView();
    }

    public EmoticonView(Context context, List<EmoticonBean> list) {
        super(context);
        this.mContext = context;
        this.mEmoticonList = list;
        initView();
        initIndex();
        setAdapter();
    }

    private void initView() {
        View mPagerView = LayoutInflater.from(mContext).inflate(R.layout.emoticon_view_pager, null);
        mViewPager = (WrapContentViewPager) mPagerView.findViewById(R.id.emoticon_view_pager);
        mIndexGroup = (LinearLayout) mPagerView.findViewById(R.id.emoticon_index_group);
        addView(mPagerView);
    }

    private void initIndex() {
        int m = 1;
        if (mEmoticonList.size() % mPageItemCount == 0) {
            m = 0;
        }
        mViewPagerSize = mEmoticonList.size() / mPageItemCount + m;
        if (0 < mViewPagerSize) {
            mIndexGroup.removeAllViews();
            if (1 == mViewPagerSize) {
                mIndexGroup.setVisibility(View.GONE);
            } else if (1 < mViewPagerSize) {
                mIndexGroup.setVisibility(View.VISIBLE);
                for (int h = 0; h < mViewPagerSize; h++) {
                    ImageView mIndexImg = new ImageView(mContext);
                    mIndexGroup.addView(mIndexImg);
                    LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params.setMargins(5, 0, 5, 0);
                    mIndexImg.setLayoutParams(params);
                    mIndexImg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.draw_emoticon_index_shape));
                }
            }
        }
        if (mViewPagerSize != 1) {
            mIndexImgSize = new ImageView[mViewPagerSize];
            for (int k = 0; k < mViewPagerSize; k++) {
                mIndexImgSize[k] = (ImageView) mIndexGroup.getChildAt(k);
            }
            mIndexImgSize[0].setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.draw_emoticon_index_opt_shape));
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    setCurrentIndex(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    // 设置下标
    private void setCurrentIndex(int position) {
        if (position < 0 || position > mViewPagerSize - 1) {
            return;
        }
        for (int i = 0; i < mIndexImgSize.length; i++){
            mIndexImgSize[i].setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.draw_emoticon_index_shape));
        }
        mIndexImgSize[position].setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.draw_emoticon_index_opt_shape));
    }

    // 设置ViewPager设配器
    private void setAdapter() {
        mViews = new ArrayList<>();
        for (int i = 0; i < mViewPagerSize; i++) {
            mViews.add(getViewPagerItem(i));
        }
        mViewPager.setAdapter(new EmoticonPageAdapter(mViews));
    }

    public void setOnEmoticonItemListener(OnEmoticonItemListener listener){
        if(listener != null){
            this.mListener = listener;
        }
    }

    /**
     * ViewPager每个页面中的GridView布局
     *
     * @param index ViewPager当前页码
     * @return
     */
    private View getViewPagerItem(int index) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.emoticon_grid_view, null);
        WrapContentGridView mEmoticonGrid = (WrapContentGridView) mView.findViewById(R.id.emoticon_grid_view);
        mEmoticonGrid.setNumColumns(mPageItemColumn);
        EmoticonGridAdapter mAdapter = new EmoticonGridAdapter(mContext, mEmoticonList, index, mPageItemCount, mPageItemColumn);
        mEmoticonGrid.setAdapter(mAdapter);
        mEmoticonGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(mListener != null){
                    mListener.onEmoticonListener(mEmoticonList.get(position));
                }
            }
        });
        return mEmoticonGrid;
    }

}
