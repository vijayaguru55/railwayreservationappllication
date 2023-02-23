package com.railwayticketreservation.ticket;

import java.time.LocalDate;
import java.util.ArrayList;

import com.railwayticketreservation.dto.Passenger;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;

public abstract class TicketControllerCallback {

	protected abstract Train chechAvailablity(String from, String to, LocalDate date, User user);

	protected abstract void bookticket(ArrayList<Passenger> passengers, Train train, User user, boolean b);

	protected abstract void cancelTicket(String ticketId, User user);

}
