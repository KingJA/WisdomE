package com.kingja.flashlighttest;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

/**
 * Description:TODO
 * Create Time:2017/9/15 11:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Camera2Flashlight implements FlashlightService {

    private final CameraManager manager;

    public Camera2Flashlight(Context context) {
        manager = (CameraManager) context.getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
    }

    @Override
    public void openFlashlight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                manager.setTorchMode("0", true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeFlashlight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                manager.setTorchMode("0", false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void releaseFlashlight() {

    }

    private boolean isLOLLIPOP() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
