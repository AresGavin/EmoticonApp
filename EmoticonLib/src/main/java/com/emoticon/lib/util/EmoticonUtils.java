package com.emoticon.lib.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.emoticon.lib.model.EmoticonBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Gavin 时间：2016/8/17.
 * 描述：
 */
public class EmoticonUtils {

    public static final String ASSETS_FILE_PATH = "asset:///emoticon/";

    public static List<EmoticonBean> getEmoticonList(Context context) {
        List<EmoticonBean> mEmoticonList = new ArrayList<>();
        String[] mBitmapList = getAssetsBitmap(context, "emoticon");
        for (int i = 0; i < mBitmapList.length; i++) {
            mEmoticonList.add(new EmoticonBean(i, mBitmapList[i], "//表情" + i));
        }
        return mEmoticonList;
    }

    /**
     * 获取Assets目录下的图片资源
     *
     * @param context
     * @param fileName
     * @return
     */
    private static String[] getAssetsBitmap(Context context, String fileName) {
        String[] files = null;
        AssetManager assetManager = context.getAssets();
        try {
            files = assetManager.list(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }
}
