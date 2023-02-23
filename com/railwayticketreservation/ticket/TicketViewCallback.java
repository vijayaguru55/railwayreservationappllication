package com.railwayticketreservation.ticket;

import java.util.ArrayList;

import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;

public abstract class TicketViewCallback {

	protected abstract void trainsNotAvail(String string, User user);

	protected abstract Train chooseTrains(ArrayList<Train> trains);

	protected abstract void bookingSuccess(User user, ArrayList<Ticket> tickets);

	protected abstract void bookingFailed(String string, User user);

	protected abstract void cancellationSucces(User user);

	protected abstract void cancellationFailed(String message, User user);

	protected abstract boolean proceedRac();

	protected abstract boolean proceedPaymrnt(int ticketprice, int tickets);

}
