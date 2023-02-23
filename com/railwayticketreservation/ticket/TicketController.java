package com.railwayticketreservation.ticket;

import java.time.LocalDate;
import java.util.ArrayList;

import com.railwayticketreservation.dto.Passenger;
import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;
import com.railwayticketreservation.ticket.TicketModel.TicketModelControllecallback;

public class TicketController extends TicketControllerCallback implements TicketModelControllecallback {
	private TicketViewCallback viewCallback;
	private TicketModelCallback modelCallback;

	public TicketController(TicketView ticketView) {
		this.viewCallback = ticketView;
		this.modelCallback = new TicketModel(this);
	}

	@Override
	protected Train chechAvailablity(String from, String to, LocalDate date, User user) {
		return modelCallback.checkAvailablity(from, to, date, user);
	}

	@Override
	public Train chooseTrains(ArrayList<Train> trains) {
		return viewCallback.chooseTrains(trains);

	}

	@Override
	public void trainsNotAvail(String message, User user) {

		viewCallback.trainsNotAvail(message, user);
	}

	@Override
	protected void bookticket(ArrayList<Passenger> passengers, Train train, User user, boolean isConfirmed) {
		modelCallback.bookTicket(passengers, train, user, isConfirmed);
	}

	@Override
	public void bookingSuccess(User user, ArrayList<Ticket> tickets) {
		viewCallback.bookingSuccess(user, tickets);
	}

	@Override
	public void bookingFailed(String message, User user) {
		viewCallback.bookingFailed(message, user);
	}

	@Override
	protected void cancelTicket(String ticketId, User user) {
		modelCallback.cancelTicket(ticketId, user);
	}

	@Override
	public void cancellationSuccess(User user) {
		viewCallback.cancellationSucces(user);
	}

	@Override
	public void cancelaltionFailed(String message, User user) {
		viewCallback.cancellationFailed(message, user);
	}

	@Override
	public boolean proceedRac() {

		return viewCallback.proceedRac();
	}

	@Override
	public boolean proceedPayment(int ticketprice, int tickets) {

		return viewCallback.proceedPaymrnt(ticketprice, tickets);
	}
}
