package com.revature.model;

public class User {


	private long id;
	private String firstName;
	private String lastName;
	private boolean isAdmin;
	private String email;
	private String password;
	private String address;
	private String address2;
	private String phoneNumber;
	private String position;

	public User(long id, String firstName, String lastName, boolean isAdmin, String email, String password,
			String address, String address2, String phoneNumber, String position) {
		
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.email = email;
		this.password = password;
		this.address = address;
		this.address2 = address2;
		this.phoneNumber = phoneNumber;
		this.position = position;

	}

	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", isAdmin=" + isAdmin
				+ ", email=" + email + ", password=" + password + ", address=" + address + ", address2=" + address2
				+ ", phoneNumber=" + phoneNumber + ", position=" + position + "]";
	}
}
