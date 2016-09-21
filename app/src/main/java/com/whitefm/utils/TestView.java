package com.whitefm.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yeqinfu on 9/21/16.
 */
public class TestView extends View {

    private Paint mPaint = new Paint();

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        drawBg(canvas);
        drawDiff(canvas);
        drawReplace(canvas);
        drawUnion(canvas);
        drawXor(canvas);
        drawReverse(canvas);
        drawIntersect(canvas);
    }

    //背景
    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 50, 50, mPaint);
    }

    //背景
    private void drawBg(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 10);
        drawScene(canvas);
        canvas.restore();
    }

    //相减
    private void drawDiff(Canvas canvas) {
        canvas.save();
        canvas.translate(160, 10);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

    }

    //取代
    private void drawReplace(Canvas canvas) {
        Path path = new Path();
        canvas.save();
        canvas.translate(10, 160);
        path.reset();
        canvas.clipPath(path); // makes the clip empty
        path.addCircle(50, 50, 50, Path.Direction.CCW);
        canvas.clipPath(path, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10, 160);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();
    }

    //并集
    private void drawUnion(Canvas canvas) {
        canvas.save();
        canvas.translate(160, 160);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
        drawScene(canvas);
        canvas.restore();
    }

    //存异去同XOR
    private void drawXor(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();
    }

    //Difference的相反运算，将后画的部分中去掉先前的部分
    private void drawReverse(Canvas canvas) {
        canvas.save();
        canvas.translate(160, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100,
                Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

//		canvas.save();
//		canvas.translate(160, 310);
//		canvas.clipRect(0, 0, 60, 60);
//		canvas.clipRect(40, 40, 100, 100,
//		Region.Op.INTERSECT);
//		drawScene(canvas);
//		canvas.restore();
    }

    //交集
    private void drawIntersect(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 460);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100,
                Region.Op.INTERSECT);
        drawScene(canvas);
        canvas.restore();
    }
}
