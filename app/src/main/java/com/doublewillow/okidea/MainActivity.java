package com.doublewillow.okidea;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.databinding.ActivityMainBinding;
import com.doublewillow.okidea.httpdemo.HttpActivity;
import com.doublewillow.okidea.httpdemo.MyViewActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMainBinding mainBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        mainBinding = DataBindingUtil.bind(view);
        setContentView(view);
        mainBinding.btnStart.setOnClickListener(this);
        mainBinding.btnStop.setOnClickListener(this);
        mainBinding.btnHttp.setOnClickListener(this);
        mainBinding.btnView.setOnClickListener(this);
    }


    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_start) {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        } else if (id == R.id.btn_stop) {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        } else if (id == R.id.btn_http) {
            Intent intent = new Intent(this, HttpActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn_view) {
            Intent intent = new Intent(this, MyViewActivity.class);
            startActivity(intent);
        }
    }
}
