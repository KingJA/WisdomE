package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.tdr.wisdome.R;

import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/13 13:45
 * 修改备注：
 */
public class ChargerAlarmAdapter extends BaseLvAdapter<GetChargerWarningInfoList.ContentBean.DataBean> {


    public ChargerAlarmAdapter(Context context, List<GetChargerWarningInfoList.ContentBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        if (list.size() > 3) {
            return 3;
        }else{
            return super.getCount();
        }
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_charge_alarm, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_alarmTime.setText(list.get(position).getWarn_time());
        viewHolder.tv_alarmMsg.setText("("+list.get(position).getWarn_msg()+")");
        return convertView;
    }


    public class ViewHolder {
        final TextView tv_alarmTime;
        final TextView tv_alarmMsg;
        public final View root;

        public ViewHolder(View root) {
            tv_alarmTime = (TextView) root.findViewById(R.id.tv_alarmTime);
            tv_alarmMsg = (TextView) root.findViewById(R.id.tv_alarmMsg);
            this.root = root;
        }
    }

}
