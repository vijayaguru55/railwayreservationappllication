package com.railwayticketreservation.user;

import java.util.ArrayList;

import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.repository.RailwayManagementDatabase;

public class UserModel extends UserModelCallback {
	private UserModelControllercallback controllercallback;

	public UserModel(UserController userController) {
		this.controllercallback = userController;
	}

	interface UserModelControllercallback {

		void userAddedSuccess(User user);

		void userAddingFailed(String string);

		void noTicket(User user, String string);

	}

	@Override
	protected void createUser(String name, String email, String gender, long mobileNo, byte age, String password) {
		User user = new User(name, email, age, mobileNo, gender);
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		if (database.isexistingUser(name, password)) {
			controllercallback.userAddingFailed("User already exist");
		}
		database.addUser(user, password);
		controllercallback.userAddedSuccess(user);

	}

	@Override
	protected ArrayList<Ticket> viewTickets(User user) {
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		ArrayList<Ticket> tickets = database.getTickets(user);
		if(tickets==null || tickets.size()==0)
			controllercallback.noTicket(user,"No tickets found..");
		return tickets;
	}
}
