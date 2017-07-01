package com.doublewillow.okidea.myview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.doublewillow.okidea.R;

/**
 * @author zhouyou
 * @version 1.0
 * @desc
 * @date 2017/6/30 17:49
 */

public class LoadBtn extends View implements Animator.AnimatorListener {

    private static final String TAG = "LoadButton";

    private final int mStrokeColor;
    private final int mTextColor;
    private final float mProgressWidth;
    private OnClickListener mListenner;
    private Paint mPaint;

    private int mDefaultWidth;
    private int mDefaultRadiu;

    private int rectWidth;

    private TextPaint mTextPaint;

    private int mDefaultTextSize;

    private int mTopBottomPadding;
    private int mLeftRightPadding;

    private String mText;

    private int mTextWidth;
    private int mTextSize;
    private int mRadiu;


    private Path mPath;

    private RectF leftRect;
    private RectF rightRect;
    private RectF contentRect;
    private RectF progressRect;

    private int left;
    private int right;
    private int top;
    private int bottom;

    private boolean isUnfold;

    private int mBackgroundColor;

    private State mCurrentState;
    private float circleSweep;
    private ObjectAnimator loadAnimator;
    private ObjectAnimator shrinkAnim;

    private Drawable mSuccessedDrawable;
    private Drawable mErrorDrawable;
    private Drawable mPauseDrawable;

    private boolean progressReverse;
    private int mProgressSecondColor;
    private int mProgressColor;
    private int mProgressStartAngel;

    enum State {
        INITIAL,
        FODDING,
        LOADDING,
        COMPLETED_ERROR,
        COMPLETED_SUCCESSED,
        LOADDING_PAUSE
    }

    public LoadBtn(Context context) {
        this(context, null);
    }

    public LoadBtn(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDefaultRadiu = 40;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadButton);
        mDefaultTextSize = 24;
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.LoadButton_android_textSize,
                mDefaultTextSize);
        mStrokeColor = typedArray.getColor(R.styleable.LoadButton_stroke_color, Color.RED);
        mTextColor = typedArray.getColor(R.styleable.LoadButton_content_color, Color.WHITE);
        mText = typedArray.getString(R.styleable.LoadButton_android_text);
        mRadiu = typedArray.getDimensionPixelOffset(R.styleable.LoadButton_radiu, mDefaultRadiu);
        mTopBottomPadding = typedArray.getDimensionPixelOffset(R.styleable.LoadButton_contentPaddingTB, 10);
        mLeftRightPadding = typedArray.getDimensionPixelOffset(R.styleable.LoadButton_contentPaddingLR, 10);
        mBackgroundColor = typedArray.getColor(R.styleable.LoadButton_backColor, Color.WHITE);
        mProgressColor = typedArray.getColor(R.styleable.LoadButton_progressColor, Color.WHITE);
        mProgressSecondColor = typedArray.getColor(R.styleable.LoadButton_progressSecondColor, Color.parseColor("#c3c3c3"));
        mProgressWidth = typedArray.getDimensionPixelOffset(R.styleable.LoadButton_progressedWidth, 2);

        mSuccessedDrawable = typedArray.getDrawable(R.styleable.LoadButton_loadSuccessDrawable);
        mErrorDrawable = typedArray.getDrawable(R.styleable.LoadButton_loadErrorDrawable);
        mPauseDrawable = typedArray.getDrawable(R.styleable.LoadButton_loadPauseDrawable);

        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mStrokeColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);

        mDefaultWidth = 200;

        mTextPaint = new TextPaint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //用于保存最终尺寸
        int resultW = widthSize;
        int resultH = heightSize;

        // contentW contentH用于确定中间矩形的尺寸

        int contentW = 0;
        int contentH = 0;

        if (widthSize == MeasureSpec.AT_MOST) {
            mTextWidth = (int) mTextPaint.measureText(mText);
            contentW += mTextWidth + mLeftRightPadding * 2 + mRadiu * 2;
            resultW = contentW < widthSize ? contentW : widthSize;
        }

        if (heightSize == MeasureSpec.AT_MOST) {
            contentH += mTopBottomPadding * 2 + mTextSize;
            resultH = contentH < heightSize ? contentH : heightSize;
        }

        contentW = contentW < 2 * mRadiu ? 2 * mRadiu : contentW;
        contentH = contentH < 2 * mRadiu ? 2 * mRadiu : contentH;

        mRadiu = resultH / 2;

        rectWidth = resultW - 2 * mRadiu;

        setMeasuredDimension(resultW, resultH);

        Log.d(TAG,"onMeasure: w:"+resultW+" h:"+resultH);

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
