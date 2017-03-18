package test.hjd.com.broadcastreceiver;

import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    AirPlaneModeBroadcastReceiver2 broadcastReceiver2;
//    MyBroadcastReceiver myBroadcastReceiver2;

    ScreenBroadcastReceiver screenBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //输出当前线程所在的进程id/线程id
//        System.out.println("Mainactivity--------->" + android.os.Process.myPid());
//        System.out.println("Mainactivity-------> " + Thread.currentThread().getId());

        /*
        广播接受者的动态注册.所谓的动态注册,就是通过代码的编写来完成相应的注册
         */
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.intent.action.AIRPLANE_MODE");
//        broadcastReceiver2 = new AirPlaneModeBroadcastReceiver2();
//        registerReceiver(broadcastReceiver2, filter);


//        IntentFilter filter1 = new IntentFilter();
//        filter1.addAction("hahahha");
//        myBroadcastReceiver2 = new MyBroadcastReceiver();
//        registerReceiver(myBroadcastReceiver2, filter1);

//        //本地广播接受者的注册
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("hahahha");
//        LocalBroadcastManager.getInstance(this).registerReceiver(new MyBroadcastReceiver(), intentFilter);

        //特殊的广播接受者(屏幕的变化、电池电量的变化)
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        screenBroadcastReceiver = new ScreenBroadcastReceiver();
        registerReceiver(screenBroadcastReceiver, intentFilter);


        //获取别的应用的数据
        ContentValues values = new ContentValues();
        values.put("name", "hjd1");
        values.put("salary", "31000");
        values.put("id", 1 + "");
        //使用contentResolver通过contentProvider往别的app里面插数据.
        Uri uri = Uri.parse("content://test.hjd.com.contentprovider");

        getContentResolver().insert(uri, values);
        //?
//        getContentResolver().update(uri, values, null, null);

        //delete(uri,where语句,where语句中的占位符中的数据)
//        getContentResolver().delete(uri, "id = ?", new String[]{6 + ""});

        getContentResolver().query(uri,null,null,null,null);


        //注册内容观察者
        getContentResolver().registerContentObserver(Uri.parse("content://test.hjd.com.contentprovider"), true, new MyContentObserver(new Handler()));
    }


    class MyContentObserver extends ContentObserver{
        public MyContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            System.out.println("------>hahahahah 监听到了数据的变化");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("------->注销广播接受者");
        unregisterReceiver(broadcastReceiver2);


//        unregisterReceiver(myBroadcastReceiver2);
        unregisterReceiver(screenBroadcastReceiver);

    }

    public void send(View view) {
        //发送自定义的广播
        Intent intent = new Intent();
        //要想广播接受者能够监听到这一个广播.
        //设置的action就要和receiver监听的action保持一致
        intent.setAction("hahahha");
        /*
        自定义权限需要以packageName.任意字符串来定义.
        这时候需要监听这一条广播的广播接受者就必须要声明并且使用该权限
        如:
        <!--声明自定义权限-->
    <permission android:name="test.hjd.com.broadcastreceiver.xixixixi" />
    <!--使用自定义权限-->
    <uses-permission android:name="test.hjd.com.broadcastreceiver.xixixixi" />

         */
//        sendBroadcast(intent, "test.hjd.com.broadcastreceiver.xixixixi");
//        sendBroadcast(intent);


        //发送本地广播(只在本app内进行广播)
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


//        intent.putExtra("username", "hjd 无序广播这个是发送端传过来的数据");
        //发送有序广播
//        sendOrderedBroadcast(intent, null, new MyBroadcastReceiver(), null, 0, "这个是初始化数据", null);


        sendBroadcast(intent);


        //获取别的应用的数据
        ContentValues values = new ContentValues();
        values.put("name", "hjd1");
        values.put("salary", "31000");
        values.put("id", 1 + "");
        //使用contentResolver通过contentProvider往别的app里面插数据.
        Uri uri = Uri.parse("content://test.hjd.com.contentprovider");

        getContentResolver().insert(uri, values);
    }
}
