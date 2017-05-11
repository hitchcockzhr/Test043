package com.example.hitchcockzhr.test043;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3, button4;
    boolean isExit;
    Handler mHandler;
    ProgressDialog m_pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3) ;
        button4= (Button)findViewById(R.id.button4) ;
        m_pDialog = new ProgressDialog(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NetworkUtils.isNetWorkAvailable(MainActivity.this)){
                    Toast.makeText(MainActivity.this, "网络连接正常", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "网络连接不正常", Toast.LENGTH_LONG).show();
                    showNormalDialog();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                isExit = false;
            }

        };
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 设置进度条风格，风格为圆形，旋转的
                m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                // 设置ProgressDialog 提示信息
                m_pDialog.setMessage("请稍等。。。");

                // 设置ProgressDialog 的进度条是否不明确
                m_pDialog.setIndeterminate(false);

                // 设置ProgressDialog 是否可以按退回按键取消
                m_pDialog.setCancelable(false);

                m_pDialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_pDialog.hide();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);

        normalDialog.setTitle("网络信息提示");
        normalDialog.setMessage("当前网络不可用，请进行网络设置");
        normalDialog.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        openSettings();
                    }
                });
        normalDialog.setNegativeButton("退出",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        // 显示
        normalDialog.show();
    }

    private void openSettings(){
        if(android.os.Build.VERSION.SDK_INT > 13 ){
            //3.2以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        } else {
            startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
        }
    }

    public void exit(){
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }

}

