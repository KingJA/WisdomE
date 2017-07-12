package com.tdr.wisdome.actvitiy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.kingja.cardpackage.activity.PreRecordInfoActivity;
import com.kingja.cardpackage.activity.PreRegisterInfoActivity;
import com.kingja.cardpackage.entiy.GetPreRate;
import com.kingja.cardpackage.entiy.IndexData;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.ui.popupwindow.ListPop;
import com.tdr.wisdome.R;

import java.util.Arrays;

import static com.tdr.wisdome.R.id.ll_preRecord;

/**
 * 车辆预登记二维码
 * Created by Linus_Xie on 2016/8/29.
 */
public class CarQrActivity extends Activity implements View.OnClickListener, ListPop.OnPopItemClickListener {

    private Bitmap bitmapQrCode;
    private ListPop listPop;
    private GetPreRate.ContentBean preRateBean;
    private TextView tv_meetStatus;
    private TextView tv_meetNum;
    private TextView tv_meetTime;
    private TextView tv_siteName;
    private TextView tv_meetFrom;
    private boolean editAble;
    private String meetTime;
    private LinearLayout ll_preRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carcode);
        preRateBean = (GetPreRate.ContentBean) getIntent().getSerializableExtra("PreRateBean");
        initView();
        initData();
    }

    private ImageView image_back;
    private TextView text_title;
    private TextView text_deal;

    private ImageView image_qrCode;

    private void initView() {
        ll_preRecord = (LinearLayout) findViewById(R.id.ll_preRecord);
        image_back = (ImageView) findViewById(R.id.image_back);
        tv_meetStatus = (TextView) findViewById(R.id.tv_meetStatus);
        tv_meetNum = (TextView) findViewById(R.id.tv_meetNum);
        tv_meetTime = (TextView) findViewById(R.id.tv_meetTime);
        tv_siteName = (TextView) findViewById(R.id.tv_siteName);
        tv_meetFrom = (TextView) findViewById(R.id.tv_meetFrom);


        image_back.setOnClickListener(this);
        text_title = (TextView) findViewById(R.id.text_title);
        text_title.setText("预登记二维码");
        text_deal = (TextView) findViewById(R.id.text_deal);
        text_deal.setText("查看");
        text_deal.setOnClickListener(this);
        text_deal.setVisibility(View.VISIBLE);

        image_qrCode = (ImageView) findViewById(R.id.image_qrCode);
        listPop = new ListPop(text_deal, this, Arrays.asList("预登记信息", "预约信息"));
        listPop.setOnPopItemClickListener(this);
    }

    private void initData() {
        try {
            bitmapQrCode = com.tdr.wisdome.zxing.Utils.Create2DCode(preRateBean.getPrerateID());
            image_qrCode.setImageBitmap(bitmapQrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        meetTime = preRateBean.getInvalidTime().substring(0, "2011-01-01".length());
        editAble = preRateBean.getState() == 0 && compareToday(meetTime);
        if ("天津市".equals(DataManager.getCityName())) {
            ll_preRecord.setVisibility(View.VISIBLE);
            tv_meetStatus.setText(editAble ?"(有效)":"(失效)");
            tv_meetNum.setText(preRateBean.getSeq()+"");
            tv_meetTime.setText(meetTime);
            GetPreRate.ContentBean.RegistersiteBean registersite = preRateBean.getRegistersite();
            if (registersite != null) {
                tv_siteName.setText(registersite.getRegistersiteName());
            }

            GetPreRate.ContentBean.RegisterConfigBean register_config = preRateBean.getRegister_Config();
            if (register_config != null) {
                tv_meetFrom.setText(register_config.getOnTime()+"-"+ register_config.getOffTime());
            }
        }




    }

    public boolean compareToday(String meetTime) {
        String formatDate = TimeUtil.getFormatDate();
        if (meetTime.compareTo(formatDate) < 0) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;

            case R.id.text_deal:
                if (CheckUtil.checkCity("天津市")) {
                    listPop.showPopupWindowDown();
                } else {
                    PreRegisterInfoActivity.goActivity(this,preRateBean.getPrerateID(),editAble);
                    finish();
                }

                break;
        }
    }

    @Override
    public void onPopItemClick(int position, String tag) {
        switch (position) {
            case 0:
                PreRegisterInfoActivity.goActivity(this,preRateBean.getPrerateID(),editAble);
                finish();
                break;
            case 1:
                PreRecordInfoActivity.goActivityAndFinish(this, preRateBean,editAble);
                break;
            default:
                break;
        }
    }

    public static void goActivity(Context context, IndexData.ContentBean.PreRateBean preRateBean) {
        Intent intent = new Intent(context, CarQrActivity.class);
        intent.putExtra("PreRateBean", preRateBean);
        context.startActivity(intent);
    }


}
