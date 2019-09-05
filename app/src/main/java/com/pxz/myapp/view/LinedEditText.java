package com.pxz.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * 类说明：带下划线的EditText
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/19 14:54
 */
public class LinedEditText extends AppCompatEditText {
    public LinedEditText(Context context) {
        super(context);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置画笔
        Paint mPaint = new Paint();
        //描边
        mPaint.setStyle(Paint.Style.STROKE);
        //画笔颜色 ：灰色
        mPaint.setColor(Color.LTGRAY);
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 5);
        //DashPathEffect效果，虚实线结合
        mPaint.setPathEffect(effects);
        //子View左边距离父view原点的距离
        int left = getLeft();
        //子View右边距离父view原点的距离
        int right = getRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        //view高度
        int height = getHeight();
        //返回一行的高度
        int lineHeight = getLineHeight();
        int spcingHeight = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            spcingHeight = (int) getLineSpacingExtra();
        }
        //横向条数
        int count = (height - paddingTop - paddingBottom) / lineHeight;
        for (int i = 0; i < count; i++) {
            //得到第一行线，距离view上面的高度
            int baseline = lineHeight * (i + 1) + paddingTop - spcingHeight / 2;
            canvas.drawLine(paddingLeft, (int) (baseline * 1.0), right - paddingRight * (int) 1.8, (int) (baseline * 1.0), mPaint);
            //画线
        }
        super.onDraw(canvas);
    }
}