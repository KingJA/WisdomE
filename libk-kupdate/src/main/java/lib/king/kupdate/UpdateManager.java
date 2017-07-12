package lib.king.kupdate;

import android.app.Activity;

import lib.king.kupdate.strategy.LoadStrategy;
import lib.king.kupdate.strategy.WebServiceStrategy;
import lib.king.kupdate.task.VersionTask;

/**
 * Description：TODO
 * Create Time：2016/9/26 13:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdateManager {


    private boolean updateCancleable;
    private boolean showDownloadDialog;
    private Activity activity;
    private LoadStrategy mLoadStrategy;
    private String updateContent;

    public UpdateManager(Builder builder) {
        this.showDownloadDialog = builder.showDownloadDialog;
        this.updateCancleable = builder.updateCancleable;
        this.activity = builder.activity;
        this.mLoadStrategy = builder.mLoadStrategy;
        this.updateContent = builder.updateContent;
    }

    public void checkUpdate() {
        VersionTask versionTask = new VersionTask(activity, updateCancleable, showDownloadDialog, mLoadStrategy, updateContent);
        versionTask.execute(Constants.APK_NAME);
    }

    public static class Builder {
        private Activity activity;
        private boolean updateCancleable;
        private boolean showDownloadDialog;
        private LoadStrategy mLoadStrategy;
        private String updateContent;

        public Builder(Activity activity) {
            this.activity = activity;
            updateCancleable =true;//默认可以取消更新
            showDownloadDialog=true;//默认显示下载进度框
            mLoadStrategy= new WebServiceStrategy();//默认用WebService下载
            updateContent="更新内容";
        }

        public Builder setUpdateCancleable(boolean cancleable) {
            this.updateCancleable = cancleable;
            return this;
        }

        public Builder setShowDownloadDialog(boolean showDownloadDialog) {
            this.showDownloadDialog = showDownloadDialog;
            return this;
        }

        public Builder setLoadStrategy(LoadStrategy mLoadStrategy) {
            this.mLoadStrategy = mLoadStrategy;
            return this;
        }

        public Builder setUpdateContent(String updateContent) {
            this.updateContent = updateContent;
            return this;
        }

        public UpdateManager build() {
            return new UpdateManager(this);
        }
    }
}
