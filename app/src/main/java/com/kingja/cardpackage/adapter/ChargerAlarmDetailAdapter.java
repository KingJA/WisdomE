package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.kingja.cardpackage.util.TimeUtil;
import com.tdr.wisdome.R;

import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/13 13:45
 * 修改备注：
 */
public class ChargerAlarmDetailAdapter extends BaseLvAdapter<GetChargerWarningInfoList.ContentBean.DataBean> {


    public ChargerAlarmDetailAdapter(Context context, List<GetChargerWarningInfoList.ContentBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_charge_alarm_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.v_readTag.setVisibility(list.get(position).getIsread()==1?View.GONE:View.VISIBLE);
        viewHolder.tv_alarmTime.setText(TimeUtil.getTimeTip(list.get(position).getWarn_time()));
        viewHolder.tv_alarmType.setText(list.get(position).getWarn_msg() + "");
        viewHolder.tv_alarmMsg.setText("充电器在" + list.get(position).getWarn_time() + list.get(position).getWarn_msg()
                + "，已经断电，请尽快处理");
        return convertView;
    }

    public void setAllReaded() {
        for (GetChargerWarningInfoList.ContentBean.DataBean dataBean : list) {
            dataBean.setIsread(1);
        }
        notifyDataSetChanged();
    }

    public void setReaded(int warnId) {
        for (GetChargerWarningInfoList.ContentBean.DataBean dataBean : list) {
            if (dataBean.getWarnid() == warnId) {
                dataBean.setIsread(1);
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolder {
        final TextView tv_alarmType;
        final TextView tv_alarmTime;
        final TextView tv_alarmMsg;
        final View v_readTag;
        public final View root;

        public ViewHolder(View root) {
            v_readTag = root.findViewById(R.id.v_readTag);
            tv_alarmType = (TextView) root.findViewById(R.id.tv_alarmType);
            tv_alarmTime = (TextView) root.findViewById(R.id.tv_alarmTime);
            tv_alarmMsg = (TextView) root.findViewById(R.id.tv_alarmMsg);
            this.root = root;
        }
    }

}
