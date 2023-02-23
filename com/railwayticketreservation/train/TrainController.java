package com.railwayticketreservation.train;

import java.time.LocalDate;

import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.train.TrainModel.TrainModelControllerCallback;

public class TrainController extends TrainControllerCallback implements TrainModelControllerCallback {
	private TrainViewCallback viewCallback;
	private TrainModelCallback modelCallback;

	public TrainController(TrainView trainView) {
		this.viewCallback = trainView;
		this.modelCallback = new TrainModel(this);
	}

	@Override
	protected void createTrain(String name, int number, String from, String to, int sitPrice, int berthPrice,
			LocalDate date, User user, String time) {
		modelCallback.createTrain(name, number, from, to, sitPrice, berthPrice, date, user, time);
	}

	@Override
	public void addSuccess(String string, User user) {
		viewCallback.trainAddSuccess(string, user);
	}
}
