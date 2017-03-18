package test.hjd.com.selfwidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * Created by huangjundong on 2017/3/10.
 */
public class YoukuActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;

    boolean isLevelDisplay2 = true;
    boolean isLevelDisplay3 = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.youku_main);


        ImageButton ib_home = (ImageButton) findViewById(R.id.ib_home);
        ib_home.setOnClickListener(this);
        ImageButton ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        ib_menu.setOnClickListener(this);


        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_home) {
            System.out.println("--------->你点击了home键");

            if (isLevelDisplay2) {
                long delay = 0;
                if (isLevelDisplay3) {
                    AnimationUtil.rotateAnimation(true, rl_level3);
                    isLevelDisplay3 = false;
                    delay = 200;
                }

                AnimationUtil.rotateAnimation(true, rl_level2, delay);
                isLevelDisplay2 = false;
            } else {
                AnimationUtil.rotateAnimation(false, rl_level2);

                isLevelDisplay2 = true;
            }
        } else if (v.getId() == R.id.ib_menu) {
            System.out.println("--------->你点击了menu键");
            if (isLevelDisplay3) {
                AnimationUtil.rotateAnimation(true, rl_level3);
                isLevelDisplay3 = false;
            } else {
                AnimationUtil.rotateAnimation(false, rl_level3);
                isLevelDisplay3 = true;
            }
        }
    }
}
