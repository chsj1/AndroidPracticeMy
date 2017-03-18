package test.hjd.com.service2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/3/2.
 */

public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("------>这时通过服务注册的receiver.我监听到了屏幕的变化");
    }
}
