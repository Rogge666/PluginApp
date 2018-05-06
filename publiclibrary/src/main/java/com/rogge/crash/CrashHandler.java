package com.rogge.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Looper;
import android.text.format.DateFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * [Description]
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/6/18.
 * @since 1.0.0
 */

public class CrashHandler extends BaseCrashHandler {
    private Context context;
    private String versionName;
    private int versionCode;

    public CrashHandler(Context context) {
        this.context = context;
    }

    @Override
    public boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Looper.loop();
            }

        }.start();

        saveLog(ex);
        return true;
    }

    private void saveLog(Throwable ex) {
        try {

            File errorDir = new File(context.getExternalCacheDir().getAbsolutePath() + "/Logs");
            if (!errorDir.exists()) {
                errorDir.mkdirs();
            }
            File errorFile = new File(errorDir, "/" + DateFormat.format("yyyy年MM月dd日 HH时mm分ss秒 E ", new Date()).toString() + "crash_log.log");
            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }

            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;

            OutputStream out = new FileOutputStream(errorFile, true);
            out.write(("\n\n-----错误分割线" + DateFormat.format("yyyy年MM月dd日 HH时mm分ss秒 E ", new Date()) + "-----\n\n").getBytes());
            out.write(getCrashHead().getBytes());
            PrintStream stream = new PrintStream(out);
            ex.printStackTrace(stream);
            stream.flush();
            out.flush();
            stream.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCrashHead() {
        return "\n************* Crash Log Head ****************" + "\n设备厂商             : " + Build.MANUFACTURER +// 设备厂商
                "\n设备型号             : " + Build.MODEL +// 设备型号
                "\n系统版本             : " + Build.VERSION.RELEASE +// 系统版本
                "\nSDK版本              : " + Build.VERSION.SDK_INT +// SDK版本
                "\nApp VersionName     : " + versionName +
                "\nApp VersionCode    : " + versionCode + "\n************* Crash Log Head ****************\n\n";
    }
}
