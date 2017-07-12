package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.IndexData;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CarBindAdapter extends BaseLvAdapter<IndexData.ContentBean.BindingListBean> {
    public CarBindAdapter(Context context, List<IndexData.ContentBean.BindingListBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_left_right, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getPlateNumber());
        viewHolder.tvoperation.setText(list.get(position).getStatus()==1?"已布防":"未布防");
        viewHolder.tvoperation.setTextColor(list.get(position).getStatus()==1?context.getResources().getColor(R.color.bg_blue):context.getResources().getColor(R.color.bg_red));

        return convertView;
    }


    public class ViewHolder {
        public final TextView name;
        public final TextView tvoperation;
        public final View root;

        public ViewHolder(View root) {
            name = (TextView) root.findViewById(R.id.name);
            tvoperation = (TextView) root.findViewById(R.id.tv_operation);
            this.root = root;
        }
    }

    public void setBufang(int status,String plateNumber) {
        for (IndexData.ContentBean.BindingListBean bindBean : list) {
            if (plateNumber.equals(bindBean.getPlateNumber())) {
                bindBean.setStatus(status);
                break;
            }
        }
        this.notifyDataSetChanged();
    }
}
