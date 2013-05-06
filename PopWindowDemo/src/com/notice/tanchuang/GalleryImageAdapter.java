package com.notice.tanchuang;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class GalleryImageAdapter extends BaseAdapter{
	
	private Context context;
	
	private Integer[] imgIds = {
			R.drawable.details_menu_delete,R.drawable.details_menu_download,R.drawable.details_menu_rename,
			R.drawable.details_menu_email,	R.drawable.details_menu_delete,R.drawable.details_menu_download,R.drawable.details_menu_rename,
			R.drawable.details_menu_email
	};

	public GalleryImageAdapter(Context c) {
		super();
		context = c;
		
	}
	
	public int getCount() {
		return imgIds.length;
	}

	public Object getItem(int position) {
		return imgIds[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView iv = new ImageView(context);
		iv.setImageResource(imgIds[position]);
		iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		iv.setScaleType(ImageView.ScaleType.FIT_XY);	
		return iv;
	}
	
	
}
