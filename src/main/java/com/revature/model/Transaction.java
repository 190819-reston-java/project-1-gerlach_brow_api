package com.revature.model;

import java.io.File;

public class Transaction {

	private long id;
	private long userId;
	private String status;
	private String managerName;
	private String transDate;
	private String imgUrl;
	private String comment;
	
	

	public Transaction(long id, long userId, String status, String managerName, String transDate, String imgUrl,
			String comment) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
		this.managerName = managerName;
		this.transDate = transDate;
		this.imgUrl = imgUrl;
		this.comment = comment;
	}

	public Transaction() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", userId=" + userId + ", status=" + status + ", managerName=" + managerName
				+ ", transDate=" + transDate + ", imgUrl=" + imgUrl + ", comment=" + comment + "]";
	}

}
