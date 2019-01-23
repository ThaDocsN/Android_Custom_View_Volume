package com.thadocizn.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class VolumeControlView extends View {
    private static final int STROKE = 5;
    protected int distance, start, end;
    protected Paint paintOuter, paintInner, paintKnob;
    protected int currentSetting, rotation;

    public VolumeControlView(Context context) {
        super(context);
        init(null);
    }

    public int getCurrentSetting() {
        return currentSetting;
    }

    public void setCurrentSetting(int currentSetting) {
        this.currentSetting = currentSetting;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            start = (int) event.getX();
            break;

            case MotionEvent.ACTION_MOVE:
                end = (int) event.getX();
                distance = end - start;
                rotation = distance;

                int maximumRotation = 100;
                int minimumRotation = 0;

                if (rotation > maximumRotation){
                    rotation = maximumRotation;
                }

                if (rotation < minimumRotation){
                    rotation = minimumRotation;
                }

                setCurrentSetting(rotation);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int y = getHeight() /2;
        int x = getWidth() /2;
        int radius = 0;


        if (y > x){
            radius = x -10;
        }else {
            radius = y - 10;
        }

        canvas.rotate(rotation);

        int knob = (int) (radius * .10f);
        int smallCircle = (int) (radius * .90f);

        canvas.drawCircle(x, y, radius, paintOuter);
        canvas.drawCircle(x, y, smallCircle, paintInner);
        canvas.drawCircle(x, y, knob, paintKnob);
    }

    public VolumeControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public VolumeControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public VolumeControlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    protected void init(AttributeSet attrs){

        paintOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuter.setStyle(Paint.Style.FILL);

        paintKnob  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintKnob.setStyle(Paint.Style.FILL);

        paintInner = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintInner.setStyle(Paint.Style.STROKE);
        paintInner.setStrokeWidth(STROKE);

        if (attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.VolumeControlView);
            paintOuter.setColor(typedArray.getColor(R.styleable.VolumeControlView_outer_circle, Color.RED));
            paintInner.setColor(typedArray.getColor(R.styleable.VolumeControlView_inner_circle, Color.YELLOW));
            paintKnob.setColor(typedArray.getColor(R.styleable.VolumeControlView_knob_circle, Color.WHITE));
            typedArray.recycle();
        }
    }
}
