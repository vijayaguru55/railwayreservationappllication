package com.railwayticketreservation.train;

import com.railwayticketreservation.dto.User;

public abstract class TrainViewCallback {

	protected abstract void trainAddSuccess(String string, User user);

}
