package com.railwayticketreservation.dto;

import java.time.LocalDate;

public class Ticket {
	private String ticketId;
	private String trainId;
	private String passengerName;
	private byte passengerAge;
	private String passengergender;
	private String berth;
	private String seatNo;
	private static int id = 101;
	private String fromStation;
	private String toStation;
	private LocalDate date;
	private String time;
	private int ticketprice;
	private boolean confirmed = false;
	private boolean isTatkal = false;

	public Ticket(String name, byte age, String gender, String from, String to, int price, LocalDate date, String time,
			String trainId, String berth) {
		this.passengerAge = age;
		this.passengergender = gender;
		this.passengerName = name;
		this.date = date;
		this.time = time;
		this.fromStation = from;
		this.toStation = to;
		this.ticketprice = price;
		this.trainId = trainId;
		this.seatNo = "A" + id;
		this.berth = berth;
		this.ticketId = name.substring(2).toUpperCase() + id + trainId.substring(trainId.length() - 5).toUpperCase();
		setId(id + 1);
	}

	public String getBerth() {
		return berth;
	}

	public void setBerth(String berth) {
		this.berth = berth;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public byte getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(byte passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengergender() {
		return passengergender;
	}

	public void setPassengergender(String passengergender) {
		this.passengergender = passengergender;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public int getTicketprice() {
		return ticketprice;
	}

	public void setTicketprice(int ticketprice) {
		this.ticketprice = ticketprice;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
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
