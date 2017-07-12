package com.kingja.cardpackage.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.Event.GetPreRegisterListEvent;
import com.kingja.cardpackage.adapter.PreRegisterAdapter;
import com.kingja.cardpackage.base.BaseFragment;
import com.kingja.cardpackage.entiy.DelPreRate;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetPreRate;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description：预登记列表
 * Create Time：2016/8/19 14:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRegisterListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private LinearLayout mLlRoot;
    private LinearLayout mLlEmpty;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private List<GetPreRate.ContentBean> perRecordList = new ArrayList<>();
    private PreRegisterAdapter mPreRegisterAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.single_rv;
    }

    @Override
    public void initFragmentView(View view, Bundle savedInstanceState) {
        mLlRoot = (LinearLayout) view.findViewById(R.id.ll_root);
        mLlEmpty = (LinearLayout) view.findViewById(R.id.ll_empty);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) view.findViewById(R.id.rv);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        mPreRegisterAdapter = new PreRegisterAdapter(getActivity(), perRecordList);
        new RecyclerViewHelper.Builder(getActivity())
                .setCallbackAdapter(mPreRegisterAdapter)
                .setLayoutStyle(LayoutHelper.LayoutStyle.VERTICAL_LIST)
                .setDividerColor(getActivity().getResources().getColor(R.color.gray_divider))
                .setDividerHeight(0.5f)
                .build()
                .attachToRecyclerView(mRv);
    }

    @Override
    public void initFragmentVariables() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initFragmentNet() {
        mSrl.setRefreshing(true);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetPreRate, null)
                .setBeanType(GetPreRate.class)
                .setCallBack(new WebServiceCallBack<GetPreRate>() {
                    @Override
                    public void onSuccess(GetPreRate bean) {
                        mSrl.setRefreshing(false);
                        perRecordList = bean.getContent();
                        mLlEmpty.setVisibility(perRecordList.size() > 0 ? View.GONE : View.VISIBLE);
                        mPreRegisterAdapter.setData(perRecordList);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    public void initFragmentData() {
        mSrl.setOnRefreshListener(this);
        mPreRegisterAdapter.setOnDeletePreRegisterListener(new PreRegisterAdapter.OnDeletePreRegisterListener() {
            @Override
            public void onDeletePreRegister(final String prerateID, final int position) {
                final NormalDialog mDelPreRegisterDialog = DialogUtil.getDoubleDialog(getActivity(), "是否要删除该预登记信息",
                        "取消", "确定");
                mDelPreRegisterDialog.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        mDelPreRegisterDialog.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        mDelPreRegisterDialog.dismiss();
                        odoDeletePreRegister(prerateID, position);
                    }
                });
                mDelPreRegisterDialog.show();

            }
        });
    }

    private void odoDeletePreRegister(String prerateID, final int position) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("PrerateID", prerateID);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.DelPreRate, param)
                .setBeanType(DelPreRate.class)
                .setCallBack(new WebServiceCallBack<DelPreRate>() {
                    @Override
                    public void onSuccess(DelPreRate bean) {
                        setProgressDialog(false);
                        mPreRegisterAdapter.deletePreRegister(position);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void setFragmentData() {

    }

    @Override
    public void onRefresh() {
        mSrl.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetPreRegisterList(GetPreRegisterListEvent event) {
        initFragmentNet();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
