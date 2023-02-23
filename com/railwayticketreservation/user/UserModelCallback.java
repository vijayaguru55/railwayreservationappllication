package com.railwayticketreservation.user;

import java.util.ArrayList;

import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.User;

public abstract class UserModelCallback {

	protected abstract void createUser(String name, String email, String gender, long mobileNo, byte age,
			String password);

	protected abstract ArrayList<Ticket> viewTickets(User user);

}
