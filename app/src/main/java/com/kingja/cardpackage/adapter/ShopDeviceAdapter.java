package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.ShangPu_DeviceLists;
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
public class ShopDeviceAdapter extends BaseRvAdaper<ShangPu_DeviceLists.ContentBean> {
    private OnShopDeviceDeliteListener onShopDeviceDeliteListener;
    private final Map<String, String> typeMap;

    public ShopDeviceAdapter(Context context, List<ShangPu_DeviceLists.ContentBean> list) {
        super(context, list);
        typeMap = DeviceTypeUtil.getTypeMap(TempConstants.DEVICETYPE);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new PersonManagerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_person_device;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, ShangPu_DeviceLists.ContentBean bean, final int position) {
        final PersonManagerViewHolder holder = (PersonManagerViewHolder) baseHolder;
        holder.tv_deviceName.setText("设备名称："+bean.getDEVICENAME());
        holder.tv_deviceType.setText(typeMap.get(bean.getDEVICETYPE()));
        holder.tv_deviceCode.setText("设备编号："+bean.getDEVICECODE());


        holder.iv_delete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onShopDeviceDeliteListener != null) {
                    onShopDeviceDeliteListener.onShopDeviceDelite(position, list.get(position).getDEVICEID(), list.get(position).getDEVICETYPE(), list.get(position).getDEVICECODE());
                }
            }
        });
    }


    public void setOnShopDeviceDeliteListener(OnShopDeviceDeliteListener onShopDeviceDeliteListener) {
        this.onShopDeviceDeliteListener = onShopDeviceDeliteListener;
    }

    public interface OnShopDeviceDeliteListener {
        void onShopDeviceDelite(int position, String deviceId, String deviceType, String deviceCode);
    }

    public void deleteItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()) {
            notifyItemRangeChanged(position, list.size() - position);
        }
    }


    class PersonManagerViewHolder extends ViewHolder {
        public TextView tv_deviceName;
        public TextView tv_deviceType;
        public TextView tv_deviceCode;
        public ImageView iv_delete;

        public PersonManagerViewHolder(View itemView) {
            super(itemView);
            tv_deviceName = (TextView) itemView.findViewById(R.id.tv_deviceName);
            tv_deviceType = (TextView) itemView.findViewById(R.id.tv_deviceType);
            tv_deviceCode = (TextView) itemView.findViewById(R.id.tv_deviceCode);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }
}
