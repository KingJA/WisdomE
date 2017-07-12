package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.activity.AutoDeployActivity;
import com.kingja.cardpackage.activity.CarMsgActivity;
import com.kingja.cardpackage.entiy.GetBindCarList;
import com.kingja.cardpackage.entiy.GetBindingList;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.tdr.wisdome.R;

import java.util.List;

import static com.tdr.wisdome.R.id.tv_plateNumber;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NewCarAdapter extends BaseRvAdaper<GetBindCarList.ContentBean.BindingCarsBean> {


    private OnSetDeployListener onSetDeployListener;
    private boolean autoDeployable;

    public NewCarAdapter(Context context, List<GetBindCarList.ContentBean.BindingCarsBean> list,boolean autoDeployable) {
        super(context, list);
        this.autoDeployable = autoDeployable;
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new NewCarViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_new_car;
    }

    @Override
    protected void bindHolder(final ViewHolder baseHolder, final GetBindCarList.ContentBean.BindingCarsBean bean, final int position) {
        final NewCarViewHolder holder = (NewCarViewHolder) baseHolder;
        holder.tv_plateNumber.setText(bean.getPlateNumber());
        holder.iv_isRead.setVisibility(bean.getIsRead() == 1 ? View.GONE : View.VISIBLE);
        if (bean.getStatus() == 0 || bean.getStatus() == 2) {
            holder.tv_bufang.setText("布防");
            holder.tv_bufang.setTextColor(context.getResources().getColor(R.color.bg_blue));
            holder.tv_bufang.setBackgroundResource(R.drawable.shape_lblue_bwhite_r2);
            holder.tv_bufang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSetDeployListener != null) {
                        onSetDeployListener.onAddDeploy(bean.getPlateNumber());
                    }
                }
            });
        } else {
            holder.tv_bufang.setText("已布防");
            holder.tv_bufang.setTextColor(context.getResources().getColor(R.color.bg_white));
            holder.tv_bufang.setBackgroundResource(R.drawable.shape_lblue_bblue_r2);
            holder.tv_bufang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSetDeployListener != null) {
                        onSetDeployListener.onDelDeploy(bean.getPlateNumber());
                    }
                }
            });
        }
        holder.ll_isAuto.setVisibility(autoDeployable? View.VISIBLE : View.GONE);


        if (bean.getAutoDeployRecords().size()>0) {
            holder.tv_auto_msg.setText(bean.getAutoDeployRecords().get(0)
                    .getStartTime() + "-" + bean.getAutoDeployRecords().get(0)
                    .getEndTime()+ " 自动布防已开启 " + TimeUtil.getWeekString(bean.getAutoDeployRecords().get(0)
                    .getWeek()));
            holder.tv_auto_bufang.setText("关闭");
            holder.tv_auto_bufang.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    if (onSetDeployListener != null) {
                        onSetDeployListener.onDelAutolDeploy(bean.getPlateNumber(),bean.getAutoDeployRecords().get(0).getLISTID());
                    }
                }
            });
        } else {
            holder.tv_auto_msg.setText("开启自动撤布防");
            holder.tv_auto_bufang.setText("开启");
            holder.tv_auto_bufang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AutoDeployActivity.goActivity(context,bean.getPlateNumber());
                }
            });
        }


        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSetDeployListener != null) {
                    onSetDeployListener.onUnbindCar(bean.getBindingID(),position);
                }
            }
        });

        holder.tv_plateNumber.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                CarMsgActivity.goAcivity(context,bean.getPlateNumber());
            }
        });
    }

    public void unbindCar(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()) {
            notifyItemRangeChanged(position, list.size() - position);
        }
    }


    class NewCarViewHolder extends ViewHolder {
        public TextView tv_auto_msg;
        public TextView tv_plateNumber;
        public TextView tv_bufang;
        public TextView tv_auto_bufang;
        public TextView tv_delete;
        public ImageView iv_isRead;
        public LinearLayout ll_isAuto;

        public NewCarViewHolder(View itemView) {
            super(itemView);
            tv_auto_msg = (TextView) itemView.findViewById(R.id.tv_auto_msg);
            tv_plateNumber = (TextView) itemView.findViewById(R.id.tv_plateNumber);
            tv_bufang = (TextView) itemView.findViewById(R.id.tv_bufang);
            tv_auto_bufang = (TextView) itemView.findViewById(R.id.tv_auto_bufang);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            iv_isRead = (ImageView) itemView.findViewById(R.id.iv_isRead);
            ll_isAuto = (LinearLayout) itemView.findViewById(R.id.ll_isAuto);
        }
    }

    public interface OnSetDeployListener {
        void onAddDeploy(String plateNumber);

        void onDelDeploy(String plateNumber);
        void onDelAutolDeploy(String plateNumber,String listId);
        void onUnbindCar(String bindingId,int position);
    }

    public void setOnSetDeployListener(OnSetDeployListener onSetDeployListener) {
        this.onSetDeployListener = onSetDeployListener;
    }
}
