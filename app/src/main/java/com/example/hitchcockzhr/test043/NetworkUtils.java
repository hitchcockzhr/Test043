package com.example.hitchcockzhr.test043;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by hitchcockzhr on 2017/5/12.
 */

public class NetworkUtils {

//判断网络是否连接
    public static boolean isNetWorkAvailable(Context context) {

//1.得到网络连接管理器

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

//2.网络信息

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null) {

            return true;

        }
        return false;
    }

//判断是否是wifi
// 打开wifi设置的页面
//Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
//startActivity(intent);
    public static boolean isWifi(Context context) {
//网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//2.网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == connectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

//判断是否是手机流量
    public static boolean isMobile(Context context) {
//1.网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//2.网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == connectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }
}