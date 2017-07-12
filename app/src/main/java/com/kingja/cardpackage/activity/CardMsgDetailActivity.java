package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.adapter.CardMsgAdapter;
import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.tdr.wisdome.R;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2017/4/19 10:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CardMsgDetailActivity extends BackTitleActivity {

    private GetUserMessagePager.ContentBean.DataBean msg;
    private TextView tv_alarmType;
    private TextView tv_alarmDate;
    private TextView tv_alarmContent;

    @Override
    protected void initVariables() {
        msg = (GetUserMessagePager.ContentBean.DataBean) getIntent().getSerializableExtra("msg");
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
        tv_alarmType.setText(getCardType(msg.getCardCode()));
        tv_alarmDate.setText(msg.getCreateTime());
        tv_alarmContent.setText(msg.getMessage());
    }

    @Override
    protected void setData() {
        setTitle("消息详情");
    }

    public static void goActivity(Context context, GetUserMessagePager.ContentBean.DataBean msg) {
        Intent intent = new Intent(context, CardMsgDetailActivity.class);
        intent.putExtra("msg", msg);
        context.startActivity(intent);
    }

    private String getCardType(String cardCode) {
        String cardType = "未知类型";
        switch (cardCode) {
            case "1001":
                cardType = "我的住房";
                break;
            case "1002":
                cardType = "我的出租房";
                break;
            case "1003":
                cardType = "我的车";
                break;
            case "1004":
                cardType = "我的店";
                break;
            case "1005":
                cardType = "亲情关爱";
                break;
            case "1006":
                cardType = "服务商城";
                break;
            case "1007":
                cardType = "出租房代管";
                break;
        }
        return cardType;
    }
}
