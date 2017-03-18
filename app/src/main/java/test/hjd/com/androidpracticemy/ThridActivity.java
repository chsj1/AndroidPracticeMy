package test.hjd.com.androidpracticemy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by huangjundong on 2017/2/25.
 */

public class ThridActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_3);

        setResult(2000, new Intent().putExtra("address", "上海市"));
    }
}
