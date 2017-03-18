package test.hjd.com.service2;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    FirstService.MyBinder binder;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("------>onServiceConnected");

            binder = (FirstService.MyBinder) service;
            binder.sayHello();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("------>activity tid: " + Thread.currentThread().getId());

        intent = new Intent(MainActivity.this, FirstService.class);
//        startService(intent);
//
//
//        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void startService(View view) {
        //以显式启动的方式来启动service
//        Intent intent = new Intent(MainActivity.this, FirstService.class);
//        startService(intent);


//        Intent intent = new Intent();
//        intent.setClass(MainActivity.this, FirstService.class);
//        startService(intent);

//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName(MainActivity.this, FirstService.class));
//        startService(intent);

//        Intent intent = new Intent();
//        intent.setClassName(MainActivity.this, "test.hjd.com.service2.FirstService");
//        startService(intent);


//        Intent intent = new Intent();
//        intent.setAction("firstservicee");
//        startService(intent);


        intent = new Intent(MainActivity.this, FirstService.class);
        startService(intent);


    }


    public void bindService(View view) {
//        Intent intent = new Intent(MainActivity.this, FirstService.class);
//        bindService(intent, conn, BIND_AUTO_CREATE);

        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(conn);
    }

    public void stopService(View view) {
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("------>activity onDestroy");
//        unbindService(conn);
    }


    public void startForeGround(View view) {
        //启动前台服务
//        startService(new Intent(MainActivity.this, ForeGroundService.class));

        //启动远程服务
//        startService(new Intent(MainActivity.this, MyIntentService.class));


        //通过service注册receiver
        startService(new Intent(MainActivity.this, ScreenService.class));
    }

}
