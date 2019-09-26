package com.revature.model;

import java.io.File;

public class Transaction {

	private long id;
	private long userId;
	private String status;
	private String managerName;
	private String transDate;
	private File receiptImg;
	private String comment;

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

	public File getReceiptImg() {
		return receiptImg;
	}

	public void setReceiptImg(File receiptImg) {
		this.receiptImg = receiptImg;
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
				+ ", transDate=" + transDate + ", receiptImg=" + receiptImg + ", comment=" + comment + "]";
	}

}
