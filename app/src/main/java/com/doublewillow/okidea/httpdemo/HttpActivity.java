package com.doublewillow.okidea.httpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.doublewillow.lib_frame.BaseActivity;
import com.doublewillow.okidea.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
//        WebView webView = getView(R.id.wv_test);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://www.baidu.com");
        Button button = getView(R.id.btn_http_url_connection);
        textView = getView(R.id.tv_http_url_connection);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpUrlConnection();
            }
        });
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }

    public void sendRequestWithHttpUrlConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://juejin.im/search?query=android%20http");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setConnectTimeout(8000);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    //下边对获取到的输入流进行获取

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    showResponse(stringBuffer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
