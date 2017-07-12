package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.kingja.cardpackage.util.TimeUtil;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CardMsgAdapter extends BaseLvAdapter<GetUserMessagePager.ContentBean.DataBean> {
    public CardMsgAdapter(Context context, List<GetUserMessagePager.ContentBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_card_msg, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (list.get(position).getIsRead() == 1) {
            viewHolder.ivreaded.setVisibility(View.INVISIBLE );
            viewHolder.tv_card_type.setTextColor(context.getResources().getColor(R.color.font_9));
            viewHolder.iv_alarmType.setBackgroundResource(R.drawable.circle_gray);
            viewHolder.tvalarmmsg.setTextColor(context.getResources().getColor(R.color.font_9));
        }else{
            viewHolder.ivreaded.setVisibility(View.VISIBLE );
            viewHolder.tv_card_type.setTextColor(context.getResources().getColor(R.color.font_3));
            viewHolder.iv_alarmType.setBackgroundResource(R.drawable.circle_blue);
            viewHolder.tvalarmmsg.setTextColor(context.getResources().getColor(R.color.font_6));
        }


        viewHolder.tvalarmtime.setText(TimeUtil.getTimeTip(list.get(position).getCreateTime()));
        viewHolder.tv_card_type.setText(getCardType(list.get(position).getCardCode()));
        viewHolder.tvalarmType.setText(getAlarmType(list.get(position).getMessageType()));
        viewHolder.tvalarmmsg.setText(list.get(position).getMessage());

        return convertView;
    }


    public void setReadedStatus(int position) {
        list.get(position).setIsRead(1);
        this.notifyDataSetChanged();
    }

    public void setAllReadedStatus() {
        for (GetUserMessagePager.ContentBean.DataBean msg : list) {
            msg.setIsRead(1);
        }
        this.notifyDataSetChanged();
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

    public class ViewHolder {
        public final ImageView ivalarm;
        public final TextView tv_card_type;
        public final TextView tvalarmtime;
        public final TextView tvalarmType;
        public final TextView tvalarmmsg;
        public final ImageView ivreaded;
        public final ImageView iv_alarmType;
        public final View root;

        public ViewHolder(View root) {
            ivalarm = (ImageView) root.findViewById(R.id.iv_alarm);
            tv_card_type = (TextView) root.findViewById(R.id.tv_card_type);
            tvalarmtime = (TextView) root.findViewById(R.id.tv_alarm_time);
            tvalarmType = (TextView) root.findViewById(R.id.tv_alarmType);
            tvalarmmsg = (TextView) root.findViewById(R.id.tv_alarm_msg);
            ivreaded = (ImageView) root.findViewById(R.id.iv_readed);
            iv_alarmType = (ImageView) root.findViewById(R.id.iv_alarmType);
            this.root = root;
        }
    }
}
