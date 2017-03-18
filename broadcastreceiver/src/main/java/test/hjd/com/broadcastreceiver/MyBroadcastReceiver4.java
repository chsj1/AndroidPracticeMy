package test.hjd.com.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/2/26.
 */

public class MyBroadcastReceiver4 extends BroadcastReceiver {

    public MyBroadcastReceiver4() {
//        System.out.println("MyBroadcastReceiver--------->" + android.os.Process.myPid());
//        System.out.println("MyBroadcastReceiver-------> "+ Thread.currentThread().getId());
//        System.out.println("------>创建receiver对象");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("------->4 接收到了自定义的广播");


        //有序广播可以被中止.
//        abortBroadcast();


        //获取数据
        String data = getResultData();
        System.out.println("------>4 拿到的数据是: " + data);
        //修改数据.数据可以被修改
        setResultData(data + "(我已经修改过了)");


        String username = intent.getStringExtra("username");
        System.out.println("------>4 username: " + username);

    }
}
