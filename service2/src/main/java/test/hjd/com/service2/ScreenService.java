package test.hjd.com.service2;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/3/2.
 */

/*
演示通过service来注册receiver
 */
public class ScreenService extends Service {

    ScreenReceiver screenReceiver;

    @Override
    public void onCreate() {
        super.onCreate();


        System.out.println("------>注册receiver");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");

        screenReceiver = new ScreenReceiver();
        registerReceiver(screenReceiver, intentFilter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("-------->注销receiver");
        unregisterReceiver(screenReceiver);
    }
}
