package test.hjd.com.service2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/3/2.
 */

public class RemoteService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public void sayHello() throws RemoteException {
            System.out.println("------->say Hello from remote service");
        }
    };
}
