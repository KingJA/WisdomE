package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetMessagePager;
import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.tdr.wisdome.R;

/**
 * Description:车辆警报详情
 * Create Time:2017/4/19 10:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CarMsgDetailActivity extends BackTitleActivity {

    private GetMessagePager.ContentBean msg;
    private TextView tv_alarmType;
    private TextView tv_alarmDate;
    private TextView tv_alarmContent;

    @Override
    protected void initVariables() {
        msg = (GetMessagePager.ContentBean) getIntent().getSerializableExtra("msg");
    }

    @Override
    protected void initContentView() {
        tv_alarmType = (TextView) findViewById(R.id.tv_alarmType);
        tv_alarmDate = (TextView) findViewById(R.id.tv_alarmDate);
        tv_alarmContent = (TextView) findViewById(R.id.tv_alarmContent);

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_card_msg_detail;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        tv_alarmType.setText(msg.getPLATENUMBER());
        tv_alarmDate.setText(msg.getMONITORTIME());
        tv_alarmContent.setText(msg.getMESSAGE());
    }

    @Override
    protected void setData() {
        setTitle("警报详情");
    }

    public static void goActivity(Context context, GetMessagePager.ContentBean msg) {
        Intent intent = new Intent(context, CarMsgDetailActivity.class);
        intent.putExtra("msg", msg);
        context.startActivity(intent);
    }

}
