package com.tdr.wisdome.actvitiy;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.kingja.cardpackage.activity.BackTitleActivity;
import com.kingja.cardpackage.activity.InsuranceStatusActivity;
import com.pizidea.imagepicker.ImageUtil;
import com.tdr.wisdome.R;

/**
 * Description：TODO
 * Create Time：2016/12/8 15:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BigImageActivity extends BackTitleActivity {

    private ImageView iv_big_image;
    private String image;

    @Override
    protected void initVariables() {
        image = getIntent().getStringExtra("image");
    }

    @Override
    protected void initContentView() {
        iv_big_image = (ImageView) findViewById(R.id.iv_big_image);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_bigimage;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        iv_big_image.setImageBitmap(ImageUtil.base64ToBitmap(image));
    }

    @Override
    protected void setData() {
        setTitle("浏览大图");
    }
    public static void goActivity(Activity activity, String image) {
        Intent intent = new Intent(activity, BigImageActivity.class);
        intent.putExtra("image", image);
        activity.startActivity(intent);

    }
}
