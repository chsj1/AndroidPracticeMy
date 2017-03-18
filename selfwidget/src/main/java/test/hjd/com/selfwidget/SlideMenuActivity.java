package test.hjd.com.selfwidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by huangjundong on 2017/3/16.
 */

public class SlideMenuActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slide_menu_main);

        final SlideMenu sm = (SlideMenu) findViewById(R.id.sm);

        findViewById(R.id.ib_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.switchState();
            }
        });
    }
}
