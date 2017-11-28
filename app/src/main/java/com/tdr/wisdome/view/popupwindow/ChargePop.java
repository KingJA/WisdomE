package com.tdr.wisdome.view.popupwindow;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.tdr.wisdome.R;


public class ChargePop extends PopupWindowBaseDown implements View.OnClickListener {

    private LinearLayout ll_bluetooth;
    private LinearLayout ll_chargeRecord;
    private LinearLayout ll_autoCharge;
    private LinearLayout ll_topCharge;

    public ChargePop(View parentView, Activity activity) {
        super(parentView, activity);
        this.setAnimationStyle(R.style.PopupRightTopAnimation);
    }

    @Override
    public View setPopupView(Activity activity) {
        popupView = View.inflate(activity, R.layout.pop_charge, null);
        return popupView;
    }

    @Override
    public void initChildView() {
        ll_bluetooth = (LinearLayout) popupView.findViewById(R.id.ll_bluetooth);
        ll_chargeRecord = (LinearLayout) popupView.findViewById(R.id.ll_chargeRecord);
        ll_autoCharge = (LinearLayout) popupView.findViewById(R.id.ll_autoCharge);
        ll_topCharge = (LinearLayout) popupView.findViewById(R.id.ll_topCharge);
        ll_bluetooth.setOnClickListener(this);
        ll_chargeRecord.setOnClickListener(this);
        ll_autoCharge.setOnClickListener(this);
        ll_topCharge.setOnClickListener(this);
    }


    @Override
    public void OnChildClick(View v) {
        if (onItemClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.ll_bluetooth:
                onItemClickListener.onPopItemClick(0);
                break;
            case R.id.ll_chargeRecord:
                onItemClickListener.onPopItemClick(1);
                break;
            case R.id.ll_autoCharge:
                onItemClickListener.onPopItemClick(2);
                break;
            case R.id.ll_topCharge:
                onItemClickListener.onPopItemClick(3);
                break;
        }
        dismiss();
    }

    public interface OnItemClickListener {
        void onPopItemClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
