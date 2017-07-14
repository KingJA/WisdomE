package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.activity.InsuranceStatusActivity;
import com.kingja.cardpackage.entiy.GetClaim;
import com.kingja.cardpackage.entiy.GetClaimInfoList;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InsuranceAdapter extends BaseRvAdaper<GetClaim> {


    public InsuranceAdapter(Context context, List<GetClaim> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new ClaimInfoViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_insurance;
    }

    @Override
    protected void bindHolder(final ViewHolder baseHolder, final GetClaim bean, final int position) {
        final ClaimInfoViewHolder holder = (ClaimInfoViewHolder) baseHolder;
                holder.tv_plateNumber.setText(bean.getPlateNumber());
                holder.tv_date.setText("申报时间: "+bean.getDeclareTime());
        holder.ll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsuranceStatusActivity.goActivity(context,bean.getEcId());
            }
        });
    }


    class ClaimInfoViewHolder extends ViewHolder {
        public TextView tv_plateNumber;
        public TextView tv_date;
        public LinearLayout ll_more;

        public ClaimInfoViewHolder(View itemView) {
            super(itemView);
            tv_plateNumber = (TextView) itemView.findViewById(R.id.tv_plateNumber);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            ll_more = (LinearLayout) itemView.findViewById(R.id.ll_more);
        }
    }
}
