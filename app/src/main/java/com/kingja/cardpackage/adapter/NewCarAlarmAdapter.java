package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetBindingList;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NewCarAlarmAdapter extends BaseRvAdaper<GetBindingList.ContentBean> {


    public NewCarAlarmAdapter(Context context, List<GetBindingList.ContentBean> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new NewCarViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_card_msg;
    }

    @Override
    protected void bindHolder(final ViewHolder baseHolder, final GetBindingList.ContentBean bean, final int position) {
        final NewCarViewHolder holder = (NewCarViewHolder) baseHolder;
        holder.tv_plateNumber.setText(bean.getPlateNumber());
        holder.iv_isRead.setVisibility(bean.getIsRead()==1?View.GONE:View.VISIBLE);
    }


    class NewCarViewHolder extends ViewHolder {
        public TextView tv_plateNumber;
        public TextView tv_bufang;
        public TextView tv_auto_bufang;
        public ImageView iv_isRead;

        public NewCarViewHolder(View itemView) {
            super(itemView);
            tv_plateNumber = (TextView) itemView.findViewById(R.id.tv_plateNumber);
            tv_bufang = (TextView) itemView.findViewById(R.id.tv_bufang);
            tv_auto_bufang = (TextView) itemView.findViewById(R.id.tv_auto_bufang);
            iv_isRead = (ImageView) itemView.findViewById(R.id.iv_isRead);
        }
    }
}
