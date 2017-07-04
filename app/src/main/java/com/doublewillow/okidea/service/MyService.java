package com.doublewillow.okidea.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.doublewillow.lib_frame.log.Log4a;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log4a.d("test", "onCreate-excute");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log4a.d("test", "onStartCommand-excute");

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log4a.d("test", "onDestroy-excute");

    }
}
