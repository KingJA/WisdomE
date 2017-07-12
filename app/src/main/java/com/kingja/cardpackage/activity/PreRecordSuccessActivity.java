package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.Event.GetPreRegisterListEvent;
import com.kingja.cardpackage.Event.OnSwtichEvent;
import com.kingja.cardpackage.util.CheckUtil;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Description：TODO
 * Create Time：2017/1/21 14:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRecordSuccessActivity extends BackTitleActivity {

    private TextView mTvTjsite;
    private String recordSite;
    private TextView mTvConfirm;

    @Override
    protected void initVariables() {
        recordSite = getIntent().getStringExtra("RecordSite");
    }

    @Override
    protected void initContentView() {
        mTvTjsite = (TextView) findViewById(R.id.tv_tj_site);
        mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_pre_success;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvTjsite.setVisibility(CheckUtil.checkCity("天津市") ? View.VISIBLE : View.GONE);
        mTvTjsite.setText("请到" + recordSite + "办理登记手续");
        EventBus.getDefault().post(new OnSwtichEvent());
        EventBus.getDefault().post(new GetPreRegisterListEvent());
    }

    @Override
    protected void setData() {
        setTitle("预登记");
    }


    public static void goActivity(Activity activity, String recordSite) {
        Intent intent = new Intent(activity, PreRecordSuccessActivity.class);
        intent.putExtra("RecordSite", recordSite);
        activity.startActivity(intent);
    }
}
