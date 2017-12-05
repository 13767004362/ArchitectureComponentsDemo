package com.xingen.architecturecomponents.net.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by ${新根} on 2017/12/4.
 * blog:http://blog.csdn.net/hexingen
 *
 * 圆角的图片
 */

public class CircularBitmapImageViewTarget extends BitmapImageViewTarget {
    private Context context;
    private ImageView imageView;
    public CircularBitmapImageViewTarget(Context context,ImageView view) {
        super(view);
        this.context=context;
        this.imageView=view;
    }
    /**
     * 重写 setResource（），生成圆角的图片
     * @param resource
     */
    @Override
    protected void setResource(Bitmap resource) {
        RoundedBitmapDrawable bitmapDrawable= RoundedBitmapDrawableFactory.create(this.context.getResources(),resource);
        bitmapDrawable.setCircular(true);
        this.imageView.setImageDrawable(bitmapDrawable);
    }
}
