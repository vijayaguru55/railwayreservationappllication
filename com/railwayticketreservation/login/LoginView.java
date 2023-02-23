package com.railwayticketreservation.login;

import java.util.Scanner;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.ticket.TicketView;
import com.railwayticketreservation.train.TrainView;
import com.railwayticketreservation.user.UserView;

public class LoginView extends LoginViewCallBack {
	Scanner scanner = new Scanner(System.in);
	private LoginControllerCallback controllerCallback;

	public LoginView() {
		this.controllerCallback = new LoginController(this);
	}

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.create();
	}

	public void create() {
		menu();
	}

	private void menu() {
		System.out.println("1.SignUp\n2.Login\n3.Exit");
		String option = scanner.next();

		switch (option) {
		case "1":
			UserView userView = new UserView();
			userView.signUp();
			break;
		case "2":
			login();
			break;
		case "3":
			System.out.println("Thank..You");
			System.exit(0);
		default: {
			System.out.println("invalid option");
			menu();
		}
			break;
		}
	}

	private void login() {
		System.out.println("Enter user name:");
		String name = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		controllerCallback.checkCredential(name, password);
	}

	@Override
	protected void loginSucccess(User user) {
		System.out.println("Welcome back " + user.getName());
		if (user.getName().equals("adminPanel@123")) {
			TrainView trainView = new TrainView();
			trainView.menu(user);
		} else {
			TicketView ticketView = new TicketView();
			ticketView.menu(user);
		}
	}

	@Override
	protected void loginFailde(String message) {
		System.out.println(message);
		menu();
	}

}
