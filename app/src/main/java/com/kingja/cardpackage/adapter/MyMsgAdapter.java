package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetClaimInfoList;
import com.kingja.cardpackage.entiy.GetLastUserMessage;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyMsgAdapter extends BaseRvAdaper<GetLastUserMessage.ContentBean> {


    public MyMsgAdapter(Context context, List<GetLastUserMessage.ContentBean> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new MyMsgViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_my_msg;
    }

    @Override
    protected void bindHolder(final ViewHolder baseHolder, final GetLastUserMessage.ContentBean bean, final int
            position) {
        final MyMsgViewHolder holder = (MyMsgViewHolder) baseHolder;
        holder.tvalarmtime.setText(TimeUtil.getTimeTip(list.get(position).getCreateTime()));
        holder.tv_card_type.setText(getCardType(list.get(position).getCardCode()));
        holder.tvalarmType.setText(getAlarmType(list.get(position).getMessageType()));
        holder.tvalarmmsg.setText(list.get(position).getMessage());
        holder.ivreaded.setVisibility(list.get(position).getIsRead() == 1 ? View.INVISIBLE : View.VISIBLE);
    }




    class MyMsgViewHolder extends ViewHolder {
        public final ImageView ivalarm;
        public final TextView tv_card_type;
        public final TextView tvalarmtime;
        public final TextView tvalarmType;
        public final TextView tvalarmmsg;
        public final ImageView ivreaded;

        public MyMsgViewHolder(View itemView) {
            super(itemView);
            ivalarm = (ImageView) itemView.findViewById(R.id.iv_alarm);
            tv_card_type = (TextView) itemView.findViewById(R.id.tv_card_type);
            tvalarmtime = (TextView) itemView.findViewById(R.id.tv_alarm_time);
            tvalarmType = (TextView) itemView.findViewById(R.id.tv_alarmType);
            tvalarmmsg = (TextView) itemView.findViewById(R.id.tv_alarm_msg);
            ivreaded = (ImageView) itemView.findViewById(R.id.iv_readed);
        }
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

    private String getAlarmType(int messageType) {
        String alartType = "未知类型";
        switch (messageType) {
            case 1:
                alartType = "普通";
                break;
            case 2:
                alartType = "报警";
                break;
            case 3:
                alartType = "预警";
                break;
        }
        return alartType;
    }
}
