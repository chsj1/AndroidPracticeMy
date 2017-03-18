package test.hjd.com.service2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

/**
 * Created by huangjundong on 2017/3/2.
 */

public class ForeGroundService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();


        Notification notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        //状态栏提示文本
        notification.tickerText = "this is tickerText";
        //指定了点击状态栏条目的跳转
        notification.contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        //指定了状态栏展开以后我们看到的界面
        notification.contentView = new RemoteViews(getPackageName(), R.layout.notify_main);
        //启动前台服务
        startForeground(1, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
