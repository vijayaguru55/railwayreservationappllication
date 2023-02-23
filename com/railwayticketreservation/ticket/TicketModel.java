package com.railwayticketreservation.ticket;

import java.time.LocalDate;
import java.util.ArrayList;

import com.railwayticketreservation.dto.Passenger;
import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.repository.RailwayManagementDatabase;

public class TicketModel extends TicketModelCallback {
	private TicketModelControllecallback controllecallback;

	public TicketModel(TicketModelControllecallback controllecallback) {
		this.controllecallback = controllecallback;
	}

	interface TicketModelControllecallback {

		Train chooseTrains(ArrayList<Train> trains);

		void trainsNotAvail(String string, User user);

		void bookingSuccess(User user, ArrayList<Ticket> tickets);

		void bookingFailed(String string, User user);

		void cancellationSuccess(User user);

		void cancelaltionFailed(String string, User user);

		boolean proceedRac();

		boolean proceedPayment(int ticketprice, int i);

	}

	@Override
	protected Train checkAvailablity(String from, String to, LocalDate date, User user) {
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		ArrayList<Train> trains = database.checkAvailability(from, to, date);

		if (trains == null || trains.size() < 1) {
			controllecallback.trainsNotAvail("No trains available for this route", user);
			return null;
		}

		return controllecallback.chooseTrains(trains);
	}

	@Override
	protected void bookTicket(ArrayList<Passenger> passengers, Train train, User user, boolean isConfirmed) {
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		ArrayList<Ticket> tickets = createTicket(passengers, train, isConfirmed);

		if (tickets == null) {
			controllecallback.bookingFailed("No seats....!", user);
		} else if (controllecallback.proceedPayment(tickets.get(0).getTicketprice(), tickets.size())) {
			database.updateTickets(user, tickets);
			if (!isConfirmed)
				database.updateRac(tickets);
			controllecallback.bookingSuccess(user, tickets);
		} else {
			controllecallback.bookingFailed("Booking failed due to Payment cancellation..!", user);
		}
	}

	private ArrayList<Ticket> createTicket(ArrayList<Passenger> passengers, Train train, boolean isConfirmed) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		for (Passenger passenger : passengers) {

			if (isBerthAvail(passenger.getBerthOrSeat(), train) || !isConfirmed || passenger.isTatkal()) {
				int price = getPrice(passenger.getBerthOrSeat(), train, false, isConfirmed);
				if (passenger.isTatkal()) {
					train.setAvailTatkal(train.getAvailTatkal() - 1);
					price = passenger.isTatkal() ? price + (price / 100) * 30 : price;
				}
				Ticket ticket = new Ticket(passenger.getName(), passenger.getAge(), passenger.getGender(),
						train.getStartingStation(), train.getEndingStation(), price, train.getDate(), train.getTime(),
						train.getTrainId(), passenger.getBerthOrSeat());
				ticket.setConfirmed(isConfirmed);
				if (passenger.isTatkal())
					ticket.setTatkal(true);
				tickets.add(ticket);
			} else {
				return null;
			}
		}
		return tickets;
	}

	private int getPrice(String berthOrSeat, Train train, boolean update, boolean isConfirmed) {
		if (!isConfirmed) {
			if (update)
				train.setAvailableRACTicket(train.getAvailableRACTicket() + 1);
			else
				train.setAvailableRACTicket(train.getAvailableRACTicket() - 1);
		}

		switch (berthOrSeat) {
		case "Upper": {
			if (isConfirmed) {
				if (update) {
					train.setAvailableUpperBirths(train.getAvailableUpperBerths() + 1);
					train.setBerths(train.getBerths() + 1);
				} else {
					train.setAvailableUpperBirths(train.getAvailableUpperBerths() - 1);
					train.setBerths(train.getBerths() - 1);
				}
			}
			return train.getBerthPrice();
		}
		case "Lower": {
			if (isConfirmed) {
				if (update) {
					train.setAvailableLowerBirths(train.getAvailableLowerBerths() + 1);
					train.setBerths(train.getBerths() + 1);
				} else {
					train.setAvailableLowerBirths(train.getAvailableLowerBerths() - 1);
					train.setBerths(train.getBerths() - 1);
				}
			}

			return train.getBerthPrice();
		}
		case "Middle": {
			if (isConfirmed) {
				if (update) {
					train.setAvailableMiddleBirths(train.getAvailableMiddleBerths() + 1);
					train.setBerths(train.getBerths() + 1);
				} else {
					train.setAvailableMiddleBirths(train.getAvailableMiddleBerths() - 1);
					train.setBerths(train.getBerths() - 1);
				}
			}
			return train.getBerthPrice();
		}
		case "Chair": {
			if (isConfirmed) {
				if (update) {
					train.setAvailableSittings(train.getAvailableSittings() + 1);
					train.setSiitings(train.getSiitings() + 1);
				} else {
					train.setAvailableSittings(train.getAvailableSittings() - 1);
					train.setSiitings(train.getSiitings() - 1);
				}
			}
			return train.getSittingPrice();
		}
		default:
			break;
		}

		return 0;
	}

	private boolean isBerthAvail(String berthOrSeat, Train train) {
		switch (berthOrSeat) {
		case "Upper": {
			return train.getAvailableUpperBerths() == 0 ? false : true;
		}
		case "Lower": {
			return train.getAvailableLowerBerths() == 0 ? false : true;
		}
		case "Middle": {
			return train.getAvailableMiddleBerths() == 0 ? false : true;
		}
		case "Chair": {
			return train.getAvailableSittings() == 0 ? false : true;
		}
		default:
			break;
		}
		return false;
	}

	@Override
	protected void cancelTicket(String ticketId, User user) {
		RailwayManagementDatabase database = RailwayManagementDatabase.getInstance();
		Ticket ticket = database.isValidTicket(ticketId, user);
		if (ticket != null) {
			if (ticket.isTatkal()) {
				controllecallback.cancelaltionFailed("Tatkal tickets can't be cancelled", user);
			}
			Train train = database.getTrain(ticket.getTrainId());
			getPrice(ticket.getBerth(), train, true, ticket.isConfirmed());
			if (ticket.isConfirmed()) {
				if (database.isRacAvail()) {
					Ticket racTicket = database.getTopRACTicket();
					racTicket.setConfirmed(true);
				}
			} else {
				database.getTopRACTicket();
			}
			controllecallback.cancellationSuccess(user);
		} else {
			controllecallback.cancelaltionFailed("Invalid ticket details", user);
		}
	}

}
