package com.notice.tanchuang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MyPopWindow extends Activity {
    /** Called when the activity is first created. */
	
	private PopupWindow popup;
	private Button button;
	private LinearLayout mLayout;
	
	private Gallery gallery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initPopupMenu();
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (popup != null) {
					if (popup.isShowing()) {
						popup.dismiss();
					} else {
//						popup.showAtLocation(
//								findViewById(R.id.linear),
//								Gravity.CENTER, 0, 70);
						popup.showAsDropDown(button,20,20);
						
					}
				}
			}
        	
        });
        gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(MyPopWindow.this, "你点击了"+arg2, Toast.LENGTH_LONG).show();
			}
		});
    }
    private void initPopupMenu() {
    	mLayout = new LinearLayout(MyPopWindow.this);
    	mLayout.setOrientation(LinearLayout.VERTICAL);
    	gallery = new Gallery(this);
    	gallery.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    	
    	gallery.setSpacing(10);
    	gallery.setPadding(10, 10, 10, 10);
    	GalleryImageAdapter adapter = new GalleryImageAdapter(this);
    	gallery.setAdapter(adapter);
    	gallery.setSelection(3);
    	mLayout.addView(gallery);
    	// 创建Popup
		popup = new PopupWindow(mLayout,LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		popup.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.icon));// 设置menu菜单背景
//		popup.setFocusable(true);// menu菜单获得焦点 如果没有获得焦点menu菜单中的控件事件无法响应
		popup.update();
		// 设置默认项
    }

}