package com.emoticon.lib.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者：Gavin 时间：2016/8/18.
 * 描述：
 */
public class ImageLoadUtils {

    public static void loadBitmap(String uri, SimpleDraweeView simpleDraweeView) {
        Uri mUri = Uri.parse(uri);
        simpleDraweeView.setImageURI(mUri);
    }
}
