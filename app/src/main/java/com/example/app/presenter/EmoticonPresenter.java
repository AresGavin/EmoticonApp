package com.example.app.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.emoticon.lib.model.EmoticonBean;
import com.emoticon.lib.util.EmoticonUtils;
import com.emoticon.lib.widget.EmoticonView;
import com.emoticon.lib.widget.OnEmoticonItemListener;
import com.example.app.biz.IEmoticonView;

import java.util.List;

/**
 * 作者：Gavin 时间：2016/8/19.
 * 描述：
 */
public class EmoticonPresenter {

    private IEmoticonView mIEmoticonView;

    public EmoticonPresenter(IEmoticonView emoticonView){
        this.mIEmoticonView = emoticonView;
    }

    public void loadingEmoticon(final Activity activity){
        // 获取表情资源数据
        List<EmoticonBean> mEmoticonList = EmoticonUtils.getEmoticonList(activity);
        if (mEmoticonList != null && mEmoticonList.size() > 0) {
            // 添加表情View
            EmoticonView mEmoticonView = new EmoticonView(activity, mEmoticonList);
            mIEmoticonView.getEmoticonGroup().addView(mEmoticonView);
            // 事件监听
            mEmoticonView.setOnEmoticonItemListener(new OnEmoticonItemListener() {
                @Override
                public void onEmoticonListener(EmoticonBean emoticon) {
                    Toast.makeText(activity, emoticon.getEmoticonName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
