package com.railwayticketreservation.ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.railwayticketreservation.dto.Passenger;
import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.login.LoginView;
import com.railwayticketreservation.train.TrainView;
import com.railwayticketreservation.user.UserView;

public class TicketView extends TicketViewCallback {
	Scanner scanner = new Scanner(System.in);
	private TicketControllerCallback controllerCallback;

	public TicketView() {
		this.controllerCallback = new TicketController(this);
	}

	public void menu(User user) {
		System.out.println("-------------------------------------------");
		System.out.println("1.Book ticket\n2.Cancel Ticket\n3.View My tickets\n4.LogOut");
		String option = null;
		try {
			option = scanner.next();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
			menu(user);
		}
		switch (option) {
		case "1":
			bookticket(user);
			break;
		case "2":
			cancelTicket(user);
			break;
		case "3":
			UserView userView = new UserView();
			userView.viewTickets(user);
		case "4": {
			LoginView loginView = new LoginView();
			loginView.create();
		}
			break;
		default:
			System.out.println("Invalid input");
			menu(user);
			break;
		}
	}

	private void cancelTicket(User user) {
		System.out.println("Enter ticket Id:");
		String ticketId = scanner.next();
		controllerCallback.cancelTicket(ticketId, user);
	}

	private void bookticket(User user) throws InputMismatchException {
		System.out.println("Entet from location:");
		String from = scanner.next();
		System.out.println("Enter destination:");
		String to = scanner.next();
		LocalDate date = null;
		Period isvalid = null;
		try {
			System.out.println("Enter travel date as (yyyy-mm-dd)");
			String dateString = scanner.next();
			date = LocalDate.parse(dateString);
			LocalDate current = LocalDate.now();
			isvalid = Period.between(current, date);
			while (isvalid.getYears() < 0 || isvalid.getMonths() < 0 || isvalid.getDays() < 0) {

				System.out.println("Invalid date , Please enter valid date");
				System.out.println("Enter   Date as(yyyy-mm-dd)");
				isvalid = isvalid.between(current, date = date.parse(dateString = scanner.next()));
			}
		} catch (Exception e) {
			System.out.println("Invalid input");
			bookticket(user);
		}
		Train train = controllerCallback.chechAvailablity(from, to, date, user);
		if (train == null)
			menu(user);
		boolean availTatkal = false;
		if (isvalid.getYears() == 0 && isvalid.getMonths() == 0 && isvalid.getDays() <= 1) {
			if (LocalTime.now().isAfter(LocalTime.of(10, 00))) {
				availTatkal = true;
			}
		}
		ArrayList<Passenger> passengers = getPassengerdetails(train, availTatkal);

		if (passengers != null) {
			if (passengers.get(0).isConfirmed())
				controllerCallback.bookticket(passengers, train, user, true);
			else
				controllerCallback.bookticket(passengers, train, user, false);
		} else
			menu(user);

	}

	private ArrayList<Passenger> getPassengerdetails(Train train, boolean isTatkal) {
		int totalSeats = train.getBerths() + train.getSiitings();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		System.out.println("Enter number of tickets to book");
		int ticketsCount = scanner.nextInt();
		boolean tatkal = false;
		if ((isTatkal && train.getAvailTatkal() >= ticketsCount && (tatkal = proceedTatkal()))
				|| (tatkal == false && totalSeats >= ticketsCount)
				|| (tatkal == false && train.getAvailableRACTicket() >= ticketsCount && proceedRac())) {
			for (int i = 0; i < ticketsCount; i++) {
				Passenger passenger = getPasssenger();
				if (totalSeats < ticketsCount) {
					passenger.setConfirmed(false);
				}
				if (tatkal) {
					passenger.setConfirmed(true);
					passenger.setTatkal(tatkal);
				}
				passengers.add(passenger);
			}
			return passengers;
		}
		System.out.println("No seats");
		return null;

	}

	private boolean proceedTatkal() {
		System.out.println(
				"Tatkal tickets are available...\nTatkal tickets cahrges  are 30% more than normal ticket..\nPress 1 to proceed");
		int option = scanner.nextInt();
		return option == 1 ? true : false;
	}

