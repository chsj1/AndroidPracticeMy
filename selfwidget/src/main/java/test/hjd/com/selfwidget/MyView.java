package test.hjd.com.selfwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangjundong on 2017/3/18.
 */

/*
内部执行顺序:
onMeasure()--->onLayout()---->onDraw()

外部执行顺序:
在onResume()执行执行

构造函数:
1)当调用代码的方式添加组件的时候,调用只有一个参数的构造函数
2)当通过配置文件的方式添加组件的时候，调用只有2个参数的构造函数.(没有指定自定义属性)
 */
public class MyView extends View {
    /*
    通过代码的方式添加组件,执行有1个参数的方法
     */
    public MyView(Context context) {
        super(context);
        System.out.println("------->执行了1");
    }

    /*
    通过配置文件添加组件,执行有两个参数的方法
     */
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("------->执行了2");
    }


    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        System.out.println("------->执行了3");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        System.out.println("----->onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("----->onLayout");
    }


    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("------>onDraw");
    }
}
