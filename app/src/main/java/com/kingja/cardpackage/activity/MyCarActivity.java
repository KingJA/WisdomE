package com.kingja.cardpackage.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.adapter.CarBindAdapter;
import com.kingja.cardpackage.adapter.CarPerAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetCodeList;
import com.kingja.cardpackage.entiy.IndexData;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.FixedListView;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.popupwindow.BaseBottomPop;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.CarBindingActivity;
import com.tdr.wisdome.actvitiy.CarQrActivity;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.view.popupwindow.CarPop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：我的车
 * Create Time：2017/1/19 9:48
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyCarActivity extends BackTitleActivity implements CarPop.OnCarPopClickListener, AdapterView.OnItemClickListener ,CarPerAdapter.OnPreRecordDeleteListener{
    private DbManager.DaoConfig daoConfig;
    private DbManager db;
    private List<IndexData.ContentBean.BindingListBean> carBindList = new ArrayList<>();
    private List<IndexData.ContentBean.PreRateBean> carRecordList = new ArrayList<>();
    private TextView mTvBufang;
    private TextView mTvPlateNumber;
    private TextView mTvCarBind;
    private FixedListView mLvCarBind;
    private TextView mTvPreRecord;
    private FixedListView mLvPreRecord;
    private CarBindAdapter carBindAdapter;
    private CarPerAdapter carPerAdapter;
    private CarPop carPop;
    private String ecID;
    private LinearLayout mLlChangeCar;
    private LinearLayout mLlInsuranceInfo;
    private int calimState;
    private LinearLayout mLlRoot;
    private int status;
    private String plateNumber;
    private BaseBottomPop personBaseBottomPop;
    private NormalDialog bufangDialog;


    @Override
    protected void initVariables() {
        mZeusManager.checkPermissions(permissionArr, true);
        EventBus.getDefault().register(this);
        initDb();
    }

    @Override
    protected void initContentView() {
        carPop = new CarPop(mRlTopMenu, this);
        carPop.setOnCarPopClickListener(this);

        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mLlChangeCar = (LinearLayout) findViewById(R.id.ll_changeCar);
        mLlInsuranceInfo = (LinearLayout) findViewById(R.id.ll_insuranceInfo);

        mTvBufang = (TextView) findViewById(R.id.tv_bufang);
        mTvPlateNumber = (TextView) findViewById(R.id.tv_plateNumber);

        mTvCarBind = (TextView) findViewById(R.id.tv_carBind);
        mTvPreRecord = (TextView) findViewById(R.id.tv_preRecord);

        mLvCarBind = (FixedListView) findViewById(R.id.lv_carBind);
        mLvPreRecord = (FixedListView) findViewById(R.id.lv_preRecord);

        carBindAdapter = new CarBindAdapter(this, carBindList);
        carPerAdapter = new CarPerAdapter(this, carRecordList);
        mLvCarBind.setAdapter(carBindAdapter);
        mLvPreRecord.setAdapter(carPerAdapter);

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_mycar;
    }

    @Override
    protected void initNet() {
        //1.获取车辆数据库数据
        //2.获取车辆绑定和预登记接口
        getCarDb();

    }

    private void getCarDb() {
        String updateTime = "1990-01-01 00:00:01";
        if (TextUtils.isEmpty(DataManager.getLastCity()) || !(DataManager.getCityName().equals(DataManager.getLastCity()))) {
            updateTime = "1990-01-01 00:00:01";
            DataManager.putLastCity(DataManager.getCityName());
        } else {
            updateTime = com.tdr.wisdome.util.Constants.getLastUpdateTime();
        }
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("updatetime", updateTime);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetCodeList, param)
                .setBeanType(GetCodeList.class)
                .setCallBack(new WebServiceCallBack<GetCodeList>() {
                    @Override
                    public void onSuccess(GetCodeList bean) {
                        List<KJBikeCode> carInfoList = bean.getContent();
                        Log.e(TAG, "数据条数: " + carInfoList.size());
                        if (carInfoList.size() > 0) {
                            try {
                                db.dropTable(KJBikeCode.class);
                            } catch (DbException e) {
                                e.printStackTrace();
                            }
                            saveDate(carInfoList);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setProgressDialog(false);
                                    getCarData();
                                }
                            }, 10000);
                        } else {
                            setProgressDialog(false);
                            getCarData();
                        }


                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private <T> void saveDate(final List<T> list) {

        x.task().run(new Runnable() {
            @Override
            public void run() {
                for (T t : list) {
                    try {
                        db.save(t);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }

                com.tdr.wisdome.util.Constants.setLastUpdateTime(Utils.getNowTime());
            }
        });
    }

    private void initDb() {
        daoConfig = new DbManager.DaoConfig()
//                .setDbDir(Environment.getExternalStorageDirectory())
                .setDbName("WisdomE.db")
                .setDbVersion(1)
                .setAllowTransaction(true)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    }
                });
        db = x.getDb(daoConfig);
    }

    private void getCarData() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.IndexData, param)
                .setBeanType(IndexData.class)
                .setCallBack(new WebServiceCallBack<IndexData>() {
                    @Override
                    public void onSuccess(IndexData bean) {
                        setProgressDialog(false);
                        carBindList = bean.getContent().getBindingList();
                        carRecordList = bean.getContent().getPreRate();
                        setDefalutCar();
                        if (carBindList.size() > 0) {
                            carBindAdapter.setData(carBindList);
                        } else {
                            mTvCarBind.setText("暂无车辆绑定信息");
                        }

                        if (carRecordList.size() > 0) {
                            carPerAdapter.setData(carRecordList);
                        } else {
                            mTvCarBind.setText("暂无预登记信息");
                        }

                        personBaseBottomPop = new BaseBottomPop<IndexData.ContentBean.BindingListBean>(MyCarActivity.this, carBindList) {
                            @Override
                            protected void fillLvData(List<IndexData.ContentBean.BindingListBean> list, int position, TextView tv) {
                                tv.setText(list.get(position).getPlateNumber());
                            }

                            @Override
                            protected void onItemSelect(IndexData.ContentBean.BindingListBean person) {
                                plateNumber=person.getPlateNumber();
                                mTvPlateNumber.setText(plateNumber);
                                status=person.getStatus();
                                setBufangButtonStatus(status);
                                if (calimState != 0 && calimState != 1) {
                                    mLlInsuranceInfo.setVisibility(View.VISIBLE);
                                }else{
                                    mLlInsuranceInfo.setVisibility(View.GONE);
                                }
                            }
                        };

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void setDefalutCar() {
        if (carBindList.size() > 0) {
            mLlChangeCar.setVisibility(View.VISIBLE);
            mTvBufang.setEnabled(true);
            mLlChangeCar.setEnabled(true);
            plateNumber = carBindList.get(0).getPlateNumber();
            mTvPlateNumber.setText(plateNumber);
            ecID = carBindList.get(0).getEcID();
            calimState = carBindList.get(0).getClaimState();
            status = carBindList.get(0).getStatus();
            setBufangButtonStatus(status);
            if (calimState != 0 && calimState != 1) {
                mLlInsuranceInfo.setVisibility(View.VISIBLE);
            }
        }


    }

    private void setBufangButtonStatus(int status) {
        if (status == 1) {//已布防=》显示关闭布防
            mTvBufang.setText("关闭布防");
            mTvBufang.setBackgroundResource(R.drawable.btn_close_bufang);
        } else {//未布防=》显示开启布防
            mTvBufang.setText("开启布防");
            mTvBufang.setBackgroundResource(R.drawable.btn_open_bufang);
        }
    }

    @Override
    protected void initData() {
        mTvBufang.setOnClickListener(this);
        mLlChangeCar.setOnClickListener(this);
        mLlInsuranceInfo.setOnClickListener(this);
        mLvCarBind.setOnItemClickListener(this);
        mLvPreRecord.setOnItemClickListener(this);
        carPerAdapter.setOnPreRecordDeleteListener(this);
    }

    @Override
    protected void setData() {
        setTitle("我的车");
        setOnMenuClickListener(new OnMenuClickListener() {
            @Override
            public void onMenuClick() {
                carPop.showPopupWindowDown();
            }
        }, R.drawable.more);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_bufang:
                bufangDialog = DialogUtil.getDoubleDialog(this, "您是否要"+(status==1? "关闭":"开启")+"布防", "取消",status==1? "关闭":"开启");
                bufangDialog.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        bufangDialog.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        bufangDialog.dismiss();
                        setBufang(status, plateNumber);
                    }
                });
                bufangDialog.show();


                break;
            case R.id.ll_changeCar:
                if (carBindList.size() > 1) {
                    personBaseBottomPop.showPopAtBottom(mLlRoot);
                }else{
                    ToastUtil.showToast("没有其他车辆");
                }

                break;
            case R.id.ll_insuranceInfo:
                if (CheckUtil.checkEmpty(ecID, "暂时没有保险信息")) {
                    InsuranceStatusActivity.goActivity(this, ecID);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onCarPop(int position) {
        switch (position) {
            case 0:
                GoUtil.goActivity(this, CarBindingActivity.class);
                break;

            case 1:
                GoUtil.goActivity(this, PreRegisterInfoActivity.class);
                break;

        }
        carPop.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_carBind:
                IndexData.ContentBean.BindingListBean bindBean = (IndexData.ContentBean.BindingListBean) parent.getItemAtPosition(position);
                CarDetailActivity.goActivity(this, bindBean);
                break;
            case R.id.lv_preRecord:
                IndexData.ContentBean.PreRateBean recordBean = (IndexData.ContentBean.PreRateBean) parent.getItemAtPosition(position);
                CarQrActivity.goActivity(this, recordBean);
                break;

        }
    }

    private void setBufang(final int status, final String plateNumber) {
        // 1 已布防=》显示关闭布防 =>关闭布防
        String methodName = status == 1 ? KConstants.DelElecDeploy : KConstants.AddElecDeploy;
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("platenumber", plateNumber);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, methodName, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        MyCarActivity.this.status = status == 1 ? 0 : 1;
                        carBindAdapter.setBufang(MyCarActivity.this.status, plateNumber);
                        setBufangButtonStatus(MyCarActivity.this.status);
                        setProgressDialog(false);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onPreRecordDelete(IndexData.ContentBean.PreRateBean preRateBean, final int position) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("PrerateID", preRateBean.getPrerateID());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.DelPreRate, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        setProgressDialog(false);
                        carPerAdapter.removeItem(position);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetCarData(GetCarDataEvent event) {
        getCarData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
