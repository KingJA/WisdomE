package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.activity.AutoDeployActivity;
import com.kingja.cardpackage.activity.CarMsgActivity;
import com.kingja.cardpackage.entiy.GetBindCarList;
import com.kingja.cardpackage.entiy.GetPreRate;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.kingja.recyclerviewhelper.DrawHelperLayout;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.CarQrActivity;

import java.util.List;

import static com.tdr.wisdome.R.id.text_state;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRegisterAdapter extends BaseRvAdaper<GetPreRate.ContentBean> {


    private OnDeletePreRegisterListener onDeletePreRegisterListener;

    public PreRegisterAdapter(Context context, List<GetPreRate.ContentBean> list) {
        super(context, list);
    }

    @Override
    protected BaseRvAdaper.ViewHolder createViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_pre_register;
    }

    @Override
    protected void bindHolder(final BaseRvAdaper.ViewHolder baseHolder, final GetPreRate.ContentBean bean, final int
            position) {
        final ViewHolder holder = (ViewHolder) baseHolder;
        holder.tv_plateNumber.setText(bean.getPrerateName());
        if (bean.getState() == 0) {
            holder.tv_state.setText("有效");
            holder.tv_state.setTextColor(context.getResources().getColor(R.color.colorStatus));
        } else if (list.get(position).getState() == 1) {
            holder.tv_state.setText("失效");
            holder.tv_state.setTextColor(context.getResources().getColor(R.color.colorHint));
        } else {
            holder.tv_state.setText("已使用");
            holder.tv_state.setTextColor(context.getResources().getColor(R.color.colorHint));
        }
        holder.drawHelperLayout.setOnRootClickListener(new DrawHelperLayout.OnRootClickListener() {
            @Override
            public void onRootClick() {
                Intent intent = new Intent(context, CarQrActivity.class);
                intent.putExtra("prerateID", bean.getPrerateID());
                intent.putExtra("PreRateBean", bean);
                context.startActivity(intent);
            }
        });
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeletePreRegisterListener != null) {
                    onDeletePreRegisterListener.onDeletePreRegister(bean.getPrerateID(),position);
                }
            }
        });
    }

    public void deletePreRegister(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()) {
            notifyItemRangeChanged(position, list.size() - position);
        }
    }


    class ViewHolder extends BaseRvAdaper.ViewHolder {
        public TextView tv_plateNumber;
        public TextView tv_delete;
        public TextView tv_state;
        public DrawHelperLayout drawHelperLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_plateNumber = (TextView) itemView.findViewById(R.id.tv_plateNumber);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            tv_state = (TextView) itemView.findViewById(R.id.tv_state);
            drawHelperLayout = (DrawHelperLayout) itemView.findViewById(R.id.drawHelperLayout);
        }
    }

    public interface OnDeletePreRegisterListener {
        void onDeletePreRegister(String prerateID,int position);
    }

    public void setOnDeletePreRegisterListener(OnDeletePreRegisterListener onDeletePreRegisterListener) {
        this.onDeletePreRegisterListener = onDeletePreRegisterListener;
    }
}
