package com.qskj.tyt.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.qskj.tyt.R;


/**
 * 一键清除的 AppCompatAutoCompleteTextView,可当EditText使用，
 * 1.带删除图标，一键清除
 * 2.可输入
 * 3.失去焦点的时候 隐藏软键盘
 * 4.可设置自动提醒历史搜索记录(代码中设置一个历史纪录的数据适配器就好)
 * Create on 2015/10/10 By 赵鑫
 */
public class AppCompatAutoCompleteClearTextView extends AppCompatAutoCompleteTextView implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {

    private Drawable mClearTextIcon;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnTouchListener mOnTouchListener;
    private Context mContext;

    public AppCompatAutoCompleteClearTextView(final Context context) {
        super(context);
        mContext = context;
        init(mContext);
    }

    public AppCompatAutoCompleteClearTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(mContext);
    }

    public AppCompatAutoCompleteClearTextView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(mContext);
    }

    private void init(final Context context) {
        final Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.ic_delete);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable); //Wrap the drawable so that it can be tinted pre Lollipop
        DrawableCompat.setTint(wrappedDrawable, getCurrentHintTextColor());
        mClearTextIcon = wrappedDrawable;
        mClearTextIcon.setBounds(0, 0, mClearTextIcon.getIntrinsicHeight(), mClearTextIcon.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
            //  失去焦点，隐藏软键盘
            //1.得到InputMethodManager对象
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            //2.调用hideSoftInputFromWindow方法隐藏软键盘
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (mClearTextIcon.isVisible() && x > getWidth() - getPaddingRight() - mClearTextIcon.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText("");
            }
            return true;
        }
        return mOnTouchListener != null && mOnTouchListener.onTouch(view, motionEvent);
    }

    @Override
    public final void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (isFocused()) {
            setClearIconVisible(text.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setClearIconVisible(final boolean visible) {
        mClearTextIcon.setVisible(visible, false);
        final Drawable[] compoundDrawables = getCompoundDrawables();
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                visible ? mClearTextIcon : null,
                compoundDrawables[3]);
    }
    
}
