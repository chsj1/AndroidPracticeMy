package test.hjd.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/2/26.
 */

public class MyBroadcastReceiver3 extends BroadcastReceiver {

    public MyBroadcastReceiver3() {
//        System.out.println("MyBroadcastReceiver--------->" + android.os.Process.myPid());
//        System.out.println("MyBroadcastReceiver-------> "+ Thread.currentThread().getId());
//        System.out.println("------>创建receiver对象");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("------->3 接收到了自定义的广播");


        String data = getResultData();
        System.out.println("------>3 拿到的数据是: " + data);


        String username = intent.getStringExtra("username");
        System.out.println("------>3 username: " + username);
    }
}
