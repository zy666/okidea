package com.doublewillow.lib_frame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * @author zhouyou
 * @version 1.0
 * @desc
 * @date 2017/6/9 14:28
 */

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isAlive = false;
    private boolean isRunning = false;
    public Activity mActivity;

    //侧滑关闭activity。swipeBackActivity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setWindowTheme();
        super.onCreate(savedInstanceState);

        mActivity = this;
        isAlive = true;

    }

    public void showToast(String msg) {
        if(!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
    //通过程序改变屏幕显示的方向
    // SCREEN_ORIENTATION_UNSPECIFIED,//未指定，此为默认值。由Android系统自己选择合适的方向。
    // ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE 横屏
    // ActivityInfo.SCREEN_ORIENTATION_PORTRAIT 竖屏
    public void setWindowTheme() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


//    public View findViewById(int id) {
//        View view = super.findViewById(id);
//        if (view == null && mHelper != null)
//            view = mHelper.findViewById(id);
//        return view;
//    }

    //封装findViewById
    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }


    public Activity getViewActivity() {
        return this;
    }
    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        System.gc();
    }

    @Override
    protected void onDestroy() {

        isAlive = false;
        isRunning = false;
        super.onDestroy();

        if (mActivity != null) mActivity = null;
    }
}
