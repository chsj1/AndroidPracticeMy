package test.hjd.com.selfwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by huangjundong on 2017/3/18.
 */

public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private View header;
    private int measureHeight;
    private float downY;
    private TextView tv_header;


    int currentState = 0;
    public static final int PULL_TO_REFRESH = 0;
    public static final int RELEASE_TO_REFRESH = 1;
    public static final int REFRESHING = 2;
    private RotateAnimation downAnimation;
    private RotateAnimation upAnimation;
    private ImageView iv_arrow;
    private ProgressBar pb;

    public OnRefreshListener onRefreshListener;
    private View footer;
    private int footerMeaureHeight;


    public RefreshListView(Context context) {
        super(context);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void initHeader(Context context) {
        header = View.inflate(context, R.layout.refresh_header, null);
        header.measure(0, 0);
        measureHeight = header.getMeasuredHeight();
        header.setPadding(0, -measureHeight, 0, 0);

        tv_header = (TextView) header.findViewById(R.id.tv_header);
        addHeaderView(header);

        iv_arrow = (ImageView) header.findViewById(R.id.iv_arrow);

        pb = (ProgressBar) header.findViewById(R.id.pb);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();

                float deltaY = moveY - downY;

                int paddingTop = (int) (deltaY + (-measureHeight));

                header.setPadding(0, paddingTop, 0, 0);


                if (paddingTop < 0 && currentState != PULL_TO_REFRESH) {
                    currentState = PULL_TO_REFRESH;
                    System.out.println("---->下拉刷新");
                    tv_header.setText("下拉刷新");

                    iv_arrow.startAnimation(downAnimation);

                } else if (paddingTop >= 0 && currentState != RELEASE_TO_REFRESH) {
                    currentState = RELEASE_TO_REFRESH;
                    System.out.println("---->释放刷新");
                    tv_header.setText("释放刷新");

                    iv_arrow.startAnimation(upAnimation);
                }
                pb.setVisibility(INVISIBLE);

                break;
            case MotionEvent.ACTION_UP:

                if (currentState == PULL_TO_REFRESH) {
                    header.setPadding(0, -measureHeight, 0, 0);

                    pb.setVisibility(INVISIBLE);
                    iv_arrow.setVisibility(VISIBLE);
                } else if (currentState == RELEASE_TO_REFRESH) {
                    header.setPadding(0, 0, 0, 0);

                    currentState = REFRESHING;
                    tv_header.setText("正在刷新");


                    pb.setVisibility(VISIBLE);
                    iv_arrow.clearAnimation();
                    iv_arrow.setVisibility(INVISIBLE);

                    if (onRefreshListener != null) {
                        onRefreshListener.headerRefresh();
                    }
                }
                break;
        }

        return super.onTouchEvent(ev);
    }


    public void init() {
        initHeader(getContext());
        initAnimation();

        initFooter();

        setOnScrollListener(this);
    }

    public void initAnimation() {
        downAnimation = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);


        upAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);
    }


    public interface OnRefreshListener {
        void headerRefresh();

        void footerRefresh();
    }


    public void setOnRereshListener(OnRefreshListener onRereshListener) {
        this.onRefreshListener = onRereshListener;
    }

    public void onHeaderRefreshFinish() {
        header.setPadding(0, -measureHeight, 0, 0);
    }

    public void onFooterRefreshFinish() {
        footer.setPadding(0, -footerMeaureHeight, 0, 0);

    }

    public void initFooter() {
        footer = View.inflate(getContext(), R.layout.refresh_footer, null);
        footer.measure(0, 0);
        footerMeaureHeight = footer.getMeasuredHeight();
        footer.setPadding(0, -footerMeaureHeight, 0, 0);


        addFooterView(footer);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && getLastVisiblePosition() >= (getCount() - 1)) {
            footer.setPadding(0, 0, 0, 0);

            setSelection(getCount());

            if (onRefreshListener != null) {
                onRefreshListener.footerRefresh();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
