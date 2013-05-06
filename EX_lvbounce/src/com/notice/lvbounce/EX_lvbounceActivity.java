package com.notice.lvbounce;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class EX_lvbounceActivity extends Activity {

	// 声明自定义的ListView
	private BounceListView blv;
	// 填充listview的数组
	private String[] date = { "data1", "data2", "data3", "data4", "data5",
			"data6", "data7", "data8", "data9", "data10" };
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		// 实例化BounceListView
        blv = (BounceListView) findViewById(R.id.blv);
		// 绑定Adapter
		blv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, date));
    }
}