package com.doublewillow.okidea;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.databinding.ActivityMainBinding;
import com.doublewillow.okidea.httpdemo.HttpActivity;
import com.doublewillow.okidea.httpdemo.MyViewActivity;
import com.doublewillow.okidea.service.MyService;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityMainBinding mainBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.activity_main, null);
        mainBinding = DataBindingUtil.bind(view);
        setContentView(view);
        initBanner();
        mainBinding.btnStart.setOnClickListener(this);
        mainBinding.btnStop.setOnClickListener(this);
        mainBinding.btnHttp.setOnClickListener(this);
        mainBinding.btnView.setOnClickListener(this);
    }

    public void initBanner() {
        mainBinding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getViewActivity(), "点击了第" + position + "位", Toast.LENGTH_SHORT);
            }
        });
        //设置banner样式
        mainBinding.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mainBinding.banner.setImageLoader(new GlideImageLoader());
        //设置动画效果
        mainBinding.banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置自动轮播，默认为true
        mainBinding.banner.isAutoPlay(true);
        //设置轮播时间
        mainBinding.banner.setDelayTime(3700);
        //设置指示器位置（当renwohuaNewBinding.bannerCover模式中有指示器时）
        mainBinding.banner.setIndicatorGravity(BannerConfig.CENTER);
        ArrayList<Object> imageUrls = new ArrayList<>();
        imageUrls.add(R.drawable.banner_a);
        imageUrls.add(R.drawable.banner_b);
        imageUrls.add(R.drawable.banner_c);
        mainBinding.banner.setImages(imageUrls);
        mainBinding.banner.start();

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
