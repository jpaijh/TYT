package com.qskj.tyt.view.CalenderView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

import com.qskj.tyt.R;

/**
 * 类说明 popwindow
 *
 * @param <T> 返回的对象类型
 * @author
 */
public abstract class BasePopupWindow extends PopupWindow {

    protected View mContentView;

    public BasePopupWindow() {
        super();
    }

    public BasePopupWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BasePopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePopupWindow(Context context) {
        super(context);
    }

    public BasePopupWindow(int width, int height) {
        super(width, height);
    }

    public BasePopupWindow(View contentView, int width, int height,
                           boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public BasePopupWindow(View contentView) {
        super(contentView);
    }

    public BasePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height, true);
        mContentView = contentView;
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.Popup_Animation_Alpha);
        setBackgroundDrawable(new ColorDrawable());
        setTouchable(true);
        initViews();
        initEvents();
        init();
    }

    public abstract void initViews();

    public abstract void initEvents();

    public abstract void init();

    public View findViewById(int id) {
        return mContentView.findViewById(id);
    }
}
