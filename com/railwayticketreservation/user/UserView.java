package com.railwayticketreservation.user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.login.LoginView;
import com.railwayticketreservation.ticket.TicketView;

public class UserView extends UserViewCallback {
	Scanner scanner = new Scanner(System.in);
	private UserControllerCallback controllerCallback;

	public UserView() {
		this.controllerCallback = new UserController(this);

	}

	public void signUp() {
		String name = null;
		String email = null;
		long mobileNo = 0;
		byte age = 0;
		String password = null;
		String gender = null;
		try {
			System.out.println("Enter name:");
			name = scanner.next();
			System.out.println("Enetr emailId:");
			email = scanner.next();
			System.out.println("Choose your gender");

			gender = chooseGender();
			System.out.println("Enter mobile number:");
			mobileNo = scanner.nextLong();
			System.out.println("Enter age");
			age = scanner.nextByte();
			System.out.println("set password:");
			password = scanner.next();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
			signUp();
		}
		controllerCallback.createUser(name, email, gender, mobileNo, age, password);

	}

	private String chooseGender() {
		System.out.println("1.Male\n2.Female\n3.Transgender");
		String option = scanner.next();

		switch (option) {
		case "1":
			return "Male";
		case "2":
			return "Female";

		case "3":
			return "Transgender";
		default: {
			System.out.println("Invalid input");
			chooseGender();
		}
			break;
		}
		return null;
	}

	@Override
	protected void userAddedSuccess(User user) {
		System.out.println("User added success");
		TicketView ticketView = new TicketView();
		ticketView.menu(user);

	}

	@Override
	protected void userAddFailed(String string) {
		System.out.println(string);
		LoginView loginView = new LoginView();
		loginView.create();
	}

	public void viewTickets(User user) {
		ArrayList<Ticket> tickets = controllerCallback.viewTickets(user);
		if(tickets!=null) {
			TicketView ticketView = new TicketView();
			ticketView.viewTickets(tickets);
			ticketView.menu(user);
		}
		
	}

	@Override
	protected void noTickets(User user, String message) {
		System.out.println(message);
		TicketView ticketView = new TicketView();
		ticketView.menu(user);
	}
}
