package test.hjd.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/2/26.
 */

public class ScreenBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("------->监听到屏幕的变化");

        //获取监听到的广播的具体动作
        String action = intent.getAction();
        if (action.equals("android.intent.action.SCREEN_ON")) {
            System.out.println("-------->屏幕开启");
        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
            System.out.println("-------->屏幕关闭");
        }
    }
}
