package com.kingja.cardpackage.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.Event.CancleEntrustDeployEvent;
import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.adapter.NewCarAdapter;
import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetBindCarList;
import com.kingja.cardpackage.entiy.GetBindingList;
import com.kingja.cardpackage.entiy.GetCodeList;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.entiy.SetAutoDeploy;
import com.kingja.cardpackage.entiy.SetEntrustDeploy;
import com.kingja.cardpackage.entiy.UnBinding;
import com.kingja.cardpackage.entiy.UpdateFunctions;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.DbProgressDialog;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.FunctionUtil;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.CarBindingActivity;
import com.tdr.wisdome.actvitiy.MainActivity;
import com.tdr.wisdome.view.popupwindow.CarPop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tdr.wisdome.R.id.ll_entrustDeploy;
import static com.tdr.wisdome.R.id.sw_swich;

/**
 * Description：我的车
 * Create Time：2017/1/19 9:48
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NewCarActivity extends BackTitleActivity implements CarPop.OnCarPopClickListener, NewCarAdapter
        .OnSetDeployListener, ECardXutils3.DbProgressListener {
    private CarPop carPop;
    private NewCarAdapter mNewCarAdapter;
    private LinearLayout mLlEmpty;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private SwitchCompat mSwitchEntrust;
    private List<GetBindCarList.ContentBean.BindingCarsBean> bindingCars = new ArrayList<>();
    private LinearLayout mLlEntrustDeploy;
    private NormalDialog unbindDialog;
    private NormalDialog mDelAutoDeployDialog;
    private String url;
    private boolean autoDeployalbe;
    private boolean showInvoicealbe;
    private boolean showWeibind;

    public static final int DB_UPDATE_START = 0;
    public static final int DB_UPDATE_PROGRESS = 1;
    public static final int DB_UPDATE_END = 2;

    private DbProgressDialog dbProgressDialog;

    @Override
    public void onProgressStart(final int totleProgress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "【数据库下载】 开始" );
                dbProgressDialog = new DbProgressDialog(NewCarActivity.this, totleProgress);
                dbProgressDialog.show();
            }
        });
    }

    @Override
    public void onProgressProgress(final int progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "【数据库下载】 更新" +progress);
                dbProgressDialog.setProgress(progress);
            }
        });
    }

    @Override
    public void onProgressEnd() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "【数据库下载】 结束" );
                dbProgressDialog.dismiss();
                checkEntrustDeployAble();
                getBindCar();
            }
        });
    }



    @Override
    protected void initVariables() {
        Log.e(TAG, "initVariables: ");
        autoDeployalbe = FunctionUtil.autoDeployalbe();
        showInvoicealbe = FunctionUtil.showInvoicealbe();
        showWeibind = FunctionUtil.showWeibind();
        EventBus.getDefault().register(this);
    }

    private void checkEntrustDeployAble() {
        List<UpdateFunctions> entrustDeploys = ECardXutils3.getInstance().selectAllWheres(UpdateFunctions.class,
                "CityCode", DataManager.getCityCode(), "ColumnValue", "1");

        if (entrustDeploys != null && entrustDeploys.size() > 0) {
            UpdateFunctions function = entrustDeploys.get(0);
            if (function != null && (function.getIsValid() == 1)) {
                Log.e(TAG, function.getCityName() + "支持委托撤布防 ");
                url = function.getRemark1();
                Log.e(TAG, url);
                mLlEntrustDeploy.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void initContentView() {
        carPop = new CarPop(mRlTopMenu, this);
        carPop.setOnCarPopClickListener(this);
        carPop.setInvoiceVisibility(showInvoicealbe);
        carPop.setWeibindVisibility(showWeibind);

        mSwitchEntrust = (SwitchCompat) findViewById(R.id.switch_entrust);
        mLlEntrustDeploy = (LinearLayout) findViewById(ll_entrustDeploy);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));

        mNewCarAdapter = new NewCarAdapter(this, bindingCars, autoDeployalbe);
        new RecyclerViewHelper.Builder(this)
                .setCallbackAdapter(mNewCarAdapter)
                .setLayoutStyle(LayoutHelper.LayoutStyle.VERTICAL_LIST)
                .build()
                .attachToRecyclerView(mRv);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_new_car;
    }

    @Override
    protected void initNet() {

        setProgressDialog(true, "检测品牌数据更新");
        String updateTime = "1990-01-01 00:00:01";
        if (TextUtils.isEmpty(DataManager.getLastCity()) || !(DataManager.getCityName().equals(DataManager
                .getLastCity())
        )) {
            updateTime = "1990-01-01 00:00:01";
            DataManager.putLastCity(DataManager.getCityName());
        } else {
            updateTime = DataManager.getLastUpdateCarBrand();
        }

        Map<String, Object> param = new HashMap<>();
        param.put("updatetime", updateTime);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetCodeList, param)
                .setBeanType(GetCodeList.class)
                .setCallBack(new WebServiceCallBack<GetCodeList>() {
                    @Override
                    public void onSuccess(final GetCodeList bean) {
                        setProgressDialog(false);
                        List<KJBikeCode> carInfoList = bean.getContent();
                        Log.e(TAG, "更新车辆品牌数据: " + carInfoList.size());
                        if (carInfoList.size() > 0) {
                            ECardXutils3.getInstance().deleteAll(KJBikeCode.class);
                            ECardXutils3.getInstance().saveDate(carInfoList, NewCarActivity.this);
                            DataManager.putLastUpdateCarBrand(TimeUtil.getNowTime());
                        } else {
                            checkEntrustDeployAble();
                            getBindCar();
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void getBindCar() {
        setProgressDialog(true, "加载车辆信息...");
        Map<String, Object> param = new HashMap<>();
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetBindCarList, param)
                .setBeanType(GetBindCarList.class)
                .setCallBack(new WebServiceCallBack<GetBindCarList>() {
                    @Override
                    public void onSuccess(GetBindCarList bean) {
                        setProgressDialog(false);
                        bindingCars = bean.getContent()
                                .getBindingCars();
                        mLlEmpty.setVisibility(bindingCars.size() > 0 ? View.GONE : View.VISIBLE);
                        mSwitchEntrust.setChecked(bean.getContent().getIsGrant() == 1 ? true : false);
                        mNewCarAdapter.setData(bindingCars);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        unbindDialog = DialogUtil.getDoubleDialog(NewCarActivity.this, "是否确定要解绑该车辆?",
                "取消", "确定");
        setOnMenuClickListener(new OnMenuClickListener() {
            @Override
            public void onMenuClick() {
                carPop.showPopupWindowDown();
            }
        }, R.drawable.bg_add);
        mNewCarAdapter.setOnSetDeployListener(this);
        mSwitchEntrust.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                if (isChecked) {
                    EntrustDeployActivity.goActivity(NewCarActivity.this, url);
                } else {
                    cancelEntrustDeploy();
                }
            }
        });
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSrl.setRefreshing(false);
            }
        });
    }

    private void cancelEntrustDeploy() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("IsDeploy", 0);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.SetEntrustDeploy, param)
                .setBeanType(SetEntrustDeploy.class)
                .setCallBack(new WebServiceCallBack<SetEntrustDeploy>() {
                    @Override
                    public void onSuccess(SetEntrustDeploy bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("取消成功");
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void setData() {
        setTitle("我的车");
    }

    @Override
    public void onCarPop(int position) {
        switch (position) {
            case 0:
                GoUtil.goActivity(this, CarBindingActivity.class);
                break;
            case 1:
                GoUtil.goActivity(this, PreRegisterActivity.class);
                break;
            case 2:
                GoUtil.goActivity(this, InsuranceListActivity.class);
                break;
            case 3:
                GoUtil.goActivity(this, InvoiceListActivity.class);
                break;
            case 4:
                GoUtil.goActivity(this, BindCodeActivity.class);
                break;
            default:
                break;

        }
        carPop.dismiss();
    }

    @Override
    public void onAddDeploy(String plateNumber) {
        Map<String, Object> param = new HashMap<>();
        param.put("Platenumber", plateNumber);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.AddElecDeploy, param)
                .setBeanType(GetBindingList.class)
                .setCallBack(new WebServiceCallBack<GetBindingList>() {
                    @Override
                    public void onSuccess(GetBindingList bean) {
                        setProgressDialog(false);
                        getBindCar();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onDelDeploy(String plateNumber) {
        Map<String, Object> param = new HashMap<>();
        param.put("Platenumber", plateNumber);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.DelElecDeploy, param)
                .setBeanType(GetBindingList.class)
                .setCallBack(new WebServiceCallBack<GetBindingList>() {
                    @Override
                    public void onSuccess(GetBindingList bean) {
                        setProgressDialog(false);
                        getBindCar();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onDelAutolDeploy(final String plateNumber, final String listId) {
        mDelAutoDeployDialog = DialogUtil.getDoubleDialog(this, "是否要关闭自动布防", "取消", "确定");
        mDelAutoDeployDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                mDelAutoDeployDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                mDelAutoDeployDialog.dismiss();
                doDelAutoDeploy(plateNumber, listId);
            }
        });
        mDelAutoDeployDialog.show();
    }

    private void doDelAutoDeploy(String plateNumber, String listId) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("PlateNumber", plateNumber);
        param.put("LISTID", listId);
        param.put("IsDeploy", 0);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.SetAutoDeploy, param)
                .setBeanType(SetAutoDeploy.class)
                .setCallBack(new WebServiceCallBack<SetAutoDeploy>() {
                    @Override
                    public void onSuccess(SetAutoDeploy bean) {
                        setProgressDialog(false);
                        getBindCar();
                        ToastUtil.showToast("关闭自动撤布防设置成功");
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onUnbindCar(final String bindingId, final int position) {
        unbindDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unbindDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unbindDialog.dismiss();
                unbindCar(bindingId, position);
            }
        });
        unbindDialog.show();
    }

    private void unbindCar(String bindingId, final int position) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("BindingID", bindingId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.UnBinding, param)
                .setBeanType(UnBinding.class)
                .setCallBack(new WebServiceCallBack<UnBinding>() {
                    @Override
                    public void onSuccess(UnBinding bean) {
                        setProgressDialog(false);
                        mNewCarAdapter.unbindCar(position);

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetCarData(GetCarDataEvent event) {
        getBindCar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCancleEntrustDeploy(CancleEntrustDeployEvent event) {
        mSwitchEntrust.setChecked(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
