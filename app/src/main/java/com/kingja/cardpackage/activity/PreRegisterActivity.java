package com.kingja.cardpackage.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.kingja.cardpackage.Event.OnSwtichEvent;
import com.kingja.cardpackage.fragment.PreRegisterFragment;
import com.kingja.cardpackage.fragment.PreRegisterListFragment;
import com.kingja.ui.SwitchMultiButton;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

/**
 * Description：预登记
 * Create Time：2016/8/19 10:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRegisterActivity extends BackTitleActivity implements SwitchMultiButton.OnSwitchListener,
        BackTitleActivity.OnRightClickListener {
    private OnSaveClickListener onSaveClickListener;
    private FragmentTransaction mTransaction;
    private PreRegisterFragment mPreRegisterFragment;
    private PreRegisterListFragment mPreRegisterListFragment;

    @Override
    protected void initVariables() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initContentView() {
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_person_apply;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        setSwitch(this, Arrays.asList("登记", "列表"));
        setOnRightClickListener(this, "完成");
        setTab(0);
    }

    @Override
    protected void setData() {

    }

    @Override
    public void onSwitch(int position, String tabText) {
        setTab(position);
    }

    private void setTab(int position) {
        mTransaction = mSupportFragmentManager.beginTransaction();
        hideFragment();
        mRlTopRight.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        switch (position) {
            case 0://登记
                if (mPreRegisterFragment == null) {
                    mPreRegisterFragment = new PreRegisterFragment();
                    mTransaction.add(R.id.fl_fragment, mPreRegisterFragment);
                } else {
                    mTransaction.show(mPreRegisterFragment);
                }
                break;
            case 1://列表
                if (mPreRegisterListFragment == null) {
                    mPreRegisterListFragment = new PreRegisterListFragment();
                    mTransaction.add(R.id.fl_fragment, mPreRegisterListFragment);
                } else {
                    mTransaction.show(mPreRegisterListFragment);
                }
                break;
            default:
                break;
        }
        mTransaction.commit();
    }

    private void hideFragment() {
        if (mPreRegisterFragment != null) {
            mTransaction.hide(mPreRegisterFragment);
        }
        if (mPreRegisterListFragment != null) {
            mTransaction.hide(mPreRegisterListFragment);
        }
    }

    @Override
    public void onRightClick() {
        if (onSaveClickListener != null) {
            onSaveClickListener.onSaveClick();
        }
    }

    public interface OnSaveClickListener {
        void onSaveClick();
    }

    public void setOnSaveClickListener(OnSaveClickListener onSaveClickListener) {
        this.onSaveClickListener = onSaveClickListener;
    }


    @Override
    public void onBackPressed() {
        showQuitDialog();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            mPreRegisterFragment.onActivityResult(requestCode,resultCode,data);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwtich(OnSwtichEvent event) {
        mSbSwitch.setSelectedTab(1);
        setTab(1);
        mTransaction = mSupportFragmentManager.beginTransaction();
        mTransaction.remove(mPreRegisterFragment).commit();
        mPreRegisterFragment = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
