package com.example.app.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.app.R;
import com.example.app.biz.IEmoticonView;
import com.example.app.presenter.EmoticonPresenter;

public class MainActivity extends AppCompatActivity implements IEmoticonView {

    private LinearLayout mEmoticonGroup;

    private EmoticonPresenter presenter = new EmoticonPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmoticonGroup = (LinearLayout) findViewById(R.id.emoji_layout);
        initData();
    }

    private void initData() {
        presenter.loadingEmoticon(this);
    }

    @Override
    public LinearLayout getEmoticonGroup() {
        return mEmoticonGroup;
    }
}
