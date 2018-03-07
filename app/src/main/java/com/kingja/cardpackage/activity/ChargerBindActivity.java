package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetCodeList;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.StringUtil;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.ui.dialog.BaseListDialog;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.ImageUtil;
import com.pizidea.imagepicker.bean.ImageItem;
import com.tdr.wisdome.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2018/3/7 10:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargerBindActivity extends BackTitleActivity {

    private String chargeId;

    private EditText mEtCardNo;
    private EditText mEtShelvesNo;
    private EditText mEtEngineNo;
    private LinearLayout mLlCarBrand;
    private TextView mTvCarBrand;
    private LinearLayout mLlCarColor;
    private TextView mTvCarColor;
    private ImageView mIvCar;
    private ImageView mIvCardId;
    private ImageView mIvBill;
    private EditText mEtPs;
    private List<KJBikeCode> colorList;
    private List<KJBikeCode> cardTypeList;
    private Map<String, String> colorMap = new HashMap<>();
    private Map<String, String> cardTypeMap = new HashMap<>();
    private BaseListDialog carColorDialog;
    private BaseListDialog carBrandDialog;
    private String carColor;
    private String cardBrand;

    @Override
    protected void initVariables() {
        chargeId = getIntent().getStringExtra("chargeId");
        colorList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "4");
        cardTypeList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "6");
        for (KJBikeCode color : colorList) {
            colorMap.put(color.getCODE(), color.getNAME());
        }
        for (KJBikeCode cardType : cardTypeList) {
            cardTypeMap.put(cardType.getCODE(), cardType.getNAME());
        }
    }

    @Override
    protected void initContentView() {
        mEtCardNo = (EditText) findViewById(R.id.et_cardNo);
        mEtShelvesNo = (EditText) findViewById(R.id.et_shelvesNo);
        mEtEngineNo = (EditText) findViewById(R.id.et_engineNo);
        mTvCarBrand = (TextView) findViewById(R.id.tv_carBrand);
        mTvCarColor = (TextView) findViewById(R.id.tv_carColor);
        mIvCar = (ImageView) findViewById(R.id.iv_car);
        mIvBill = (ImageView) findViewById(R.id.iv_bill);
        mIvCardId = (ImageView) findViewById(R.id.iv_cardId);
        mEtPs = (EditText) findViewById(R.id.et_Ps);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charger_bind;
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
                        List<KJBikeCode> carInfoList = bean.getContent();
                        Log.e(TAG, "更新车辆品牌数据: " + carInfoList.size());
                        if (carInfoList.size() > 0) {
                            ECardXutils3.getInstance().deleteAll(KJBikeCode.class);
                            ECardXutils3.getInstance().saveDate(carInfoList);
                            DataManager.putLastUpdateCarBrand(TimeUtil.getNowTime());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setProgressDialog(false);
                                }
                            }, 10000);
                        } else {
                            setProgressDialog(false);
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        setTitle(chargeId);
        mIvCar.setOnClickListener(this);
        mIvBill.setOnClickListener(this);
        mIvCardId.setOnClickListener(this);
        mTvCarBrand.setOnClickListener(this);
        mTvCarColor.setOnClickListener(this);
        carColorDialog = new BaseListDialog<KJBikeCode>(this, colorList) {
            @Override
            protected void fillLvData(List<KJBikeCode> list, int position, TextView tv) {
                tv.setText(list.get(position).getNAME());
            }

            @Override
            protected void onItemSelect(KJBikeCode colorBean) {
                mTvCarColor.setText(colorBean.getNAME());
                carColor = colorBean.getCODE() + "";
            }
        };

        carBrandDialog = new BaseListDialog<KJBikeCode>(this, cardTypeList) {
            @Override
            protected void fillLvData(List<KJBikeCode> list, int position, TextView tv) {
                tv.setText(list.get(position).getNAME());
            }

            @Override
            protected void onItemSelect(KJBikeCode cardTypeBean) {
                mTvCarBrand.setText(cardTypeBean.getNAME());
                cardBrand = cardTypeBean.getCODE() + "";
            }
        };
    }

    @Override
    protected void setData() {
        setTitle("充电器绑定");
    }

    public static void goActivity(Context context, String chargeId) {
        Intent intent = new Intent();
        intent.putExtra("chargeId", chargeId);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_car:
                getPhoto(PHOTO_CAR);
                break;
            case R.id.iv_bill:
                getPhoto(PHOTO_BILL);
                break;
            case R.id.iv_cardId:
                getPhoto(PHOTO_CARD);
                break;
            case R.id.tv_carBrand:
                carBrandDialog.show();
                break;
            case R.id.tv_carColor:
                carColorDialog.show();
                break;
            default:
                break;

        }
    }

    private final int PHOTO_CAR = 0;
    private final int PHOTO_CARD = 1;
    private final int PHOTO_BILL = 2;

    private void getPhoto(final int photoType) {
        AndroidImagePicker.getInstance().pickSingle(this, true, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    Bitmap bitmap = ImageUtil.compressScaleFromF2B(items.get(0).path);
                    switch (photoType) {
                        case PHOTO_CAR:
                            String basePhotoCar = ImageUtil.bitmapToBase64(bitmap);
                            mIvCar.setImageBitmap(bitmap);
                            break;
                        case PHOTO_CARD:
                            String basePhotoCardId = ImageUtil.bitmapToBase64(bitmap);
                            mIvCardId.setImageBitmap(bitmap);
                            break;
                        case PHOTO_BILL:
                            String basePhotoBill = ImageUtil.bitmapToBase64(bitmap);
                            mIvBill.setImageBitmap(bitmap);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }


}
