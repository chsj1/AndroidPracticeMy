package test.hjd.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/2/26.
 */

/*
广播接受者的基本使用:
1)extends BroadcastReceiver
2)注册(在<application>界面下完成<receiver>节点的配置)
 */
public class AirPlaneModeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("-------->我监听到了广播");
    }
}
