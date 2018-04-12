package com.kingja.superindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/12/4 9:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SuperIndicator extends View {
    private static final int DEFAULT_POINT_SIZE = 32;
    private static final int DEFAULT_LINE_SIZE = 4;
    private static final int DEFAULT_MARGIN = 6;
    private static final int NORMAL_COLOR = 0xffbebebe;
    private static final int ACTION_COLOR = 0xff0000bb;

    private static final int DEFAULT_TEXT_SIZE = 20;
    private static final String TAG = "SuperIndicator";
    private List<String> tabs = new ArrayList<>();
    private int width;
    private int height;
    private int perWith;
    private Paint pointPaint;
    private Paint textPaint;
    private float textHeight;
    private float offsetY;
    private float drawTextY;
    private int progress;
    private Paint linePaint;
    private float indicatorPointSize;
    private int indicatorNormalColor;
    private int indicatorActionColor;
    private float indicatorMargin;

    public SuperIndicator(Context context) {
        this(context, null);
    }

    public SuperIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public SuperIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SuperIndicator);
        indicatorPointSize = a.getDimension(R.styleable.SuperIndicator_indicatorPointSize, dp2Px
                (DEFAULT_POINT_SIZE));
        float indicatorTextSize = a.getDimension(R.styleable.SuperIndicator_indicatorTextSize, sp2Px
                (DEFAULT_TEXT_SIZE));
        float indicatorLineSize = a.getDimension(R.styleable.SuperIndicator_indicatorLineSize, dp2Px
                (DEFAULT_LINE_SIZE));
        indicatorMargin = a.getDimension(R.styleable.SuperIndicator_indicatorMargin, dp2Px
                (DEFAULT_MARGIN));
        indicatorNormalColor = a.getColor(R.styleable.SuperIndicator_indicatorNormalColor, NORMAL_COLOR);
        indicatorActionColor = a.getColor(R.styleable.SuperIndicator_indicatorActionColor, ACTION_COLOR);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(indicatorNormalColor);
        pointPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(indicatorNormalColor);
        linePaint.setStrokeWidth(indicatorLineSize);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(indicatorTextSize);
        textPaint.setColor(indicatorNormalColor);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textHeight = fontMetrics.bottom - fontMetrics.top;
        offsetY = 0.5f * textHeight - fontMetrics.bottom;
    }

    public void setProgress(int progress) {
        if (progress == -1) {
            return;
        }
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), (int) (indicatorMargin + indicatorPointSize + textHeight));
    }

    public int dp2Px(int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpi, getContext().getResources()
                .getDisplayMetrics());
    }

    public int sp2Px(int dpi) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dpi, getContext().getResources()
                .getDisplayMetrics());
    }

    public void setTabs(List<String> list) {
        this.tabs = list;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        drawTextY = indicatorMargin + indicatorPointSize + 0.5f * textHeight + offsetY;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tabs.size() > 0) {
            perWith = width / tabs.size();
        }
        for (int i = 0; i < tabs.size(); i++) {
            if (i <= progress) {
                //normal
                textPaint.setColor(indicatorActionColor);
                pointPaint.setColor(indicatorActionColor);
            } else {
                //action
                textPaint.setColor(indicatorNormalColor);
                pointPaint.setColor(indicatorNormalColor);
            }

            if (i < progress) {
                //normal
                linePaint.setColor(indicatorActionColor);
            } else {
                //action
                linePaint.setColor(indicatorNormalColor);
            }
            int drawX = (int) (perWith * i + 0.5f * perWith);
            float drawY = 0.5f * indicatorPointSize;

            /*绘制文字*/
            String tab = tabs.get(i);
            float tabWidth = textPaint.measureText(tab);
            float textOffsetX = tabWidth * 0.5f;
            float drawTextX = drawX - textOffsetX;
            canvas.drawText(tab, drawTextX, drawTextY, textPaint);

            if (i != tabs.size() - 1) {
                int nextDrawX = (int) (perWith * (i + 1) + 0.5f * perWith);
                canvas.drawLine(drawX, drawY, nextDrawX, drawY, linePaint);
            }
            canvas.drawCircle(drawX, drawY, 0.5f * indicatorPointSize, pointPaint);

        }
    }
}
