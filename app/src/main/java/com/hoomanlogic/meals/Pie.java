package com.hoomanlogic.meals;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class Pie extends View {
    private int mBgColor = Color.DKGRAY;
    private int mBorderColor = Color.WHITE;
    private float mBorderThickness = 20;
    private float mMax = 60;
    private float[] mData = { 10, 5, 30 };
    private int[] mColors = {
        Color.parseColor("#0074d9"),
        Color.rgb(24, 255, 95),
        Color.MAGENTA
    };

    // Painters
    private Paint mFillPaint = null;
    private Paint mStrokePaint = null;

    public Pie(Context context) {
        super(context);
        init(null, 0);
    }

    public Pie(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Pie(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Set clear background
        this.setBackgroundColor(Color.TRANSPARENT);

        // Load attributes
        final TypedArray a = this.getContext().obtainStyledAttributes(
            attrs,
            R.styleable.Pie,
            defStyle,
            0
        );
        mBgColor = a.getColor(R.styleable.Pie_bgColor, mBgColor);
        mBorderColor = a.getColor(R.styleable.Pie_borderColor, mBorderColor);
        mBorderThickness = a.getDimension(R.styleable.Pie_borderThickness, mBorderThickness);
        mMax = a.getFloat(R.styleable.Pie_max, mMax);

        // Dispose attributes collection
        a.recycle();

        // Set up Fill Painter
        mFillPaint = new Paint();
        mFillPaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mFillPaint.setStyle(Paint.Style.FILL);

        // Set up Stroke Painter
        mStrokePaint = new Paint();
        mStrokePaint.setFlags(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Sets the data used to set the pie segments
     */
    public void setData(float[] data) {
        this.mData = data;
        this.invalidate();
    }

    /**
     * Sets the colors used for the series of data
     */
    public void setColors(int[] colors) {
        this.mColors = colors;
        this.invalidate();
    }

    private float[] getSegments() {
        float total = this.getTotal();
        float[] segments = new float[this.mData.length];
        for (int i = 0; i < this.mData.length; i++) {
            segments[i] = (this.mData[i] / total) * 360;
        }
        return segments;
    }

    private float getTotal() {
        if (mMax > 0) {
            return mMax;
        }
        else {
            float total = 0;
            for (float val : this.mData) {
                total += val;
            }
            return total;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float borderStrokeHalf = mBorderThickness / 2;
        int x = getWidth();
        int y = getHeight();
        float xPad = 0;
        float yPad = 0;
        if (x > y) {
            xPad += (x - y) / 2;
        }
        else if (y > x) {
            yPad += (y - x) / 2;
        }
        int diameter = Math.min(x, y);
        int radius = diameter / 2;

        // Draw the background circle.
        mFillPaint.setColor(mBgColor);
        canvas.drawCircle(x / 2, y / 2, radius, mFillPaint);

        // Draw a pie section
        float[] segments = getSegments();
        float arcSpanned = -90;
        RectF arcBoundary = new RectF(xPad + borderStrokeHalf, yPad + borderStrokeHalf, diameter + xPad - borderStrokeHalf, diameter + yPad - borderStrokeHalf);
        for (int i = 0; i < segments.length; i++) {
            mFillPaint.setColor(mColors[i]);
            canvas.drawArc(arcBoundary, arcSpanned, segments[i], true, mFillPaint);
            arcSpanned += segments[i];
        }

        // Draw the pie border
        mStrokePaint.setColor(mBorderColor);
        mStrokePaint.setStrokeWidth(borderStrokeHalf * 2);
        canvas.drawCircle(x / 2, y / 2, radius - borderStrokeHalf, mStrokePaint);
    }
}
