package com.railwayticketreservation.ticket;

import java.time.LocalDate;
import java.util.ArrayList;

import com.railwayticketreservation.dto.Passenger;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;

public abstract class TicketModelCallback {

	protected abstract Train checkAvailablity(String from, String to, LocalDate date, User user);

	protected abstract void bookTicket(ArrayList<Passenger> passengers, Train train, User user, boolean isConfirmed);

	protected abstract void cancelTicket(String ticketId, User user);

}
