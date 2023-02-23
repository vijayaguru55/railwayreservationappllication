package com.railwayticketreservation.dto;

public class User {
	private String name;
	private byte age;
	private long mobileNumber;
	private int id = 1012;
	private String gender;
	private String email;
	private String userId;

	public User(String name, String email, byte age, long mobileNo, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.mobileNumber = mobileNo;
		this.userId = name.substring(2).toUpperCase() + id;
		id++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
