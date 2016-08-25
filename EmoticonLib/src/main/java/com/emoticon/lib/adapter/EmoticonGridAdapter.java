package com.emoticon.lib.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.emoticon.lib.R;
import com.emoticon.lib.model.EmoticonBean;
import com.emoticon.lib.util.EmoticonUtils;
import com.emoticon.lib.util.ImageLoadUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Gavin 时间：2016/8/16.
 * 描述：
 */
public class EmoticonGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<EmoticonBean> mEmoticonList;

    private int mEmoticonListSize;// 传进来List的大小

    private int mIndex;// 当前ViewPager页码

    private int mPageItemCount;// 每页显示的Item个数

    private int mPageItemColumn; // 每页显示Item列数

    public EmoticonGridAdapter(Context context, List<EmoticonBean> list, int index, int pageItemCount, int pageItemColumn) {
        this.mContext = context;
        this.mIndex = index;
        this.mPageItemCount = pageItemCount;
        this.mPageItemColumn = pageItemColumn;
        mEmoticonList = new ArrayList<>();
        mEmoticonListSize = list.size();
        int mEmoticonListIndex = index * pageItemCount;
        for (int i = mEmoticonListIndex; i < list.size(); i++) {
            mEmoticonList.add(list.get(i));
        }
    }

    @Override
    public int getCount() {
        int mCount = mEmoticonListSize / mPageItemCount;
        if (mIndex == mCount) {
            return mEmoticonListSize - mPageItemCount * mIndex;
        } else {
            return mPageItemCount;
        }
    }

    @Override
    public Object getItem(int position) {
        return mEmoticonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.emoticon_grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mEmoticonLayout = (LinearLayout) view.findViewById(R.id.emoticon_grid_item_layout);
            viewHolder.mEmoticonImg = (SimpleDraweeView) view.findViewById(R.id.emoticon_img);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        // 计算Item布局占屏幕的宽高
        int mEmoticonLayoutSize = getScreenWidth(mContext) / mPageItemColumn;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.mEmoticonLayout.getLayoutParams();
        params.width = mEmoticonLayoutSize;
        params.height = mEmoticonLayoutSize;
        viewHolder.mEmoticonLayout.setLayoutParams(params);

        String filePath = EmoticonUtils.ASSETS_FILE_PATH + mEmoticonList.get(position).getEmoticonUrl();
        ImageLoadUtils.loadBitmap(filePath, viewHolder.mEmoticonImg);
        return view;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    class ViewHolder {
        LinearLayout mEmoticonLayout;
        SimpleDraweeView mEmoticonImg;
    }
}
