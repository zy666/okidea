package com.doublewillow.okidea.avtivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.R;
import com.doublewillow.okidea.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_login, null);
        loginBinding = DataBindingUtil.bind(view);
        setContentView(view);

        loginBinding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {

    }

}
