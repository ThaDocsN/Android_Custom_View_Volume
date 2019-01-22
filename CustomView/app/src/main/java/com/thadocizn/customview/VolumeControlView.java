package com.thadocizn.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class VolumeControlView extends View {
    private static final int STROKE = 5;
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int y = getHeight() /2;
        int x = getWidth() /2;
        int radius = 100;


        if (y > x){
            radius = x -10;
        }else {
            radius = y - 10;
        }

        canvas.rotate(rotation);

        int knob = (int) (radius * .90f);
        int smallCircle = (int) (radius * .10f);
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
        paintInner = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintKnob  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintOuter = new Paint(Paint.ANTI_ALIAS_FLAG);

        paintOuter.setStyle(Paint.Style.FILL);
        paintKnob.setStyle(Paint.Style.FILL);

        paintInner.setStyle(Paint.Style.STROKE);
        paintInner.setStrokeWidth(STROKE);

        if (attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.VolumeControlView);
            paintInner.setColor(typedArray.getColor(R.styleable.VolumeControlView_inner_circle, Color.BLUE));
            paintKnob.setColor(typedArray.getColor(R.styleable.VolumeControlView_knob_circle, Color.WHITE));
            paintOuter.setColor(typedArray.getColor(R.styleable.VolumeControlView_outer_circle, Color.RED));
            typedArray.recycle();
        }
    }
}
