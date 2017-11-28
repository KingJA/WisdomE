package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetChargerSettingList;
import com.tdr.wisdome.R;

import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/13 13:45
 * 修改备注：
 */
public class AutoChargerConfigAdapter extends BaseLvAdapter<GetChargerSettingList.ContentBean.DataBean> {


    public AutoChargerConfigAdapter(Context context, List<GetChargerSettingList.ContentBean.DataBean> list) {
        super(context, list);
    }


    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_charge_config, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_configTime.setText(list.get(position).getAuto_operate() == 1 ? list.get(position).getAuto_start
                () : list.get(position).getAuto_end());
        viewHolder.tv_configContent.setText((list.get(position).getAuto_operate() == 1 ? "自动开始，" : "自动结束，") + (list
                .get(position).getAuto_frequency() == 1 ? "一次" : "重复"));
        viewHolder.sw_swich.setChecked(list.get(position).getIsdisable() == 0);
        return convertView;
    }


    public class ViewHolder {
        final TextView tv_configTime;
        final TextView tv_configContent;
        final SwitchCompat sw_swich;
        public final View root;

        public ViewHolder(View root) {
            tv_configTime = (TextView) root.findViewById(R.id.tv_configTime);
            tv_configContent = (TextView) root.findViewById(R.id.tv_configContent);
            sw_swich = (SwitchCompat) root.findViewById(R.id.sw_swich);
            this.root = root;
        }
    }
}