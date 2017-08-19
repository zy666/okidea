package com.doublewillow.okidea.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.R;

public class MyViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, MyViewActivity.class);
    }
}