	private Passenger getPasssenger() {
		System.out.println("Enter passenger Name:");
		String name = scanner.next();
		System.out.println("Enter passenger gender");
		String gender = chooseGender();
		System.out.println("Enter age");
		byte age = scanner.nextByte();
		String berth = choosebirth();
		return new Passenger(name, gender, age, berth);
	}

	private String chooseGender() {
		System.out.println("1.Male\n2.Female\n3.Transgender");
		byte option = scanner.nextByte();
		String gender = "";
		switch (option) {
		case 1:
			gender = "Male";
			break;
		case 2:
			gender = "Female";
			break;
		case 3:
			gender = "Transgender";
			break;
		default: {
			System.out.println(" valid input");
			chooseGender();
		}
			break;
		}
		return gender;
	}

	private String choosebirth() {
		System.out.println("Choose berth preference and seatings:");
		System.out.println("1.UpperBerth.\n2.LowerBerth.\n3.MiddleBerth.\n4.Chaircar");
		int option = scanner.nextInt();
		switch (option) {
		case 1:
			return "Upper";
		case 2:
			return "Lower";
		case 3:
			return "Middle";
		case 4:
			return "Chair";
		default: {
			System.out.println("Choose valid options");
			choosebirth();
		}
			break;
		}
		return null;
	}

	@Override
	protected void trainsNotAvail(String message, User user) {
		System.out.println(message);
		menu(user);
	}

	@Override
	protected Train chooseTrains(ArrayList<Train> trains) {
		System.out.println(trains.size());

		TrainView trainView = new TrainView();
		int count = 1;
		for (Train train : trains) {
			if (train.getAvailableRACTicket() <= 0) {
				System.out.println(train.getTrainName() + " is filled");
				if (count == trains.size()) {
					System.out.println("No Trains......");
					return null;
				} else {
					count++;
					continue;
				}

			}
			System.out.println("press " + count++ + " to choose");
			trainView.viewtrain(train);
		}
		int option = scanner.nextInt();
		if (option > trains.size())
			return chooseTrains(trains);
		return trains.get(option - 1);
	}

	@Override
	protected void bookingSuccess(User user, ArrayList<Ticket> tickets) {
		viewTickets(tickets);
		menu(user);
	}

	public void viewTickets(ArrayList<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			System.out.println("---------------------------------------------------------------------");
			viewTicket(ticket);
		}

	}

	private void viewTicket(Ticket ticket) {
		System.out.println("Name :"+ticket.getPassengerName()+" "+ticket.getPassengerAge() +"/t"+ticket.getPassengergender());
		System.out.println("Train Id:" + ticket.getTrainId());
		System.out.println("From :" + ticket.getFromStation() + "\t to :" + ticket.getToStation());
		System.out.println("TicketId :" + ticket.getTicketId());
		System.out.println("SeatNo :" + ticket.getSeatNo() + "\t " + ticket.getBerth());
		System.out.println("Date : " + ticket.getDate() + "\t Time:" + ticket.getTime());
		System.out.println(ticket.isConfirmed() ? "Booking Confirmed...." : "Booked in Waiting List...");
		System.out.println(".....Happy and Safe Jouney " + ticket.getPassengerName() + ".....");

		System.out.println("\tThank You..\t");
	}

	@Override
	protected void bookingFailed(String message, User user) {
		System.out.println(message);
		menu(user);
	}

	@Override
	protected void cancellationSucces(User user) {
		System.out.println("Ticket Cancelled..");
		System.out.println("Amount refund within 3 days of bank working days");
		menu(user);
	}

	@Override
	protected void cancellationFailed(String message, User user) {
		System.out.println(message);
		menu(user);
	}

	@Override
	protected boolean proceedRac() {
		System.out.println("Only RAC tickets are available... \nPress 1 to proceed");
		String proceed = scanner.next();
		return proceed.equals("1") ? true : false;
	}

	@Override
	protected boolean proceedPaymrnt(int ticketprice, int tickets) {
		System.out.println("No.Of Tickets :" + tickets + "\nPrice :" + ticketprice);
		System.out.println("Total price   :" + ticketprice * tickets);
		System.out.println("Press 1.ProceedPayment\n2.Cancel");
		String proceed = scanner.next();
		return proceed.equals("1") ? true : false;
	}
}
