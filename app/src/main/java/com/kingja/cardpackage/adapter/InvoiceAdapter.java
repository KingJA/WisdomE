package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.activity.DetailInvoiceActivity;
import com.kingja.cardpackage.entiy.GetInvoiceInfoList;
import com.kingja.cardpackage.util.StringUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.tdr.wisdome.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InvoiceAdapter extends BaseRvAdaper<GetInvoiceInfoList.ContentBean> {


    public InvoiceAdapter(Context context, List<GetInvoiceInfoList.ContentBean> list) {
        super(context, list);
    }

    @Override
    protected BaseRvAdaper.ViewHolder createViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_invoice;
    }

    @Override
    protected void bindHolder(final BaseRvAdaper.ViewHolder baseHolder, final GetInvoiceInfoList.ContentBean bean,
                              final int
            position) {
        final ViewHolder holder = (ViewHolder) baseHolder;
        holder.tv_plateNumber.setText(bean.getPLATENUMBER());
        holder.tv_invoiceCode.setText("发票代码: " + bean.getFPDM());
        final String realUrl = StringUtil.getWholeUrl(bean.getJPG_URL());
        Log.e(TAG, "realUrl: "+realUrl );
        holder.ll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailInvoiceActivity.goActivity(context,realUrl);
            }
        });
    }


    class ViewHolder extends BaseRvAdaper.ViewHolder {
        public TextView tv_plateNumber;
        public TextView tv_invoiceCode;
        public LinearLayout ll_more;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_plateNumber = (TextView) itemView.findViewById(R.id.tv_plateNumber);
            tv_invoiceCode = (TextView) itemView.findViewById(R.id.tv_invoiceCode);
            ll_more = (LinearLayout) itemView.findViewById(R.id.ll_more);
        }
    }
}
