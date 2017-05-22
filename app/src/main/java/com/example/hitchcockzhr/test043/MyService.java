package com.example.hitchcockzhr.test043;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public static final String TAG = "MyService";
    private MyBinder mBinder = new MyBinder();
    private Handler mHandler;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MyBinder extends Binder {
        public void startDownload() {
            Log.d("TAG", "startDownload()");
            // 具体做后台处理的事情，例如后台下载啊
            if(NetworkUtils.isNetWorkAvailable(getApplicationContext())){
                //Toast.makeText(getApplicationContext(), "网络连接正常", Toast.LENGTH_LONG).show();
                mHandler.post(new ToastRunnable("网络连接正常"));
            }else {
               // Toast.makeText(getApplicationContext(), "网络连接不正常", Toast.LENGTH_LONG).show();
                mHandler.post(new ToastRunnable("网络连接不正常"));
            }
        }
    }
    private class ToastRunnable implements Runnable {
        String mText;

        public ToastRunnable(String text) {
            mText = text;
        }

        @Override
        public void run(){
            Toast.makeText(getApplicationContext(), mText, Toast.LENGTH_SHORT).show();
        }
    }

}
