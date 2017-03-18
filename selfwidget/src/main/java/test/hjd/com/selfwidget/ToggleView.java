package test.hjd.com.selfwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huangjundong on 2017/3/17.
 */

public class ToggleView extends View {

    private Bitmap switchBackgroundBitmap;
    private Paint paint;
    private Bitmap slideBtnBitmap;
    private float downX;

    public int touchMode = 0;
    public static int IS_TOUCHING = 1;
    public static int RELEASE = 0;

    public int switchMode = 0;
    public static int CLOSE_MODE = 0;
    public static int OPEN_MODE = 1;

    public OnSwitchStateChangeListener onSwitchStateChangeListener;

    public ToggleView(Context context) {
        super(context);

        init();
    }

    private void init() {
        paint = new Paint();
    }

    public ToggleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();

        String namespace = "http://schemas.android.com/apk/lib/test.hjd.com.selfwidget";

        int resId = attrs.getAttributeResourceValue(namespace, "switch_background", -1);
        switchBackgroundBitmap = BitmapFactory.decodeResource(getResources(), resId);


        int slideButonResId = attrs.getAttributeResourceValue(namespace, "slide_button", -1);
        slideBtnBitmap = BitmapFactory.decodeResource(getResources(), slideButonResId);

    }

    public ToggleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(switchBackgroundBitmap.getWidth(), heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawBitmap(switchBackgroundBitmap, 0, 0, paint);


        float currentX = downX - slideBtnBitmap.getWidth() / 2;
        if (currentX < 0) {
            currentX = 0;
        }

        float maxLeft = switchBackgroundBitmap.getWidth() - slideBtnBitmap.getWidth();
        if (currentX >= maxLeft) {
            currentX = maxLeft;
        }

        /*
        主要涉及到两个状态:触摸状态、开关状态
         */
        if (touchMode == RELEASE) {
            if (switchMode == CLOSE_MODE) {
                canvas.drawBitmap(slideBtnBitmap, 0, 0, paint);
            } else if (switchMode == OPEN_MODE) {
                canvas.drawBitmap(slideBtnBitmap, maxLeft, 0, paint);
            }
        } else if (touchMode == IS_TOUCHING) {
            canvas.drawBitmap(slideBtnBitmap, currentX, 0, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchMode = IS_TOUCHING;
                break;
            case MotionEvent.ACTION_MOVE:
                downX = event.getX();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchMode = RELEASE;

                if (downX < switchBackgroundBitmap.getWidth() / 2) {
                    if (switchMode != CLOSE_MODE) {
                        switchMode = CLOSE_MODE;

                        onSwitchStateChangeListener.switchStateChange(switchMode);
                    }
                } else {
                    if (switchMode != OPEN_MODE) {
                        switchMode = OPEN_MODE;
                        onSwitchStateChangeListener.switchStateChange(switchMode);
                    }
                }
                //重新绘制界面
                invalidate();
                break;
        }
        return true;
    }


    public interface OnSwitchStateChangeListener {
        public void switchStateChange(int state);
    }

    public void setOnSwitchStateChangeListener(OnSwitchStateChangeListener onSwitchStateChangeListener) {
        this.onSwitchStateChangeListener = onSwitchStateChangeListener;
    }

}
