package test.hjd.com.selfwidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by huangjundong on 2017/3/17.
 */

public class ToggleViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.toggleview_main);

        ((ToggleView) findViewById(R.id.tv_switch)).setOnSwitchStateChangeListener(new ToggleView.OnSwitchStateChangeListener() {
            @Override
            public void switchStateChange(int state) {
                System.out.println("------->现在的状态是: " + state);
            }
        });

    }
}
