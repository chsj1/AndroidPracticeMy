package test.hjd.com.androidpracticemy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by huangjundong on 2017/2/25.
 */

public class EighthActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_8);
    }

    public void finish1(View view) {
        finish();
    }
}
