package com.tdr.wisdome.actvitiy;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.activity.AgentActivity;
import com.kingja.cardpackage.activity.ChargeListActivity;
import com.kingja.cardpackage.activity.ChargerActivity;
import com.kingja.cardpackage.activity.HouseActivity;
import com.kingja.cardpackage.activity.IntermediaryActivity;
import com.kingja.cardpackage.activity.LoginActivity;
import com.kingja.cardpackage.activity.MyMsgActivity;
import com.kingja.cardpackage.activity.NewCarActivity;
import com.kingja.cardpackage.activity.NfcRoomActivity;
import com.kingja.cardpackage.activity.PerfectInfoActivity;
import com.kingja.cardpackage.activity.RentActivity;
import com.kingja.cardpackage.activity.ShopActivity;
import com.kingja.cardpackage.adapter.MainCardAdapter;
import com.kingja.cardpackage.base.BaseActivity;
import com.kingja.cardpackage.db.DatebaseManager;
import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.City;
import com.kingja.cardpackage.entiy.CityEvent;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetCardEvent;
import com.kingja.cardpackage.entiy.GetMsgCountEvent;
import com.kingja.cardpackage.entiy.GetUserCards;
import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.kingja.cardpackage.entiy.UpdateFunctionList_Result;
import com.kingja.cardpackage.entiy.UpdateFunctions;
import com.kingja.cardpackage.entiy.UserCard;
import com.kingja.cardpackage.net.PoolManager;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.ActivityManager;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;
import com.tdr.wisdome.amap.LocationTask;
import com.tdr.wisdome.amap.OnLocationGetListener;
import com.tdr.wisdome.amap.PositionEntity;
import com.tdr.wisdome.util.Constants;
import com.tdr.wisdome.util.PermissionUtils;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.util.WebServiceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import lib.king.kupdate.UpdateManager;
import lib.king.kupdate.strategy.WebServiceStrategy;

import static com.kingja.cardpackage.util.KConstants.CITY_POLICE_DB;


