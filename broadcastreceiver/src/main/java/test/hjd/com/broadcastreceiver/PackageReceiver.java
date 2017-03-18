package test.hjd.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/2/26.
 */

public class PackageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("------->监听到了应用的广播");

        String action = intent.getAction();
        if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
            System.out.println("-------->应用被卸载");
        } else if (action.equals("android.intent.action.PACKAGE_ADDED")) {
            System.out.println("-------->应用被安装");
        }

        /*
        如果 package相关的广播,我么可以通过intent.
        getDataString来获取被操作的应用的包名
         */
        System.out.println("-------->" + intent.getDataString());
    }
}
