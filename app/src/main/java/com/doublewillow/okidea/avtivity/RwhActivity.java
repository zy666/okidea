package com.doublewillow.okidea.avtivity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.R;
import com.doublewillow.okidea.databinding.ActivityRwhBinding;

public class RwhActivity extends BaseActivity implements View.OnClickListener {
    private ActivityRwhBinding rwhBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_rwh, null);
        rwhBinding = DataBindingUtil.bind(view);
        setContentView(view);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, RwhActivity.class);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {

        } else if (id == R.id.btn_login) {

        }
    }
}
