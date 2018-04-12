package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetChargerRecordList;
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
public class ChargerRecordAdapter extends BaseLvAdapter<GetChargerRecordList.ContentBean.DataBean> {


    public ChargerRecordAdapter(Context context, List<GetChargerRecordList.ContentBean.DataBean> list) {
        super(context, list);
    }


    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_charge_record, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_chargerTime.setText(list.get(position).getCharger_starttime());
        viewHolder.tv_cost.setText(TimeUtil.getDuringTime(list.get(position).getCharger_starttime(),list.get(position).getCharger_endtime()));
        viewHolder.tv_chargerTotle.setText(list.get(position).getElectricity_total()+"千瓦时");
        return convertView;
    }


    public class ViewHolder {
        final TextView tv_chargerTime;
        final TextView tv_chargerTotle;
        final TextView tv_cost;
        public final View root;

        public ViewHolder(View root) {
            tv_chargerTime = (TextView) root.findViewById(R.id.tv_chargerTime);
            tv_chargerTotle = (TextView) root.findViewById(R.id.tv_chargerTotle);
            tv_cost = (TextView) root.findViewById(R.id.tv_cost);
            this.root = root;
        }
    }

}
