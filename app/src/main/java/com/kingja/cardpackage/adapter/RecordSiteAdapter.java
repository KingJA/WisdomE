package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetRegistersiteList;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RecordSiteAdapter extends BaseLvAdapter<GetRegistersiteList.ContentBean> {
    public RecordSiteAdapter(Context context, List<GetRegistersiteList.ContentBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_record_site, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvsiteName.setText("名称: "+list.get(position).getRegistersiteName());
        viewHolder.tvsiteAddress.setText("地址: "+list.get(position).getAdress());
        return convertView;
    }


    public class ViewHolder {
        public final TextView tvsiteName;
        public final TextView tvsiteAddress;
        public final View root;

        public ViewHolder(View root) {
            tvsiteName = (TextView) root.findViewById(R.id.tv_siteName);
            tvsiteAddress = (TextView) root.findViewById(R.id.tv_siteAddress);
            this.root = root;
        }
    }
}
