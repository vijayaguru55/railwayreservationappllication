package com.railwayticketreservation.login;

import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.repository.RailwayManagementDatabase;

public class LoginModel extends LoginModelCallback {
	loginModelControllerCallback controllerCallback;

	public LoginModel(loginModelControllerCallback controller) {
		this.controllerCallback = controller;
	}

	interface loginModelControllerCallback {

		void loginSuccess(User user);

		void loginFailed(String string);

	}

	@Override
	protected void checkCredentials(String name, String password) {
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		// initiallyAdding for admin;
		if (name.equals("adminPanel@123")) {
			User admin = new User("adminPanel@123", "admin112", (byte) 20, (long) 90988786, "Male");
			database.addUser(admin, password);
		}

		User user = database.checkCredials(name, password);

		if (user != null) {
			controllerCallback.loginSuccess(user);
		} else {
			controllerCallback.loginFailed("Invalid credentials");
		}
	}

}
