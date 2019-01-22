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
    private static final int STROKE = 1;
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

        int height = getHeight();
        int width = getWidth();
        int radius = 0;


        if (height > width){
            radius = width -10;
        }else {
            radius = height - 10;
        }

        canvas.rotate(rotation);

        int knob = (int) (radius * .90);
        int smallCircle = (int) (radius * .10);
        canvas.drawCircle(width, height, radius, paintOuter);
        canvas.drawCircle(width, height, smallCircle, paintInner);
        canvas.drawCircle(width, height, knob, paintKnob);
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
            paintKnob.setColor(typedArray.getColor(R.styleable.VolumeControlView_inner_circle, Color.WHITE));
            paintOuter.setColor(typedArray.getColor(R.styleable.VolumeControlView_inner_circle, Color.RED));
            typedArray.recycle();
        }
    }
}
