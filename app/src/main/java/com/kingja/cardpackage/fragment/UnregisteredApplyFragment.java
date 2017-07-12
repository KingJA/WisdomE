package com.kingja.cardpackage.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.activity.KCamera;
import com.kingja.cardpackage.activity.UnregisteredApplyActivity;
import com.kingja.cardpackage.base.BaseFragment;
import com.kingja.cardpackage.db.DbDaoXutils3;
import com.kingja.cardpackage.entiy.ApplyPerson;
import com.kingja.cardpackage.entiy.Basic_JuWeiHui_Kj;
import com.kingja.cardpackage.entiy.Basic_PaiChuSuo_Kj;
import com.kingja.cardpackage.entiy.Basic_XingZhengQuHua_Kj;
import com.kingja.cardpackage.entiy.ChuZuWu_AgencySelfReportingIn;
import com.kingja.cardpackage.entiy.ChuZuWu_AgencySelfReportingInResult;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.RefreshUnregisteredList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.ApplyView;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.dialog.BaseListDialog;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Description：设备申报
 * Create Time：2016/8/19 14:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnregisteredApplyFragment extends BaseFragment implements View.OnClickListener, UnregisteredApplyActivity
        .OnSaveClickListener {
    private UnregisteredApplyActivity mUnregisteredApplyActivity;
    private LinkedList<ApplyView> applyViews = new LinkedList<>();

    private EditText mEtApplyAddress;
    private EditText mEtApplyRoomNo;
    private LinearLayout mLlPersons;
    private TextView mTvAddMore;
    private int currentIndex;

    private LinearLayout mLlApplyArea;
    private TextView mTvApplyArea;
    private LinearLayout mLlApplyPoliceStation;
    private TextView mTvApplyPoliceStation;
    private LinearLayout mLlApplyCountry;
    private TextView mTvApplyCountry;

    private List<Basic_XingZhengQuHua_Kj> areas = new ArrayList<>();
    private List<Basic_PaiChuSuo_Kj> policeStations = new ArrayList<>();
    private List<Basic_JuWeiHui_Kj> juweihuis = new ArrayList<>();
    private String pcscode;
    private String jwhcode;
    private String xqcode;
    private BaseListDialog areaDialog;
    private BaseListDialog policeStationsPop;
    private BaseListDialog juweihuisPop;

    @Override
    public void initFragmentVariables() {
        initAreaData();
        initAreaDialog();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_unregistered_apply;
    }

    @Override
    public void initFragmentView(View view, Bundle savedInstanceState) {
        mUnregisteredApplyActivity = (UnregisteredApplyActivity) getActivity();
        mEtApplyAddress = (EditText) view.findViewById(R.id.et_apply_address);
        mEtApplyRoomNo = (EditText) view.findViewById(R.id.et_apply_roomNo);
        mLlPersons = (LinearLayout) view.findViewById(R.id.ll_persons);
        mTvAddMore = (TextView) view.findViewById(R.id.tv_add_more);

        mLlApplyArea = (LinearLayout) view.findViewById(R.id.ll_apply_area);
        mLlApplyPoliceStation = (LinearLayout) view.findViewById(R.id.ll_apply_policeStation);
        mLlApplyCountry = (LinearLayout) view.findViewById(R.id.ll_apply_country);
        mTvApplyArea = (TextView) view.findViewById(R.id.tv_apply_area);
        mTvApplyPoliceStation = (TextView) view.findViewById(R.id.tv_apply_policeStation);
        mTvApplyCountry = (TextView) view.findViewById(R.id.tv_apply_country);
    }


    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentData() {
        addApplyView();
        mTvAddMore.setOnClickListener(this);
        mUnregisteredApplyActivity.setOnSaveClickListener(this);
        mLlApplyArea.setOnClickListener(this);
        mLlApplyPoliceStation.setOnClickListener(this);
        mLlApplyCountry.setOnClickListener(this);
    }

    @Override
    public void setFragmentData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_more:
                addApplyView();
                break;
            case R.id.ll_apply_area:
                areaDialog.show();
                break;
            case R.id.ll_apply_policeStation:
                if (CheckUtil.checkEmpty(xqcode, "请先选择辖区")) {
                    policeStationsPop.show();
                }
                break;
            case R.id.ll_apply_country:
                if (CheckUtil.checkEmpty(pcscode, "请先选择派出所")) {
                    juweihuisPop.show();
                }
                break;
        }
    }

    private void addApplyView() {
        ApplyView applyView = new ApplyView(getActivity(), applyViews.size());
        applyView.setOnOperatorListener(new ApplyView.OnOperatorListener() {
            @Override
            public void onCancle(int index) {
                if (applyViews.size() > 1) {
                    mLlPersons.removeView(applyViews.remove(index));
                    resortApplyView();
                } else {
                    ToastUtil.showToast("至少要有一个申报人员");
                }

            }

            @Override
            public void onOpenOCR(int index) {
                currentIndex = index;
                KCamera.GoCamera(getActivity());
            }
        });
        mLlPersons.addView(applyView);
        applyViews.add(applyView);
    }

    private void resortApplyView() {
        for (int i = 0; i < applyViews.size(); i++) {
            applyViews.get(i).setIndex(i);
        }
    }

    @Override
    public void onSaveClick() {
        onApply();
    }

    private List<ApplyPerson> getApplyPerons() {
        List<ApplyPerson> applyPerson = new ArrayList<>();
        for (ApplyView applyView : applyViews) {
            if (applyView.getApplyPerson() != null) {
                applyPerson.add(applyView.getApplyPerson());
            } else {
                return new ArrayList<>();
            }
        }
        return applyPerson;
    }

    /**
     * 自主申报
     */
    private void onApply() {
        String address = mEtApplyAddress.getText().toString().trim();
        String roomNo = mEtApplyRoomNo.getText().toString().trim();
        List<ApplyPerson> applyPerons = getApplyPerons();
        if (!CheckUtil.checkEmpty(xqcode, "请选择辖区") || !CheckUtil.checkEmpty(pcscode, "请选派出所") || !CheckUtil.checkEmpty
                (jwhcode, "请选择居委会") || !CheckUtil.checkEmpty(address, "请输入地址") || !CheckUtil.checkEmpty(roomNo,
                "请输入房间号") || applyPerons.size()
                == 0) {
            return;
        }
        setProgressDialog(true);
        ChuZuWu_AgencySelfReportingIn bean = new ChuZuWu_AgencySelfReportingIn();
        bean.setTaskID("1");
        bean.setXQCODE(xqcode);
        bean.setPCSCODE(pcscode);
        bean.setJWHCODE(jwhcode);
        bean.setADDRESS(address);
        bean.setROOMNO(roomNo);
        bean.setPEOPLECOUNT(applyViews.size());
        bean.setPEOPLELIST(applyPerons);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_INTERMEDIARY, KConstants
                        .ChuZuWu_AgencySelfReportingIn, bean)
                .setBeanType(ChuZuWu_AgencySelfReportingInResult.class)
                .setCallBack(new WebServiceCallBack<ChuZuWu_AgencySelfReportingInResult>() {
                    @Override
                    public void onSuccess(ChuZuWu_AgencySelfReportingInResult bean) {
                        setProgressDialog(false);
                        final NormalDialog doubleDialog = DialogUtil.getDoubleDialog(getActivity(), "是否要继续进行人员申报",
                                "离开", "继续");
                        doubleDialog.show();
                        doubleDialog.setOnBtnClickL(new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                doubleDialog.dismiss();
                                mUnregisteredApplyActivity.finish();
                            }
                        }, new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                EventBus.getDefault().post(new RefreshUnregisteredList());
                                mTvApplyArea.setText("");
                                mTvApplyPoliceStation.setText("");
                                mTvApplyCountry.setText("");
                                pcscode = "";
                                jwhcode = "";
                                xqcode = "";
                                mEtApplyAddress.setText("");
                                mEtApplyRoomNo.setText("");
                                mLlPersons.removeAllViews();
                                applyViews.clear();
                                addApplyView();
                                doubleDialog.dismiss();
                            }
                        });
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case KCamera.REQUEST_CODE_KCAMERA:
                if (Activity.RESULT_OK == resultCode) {
                    String card = data.getStringExtra("card");
                    String name = data.getStringExtra("name");
                    if (card != null) {
                        applyViews.get(currentIndex).setCardId(card);
                    }
                    if (name != null) {
                        applyViews.get(currentIndex).setName(name);
                    }
                }
                break;
        }
    }

    private void initAreaDialog() {
        areaDialog = new BaseListDialog<Basic_XingZhengQuHua_Kj>(getActivity(), areas) {
            @Override
            protected void fillLvData(List<Basic_XingZhengQuHua_Kj> list, int position, TextView tv) {
                tv.setText(list.get(position).getDMMC());
            }

            @Override
            protected void onItemSelect(Basic_XingZhengQuHua_Kj bean) {
                mTvApplyArea.setText(bean.getDMMC());
                mTvApplyPoliceStation.setText("");
                mTvApplyCountry.setText("");
                pcscode = "";
                jwhcode = "";
                xqcode = bean.getDMZM();

                policeStations = DbDaoXutils3.getInstance().selectAllWhereLike(Basic_PaiChuSuo_Kj
                        .class, "SANSHIYOUDMZM", xqcode + "%");
                initPoliceStationPop();
            }
        };
    }

    private void initPoliceStationPop() {
        policeStationsPop = new BaseListDialog<Basic_PaiChuSuo_Kj>(getActivity(), policeStations) {
            @Override
            protected void fillLvData(List<Basic_PaiChuSuo_Kj> list, int position, TextView tv) {
                tv.setText(list.get(position).getDMMC());
            }

            @Override
            protected void onItemSelect(Basic_PaiChuSuo_Kj basic_PaiChuSuo_Kj) {
                mTvApplyCountry.setText("");
                jwhcode = "";
                pcscode = basic_PaiChuSuo_Kj.getDMZM();
                mTvApplyPoliceStation.setText(basic_PaiChuSuo_Kj.getDMMC());
                juweihuis = DbDaoXutils3.getInstance().selectAllWhere(Basic_JuWeiHui_Kj.class,
                        "FDMZM", pcscode);
                initJuweihuiPop();
            }
        };
    }

    private void initJuweihuiPop() {
        juweihuisPop = new BaseListDialog<Basic_JuWeiHui_Kj>(getActivity(), juweihuis) {
            @Override
            protected void fillLvData(List<Basic_JuWeiHui_Kj> list, int position, TextView tv) {
                tv.setText(list.get(position).getDMMC());
            }

            @Override
            protected void onItemSelect(Basic_JuWeiHui_Kj basic_JuWeiHui_Kj) {
                jwhcode = basic_JuWeiHui_Kj.getDMZM();
                mTvApplyCountry.setText(basic_JuWeiHui_Kj.getDMMC());
            }
        };
    }

    private void initAreaData() {
        areas = DbDaoXutils3.getInstance().selectAll
                (Basic_XingZhengQuHua_Kj.class);
        for (int i = 0; i < areas.size(); i++) {
            if ("330300".equals(areas.get(i).getDMZM())) {
                areas.remove(i);
                break;
            }
        }
    }
}
