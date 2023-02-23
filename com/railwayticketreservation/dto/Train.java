package com.railwayticketreservation.dto;

import java.time.LocalDate;

public class Train {
	private String trainName;
	private int trainNo;
	private String trainId;
	private String startingStation;
	private String endingStation;
	private LocalDate date;
	private String time;
	private int siitings = 50;
	private int berths = 30;
	private int availableSittings = 50;
	private int availableUpperBerths = 10;
	private int availableLowerBerths = 10;
	private int availableMiddleBerths = 10;
	private int availableRACTicket = 10;
	private int availTatkal = 10;

	public int getAvailableRACTicket() {
		return availableRACTicket;
	}

	public void setAvailableRACTicket(int availableRACTicket) {
		this.availableRACTicket = availableRACTicket;
	}

	private int sittingPrice;
	private int berthPrice;

	public Train(String name, int no, String from, String to, LocalDate date, int berthPric, int sittingPrice,
			String time) {
		this.trainName = name + " Express";
		this.trainNo = no;
		this.berthPrice = berthPric;
		this.sittingPrice = sittingPrice;
		this.startingStation = from;
		this.endingStation = to;
		this.date = date;
		this.time = time;
		this.trainId = name.substring(2).toUpperCase() + no;
	}

	public int getSittingPrice() {
		return sittingPrice;
	}

	public void setSittingPrice(int sittingPrice) {
		this.sittingPrice = sittingPrice;
	}

	public int getBerthPrice() {
		return berthPrice;
	}

	public void setBerthPrice(int berthPrice) {
		this.berthPrice = berthPrice;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public int getAvailableUpperBerths() {
		return availableUpperBerths;
	}

	public void setAvailableUpperBirths(int availableUpperBerths) {
		this.availableUpperBerths = availableUpperBerths;
	}

	public int getAvailableLowerBerths() {
		return availableLowerBerths;
	}

	public void setAvailableLowerBirths(int availableLowerBerths) {
		this.availableLowerBerths = availableLowerBerths;
	}

	public int getAvailableMiddleBerths() {
		return availableMiddleBerths;
	}

	public void setAvailableMiddleBirths(int availableMiddleBerths) {
		this.availableMiddleBerths = availableMiddleBerths;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}

	public String getStartingStation() {
		return startingStation;
	}

	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}

	public String getEndingStation() {
		return endingStation;
	}

	public void setEndingStation(String endingStation) {
		this.endingStation = endingStation;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getSiitings() {
		return siitings;
	}

	public void setSiitings(int siitings) {
		this.siitings = siitings;
	}

	public int getBerths() {
		return berths;
	}

	public void setBerths(int berths) {
		this.berths = berths;
	}

	public int getAvailableSittings() {
		return availableSittings;
	}

	public void setAvailableSittings(int availableSittings) {
		this.availableSittings = availableSittings;
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

	public int getAvailTatkal() {
		return availTatkal;
	}

	public void setAvailTatkal(int availTatkal) {
		this.availTatkal = availTatkal;
	}

}
