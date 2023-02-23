package com.railwayticketreservation.dto;

public class Passenger {
	private String name;
	private byte age;
	private String gender;
	private String berthOrSeat;
	private boolean confirmed = true;
	private boolean isTatkal = false;

	public Passenger(String name, String gender, byte age, String berth) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.berthOrSeat = berth;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBerthOrSeat() {
		return berthOrSeat;
	}

	public void setBerthOrSeat(String berthOrSeat) {
		this.berthOrSeat = berthOrSeat;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public boolean isTatkal() {
		return isTatkal;
	}

	public void setTatkal(boolean isTatkal) {
		this.isTatkal = isTatkal;
	}
}
