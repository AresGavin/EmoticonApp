package com.emoticon.lib.model;

/**
 * 作者：Gavin 时间：2016/8/17.
 * 描述：
 */
public class EmoticonBean {

    private int emoticonId;// 表情符号ID

    private String emoticonUrl;// 表情地址

    private String emoticonName;// 表情昵称

    public EmoticonBean(int emoticonId, String emoticonUrl, String emoticonName) {
        this.emoticonId = emoticonId;
        this.emoticonUrl = emoticonUrl;
        this.emoticonName = emoticonName;
    }

    public int getEmoticonId() {
        return emoticonId;
    }

    public void setEmoticonId(int emoticonId) {
        this.emoticonId = emoticonId;
    }

    public String getEmoticonUrl() {
        return emoticonUrl;
    }

    public void setEmoticonUrl(String emoticonUrl) {
        this.emoticonUrl = emoticonUrl;
    }

    public String getEmoticonName() {
        return emoticonName;
    }

    public void setEmoticonName(String emoticonName) {
        this.emoticonName = emoticonName;
    }
}
