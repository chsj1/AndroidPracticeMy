package test.hjd.com.androidpracticemy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/2/26.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("-------->我也监听到了哈哈哈哈的广播");
    }
}
