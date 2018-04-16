package com.dcl.javacv.baidu.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.dcl.javacv.mvpproject.R;

/**
 * Created by Administrator on 2018/3/20.
 */

public class GiveLikeView extends View {

    private Paint mLovePaint;
    private Paint mFlyLovePaint;
    private Paint mLinePaint;

    private float rectHeight = 0;
    private float vertex = 100;
    private int maxVertex = 400;
    private int maxRectHeight = 160;
    private PathMeasure mPathMeasure;
    private float[] mCurrentPosition = new float[2];
    private Path path;

    public GiveLikeView(Context context) {
        super(context);
    }

    public GiveLikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLovePaint = new Paint();
        mLinePaint = new Paint();
        mFlyLovePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStrokeWidth(1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.designbump);
        path = new Path();
        //初始化飘起桃心的位置
        mCurrentPosition[0] = 250;
        mCurrentPosition[1] = 400;


//        path.moveTo(250 + bitmap.getWidth() / 2, 400);//为路径指定起点,如果不指定会默认从(0,0)开始
        path.moveTo(250, 400);//为路径指定起点,如果不指定会默认从(0,0)开始
//        path.lineTo(250 + bitmap.getWidth() / 2, 400 - rectHeight);//画一条直线
        //用来画贝塞尔曲线用的,该方法需要传入四个参数,其实就是需要传入拐点和终点的坐标
        path.quadTo(50, 180, 300, 0);
        mPathMeasure = new PathMeasure(path, false);
    }

    public void setMaxRectHeight(int height) {
        maxRectHeight = height;
    }

    public GiveLikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.designbump);
        canvas.drawBitmap(bitmap, 250, 400, mLovePaint);

//        int aplha = 255 - (int) (rectHeight / 150 * 255);
//        if (aplha < 0)
//            aplha = 0;
//        mFlyLovePaint.setAlpha(aplha);
        if (mCurrentPosition[1]<=49){
            // 重置路径
            path.reset();
            path.moveTo(mCurrentPosition[0], mCurrentPosition[1]);
            path.quadTo(600, 180, 300, 500);
            mPathMeasure.setPath(path,false);
        }
        canvas.drawBitmap(bitmap, mCurrentPosition[0], mCurrentPosition[1], mFlyLovePaint);
    }

    public void open() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, maxVertex);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(animation -> {
            vertex = (int) animation.getAnimatedValue();
            rectHeight = vertex / maxVertex * maxRectHeight;
            // 获取当前点坐标封装到mCurrentPosition
            mPathMeasure.getPosTan(vertex, mCurrentPosition, null);
            postInvalidate();
//            if (vertex == maxVertex) {
//                stop();
//            }

        });
        valueAnimator.start();
    }

    private void stop() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(maxVertex, 0);
        valueAnimator.setDuration(250);
        valueAnimator.addUpdateListener(animation -> {
            vertex = (int) animation.getAnimatedValue();
            invalidate();
        });
        valueAnimator.start();
    }

    public void close() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(maxRectHeight, 0);
        valueAnimator.setDuration(500);
// valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            rectHeight = (int) animation.getAnimatedValue();
            invalidate();
        });
        valueAnimator.start();
    }
}
