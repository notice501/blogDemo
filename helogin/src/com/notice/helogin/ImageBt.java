package com.notice.helogin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageBt extends LinearLayout{
	
	private ImageView iv;
	private TextView  tv;
	private onIbClickListener ic;
	
	public ImageBt(Context context){
		this(context, null);
	}

	public ImageBt(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.custombt, this, true);
		iv = (ImageView) findViewById(R.id.iv);
		tv = (TextView) findViewById(R.id.tv);
		
		
		
		
	}
	
	public void setImageResource(int resId) {  
        iv.setImageResource(resId);  
    }  
      
    public void setTextViewText(String text) {  
        tv.setText(text);  
    } 
    
    public void setOnIbClickListener(onIbClickListener click) {
        this.ic = click;
    }
    

}

	interface onIbClickListener {

    public void onclickListenr();
    
}
