package com.dcl.javacv.baidu.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.dcl.javacv.mvpproject.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/**
 * Created by dengchunlin on 2018/3/14.
 */

public class RxPerUtils {
    /**
     * 请求权限的获取方法
     * activity：Activity对象
     * permissions：需要获取的权限，可以传多个
     * aloe: (b: Boolean)：一个参数的回调方法，b为true，表示用户给了权限，false，表示没有给权限
     */
    public static void requestPermission(@NotNull Activity activity, @NotNull String[] permissions,
                                  @NotNull final Function1 aloe) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(aloe, "aloe");
        new RxPermissions(activity).request((String[]) Arrays.copyOf(permissions, permissions.length))
                .subscribe(aBoolean -> {
                    Intrinsics.checkExpressionValueIsNotNull(aBoolean, "aBoolean");
                    aloe.invoke(aBoolean);
                });
    }

    public static void setupPermission(@NotNull final Activity activity, @NotNull String permissionName,
                                      @NotNull String msg, @NotNull final Function0 aloe) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(permissionName, "permissionName");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(aloe, "aloe");
        (new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Dialog)).setTitle("权限申请")
                .setMessage("请在“权限”中开启“" + permissionName + "权限”，以正常使用" + msg)
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {

        }).setPositiveButton("去设置", (dialog, which) -> {
            if (isMiUi()) {
                setMiUiPermissions(activity);
            } else {
                activity.startActivityForResult((new Intent("android.settings.APPLICATION_DETAILS_SETTINGS"))
                        .setData(Uri.fromParts("package", activity.getPackageName(),
                                null)), 1000);
            }

        }).create().show();
    }

    private static void setMiUiPermissions(Activity activity) {
        if (isMiUi()) {
            try {
                activity.startActivityForResult((new Intent("miui.intent.action.APP_PERM_EDITOR"))
                        .setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
                        .putExtra("extra_pkgname", activity.getPackageName()), 1000);
            } catch (Exception var5) {
                try {
                    activity.startActivityForResult((new Intent("miui.intent.action.APP_PERM_EDITOR"))
                            .setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
                            .putExtra("extra_pkgname", activity.getPackageName()), 1000);
                } catch (Exception var4) {
                    activity.startActivityForResult((new Intent("android.settings.APPLICATION_DETAILS_SETTINGS"))
                            .setData(Uri.fromParts("package", activity.getPackageName(), null)), 1000);
                }
            }
        }

    }

    private static boolean isMiUi() {
        String device = Build.MANUFACTURER;
        if (Intrinsics.areEqual(device, "Xiaomi")) {
            try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                return prop.getProperty("ro.miui.ui.version.code", null) != null
                        || prop.getProperty("ro.miui.ui.version.name", null) != null
                        || prop.getProperty("ro.miui.internal.storage", null) != null;
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        return false;
    }
}
