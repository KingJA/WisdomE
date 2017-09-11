package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.NFCDevice_List;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NfcDeviceAdapter extends BaseRvAdaper<NFCDevice_List.ContentBean> {


    private OnOpenFlashlightListener onOpenFlashlightListener;

    public NfcDeviceAdapter(Context context, List<NFCDevice_List.ContentBean> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new NfcDeviceViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_nfcdevice;
    }

    @Override
    protected void bindHolder(final ViewHolder baseHolder, final NFCDevice_List.ContentBean bean, final int
            position) {
        final NfcDeviceViewHolder holder = (NfcDeviceViewHolder) baseHolder;
        holder.tv_nfcDevice_address.setText(bean.getSTANDARDADDR());
        holder.iv_flashlight.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onOpenFlashlightListener != null) {
                    onOpenFlashlightListener.onOpenFlashlight(bean.getCARDID(), bean.getDEVICECODE());
                }

            }
        });

    }

    class NfcDeviceViewHolder extends ViewHolder {
        public final ImageView iv_flashlight;
        public final TextView tv_nfcDevice_address;

        public NfcDeviceViewHolder(View itemView) {
            super(itemView);
            iv_flashlight = (ImageView) itemView.findViewById(R.id.iv_flashlight);
            tv_nfcDevice_address = (TextView) itemView.findViewById(R.id.tv_nfcDevice_address);
        }
    }

    public interface OnOpenFlashlightListener {
        void onOpenFlashlight(String cardId, String deviceCode);
    }

    public void setOnOpenFlashlight(OnOpenFlashlightListener onOpenFlashlightListener) {
        this.onOpenFlashlightListener = onOpenFlashlightListener;
    }
}
