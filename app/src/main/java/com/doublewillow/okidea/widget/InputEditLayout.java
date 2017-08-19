package com.doublewillow.okidea.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doublewillow.okidea.R;
import com.doublewillow.okidea.lib.Log4a;

/**
 * ================================================
 * 作    者：HuJin  email:zcm0858@163.com
 * 版    本：1.0
 * 创建日期：2017/6/21 10:31
 * 描    述：带清除按钮 和 密文显示按钮的Layout 继承自LinearLayout
 * 修订历史：
 * ================================================
 */

public class InputEditLayout extends LinearLayout {

    private Context mContext;
    private EditText mInputEdit;
    private ImageView mClearImage;
    private ImageView mVisibleImage;
    private Drawable mClearDrawable;
    private Drawable mOpenEyesDrawable;
    private Drawable mCloseEyesDrawable;
    private String mInputTemp = "";
    private int mEditCursorPosition;

    private boolean mHasEyesBtn = false;
    // 第一次执行布局
    private boolean isLayout = false;


    public InputEditLayout(Context context) {
        super(context);
        init(context);
    }

    public InputEditLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttrs(attrs);
    }

    public InputEditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(attrs);
    }

    private void init(Context context) {
        this.mContext = context;

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundResource(R.drawable.edit_input_bg);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!isLayout) {
            // 这里是第一次进来的时候做一些初始化
            View child = getChildAt(0);
            if (child != null && child instanceof EditText) {
                mInputEdit = (EditText) child;
            } else {
                throw new IllegalArgumentException("InputEditLayout 只允许拥有一个子View并且为EditText");
            }
            isLayout = true;
            initWidget();
            mInputEdit.measure(mInputEdit.getMeasuredWidth(), mInputEdit.getMeasuredHeight());
        }
    }

    private void initWidget() {
        //输入框
        LayoutParams inputLp = (LayoutParams) mInputEdit.getLayoutParams();
        inputLp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        inputLp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        inputLp.weight = 1;
        mInputEdit.setBackgroundColor(Color.TRANSPARENT);
        mInputEdit.setLayoutParams(inputLp);
        mInputEdit.addTextChangedListener(new SimpleEditTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int visibility = mClearImage.getVisibility();
                if (s.length() > 0 && visibility == View.INVISIBLE) {
                    mClearImage.setVisibility(View.VISIBLE);
                } else if (s.length() == 0 && visibility == View.VISIBLE) {
                    mClearImage.setVisibility(View.INVISIBLE);
                }
            }
        });

        //清除按钮
        LayoutParams imageLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mClearImage = new ImageView(mContext);
        mClearImage.setLayoutParams(imageLp);
        mClearDrawable = getResources().getDrawable(R.mipmap.clear_content3x);
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        mClearImage.setImageDrawable(mClearDrawable);
        mClearImage.setVisibility(INVISIBLE);  //默认不显示
        mClearImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mClearImage.setPadding(15, 0, 15, 0);
        mClearImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputEdit.setText("");
            }
        });
        addView(mClearImage);

        //显示按钮
        mVisibleImage = new ImageView(mContext);
        mVisibleImage.setLayoutParams(imageLp);
        mClearImage.setPadding(15, 0, 15, 0);
        mCloseEyesDrawable = getResources().getDrawable(R.mipmap.widget_eye_close3x,null);
        mOpenEyesDrawable = getResources().getDrawable(R.mipmap.widget_eye_open3x);

        int width = Math.min(mCloseEyesDrawable.getIntrinsicWidth(), mOpenEyesDrawable.getIntrinsicWidth());
        int height = Math.min(mCloseEyesDrawable.getIntrinsicHeight(), mOpenEyesDrawable.getIntrinsicHeight());

        mCloseEyesDrawable.setBounds(0, 0, width, height);
        mOpenEyesDrawable.setBounds(0, 0, width, height);
        mVisibleImage.setImageDrawable(mCloseEyesDrawable);  //密码默认密文
        mVisibleImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrHidePassword();
            }
        });
        mVisibleImage.setTag("show");
        addView(mVisibleImage);

        mVisibleImage.setVisibility(mHasEyesBtn ? VISIBLE : GONE);

    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs == null) {
            Log4a.e("hujin", "attrs is null");
            return;
        }


        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.InputEditLayout);
        if (typedArray != null) {

            mHasEyesBtn = typedArray.getBoolean(R.styleable.InputEditLayout_hasEyesBtn, false);
            typedArray.recycle();
        }
    }

    private void showOrHidePassword() {
        //记住光标开始的位置
        int pos = mInputEdit.getSelectionStart();
        //hide 密文显示  show 明文显示
        if ("hide".equals(mVisibleImage.getTag().toString())) {
            mVisibleImage.setImageDrawable(mCloseEyesDrawable);
            mInputEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            mVisibleImage.setTag("show");
        } else {
            mVisibleImage.setImageDrawable(mOpenEyesDrawable);
            mInputEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mVisibleImage.setTag("hide");
        }
        mInputEdit.setSelection(pos);
    }
}
