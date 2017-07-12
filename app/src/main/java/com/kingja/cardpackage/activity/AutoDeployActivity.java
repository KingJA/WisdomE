package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.SetAutoDeploy;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.wheelview.TimeSelector;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:自动撤布防
 * Create Time:2017/4/18 14:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AutoDeployActivity extends BackTitleActivity implements View.OnClickListener {
    private LinearLayout mLlStartTime;
    private LinearLayout mLlEndTime;
    private LinearLayout mLlWeek;
    private TextView mTvOpen;
    private String weekdayInt;
    private TextView mTvWeek;
    private String platNumber;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private static final int WEEK_SELECT = 1;
    private String startTime = "09:30";
    private String endTime = "21:30";
    private TimeSelector startTimeSelector;
    private TimeSelector endTimeSelector;


    @Override
    protected void initVariables() {
        platNumber = getIntent().getStringExtra("platNumber");
    }

    @Override
    protected void initContentView() {
        mLlStartTime = (LinearLayout) findViewById(R.id.ll_startTime);
        mLlEndTime = (LinearLayout) findViewById(R.id.ll_endTime);
        mLlWeek = (LinearLayout) findViewById(R.id.ll_week);
        mTvOpen = (TextView) findViewById(R.id.tv_open);
        mTvWeek = (TextView) findViewById(R.id.tv_week);
        mTvStartTime = (TextView) findViewById(R.id.tv_startTime);
        mTvEndTime = (TextView) findViewById(R.id.tv_endTime);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_auto_deploy;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mLlStartTime.setOnClickListener(this);
        mLlEndTime.setOnClickListener(this);
        mLlWeek.setOnClickListener(this);
        mTvOpen.setOnClickListener(this);


    }

    @Override
    protected void setData() {
        setTitle("自动撤布防");
    }

    public static void goActivity(Context context, String platNumber) {
        Intent intent = new Intent(context, AutoDeployActivity.class);
        intent.putExtra("platNumber", platNumber);
        context.startActivity(intent);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_startTime:
                startTimeSelector = new TimeSelector(this, startTime);
                startTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        startTime = hour + ":" + second;
                        mTvStartTime.setText(startTime);
                    }
                });
                startTimeSelector.show();
                break;
            case R.id.ll_endTime:
                endTimeSelector = new TimeSelector(this, startTime);
                endTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        endTime = hour + ":" + second;
                        mTvEndTime.setText(endTime);
                    }
                });
                endTimeSelector.show();
                break;
            case R.id.ll_week:
                GoUtil.goActivityForResult(this, WeekSelectActivity.class, WEEK_SELECT);
                break;
            case R.id.tv_open:
                startTime = mTvStartTime.getText().toString().trim();
                endTime = mTvEndTime.getText().toString().trim();
                if (CheckUtil.checkEmpty(startTime, "请选择开始时间")
                        && CheckUtil.checkEmpty(endTime, "请选择结束时间")
                        && CheckUtil.checkEmpty(weekdayInt, "请选择频率")) {
                    openDeploy();
                }

                break;
        }
    }

    private void openDeploy() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("PlateNumber", platNumber);
        param.put("IsDeploy", 1);
        param.put("StartTime", startTime);
        param.put("EndTime", endTime);
        param.put("Frequency", weekdayInt);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.SetAutoDeploy, param)
                .setBeanType(SetAutoDeploy.class)
                .setCallBack(new WebServiceCallBack<SetAutoDeploy>() {
                    @Override
                    public void onSuccess(SetAutoDeploy bean) {
                        setProgressDialog(false);
                        EventBus.getDefault().post(new GetCarDataEvent());
                        ToastUtil.showToast("自动撤布防设置成功");
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            weekdayInt = data.getStringExtra("weekdayInt");
            mTvWeek.setText(data.getStringExtra("weekdayStr"));
        }

    }
}
