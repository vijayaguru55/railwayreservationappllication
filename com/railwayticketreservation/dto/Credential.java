package com.railwayticketreservation.dto;

public class Credential {
	private String name;
	private String password;
	private String userId;

	public Credential(String name, String password, String userId) {
		this.name = name;
		this.password = password;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
