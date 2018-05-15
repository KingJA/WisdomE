package com.kingja.cardpackage.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.tdr.wisdome.R;


/**
 * Description：TODO
 * Create Time：2016/9/27 15:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DbProgressDialog extends AlertDialog {

    private TextView tv_progress;
    private String tip="数据加载中,请耐心等待";
    private int totleCount;

    public DbProgressDialog(Context context,int totleCount) {
        super(context, R.style.CustomAlertDialog);
        this.totleCount = totleCount;
        setCancelable(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_db_progress);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
    }

    public void setProgress(int progress) {
        tv_progress.setText(tip+" (" + progress + "/"+totleCount+")");
    }

}
