package com.notice.count;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;

public class CountViewActivity extends Activity {

    ImageView iv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        iv = (ImageView) findViewById(R.id.iv);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        Bitmap count = BitmapFactory.decodeResource(getResources(), R.drawable.tab_unread_bg);
        iv.setImageBitmap(generatorContactCountIcon(icon, count, 99));
    }

    private Bitmap generatorContactCountIcon(Bitmap icon, Bitmap count, int text) {
        // 初始化画布
        int iconSize = (int) getResources().getDimension(android.R.dimen.app_icon_size);
        Bitmap contactIcon = Bitmap.createBitmap(iconSize, iconSize, Config.ARGB_8888);
        Canvas canvas = new Canvas(contactIcon);

        // 拷贝图片
        Paint iconPaint = new Paint();
        iconPaint.setDither(true);// 防抖动
        iconPaint.setFilterBitmap(true);// 用来对Bitmap进行滤波处理，这样，当你选择Drawable时，会有抗锯齿的效果
        Rect src = new Rect(0, 0, icon.getWidth(), icon.getHeight());
        Rect dst = new Rect(0, 0, iconSize, iconSize);
        canvas.drawBitmap(icon, src, dst, iconPaint);

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(count, iconSize - 20, 0, p);

        Paint countPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        countPaint.setColor(Color.WHITE);
        countPaint.setTextSize(14f);
        countPaint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(String.valueOf(text), iconSize - 14, 15, countPaint);

        return contactIcon;
    }
}
