package com.tdr.wisdome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.City;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * 查询结果适配器
 * Created by xiezu on 2016/8/5.
 */
public class ResultListAdapter extends BaseAdapter {
    private Context mContext;
    private List<City> mCities;

    public ResultListAdapter(Context mContext, List<City> mCities) {
        this.mCities = mCities;
        this.mContext = mContext;
    }

    public class ViewHolder {
        public TextView text_resultName;
    }

    public void changeData(List<City> list) {
        if (mCities == null) {
            mCities = list;
        } else {
            mCities.clear();
            mCities.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public City getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.resultcity_item, null);
            holder = new ViewHolder();
            holder.text_resultName = (TextView) convertView.findViewById(R.id.text_resultName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text_resultName.setText(mCities.get(position).getCityName());
        return convertView;
    }
}
