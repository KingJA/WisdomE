package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.BindCharger;
import com.kingja.cardpackage.entiy.BindChargerParam;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.entiy.RefleshChargeList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.dialog.BaseListDialog;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.ImageUtil;
import com.pizidea.imagepicker.bean.ImageItem;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.BrandActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private TextView mTvCarBrand;
    private TextView mTvCarColor;
    private ImageView mIvCar;
    private ImageView mIvCardId;
    private ImageView mIvBill;
    private EditText mEtRemark;
    private List<KJBikeCode> colorList;
    private Map<String, String> colorMap = new HashMap<>();
    private BaseListDialog carColorDialog;
    private String carColor;
    private String cardBrand;
    private String basePhotoCar;
    private String basePhotoCardId;
    private String basePhotoBill;
    private final int PHOTO_CAR = 0;
    private final int PHOTO_CARD = 1;
    private final int PHOTO_BILL = 2;

    @Override
    protected void initVariables() {
        chargeId = getIntent().getStringExtra("chargeId");
        colorList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "4");
        if (colorList == null || colorList.size() == 0) {
            ToastUtil.showToast("数据库信息获取失败");
            return;
        }
        for (KJBikeCode color : colorList) {
            colorMap.put(color.getCODE(), color.getNAME());
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
        mEtRemark = (EditText) findViewById(R.id.et_Ps);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charger_bind;
    }

    @Override
    protected void initNet() {
    }

    protected void bindDevice() {
        final String cardNo = mEtCardNo.getText().toString().trim();
        String shelvesNo = mEtShelvesNo.getText().toString().trim();
        String engineNo = mEtEngineNo.getText().toString().trim();
        String remark = mEtRemark.getText().toString().trim();

        if (CheckUtil.checkEmpty(cardNo, "车牌号不能为空")
                && CheckUtil.checkEmpty(shelvesNo, "车架号不能为空")
                && CheckUtil.checkEmpty(engineNo, "电机号不能为空")
                && CheckUtil.checkEmpty(cardBrand, "请选择车辆品牌")
                && CheckUtil.checkEmpty(carColor, "请选择车辆颜色")
                && CheckUtil.checkEmpty(basePhotoCar, "请上传车辆全身照片")
                && CheckUtil.checkEmpty(basePhotoCardId, "请上传身份证照片")) {
            doBindDevice(cardNo, shelvesNo, engineNo, remark);
        }
    }

    private void doBindDevice(final String cardNo, String shelvesNo, String engineNo, String remark) {
        setProgressDialog(true);
        BindChargerParam param = new BindChargerParam();
        param.setChargerId(chargeId);//充电器编号
        param.setRemark(remark);
        param.setVehicleBrand(cardBrand);//车辆品牌
        param.setVehicleType("1");//车辆类型(1电动车，2助力车，3摩托车，4警车，5三轮车)
        param.setPlateNumber(cardNo);
        param.setColorId(carColor);
        param.setEngineNo(engineNo);
        param.setShelvesNo(shelvesNo);
        param.setOwnerName(DataManager.getRealName());
        param.setCardId(DataManager.getIdCard());
        param.setCardType("1");
        param.setResidentAddress(DataManager.getResideaddress());
        param.setPhone1(DataManager.getPhone());
        param.setPhone2("");
        param.setUnitId(DataManager.getUnitId());

        List<BindChargerParam.PhotoListFileBean> photoListFile = new ArrayList<>();
        BindChargerParam.PhotoListFileBean carPhoto = new BindChargerParam.PhotoListFileBean();
        carPhoto.setINDEX(5);
        carPhoto.setPhoto(UUID.randomUUID().toString());
        carPhoto.setPhotoFile(basePhotoCar);

        BindChargerParam.PhotoListFileBean cardIdPhoto = new BindChargerParam.PhotoListFileBean();
        cardIdPhoto.setINDEX(6);
        cardIdPhoto.setPhoto(UUID.randomUUID().toString());
        cardIdPhoto.setPhotoFile(basePhotoCardId);

        BindChargerParam.PhotoListFileBean billdPhoto = new BindChargerParam.PhotoListFileBean();
        billdPhoto.setINDEX(3);
        billdPhoto.setPhoto(UUID.randomUUID().toString());
        billdPhoto.setPhotoFile(basePhotoBill);

        photoListFile.add(carPhoto);
        photoListFile.add(cardIdPhoto);
        photoListFile.add(billdPhoto);
        param.setPhotoListFile(photoListFile);

        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants.BindCharger,
                        param)
                .setBeanType(BindCharger.class)
                .setCallBack(new WebServiceCallBack<BindCharger>() {
                    @Override
                    public void onSuccess(BindCharger bean) {
                        //更新充电器列表
                        setProgressDialog(false);
                        ToastUtil.showToast("绑定成功");
//                        EventBus.getDefault().post(new RefleshChargeList());
                        Intent intent = new Intent();
                        intent.putExtra("chargeId", chargeId);
                        intent.putExtra("plateNumber", cardNo);
                        setResult(Activity.RESULT_OK, intent);
                        finish();

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        setOnRightClickListener(new OnRightClickListener() {
            @Override
            public void onRightClick() {
                bindDevice();

            }
        }, "完成");
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

    }

    @Override
    protected void setData() {
        setTitle(chargeId);
    }

    public static void goActivity(Activity context, String chargeId, int bindDeviceCode) {
        Intent intent = new Intent(context, ChargerBindActivity.class);
        intent.putExtra("chargeId", chargeId);
        context.startActivityForResult(intent, bindDeviceCode);
    }

    public static int REQUEST_CAR_BRAND = 3;

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
                GoUtil.goActivityForResult(this, BrandActivity.class, REQUEST_CAR_BRAND);
                break;
            case R.id.tv_carColor:
                carColorDialog.show();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CAR_BRAND) {
                cardBrand = data.getStringExtra("brandCode");
                mTvCarBrand.setText(data.getStringExtra("brandName"));
            }
        }
    }

    private void getPhoto(final int photoType) {
        AndroidImagePicker.getInstance().pickSingle(this, true, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    Bitmap bitmap = ImageUtil.compressScaleFromF2B(items.get(0).path);
                    switch (photoType) {
                        case PHOTO_CAR:
                            basePhotoCar = ImageUtil.bitmapToBase64(bitmap);
                            mIvCar.setImageBitmap(bitmap);
                            break;
                        case PHOTO_CARD:
                            basePhotoCardId = ImageUtil.bitmapToBase64(bitmap);
                            mIvCardId.setImageBitmap(bitmap);
                            break;
                        case PHOTO_BILL:
                            basePhotoBill = ImageUtil.bitmapToBase64(bitmap);
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
