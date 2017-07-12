package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.ChuZuWu_MenPaiAuthorizationList;
import com.kingja.cardpackage.ui.DrawHelperLayout;
import com.kingja.cardpackage.util.StringUtil;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonManagerRvAdapter extends BaseRvAdaper<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean> {
    private OnDeliteItemListener onDeliteItemListener;

    public PersonManagerRvAdapter(Context context, List<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new PersonManagerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_person_manager;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean bean, final int position) {
        final PersonManagerViewHolder holder = (PersonManagerViewHolder) baseHolder;
        holder.tv_name.setText(bean.getNAME());
        holder.tv_cardId.setText("身份证号: " + StringUtil.hideID(bean.getIDENTITYCARD()));
        holder.tv_code.setText("识别码: " + bean.getCARDID());
        holder.tv_phone.setText(bean.getPHONENUM());
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeliteItemListener != null) {
                    holder.drawHelperLayout.close(true);
                    onDeliteItemListener.onDeliteItem(list.get(position).getLISTID(),position);
                }
            }
        });
    }

    public void setOnDeliteItemListener(OnDeliteItemListener onDeliteItemListener) {
        this.onDeliteItemListener = onDeliteItemListener;
    }

    public interface OnDeliteItemListener {
        void onDeliteItem(String listId, int position);
    }

    public void deleteItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()) {
            notifyItemRangeChanged(position, list.size() - position);
        }
    }


    class PersonManagerViewHolder extends ViewHolder {
        public TextView tv_cardId;
        public TextView tv_phone;
        public TextView tv_name;
        public TextView tv_delete;
        public TextView tv_code;
        public DrawHelperLayout drawHelperLayout;

        public PersonManagerViewHolder(View itemView) {
            super(itemView);
            tv_code = (TextView) itemView.findViewById(R.id.tv_code);
            tv_cardId = (TextView) itemView.findViewById(R.id.tv_cardId);
            tv_phone = (TextView) itemView.findViewById(R.id.et_phone);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            drawHelperLayout = (DrawHelperLayout) itemView.findViewById(R.id.drawHelperLayout);
        }
    }
}
