package com.railwayticketreservation.train;

import java.time.LocalDate;

import com.railwayticketreservation.dto.User;

public abstract class TrainControllerCallback {

	protected abstract void createTrain(String name, int number, String from, String to, int sitPrice, int berthPrice,
			LocalDate date, User user, String time);

}
