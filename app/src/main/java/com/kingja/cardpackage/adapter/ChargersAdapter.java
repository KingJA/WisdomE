package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.activity.ChargerActivity;
import com.kingja.cardpackage.entiy.GetBindChargerList;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.DrawHelperLayout;
import com.tdr.wisdome.R;

import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/13 13:45
 * 修改备注：
 */
public class ChargersAdapter extends BaseLvAdapter<GetBindChargerList.ContentBean.DataBean> {

    private OnChargeOperListener onChargeOperListener;

    public ChargersAdapter(Context context, List<GetBindChargerList.ContentBean.DataBean> list) {
        super(context, list);
    }


    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_charger, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_deviceId.setText(list.get(position).getBinding_objectid());
        viewHolder.tv_carNumber.setText(list.get(position).getBinding_objectid());
        viewHolder.tv_unbind.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onChargeOperListener != null) {
                    onChargeOperListener.onUnbindDevice(list.get(position).getUserid(), list.get(position)
                            .getBinding_objectid());
                }
            }
        });
        viewHolder.drawHelperLayout.setOnRootClickListener(new DrawHelperLayout.OnRootClickListener() {
            @Override
            public void onRootClick() {
//                ChargerActivity.goActivity(context,list.get(position).getBinding_objectid());
                if (onChargeOperListener != null) {
                    onChargeOperListener.onConnectDevice(list.get(position)
                            .getBinding_objectid());
                }
            }
        });
        return convertView;
    }


    public class ViewHolder {
        final TextView tv_deviceId;
        final TextView tv_unbind;
        final TextView tv_carNumber;
        public final DrawHelperLayout drawHelperLayout;
        public final View root;

        public ViewHolder(View root) {
            tv_deviceId = (TextView) root.findViewById(R.id.tv_deviceId);
            tv_unbind = (TextView) root.findViewById(R.id.tv_unbind);
            tv_carNumber = (TextView) root.findViewById(R.id.tv_carNumber);
            drawHelperLayout = (DrawHelperLayout) root.findViewById(R.id.drawHelperLayout);
            this.root = root;
        }
    }

    public interface OnChargeOperListener {
        void onUnbindDevice(String userId, String deviceId);
        void onConnectDevice(String deviceId);
    }

    public void setOnChargeOperListener(OnChargeOperListener onChargeOperListener) {
        this.onChargeOperListener = onChargeOperListener;
    }
}
