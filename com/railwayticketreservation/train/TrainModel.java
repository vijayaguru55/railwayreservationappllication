package com.railwayticketreservation.train;

import java.time.LocalDate;

import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.repository.RailwayManagementDatabase;

public class TrainModel extends TrainModelCallback {
	private TrainModelControllerCallback controllerCallback;

	public TrainModel(TrainModelControllerCallback controllerCallback) {
		this.controllerCallback = controllerCallback;
	}

	interface TrainModelControllerCallback {

		void addSuccess(String string, User user);

	}

	@Override
	protected void createTrain(String name, int number, String from, String to, int sitPrice, int berthPrice,
			LocalDate date, User user, String time) {
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		Train train = new Train(name, number, from, to, date, berthPrice, sitPrice, time);
		database.addtrain(train);
		controllerCallback.addSuccess("Train added success", user);
	}

}
