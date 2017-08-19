package com.doublewillow.okidea.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.R;

public class XianChengActivity extends BaseActivity {


    private static final int CODE = 1;
    private ImageView imageView;
    private TextView mTextView;

    private int[] pic = {R.drawable.banner_a, R.drawable.banner_b, R.drawable.banner_c};
    private int dex;
    private MyRunnable myRunnable = new MyRunnable();
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case CODE:
//                    mTextView.setText("未来是你的");
//                    break;
//            }
//        }
//    };

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xian_cheng);

        mTextView = (TextView) findViewById(R.id.tv_test);
        imageView = (ImageView) findViewById(R.id.iv_test);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, XianChengActivity.class);
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            dex++;
            dex = dex % 3;
            imageView.setBackgroundResource(pic[dex]);
            mHandler.postDelayed(myRunnable, 1000);
        }
    }

    public void onClickTest(View view) {
        int id = view.getId();
        if (id == R.id.btn_test) {
            mHandler.postDelayed(myRunnable, 1000);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Message message = new Message();
//                    message.what = CODE;
//                    handler.sendMessage(message);
//                }
//            }).start();
        } else if (id == R.id.tv_test) {

        }
    }
}
