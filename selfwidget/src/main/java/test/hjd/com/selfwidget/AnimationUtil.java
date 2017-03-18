package test.hjd.com.selfwidget;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by huangjundong on 2017/3/10.
 */

public class AnimationUtil {
    public static void rotateAnimation(boolean out, RelativeLayout layout) {
        rotateAnimation(out,layout,0);
    }

    public static void rotateAnimation(boolean out, RelativeLayout layout,long delay) {
        if (out) {
            int childCount = layout.getChildCount();
            for(int i = 0 ; i < childCount ; ++i) {
                layout.getChildAt(i).setEnabled(false);
            }


            RotateAnimation rotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(500);
            rotateAnimation.setStartOffset(delay);
            layout.startAnimation(rotateAnimation);
        } else {
            int childCount = layout.getChildCount();
            for(int i = 0 ; i < childCount ; ++i) {
                layout.getChildAt(i).setEnabled(true);
            }

            RotateAnimation rotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(500);
            layout.startAnimation(rotateAnimation);
        }
    }
}
