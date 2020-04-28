package com.admin.core.ui.camera;

import android.net.Uri;

import com.admin.core.deleggate.PermissionCheckerDelegate;
import com.admin.core.util.file.FileUtil;

/**
 * Copyright (C)
 *
 * @file: LatteCamera
 * @description: 照相机调用类
 */
public class LatteCamera {
    public static Uri createCropFile(){
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
