package com.data;

public class CleanedNews {

	private String title;
	private int photo;
	private String desc;
	private String content;
	private String date;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "title :" + this.title + "photo :" + this.photo
				+ "desc :" + this.desc + "content :" + this.content + "date :"
				+ this.date;
	}
}