package test.hjd.com.androidpracticemy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import test.hjd.com.service2.IMyAidlInterface;

/**
 * Created by huangjundong on 2017/2/25.
 */

/*
创建activity
1）extends Activity
2) 注册Activity
 */
public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局文件
        setContentView(R.layout.activity_main);

        System.out.println("------>MyActivity: onCreate");

        System.out.println("f-------->getTaskId: " + getTaskId() );
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("------>MyActivity: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("------>MyActivity: onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("------>MyActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("------>MyActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("------>MyActivity: onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        System.out.println("------>MyActivity: onRestart");
    }

    /*
                            activity的第一种显式启动的写法
                             */
    public void start(View view) {
        Intent intent = new Intent(MyActivity.this, SecondActivity.class);
        //通过intent,putExtra()来传数据
//        intent.putExtra("username", "hjd");
//
//        //通过bundle来传递数据
//        Bundle bundle = new Bundle();
//        bundle.putInt("salary", 25000);
//        intent.putExtra("salaryBundle", bundle);
//
//
//        Person person = new Person("hjd", 25000);
//        intent.putExtra("person", person);
//
//
//        Person2 person2 = new Person2("hjd", 26000);
//        intent.putExtra("person2", person2);
//        startActivity(intent);


        /*
        数据的回传
        startactivityForResult
        onActivityResult


        setResult(resultCode,data)
         */
//        startActivityForResult(intent, 100);


        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);


        getContentResolver().registerContentObserver(Uri.parse("content://test.hjd.com.contentprovider/"),true,new MyContentObserver(new Handler()));
    }

    class MyContentObserver extends ContentObserver {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        System.out.println("---->requestCode: " + requestCode + " ,resultCode: " + resultCode);

        System.out.println("------->data.getStringExtra: " + data.getStringExtra("callback"));

    }

    public void start1(View view) {
        Intent intent = new Intent(MyActivity.this, MyActivity.class);
        startActivity(intent);
    }

    /*
    activity的第二种显式启动的写法
     */
    public void start3(View view) {
        Intent intent = new Intent();
        intent.setClass(MyActivity.this, ThridActivity.class);
//        startActivity(intent);


        startActivityForResult(intent, 1000);
    }

    /*
   activity的第三种显式启动的写法
    */
    public void start4(View view) {
        Intent intent = new Intent();
        intent.setClassName(MyActivity.this, "test.hjd.com.androidpracticemy.FourthActivity");
        startActivity(intent);

    }

    /*
       activity的第四种显式启动的写法
        */
    public void start5(View view) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(MyActivity.this, FifthActivity.class));
        startActivity(intent);
    }

    /*
       activity的第一种隐式启动的写法
        */
    public void start6(View view) {
//        Intent intent = new Intent("sixactivity");
        Intent intent = new Intent("sameaction");
        startActivity(intent);
    }


    public void start7(View view) {
        Intent intent = new Intent();
        intent.setAction("sevenactivity");
        startActivity(intent);
    }

    public void start9(View view) {
        Intent intent = new Intent();
        intent.setAction("eightactivity");

        //只指定uri的情况
        /*
        他会把type内容置空
         */
//        intent.setData(Uri.parse("hjd:" + 1234));

        //只指定type
        /*
        他会把data内容置空
         */
//        intent.setType("aa/bb");


        //同时设置data和type的时候调用
        intent.setDataAndType(Uri.parse("hjd:" + 1234), "aa/bb");

        startActivity(intent);
    }

    /*
    隐式启动系统的activity——短信的activity.
    其中,intent-filter的配置是去android源码中寻找的

     <intent-filter>
               <action android:name="android.intent.action.SEND" />
               <category android:name="android.intent.category.DEFAULT" />
               <data android:mimeType="text/plain" />
           </intent-filter>
     */
    public void start8(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        //启动系统的短信应用,并且传递参数
        intent.putExtra("subject", "love");
        intent.putExtra("sms_body", "i love jingjing very much");
        intent.putExtra("address", "1836297xxxx");
        startActivity(intent);
    }


    public void startTranslucent(View view) {
        Intent intent = new Intent(MyActivity.this, TranslucentActivity.class);
        startActivity(intent);
    }

    public void bindRemoteService(View view) {
        Intent intent = new Intent();
        intent.setAction("test.hjd.com.service2.remoteservice");
        intent.setPackage("test.hjd.com.service2");
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                IMyAidlInterface.Stub.asInterface(service).sayHello();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
