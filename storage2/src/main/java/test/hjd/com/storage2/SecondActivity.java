package test.hjd.com.storage2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by huangjundong on 2017/3/3.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);
    }


    public void getsp(View view) {
        /*
        getSharedPreferences("share", MODE_PRIVATE):文件的名字,打开方式.
        sp.getString("name", null): 获取文件中key为name的value,默认值是null
         */
//        SharedPreferences sp = getSharedPreferences("share1", MODE_PRIVATE);

        SharedPreferences sp = getPreferences(MODE_PRIVATE);;

//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String name = sp.getString("name", null);
        System.out.println("----->name: " + name);
    }
}
