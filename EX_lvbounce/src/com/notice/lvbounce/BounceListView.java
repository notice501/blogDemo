package com.notice.lvbounce;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;

public class BounceListView extends ListView {
	private Context context;
	// 判断是否滑动出listview范围
	private boolean outBound = false;
	// 记录滑动长度
	private int distance;
	// 记录画出的y坐标值
	private int firstOut;

	/*----- 三个不同的构造器-------------*/
	public BounceListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public BounceListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public BounceListView(Context context) {
		super(context);
		this.context = context;
	}

	// 匿名内部类实现手势类的各个方法
	GestureDetector gestureDetector = new GestureDetector(
			new OnGestureListener() {
				// 用户触摸屏幕后松开
				@Override
				public boolean onSingleTapUp(MotionEvent e) {

					return false;
				}

				// 用户触摸屏幕后未松开，与onDown方法不同的是强调还未松开
				@Override
				public void onShowPress(MotionEvent e) {

				}

				// 用户触摸屏幕并滑动
				@Override
				public boolean onScroll(MotionEvent e1, MotionEvent e2,
						float distanceX, float distanceY) {
					// 第一个可见位置
					int firstPos = getFirstVisiblePosition();
					// 最后一个可见位置
					int lastPos = getLastVisiblePosition();
					// 获取AdapterView的条目数量
					int itemCount = getCount();
					if (outBound && firstPos != 0 && lastPos != (itemCount - 1)) {
						// 滑动至（0，0）
						scrollTo(0, 0);
						return false;
					}
					// 取得在第一个可见位置的View
					View firstView = getChildAt(firstPos);
					if (!outBound) {
						// 记录滑动的y坐标值
						firstOut = (int) e2.getRawY();
					}
					if (firstView != null) {
						// 记录滑动的长度
						distance = firstOut - (int) e2.getRawY();
						// 当前view滑动至滑动长度一半的位置
						scrollTo(0, distance / 2);
						return true;
					}
					return true;
				}

				// 用户长按屏幕
				@Override
				public void onLongPress(MotionEvent e) {

				}

				// 用户按下触摸屏并快速移动后松开,由1个MotionEvent ACTION_DOWN,
				// 多个ACTION_MOVE, 1个ACTION_UP触发
				@Override
				public boolean onFling(MotionEvent e1, MotionEvent e2,
						float velocityX, float velocityY) {

					return false;
				}

				// 用户触摸屏幕
				@Override
				public boolean onDown(MotionEvent e) {

					return false;
				}
			});

	// 这里最早响应触屏事件
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// 获取事件的action类型
		int act = event.getAction();
		// 判断用户已经弹起置outBound为false
		if ((act == MotionEvent.ACTION_UP || act == MotionEvent.ACTION_CANCEL)
				&& outBound) {
			outBound = false;

		}
		// 分析该event事件，返回true表示GestureDetector.OnGestureListener处理了该事件
		if (!gestureDetector.onTouchEvent(event)) {
			outBound = false;
		} else {
			outBound = true;
		}
		/* 绘制弹出的矩形，并设置一个动画效果 */
		Rect rect = new Rect();
		getLocalVisibleRect(rect);
		TranslateAnimation am = new TranslateAnimation(0, 0, -rect.top, 0);
		am.setDuration(300);
		startAnimation(am);
		scrollTo(0, 0);
		return super.dispatchTouchEvent(event);
	}
}
