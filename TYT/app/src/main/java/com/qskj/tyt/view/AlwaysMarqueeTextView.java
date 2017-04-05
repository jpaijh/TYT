package com.qskj.tyt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯 滚动的TextView
 * CB_CommodityManageActivity: 这里面用到了
 * Created by 赵 鑫 on 2015/11/12.
 */
public class AlwaysMarqueeTextView extends TextView {
    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
