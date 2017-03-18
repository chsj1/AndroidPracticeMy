package test.hjd.com.selfwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by huangjundong on 2017/3/16.
 */

public class SlideMenu extends ViewGroup {

    private float downX;
    private int leftEdge;

    private Scroller scroller;
    private float downY;

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View leftMenu = getChildAt(0);
        leftMenu.measure(leftMenu.getLayoutParams().width, heightMeasureSpec);

        getChildAt(1).measure(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View leftMenu = getChildAt(0);
        leftEdge = -leftMenu.getMeasuredWidth();
        leftMenu.layout(leftEdge, 0, 0, b);


        getChildAt(1).layout(l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                downX = event.getX();

                System.out.println("------->downX: " + downX);
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                int deltaX = (int) (downX - moveX);
                int currentX = getScrollX() + deltaX;

                if (currentX < leftEdge) {
                    scrollTo(leftEdge,0);
                } else if (currentX >= 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy(deltaX, 0);
                }
                downX = moveX;

                System.out.println("------>moveX: " + moveX);
                break;
            case MotionEvent.ACTION_UP:
                int curX = getScrollX();

                float dx = 0;
                if (curX < leftEdge / 2) {
//                    scrollTo(leftEdge, 0);

                    dx = leftEdge - curX;
                } else {
//                    scrollTo(0, 0);
                    dx = 0 - curX;
                }

                scroller.startScroll(curX, 0, (int) dx, 0);
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            int curX = scroller.getCurrX();
            scrollTo(curX,0);
        }

        invalidate();
    }

    public void init() {
        scroller = new Scroller(getContext());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();

                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();

                float deltaX = Math.abs(moveX - downX);
                float deltaY = Math.abs(moveY - downY);

                if (deltaX > deltaY && deltaX > 10) {
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void switchState() {
        int curX = getScrollX();
        scroller.startScroll(curX, 0, 0 - curX, 0);
    }
}
