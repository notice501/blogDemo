package com.notice.chatlist;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

public class DetailAdapter implements ListAdapter{
	String TAG = this.getClass().getName();
	private ArrayList<DetailEntity> coll;
	private Context ctx;
	
	public DetailAdapter(Context context, ArrayList<DetailEntity> coll){
		ctx = context;
		this.coll = coll;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return coll.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return coll.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		DetailEntity entity = (DetailEntity)coll.get(position);
		int itemLayout = entity.getLayoutID();
		LinearLayout layout = new LinearLayout(ctx);
		LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		vi.inflate(itemLayout,layout, true);
		
		TextView tvName = (TextView) layout.findViewById(R.id.message_detail_item_attachment_des);
		tvName.setText(entity.getText());
		TextView tvTime = (TextView) layout.findViewById(R.id.sms_time);
		tvTime.setText(entity.getTime());
		return layout;
	}

	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return coll.size();
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}

}
