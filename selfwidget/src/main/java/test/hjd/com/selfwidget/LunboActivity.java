package test.hjd.com.selfwidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjundong on 2017/3/11.
 */

public class LunboActivity extends Activity implements ViewPager.OnPageChangeListener{


    private List<ImageView> imageViews;

    public static int MAX_VALUE = 10000000;


    public TextView tv_content;
    public String[] contents = new String[]{
            "hello1",
            "hello2",
            "hello3",
            "hello4",
            "hello5"
    };

    int lastPosition;
    private LinearLayout ll_container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunbo_main);

        imageViews = new ArrayList<>();
        int[] imageResIds = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };
        for (int i = 0; i < imageResIds.length; ++i) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);

            imageViews.add(imageView);
        }

        final ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyPageAdapter());

        /*
        第4张.

        向左无限循环是不是第一张的原因是:
        curItem % 5 != 0
         */
        vp.setCurrentItem((MAX_VALUE / 2 - MAX_VALUE / 2 % imageViews.size()));


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vp.setCurrentItem(vp.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();


        vp.setOnPageChangeListener(this);

        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(contents[0]);

        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        for(int i = 0 ; i < imageViews.size() ; ++i) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.selector_bg_point);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);

            if (i != 0) {
                params.leftMargin = 10;
            }

            //默认只选中第一个
            if (i != 0) {
                point.setEnabled(false);
            }
            ll_container.addView(point,params);
        }
    }


    class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            int currentIndex = position % imageViews.size();
            ImageView imageView = imageViews.get(currentIndex);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int currentIndex = position % contents.length;
        tv_content.setText(contents[currentIndex]);

        ll_container.getChildAt(lastPosition).setEnabled(false);
        ll_container.getChildAt(currentIndex).setEnabled(true);
        lastPosition = currentIndex;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
