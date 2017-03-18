package test.hjd.com.myservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("------>onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Intent startService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //显示启动
//        Intent intent = new Intent(MainActivity.this, FristService.class);
//        startService(intent);

//        Intent intent = new Intent();
//        intent.setClass(MainActivity.this, FristService.class);
//        startService(intent);

//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName(MainActivity.this, FristService.class));
//        startService(intent);

//        Intent intent = new Intent();
//        intent.setClassName(MainActivity.this, "test.hjd.com.myservice.FristService");
//        startService(intent);

          //隐式启动
//        Intent intent = new Intent();
//        intent.setAction("firstservice");
//        startService(intent);
    }


    public void startService(View view) {
        startService = new Intent();
        startService.setAction("firstservice");
        startService(startService);
    }


    public void bindService(View view) {
//        Intent intent = new Intent(MainActivity.this, FristService.class);

        Intent intent = new Intent();
        intent.setAction("firstservice");
        bindService(intent, connection, BIND_AUTO_CREATE);
    }


    public void stopService(View view) {
        stopService(startService);
    }
}
