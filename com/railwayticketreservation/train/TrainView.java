package com.railwayticketreservation.train;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.login.LoginView;
import com.railwayticketreservation.ticket.TicketView;

public class TrainView extends TrainViewCallback {
	Scanner scanner = new Scanner(System.in);
	private TrainControllerCallback controllerCallback;

	public TrainView() {
		this.controllerCallback = new TrainController(this);
	}

	public void menu(User user) {
		System.out.println("1.Add train\n2.TicketMenu\n3.Logout");
		String option = scanner.next();

		switch (option) {
		case "1":
			addTrain(user);
			break;
		case "2":
			TicketView ticketView = new TicketView();
			ticketView.menu(user);
			break;
		case "3":
			LoginView loginView = new LoginView();
			loginView.create();
			break;
		default:
			System.out.println("Enter valid option");
			menu(user);
			break;
		}
	}

	private void addTrain(User user) {
		int number = 0;
		int sitPrice = 0;
		int berthPrice = 0;
		LocalDate date = null, current = null;
		Period isvalid = null;
		System.out.println("Enter train name:");
		String name = scanner.next();

		System.out.println("Enter from location:");
		String from = scanner.next();
		System.out.println("Enter to location:");
		String to = scanner.next();
		String dateString = null;
		System.out.println("Enter departure time as (HH:MM)");
		String time = scanner.next();

		try {
			System.out.println("Enter train No:");
			number = scanner.nextInt();
			System.out.println("Enter sitting price");
			sitPrice = scanner.nextInt();
			System.out.println("Enter berthPrice");
			berthPrice = scanner.nextInt();
			System.out.println("Enter travel date as (yyyy-mm-dd)");
			dateString = scanner.next();
			date = LocalDate.parse(dateString);
			current = LocalDate.now();
			isvalid = Period.between(current, date);
			while (isvalid.getYears() < 0 || isvalid.getMonths() < 0 || isvalid.getDays() < 0) {
				System.out.println("Invalid date , Please enter valid date");
				System.out.println("Enter   Date as(yyyy-mm-dd)");
				isvalid = isvalid.between(current, date = date.parse(dateString = scanner.next()));
			}

		} catch (Exception e) {
			System.out.println("Invalid input");
			addTrain(user);
		}
		controllerCallback.createTrain(name, number, from, to, sitPrice, berthPrice, date, user, time);
	}

	public void viewtrain(Train train) {
		System.out
				.println(train.getTrainName() + "\t" + train.getStartingStation() + "--->" + train.getEndingStation());
		System.out.println("Available berths :\nUpper :" + train.getAvailableUpperBerths());
		System.out.println("Lower :" + train.getAvailableLowerBerths());
		System.out.println("Middle :" + train.getAvailableMiddleBerths());
		System.out.println("Berth ticket price: " + train.getBerthPrice());
		System.out.println("Available sittings :" + train.getAvailableSittings());
		System.out.println("Sitting ticket price :" + train.getSittingPrice());
		System.out.println("Available RAC tickets:" + train.getAvailableRACTicket());
		if (train.getAvailTatkal() >= 1 && Period.between(LocalDate.now(),train.getDate()).getDays()<=1)
			System.out.println("Available Tatkal tickets :" + train.getAvailTatkal());
		System.out.println("-----------------------------------------------------------------------------");
	}

	@Override
	protected void trainAddSuccess(String string, User user) {
		System.out.println(string);
		menu(user);
	}

}
