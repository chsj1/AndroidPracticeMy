package test.hjd.com.service2;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by huangjundong on 2017/3/2.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("intent service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("------>service tid: " + Thread.currentThread().getId());

        System.out.println("---->onHandleIntent");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("------>onDestroy");
    }
}
