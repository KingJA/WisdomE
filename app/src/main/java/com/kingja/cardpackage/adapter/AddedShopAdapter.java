package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.ShangPu_DeviceLists;
import com.kingja.cardpackage.entiy.ShangPu_ListByEmp;
import com.kingja.cardpackage.ui.DrawHelperLayout;
import com.kingja.cardpackage.util.DeviceTypeUtil;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.TempConstants;
import com.tdr.wisdome.R;

import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddedShopAdapter extends BaseRvAdaper<ShangPu_ListByEmp.ContentBean> {
    private OnShopDeliteListener onShopDeliteListener;

    public AddedShopAdapter(Context context, List<ShangPu_ListByEmp.ContentBean> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new PersonManagerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_added_shop;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, ShangPu_ListByEmp.ContentBean bean, final int position) {
        final PersonManagerViewHolder holder = (PersonManagerViewHolder) baseHolder;
        holder.tv_shopName.setText(bean.getSHOPNAME());
        holder.tv_shopAddress.setText(bean.getADDRESS());


        holder.iv_delete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onShopDeliteListener != null) {
                    onShopDeliteListener.onShopDelite(position, list.get(position).getSHOPID());
                }
            }
        });
    }


    public void setOnShopDeliteListener(OnShopDeliteListener onShopDeliteListener) {
        this.onShopDeliteListener = onShopDeliteListener;
    }

    public interface OnShopDeliteListener {
        void onShopDelite(int position, String shopId);
    }

    public void deleteItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()) {
            notifyItemRangeChanged(position, list.size() - position);
        }
    }


    class PersonManagerViewHolder extends ViewHolder {
        public TextView tv_shopName;
        public TextView tv_shopAddress;
        public ImageView iv_delete;

        public PersonManagerViewHolder(View itemView) {
            super(itemView);
            tv_shopName = (TextView) itemView.findViewById(R.id.tv_shopName);
            tv_shopAddress = (TextView) itemView.findViewById(R.id.tv_shopAddress);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }
}
