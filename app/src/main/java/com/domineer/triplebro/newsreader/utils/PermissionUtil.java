package com.domineer.triplebro.newsreader.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtil {

    public static void requestPower(final Context context, final Activity activity, final String permission) {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, 1);
                //这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限.它在用户选择"不再询问"的情况下返回false
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(activity, new String[]{permission}, 1);
            }
        }
    }

    public static boolean checkPermission(Context context,String permission) {
        //是否有权限
        boolean havePermission = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;

        return havePermission;

    }

}
