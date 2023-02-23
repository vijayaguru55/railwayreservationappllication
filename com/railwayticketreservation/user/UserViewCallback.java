package com.railwayticketreservation.user;

import com.railwayticketreservation.dto.User;

public abstract class UserViewCallback {

	protected abstract void userAddedSuccess(User user);

	protected abstract void userAddFailed(String string);

	protected abstract void noTickets(User user, String message);

}
