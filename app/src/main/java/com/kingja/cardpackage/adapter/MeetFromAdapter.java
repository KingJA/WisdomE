package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetRegister_ConfigList;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：预约时间段适配器
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MeetFromAdapter extends BaseLvAdapter<GetRegister_ConfigList.ContentBean> {
    public MeetFromAdapter(Context context, List<GetRegister_ConfigList.ContentBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_meet_from, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvtime.setText(list.get(position).getOnTime()+"-"+list.get(position).getOffTime());
        viewHolder.tvtotleNum.setText("总共安装数："+list.get(position).getInstallCnt());
        viewHolder.tvavailableNum.setText("可用安装数："+list.get(position).getSurplusNum());
        return convertView;
    }



    public class ViewHolder {
        public final TextView tvtime;
        public final TextView tvtotleNum;
        public final TextView tvavailableNum;
        public final View root;

        public ViewHolder(View root) {
            tvtime = (TextView) root.findViewById(R.id.tv_time);
            tvtotleNum = (TextView) root.findViewById(R.id.tv_totleNum);
            tvavailableNum = (TextView) root.findViewById(R.id.tv_availableNum);
            this.root = root;
        }
    }
}
