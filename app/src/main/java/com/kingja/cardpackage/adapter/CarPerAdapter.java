package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.IndexData;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.CarQrActivity;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CarPerAdapter extends BaseLvAdapter<IndexData.ContentBean.PreRateBean> {
    private OnPreRecordDeleteListener onPreRecordDeleteListener;

    public CarPerAdapter(Context context, List<IndexData.ContentBean.PreRateBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_left_right_del, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getPrerateName());
        if (list.get(position).getState() == 0) {
            viewHolder.tvoperation.setText("去登记");
            viewHolder.tvoperation.setTextColor(context.getResources().getColor(R.color.colorStatus));
        } else if (list.get(position).getState() == 1) {
            viewHolder.tvoperation.setText("失效");
            viewHolder.tvoperation.setTextColor(context.getResources().getColor(R.color.colorHint));
        } else {
            viewHolder.tvoperation.setText("已使用");
            viewHolder.tvoperation.setTextColor(context.getResources().getColor(R.color.colorHint));
        }
        viewHolder.tv_delete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onPreRecordDeleteListener != null) {
                    onPreRecordDeleteListener.onPreRecordDelete(list.get(position), position);
                }
            }
        });
        viewHolder.tvoperation.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                CarQrActivity.goActivity(context, list.get(position));
            }
        });
        return convertView;
    }

    public interface OnPreRecordDeleteListener {
        void onPreRecordDelete(IndexData.ContentBean.PreRateBean preRateBean, int position);
    }

    public void setOnPreRecordDeleteListener(OnPreRecordDeleteListener onPreRecordDeleteListener) {
        this.onPreRecordDeleteListener = onPreRecordDeleteListener;
    }


    public class ViewHolder {
        public final TextView name;
        public final TextView tvoperation;
        public final TextView tv_delete;
        public final View root;

        public ViewHolder(View root) {
            name = (TextView) root.findViewById(R.id.name);
            tvoperation = (TextView) root.findViewById(R.id.tv_operation);
            tv_delete = (TextView) root.findViewById(R.id.tv_delete);
            this.root = root;
        }
    }
}
