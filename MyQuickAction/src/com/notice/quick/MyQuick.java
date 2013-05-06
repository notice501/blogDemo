package com.notice.quick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MyQuick extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		final ActionItem chart = new ActionItem();
		
		chart.setTitle("Chart");
		chart.setIcon(getResources().getDrawable(R.drawable.chart));
		chart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyQuick.this, "Chart selected" , Toast.LENGTH_SHORT).show();
			}
		});
		
		
		final ActionItem production = new ActionItem();
		
		production.setTitle("Products");
		production.setIcon(getResources().getDrawable(R.drawable.production));
		production.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyQuick.this, "Products selected", Toast.LENGTH_SHORT).show();
			}
		});
		
		final ActionItem upload = new ActionItem();
		
		upload.setTitle("上传");
		upload.setIcon(getResources().getDrawable(R.drawable.details_menu_delete));
		upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MyQuick.this, "upload", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		Button btn1 = (Button) this.findViewById(R.id.btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				QuickAction qa = new QuickAction(v);
				
				qa.addActionItem(chart);
				qa.addActionItem(production);
				qa.addActionItem(upload);
				qa.addActionItem(upload);
				qa.setAnimStyle(QuickAction.ANIM_AUTO);
				
				qa.show();
			}
		});

		final ActionItem dashboard = new ActionItem();
		
		dashboard.setIcon(getResources().getDrawable(R.drawable.dashboard));
		dashboard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyQuick.this, "dashboard selected" , Toast.LENGTH_SHORT).show();
			}
		});
		
		
		final ActionItem users = new ActionItem();
		
		users.setIcon(getResources().getDrawable(R.drawable.users));
		users.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyQuick.this, "Products selected", Toast.LENGTH_SHORT).show();
			}
		});
		
		Button btn2 = (Button) this.findViewById(R.id.btn2);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				QuickAction qa = new QuickAction(v);
				
				qa.addActionItem(dashboard);
				qa.addActionItem(users);
				qa.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
				
				qa.show();
			}
		});
	}
}