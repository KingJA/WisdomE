package com.tdr.wisdome.view.popupwindow;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.tdr.wisdome.R;


public class CarPop extends PopupWindowBaseDown implements View.OnClickListener {

    private LinearLayout ll_carBinding;
    private LinearLayout ll_preRegistration;
    private LinearLayout ll_insurance;
    private LinearLayout ll_invoice_root;

    public CarPop(View parentView, Activity activity) {
        super(parentView, activity);
    }

    @Override
    public View setPopupView(Activity activity) {
        popupView = View.inflate(activity, R.layout.pop_car, null);
        return popupView;
    }

    @Override
    public void initChildView() {
        ll_carBinding = (LinearLayout) popupView.findViewById(R.id.ll_carBinding);
        ll_invoice_root = (LinearLayout) popupView.findViewById(R.id.ll_invoice_root);
        ll_preRegistration = (LinearLayout) popupView.findViewById(R.id.ll_preRegistration);
        ll_insurance = (LinearLayout) popupView.findViewById(R.id.ll_insurance);
        ll_invoice_root.setOnClickListener(this);
        ll_carBinding.setOnClickListener(this);
        ll_preRegistration.setOnClickListener(this);
        ll_insurance.setOnClickListener(this);
    }

    public void setInvoiceVisibility(boolean isVisibility) {
        ll_invoice_root.setVisibility(isVisibility?View.VISIBLE:View.GONE);
    }

    @Override
    public void OnChildClick(View v) {
        if (onCarPopClickListener == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.ll_carBinding:
                onCarPopClickListener.onCarPop(0);
                break;

            case R.id.ll_preRegistration:
                onCarPopClickListener.onCarPop(1);
                break;
            case R.id.ll_insurance:
                onCarPopClickListener.onCarPop(2);
                break;
            case R.id.ll_invoice_root:
                onCarPopClickListener.onCarPop(3);
                break;
        }
    }

    public interface OnCarPopClickListener {
        void onCarPop(int position);
    }

    private OnCarPopClickListener onCarPopClickListener;

    public void setOnCarPopClickListener(OnCarPopClickListener onCarPopClickListener) {
        this.onCarPopClickListener = onCarPopClickListener;
    }
}
