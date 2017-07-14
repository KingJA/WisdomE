package com.tdr.wisdome.actvitiy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.kingja.cardpackage.activity.BackTitleActivity;
import com.tdr.wisdome.R;

/**
 * Description：TODO
 * Create Time：2016/12/8 15:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BigImageActivity extends BackTitleActivity {

    private static String TAG = "BigImageActivity";
    private ImageView iv_big_image;
    private Bitmap image;
    private Bitmap bitmap;

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        bitmap = (Bitmap) b.getParcelable("bitmap");
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
        iv_big_image.setImageBitmap(bitmap);
    }

    @Override
    protected void setData() {
        setTitle("浏览大图");
    }

    public static void goActivity(Context context, Bitmap bitmap) {
        Intent intent = new Intent(context, BigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("bitmap", bitmap);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
