package com.kingja.cardpackage.activity;

import com.tdr.wisdome.R;

/**
 * Description:TODO
 * Create Time:2017/11/23 9:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BluetoothSearchActivity extends BackTitleActivity {
    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_bluetooth_search;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        setTitle("蓝牙列表");
      setOnMenuClickListener(new OnMenuClickListener() {
          @Override
          public void onMenuClick() {

          }
      },R.drawable.scan);

    }

    @Override
    protected void setData() {


    }
}
