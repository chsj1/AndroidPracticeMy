package test.hjd.com.service2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/3/2.
 */

public class FirstService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("-------->xixi  onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("-------->onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("------->service onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("------->onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        System.out.println("------->onUnbind");


        return super.onUnbind(intent);
    }

    MyBinder binder = new MyBinder();

    class MyBinder extends Binder {
        public void sayHello() {
            System.out.println("----->say hello from service");
        }
    }



}
