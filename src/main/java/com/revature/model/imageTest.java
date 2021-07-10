package com.revature.model;

import java.util.Arrays;

public class imageTest {
	
	private long id;
	private long user_id;
	private byte[] img;
	
	
	
	@Override
	public String toString() {
		return "imageTest [id=" + id + ", user_id=" + user_id + ", img=" + Arrays.toString(img) + "]";
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getUser_id() {
		return user_id;
	}



	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}



	public byte[] getImg() {
		return img;
	}



	public void setImg(byte[] img) {
		this.img = img;
	}



	public imageTest(long id, long user_id, byte[] img) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.img = img;
	}
	
}
