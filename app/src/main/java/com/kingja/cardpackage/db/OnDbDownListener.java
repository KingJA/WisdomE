package com.kingja.cardpackage.db;

/**
 * Description:TODO
 * Create Time:2017/4/21 9:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OnDbDownListener<T> {
    void onSuccess(T t);

    void onShowProgress();

    void onHideProgress();
}