public class MainActivity extends BaseActivity implements View.OnClickListener,
        OnLocationGetListener, Handler.Callback {
    private static final String TAG = "MainActivity";
    private String[] permissionArray = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION};
    private String cityCode = "";//城市代码
    private final static int LOCKEY = 2003;
    public static List<String> mCardList = Arrays.asList("1001", "1002", "1003", "1004", "1005", "1006", "1007",
            "1008", "1009", "1010");
    private LocationTask mLocationTask;
    private String cityName = "";
    private RelativeLayout rl_msg;
    private TextView tv_msgCount;
    private RecyclerView rv;
    private MainCardAdapter mMainCardAdapter;
    private LinearLayout ll_personal;
    private LinearLayout ll_city;
    private TextView tv_cityName;
    private boolean neetGetUserCard = true;
    private List<String> cardCodes = new ArrayList<>();


    @Override
    protected void initVariables() {
        UpdateManager.Builder builder = new UpdateManager.Builder(this);
        builder.setUpdateCancleable(false)
                .setShowDownloadDialog(true)
                .setLoadStrategy(new WebServiceStrategy())
                .setUpdateContent("智慧e点通新版上线啦~")
                .build()
                .checkUpdate();
        Log.e(TAG, "getToken " + TextUtils.isEmpty(DataManager.getToken()));
        if (!TextUtils.isEmpty(DataManager.getToken())) {

            downFunctionDb();
        }

        copyPoliceDb();
        PermissionUtils.checkPermissionArray(MainActivity.this, permissionArray, PermissionUtils
                .PERMISSION_REQUEST_CODE);
        EventBus.getDefault().register(this);
        mLocationTask = new LocationTask(getApplicationContext());
        mLocationTask.setOnLocationGetListener(this);


    }

    private void downFunctionDb() {
        Log.e(TAG, "downFunctionDb ");
        Map<String, Object> param = new HashMap<>();
        param.put("LastUpdateTime", DataManager.getLastUpdateFunction());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.UpdateFunctionList, param)
                .setBeanType(UpdateFunctionList_Result.class)
                .setCallBack(new WebServiceCallBack<UpdateFunctionList_Result>() {
                    @Override
                    public void onSuccess(UpdateFunctionList_Result bean) {
                        List<UpdateFunctions> functions = bean.getContent();
                        Log.e(TAG, "更新功能列表: " + functions.size());
                        if (functions.size() > 0) {
                            DataManager.putLastUpdateFunction(TimeUtil.getNowTime());
                            ECardXutils3.getInstance().saveDate(functions);
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        Log.e(TAG, "功能列表 onErrorResult: " + errorResult.getResultText());
                    }
                }).build().execute();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    private void copyPoliceDb() {
        PoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                DatebaseManager.getInstance(getApplicationContext()).copyDataBase(CITY_POLICE_DB);
            }
        });
    }


    @Override
    protected void initView() {
        ll_personal = (LinearLayout) findViewById(R.id.ll_personal);
        rv = (RecyclerView) findViewById(R.id.rv);
        rl_msg = (RelativeLayout) findViewById(R.id.rl_msg);
        tv_msgCount = (TextView) findViewById(R.id.tv_msgCount);
        ll_city = (LinearLayout) findViewById(R.id.ll_city);
        tv_cityName = (TextView) findViewById(R.id.tv_cityName);
        mMainCardAdapter = new MainCardAdapter(this, mCardList);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(DataManager.getCityCode())) {
            mLocationTask.startSingleLocate();
        } else {
            tv_cityName.setText(DataManager.getCityName());
            getUserCard();

        }
        ll_personal.setOnClickListener(this);
        rl_msg.setOnClickListener(this);
        ll_city.setOnClickListener(this);
        mMainCardAdapter.setOnItemClickListener(new BaseRvAdaper.OnItemClickListener<String>() {
            @Override
            public void onItemClick(String cardCode, int position) {
                if (TextUtils.isEmpty(DataManager.getToken())) {
                    showDialogAndGoActivity("请先登录账号", LoginActivity.class);
                    return;
                }
                if (TextUtils.isEmpty(DataManager.getIdCard())) {
                    GoUtil.goActivity(MainActivity.this, PerfectInfoActivity.class);
                    return;
                }
                switch (cardCode) {
                    case "1003"://我的车
                        GoUtil.goActivity(MainActivity.this, NewCarActivity.class);
                        break;
                    case "1005"://亲情关爱
                        GoUtil.goActivity(MainActivity.this, MainCareActivity.class);
                        break;
                    case "1006"://服务商城
                        ToastUtil.showToast("当前地区暂未开通此项服务");
                        break;
                    case "1001"://我的住房
                        GoUtil.goActivity(MainActivity.this, HouseActivity.class);
                        break;
                    case "1002"://我家出租房
                        GoUtil.goActivity(MainActivity.this, RentActivity.class);
                        break;
                    case "1004"://我的店
                        GoUtil.goActivity(MainActivity.this, ShopActivity.class);
                        break;
                    case "1007"://出租房代管
                        GoUtil.goActivity(MainActivity.this, AgentActivity.class);
                        break;
                    case "1008"://出租房中介
                        GoUtil.goActivity(MainActivity.this, IntermediaryActivity.class);
                        break;
                    case "1009"://NFC门禁
                        GoUtil.goActivity(MainActivity.this, NfcRoomActivity.class);
                        break;
                    case "1010"://充电器
                        GoUtil.goActivity(MainActivity.this, ChargeListActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    protected void setData() {
        new RecyclerViewHelper.Builder(this)
                .setCallbackAdapter(mMainCardAdapter)
                .setLayoutStyle(LayoutHelper.LayoutStyle.VERTICAL_LIST)
                .setDragable(true)
                .build()
                .attachToRecyclerView(rv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_personal:
                if (TextUtils.isEmpty(Constants.getToken())) {
                    showDialogAndGoActivity("请先登录账号", LoginActivity.class);
                } else {
                    GoUtil.goActivity(this, PersonalActivity.class);
                }
                break;

            case R.id.rl_msg:
                if (TextUtils.isEmpty(Constants.getToken())) {
                    showDialogAndGoActivity("请先登录账号", LoginActivity.class);
                } else {
                    GoUtil.goActivity(this, MyMsgActivity.class);
                }
                break;

            case R.id.ll_city:
                Intent intent = new Intent(this, CityPickerActivity.class);
                intent.putExtra("activity", "MainActivity");
                startActivityForResult(intent, LOCKEY);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }

    private void showDialogAndGoActivity(String tip, final Class clazz) {
        final NormalDialog doubleDialog = DialogUtil.getDoubleDialog(this, tip, "取消", "确认");
        doubleDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                doubleDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                doubleDialog.dismiss();
                GoUtil.goActivityAndFinish(MainActivity.this, clazz);

            }
        });
        doubleDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        neetGetUserCard = true;
        JPushInterface.onPause(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCKEY:
                if (resultCode == RESULT_OK) {
                    City mInfo = (City) data.getSerializableExtra("city");
                    DataManager.putCityCode(mInfo.getCityCode());
                    DataManager.putCityName(mInfo.getCityName());
                    tv_cityName.setText(mInfo.getCityName());
                    sendCurrentCityCode(mInfo.getCityCode(), mInfo.getCityName());
                    Log.e(TAG, "手动定位城市: " + mInfo.getCityName());
                    neetGetUserCard = true;
                    getUserCard();
                }

                break;
        }
    }

    private void sendCurrentCityCode(final String cityCode, final String cityName) {
        if (!DataManager.getToken().equals("")) {
            //Token不为空则说明用户已经登录，发送设置当前城市指令
            JSONObject json = new JSONObject();
            try {
                json.put("CityCode", cityCode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("token", Constants.getToken());
            map.put("cardType", "");
            map.put("taskId", "");
            map.put("DataTypeCode", "EditCurrentCity");
            map.put("content", json.toString());
            WebServiceUtils.callWebService(Constants.WEBSERVER_URL, Constants.WEBSERVER_CARDHOLDER, map, new
                    WebServiceUtils.WebServiceCallBack() {
                        @Override
                        public void callBack(String result) {
                            if (result != null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    int resultCode = jsonObject.getInt("ResultCode");
                                    String resultText = Utils.initNullStr(jsonObject.getString("ResultText"));
                                    if (resultCode == 0) {
                                        Constants.setCityCode(cityCode);
                                        Constants.setCityName(cityName);
                                    } else {
                                        Log.e(TAG, resultText);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e(TAG, "设置当前城市JSON解析出错");
                                }
                            } else {
                                Log.e(TAG, "获取数据错误，请稍后重试！");
                            }
                        }
                    });
        }
    }

    private void getUserCard() {
        if (!neetGetUserCard) {
//            ToastUtil.showToast("不需要重新定位");
            return;
        }
        if (TextUtils.isEmpty(DataManager.getToken())) {
            ToastUtil.showToast("未登录");
            return;
        }
        if (TextUtils.isEmpty(DataManager.getCityName())) {
            ToastUtil.showToast("等待选择城市");
            return;
        }
        neetGetUserCard = false;
        setProgressDialog(true, "更新卡列表...");
        Map<String, Object> param = new HashMap<>();
        param.put("citycode", DataManager.getCityCode());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.GetUserCards, param)
                .setBeanType(GetUserCards.class)
                .setCallBack(new WebServiceCallBack<GetUserCards>() {
                    @Override
                    public void onSuccess(final GetUserCards bean) {
                        getMsgCount();
                        DataManager.putCardCodes("");
                        setProgressDialog(false);
                        final List<UserCard> cardlist = bean.getContent().getCardlist();
                        Log.e(TAG, "更新卡列表: " + cardlist.size());
                        if (cardlist.size() > 0) {
                            ECardXutils3.getInstance().saveDate(cardlist);
                            StringBuffer sb = new StringBuffer();
                            for (UserCard city : cardlist) {
                                sb.append(city.getCardCode() + "#");
                            }
                            DataManager.putCardCodes(sb.toString());
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                cardCodes.clear();
                                for (UserCard userCard : cardlist) {
                                    cardCodes.add(userCard.getCardCode());
                                }
                                mMainCardAdapter.setData(cardCodes);
                            }
                        }, 200);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void getMsgCount() {
        if (TextUtils.isEmpty(Constants.getToken())) {
            Log.e(TAG, "Constants.getToken(): " + Constants.getToken());
            return;
        }
        if (TextUtils.isEmpty(Constants.getCityCode())) {
            Log.e(TAG, "Constants.getCityCode(): " + Constants.getCityCode());
            return;
        }
        Map<String, Object> param = new HashMap<>();
        param.put("IsRead", 0);
        param.put("OnlyGetRecord", true);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants
                        .GetUserMessagePager, param)
                .setBeanType(GetUserMessagePager.class)
                .setCallBack(new WebServiceCallBack<GetUserMessagePager>() {
                    @Override
                    public void onSuccess(GetUserMessagePager bean) {
                        int msgCount = bean.getContent().getCount();
                        if (msgCount > 0) {
                            tv_msgCount.setText(msgCount > 99 ? "..." : msgCount + "");
                            tv_msgCount.setVisibility(View.VISIBLE);
                        } else {
                            tv_msgCount.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        Log.e(TAG, "卡列表 onErrorResult: " + errorResult.getResultText());
                    }
                }).build().execute();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationTask.onDestroy();
        ActivityManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLocationGet(PositionEntity entity) {
        cityName = entity.city;
        tv_cityName.setText(cityName);
        Log.e(TAG, "自动定位城市: " + cityName);
        DataManager.putCityName(cityName);
        getUserCard();
    }

    @Override
    public void onRegecodeGet(PositionEntity entity) {
        cityName = entity.city;
        tv_cityName.setText(cityName);
        Log.e(TAG, "自动重新定位城市: " + cityName);
        DataManager.putCityName(cityName);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Constants.HANDLER_KEY_GETVERSION_SUCCESS:
                break;
            case Constants.HANDLER_KEY_GETVERSION_FAIL:
                break;
        }
        return false;
    }

    private long firstTime;

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if ((secondTime - firstTime) > 2000) {
            Toast.makeText(this, "长按两次退出", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.PERMISSION_REQUEST_CODE:
                if (PermissionUtils.verifyPermissions(grantResults)) {

                } else {
                    Toast.makeText(this, "WRITE_CONTACTS Denied", Toast.LENGTH_SHORT)
                            .show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("帮助");
                    builder.setMessage("当前应用缺少必要权限。点击设置打开权限设置页,否则无法使用该app");

                    // 拒绝, 退出应用
                    builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    });

                    builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startAppSettings();
                        }
                    });

                    builder.setCancelable(false);

                    builder.show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private static final String PACKAGE_URL_SCHEME = "package:";

    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetCard(GetCardEvent event) {
        neetGetUserCard = true;
        getUserCard();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMsgCount(GetMsgCountEvent event) {
        getMsgCount();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeCity(CityEvent event) {
        tv_cityName.setText(event.getCityName());
    }


}
