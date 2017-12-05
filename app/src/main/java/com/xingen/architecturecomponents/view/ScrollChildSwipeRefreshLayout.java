package com.xingen.architecturecomponents.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${新根} on 2017/12/4.
 * blog:http://blog.csdn.net/hexingen
 */

public class ScrollChildSwipeRefreshLayout extends SwipeRefreshLayout {
    private View scrollUpChild;
    public ScrollChildSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置在哪个view中触发刷新。
     * @param view
     */
    public void setScrollUpChild(View view){
        this.scrollUpChild=view;
    }

    /**
     *ViewCompat..canScrollVertically（）：用于检查view是否可以在某个方向上垂直滑动
     * @return
     */
    @Override
    public boolean canChildScrollUp() {
        if(scrollUpChild!=null){
            return ViewCompat.canScrollVertically(scrollUpChild,-1);
        }
        return super.canChildScrollUp();
    }

    /**
     * 设置
     * @param indicator
     */
    public void showIndicator(boolean indicator){
        this.post(() -> this.setRefreshing(indicator));
    }

    /**
     * 设置默认颜色
     */
    public void setDefalutColor(){
        this.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
    }
}
