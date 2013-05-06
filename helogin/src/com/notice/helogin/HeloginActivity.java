package com.notice.helogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HeloginActivity extends Activity {
	
	private ImageBt  ib1;
	private ImageBt  ib2;
	
//	private EditView ev_usr;
//	private EditView ev_pwd;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        ib1 = (ImageBt) findViewById(R.id.bt_confirm);
        ib2 = (ImageBt) findViewById(R.id.bt_cancel);
        
        ib1.setTextViewText("确定");
        ib1.setImageResource(R.drawable.confirm);
        ib2.setTextViewText("取消");
        ib2.setImageResource(R.drawable.cancel);
        
//        ib1.setOnIbClickListener(new onIbClickListener() {
//			
//			@Override
//			public void onclickListenr() {
//				// TODO Auto-generated method stub
//				Toast.makeText(HeloginActivity.this, "xxxxxxxxxxxxx", Toast.LENGTH_LONG).show();
//			}
//		});
        
        ib1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(HeloginActivity.this, "xxxxxxxxxxxxx", Toast.LENGTH_LONG).show();
				
			}
		});
//        ev_usr = (EditView) findViewById(R.id.username);
//        ev_usr.isNameEdt();
//        ev_pwd = (EditView) findViewById(R.id.password);
//        ev_pwd.isPassWord();
    }
}