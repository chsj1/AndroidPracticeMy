package test.hjd.com.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/3/2.
 */

public class FristService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("--------->hahah onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("--------->onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("------->onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    MyBinder binder = new MyBinder();

    class MyBinder extends Binder {
        public void sayHello() {
            System.out.println("------->hello from service");
        }
    }
}
