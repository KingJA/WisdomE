package com.kingja.cardpackage.ui.pop;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.Basic_Dictionary_Kj;
import com.kingja.cardpackage.entiy.IndexData;
import com.kingja.ui.popupwindow.SingleTextAdapter;
import com.kingja.ui.wheelview.AbstractWheelTextAdapter;
import com.kingja.ui.wheelview.OnWheelChangedListener;
import com.kingja.ui.wheelview.OnWheelScrollListener;
import com.kingja.ui.wheelview.WheelView;
import com.tdr.wisdome.R;
import com.tdr.wisdome.view.popupwindow.PopupWindowBaseDown;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/4/11 16:34
 * 修改备注：
 */
public class CarSelectorPop extends PopupWindowBaseDown<List<IndexData.ContentBean.BindingListBean>> {

    private SingleTextAdapter singleTextAdapter;
    private OnBottemPopSelectListener onBottemPopSelectListener;
    private WheelView wv_carSelector;
    private int maxTextSize = 24;
    private int minTextSize = 14;
    private CarSelectorAdapter carSelectorAdapter;
    private String name;

    public CarSelectorPop(View parentView, Activity activity, List<IndexData.ContentBean.BindingListBean> data) {
        super(parentView, activity, data);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setAnimationStyle(R.style.PopupBottom2TopAnimation);
    }


    @Override
    public View setPopupView(Activity activity) {
        popupView = View.inflate(activity, R.layout.pop_car_select, null);
        return popupView;
    }

    @Override
    public void initChildView() {
        Log.e("initChildView", "initChildView: " );
        wv_carSelector = (WheelView) popupView.findViewById(R.id.wv_carSelector);
        carSelectorAdapter = new CarSelectorAdapter(activity, data, 0, maxTextSize, minTextSize);
        wv_carSelector.setVisibleItems(5);
        wv_carSelector.setViewAdapter(carSelectorAdapter);
        wv_carSelector.scroll(0,1);

        wv_carSelector.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                name = (String) carSelectorAdapter.getItemText(wheel.getCurrentItem());
                Log.e("onChanged", "name: "+name );
                setTextviewSize(name, carSelectorAdapter);
            }
        });

        wv_carSelector.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                name = (String) carSelectorAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(name, carSelectorAdapter);
            }
        });
    }
    public void setTextviewSize(String curriteItemText, CarSelectorAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }
    @Override
    public void OnChildClick(View v) {

    }

    public interface OnBottemPopSelectListener {
        void onSelect(int position, Basic_Dictionary_Kj bean);
    }

    public void setOnBottemPopSelectListener(OnBottemPopSelectListener onBottemPopSelectListener) {

        this.onBottemPopSelectListener = onBottemPopSelectListener;
    }


    private class CarSelectorAdapter extends AbstractWheelTextAdapter {
        List<IndexData.ContentBean.BindingListBean> list;

        protected CarSelectorAdapter(Context context, List<IndexData.ContentBean.BindingListBean> list, int currentItem, int maxsize, int minsize) {
            super(context, com.kingja.ui.R.layout.item_birth, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(com.kingja.ui.R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            Log.e("CharSequence", "getItemText: "+list.get(index).getPlateNumber() );
            return list.get(index).getPlateNumber();
        }
    }

}
