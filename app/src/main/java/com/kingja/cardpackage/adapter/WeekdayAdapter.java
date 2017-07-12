package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.Basic_Dictionary_Kj;
import com.kingja.cardpackage.entiy.Weekday;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/18 10:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class WeekdayAdapter extends BaseLvAdapter<Weekday> {


    public WeekdayAdapter(Context context, List<Weekday> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_weekday, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_weekday.setText(list.get(position).getWeekday());
        viewHolder.iv_selected.setVisibility(list.get(position).getChecked() ? View.VISIBLE : View.GONE);
        return convertView;
    }


    public class ViewHolder {
        public final TextView tv_weekday;
        public final ImageView iv_selected;
        public final View root;

        public ViewHolder(View root) {
            tv_weekday = (TextView) root.findViewById(R.id.tv_weekday);
            iv_selected = (ImageView) root.findViewById(R.id.iv_selected);
            this.root = root;
        }
    }

    public void selectWeekday(int position) {
        list.get(position).setChecked(!list.get(position).getChecked());
        notifyDataSetChanged();
    }

    public String getWeekdayInt() {
        StringBuffer sb = new StringBuffer();
        for (Weekday weekday : list) {
            if (weekday.getChecked()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    public String getWeekdayStr() {
        StringBuffer sb = new StringBuffer();
        for (Weekday weekday : list) {
            if (weekday.getChecked()) {
                sb.append(weekday.getWeekday()+" ");
            }
        }
        return sb.toString();
    }
}
