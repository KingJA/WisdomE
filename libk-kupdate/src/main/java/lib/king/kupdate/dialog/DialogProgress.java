package lib.king.kupdate.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.TextView;

import lib.king.kupdate.R;

/**
 * Description：TODO
 * Create Time：2016/9/27 15:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DialogProgress extends AlertDialog {

    private ProgressBar pb_update;
    private TextView tv_progress;
    private String tip="拼命下载中";

    public DialogProgress(Context context) {
        super(context, R.style.CustomAlertDialog);
    }

    public DialogProgress(Builder builder) {
        super(builder.context, R.style.CustomAlertDialog);
        this.tip=builder.tip;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_kprogress);
        pb_update = (ProgressBar) findViewById(R.id.pb_update);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
    }

    public void setProgress(int progress) {
        pb_update.setProgress(progress);
        tv_progress.setText(tip+" (" + progress + "/100)");
    }

    public static class Builder {
        private Context context;
        private String tip;

        public Builder(Context context) {
            this.context = context;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public DialogProgress build() {
            return new DialogProgress(this);
        }
    }
}
