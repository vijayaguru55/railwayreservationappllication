package com.railwayticketreservation.user;

import java.util.ArrayList;

import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.user.UserModel.UserModelControllercallback;

public class UserController extends UserControllerCallback implements UserModelControllercallback {
	private UserModelCallback modelCallback;
	private UserViewCallback viewCallback;

	public UserController(UserView viewCallback) {
		this.viewCallback = viewCallback;
		this.modelCallback = new UserModel(this);
	}

	@Override
	protected void createUser(String name, String email, String gender, long mobileNo, byte age, String password) {
		modelCallback.createUser(name, email, gender, mobileNo, age, password);
	}

	@Override
	public void userAddedSuccess(User user) {
		viewCallback.userAddedSuccess(user);
	}

	@Override
	public void userAddingFailed(String string) {
		viewCallback.userAddFailed(string);
	}

	@Override
	protected ArrayList<Ticket> viewTickets(User user) {
		return modelCallback.viewTickets(user);
	}

	@Override
	public void noTicket(User user, String message) {
		viewCallback.noTickets(user,message);
		
	}
}
