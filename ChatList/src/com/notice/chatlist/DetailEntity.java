package com.notice.chatlist;

public class DetailEntity {
	private int layoutID;
	private CharSequence text;
	private String time;
	public DetailEntity(CharSequence _text,int _layoutID,String time){
		this.text = _text;
		this.layoutID = _layoutID;
		this.time = time;
	}
	public int getLayoutID() {
		return layoutID;
	}
	public void setLayoutID(int layoutID) {
		this.layoutID = layoutID;
	}
	public CharSequence getText() {
		return text;
	}
	public void setText(CharSequence text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


}
