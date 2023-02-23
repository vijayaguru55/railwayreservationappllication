package com.railwayticketreservation.repository;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import com.railwayticketreservation.dto.Credential;
import com.railwayticketreservation.dto.Ticket;
import com.railwayticketreservation.dto.Train;
import com.railwayticketreservation.dto.User;

public class RailwayManagementDatabase {
	private static RailwayManagementDatabase database;
	private ArrayList<User> userlist = new ArrayList<User>();
	private ArrayList<Credential> credentials = new ArrayList<Credential>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	private Map<User, ArrayList<Ticket>> ticketOfuser = new HashMap<User, ArrayList<Ticket>>();
	private Deque<Ticket> racTickets = new ArrayDeque<Ticket>();

	private RailwayManagementDatabase() {

	}

	public static RailwayManagementDatabase getInstance() {
		if (database == null) {
			database = new RailwayManagementDatabase();
			return database;
		}
		return database;
	}

	public void addUser(User user, String password) {
		this.userlist.add(user);
		Credential credential = new Credential(user.getName(), password, user.getUserId());
		credentials.add(credential);
	}

	public User checkCredials(String name, String password) {
		for (Credential credential : credentials) {
			if (credential.getName().equals(name) && credential.getPassword().equals(password))
				for (User user : userlist) {
					if (user.getUserId().equals(credential.getUserId()))
						return user;
				}
		}
		return null;
	}

	public ArrayList<Train> checkAvailability(String from, String to, LocalDate date) {
		ArrayList<Train> trains = new ArrayList<Train>();
		for (Train train : this.trains) {
			if (train.getStartingStation().equals(from) && train.getEndingStation().equals(to)
					&& train.getDate().equals(date)) {
				trains.add(train);

			}
		}
		return trains;
	}

	public boolean isexistingUser(String name, String password) {
		for (Credential credential : credentials) {
			if (credential.getName().equals(name) && credential.getPassword().equalsIgnoreCase(password))
				return true;
		}
		return false;
	}

	public void addtrain(Train train) {
		this.trains.add(train);

	}

	public void updateTickets(User user, ArrayList<Ticket> tickets) {
		if (this.ticketOfuser.containsKey(user)) {
			ArrayList<Ticket> ticketsAvail = this.ticketOfuser.get(user);
			ticketsAvail.addAll(tickets);
			this.ticketOfuser.put(user, ticketsAvail);
		} else {
			this.ticketOfuser.put(user, tickets);
		}
	}

	public Ticket isValidTicket(String ticketId, User user) {
		if (this.ticketOfuser.containsKey(user)) {
			for (Ticket ticket : this.ticketOfuser.get(user)) {
				if (ticket.getTicketId().equals(ticketId)) {
					if(!ticket.isTatkal()) {
						this.ticketOfuser.get(user).remove(ticket);
					}
					return ticket;
				}
			}
		}

		return null;
	}

	public Train getTrain(String trainId) {
		for (Train train : this.trains) {
			if (train.getTrainId().equals(trainId))
				return train;
		}
		return null;
	}

	public void updateRac(ArrayList<Ticket> tickets) {
		this.racTickets.addAll(tickets);
	}

	public boolean isRacAvail() {
		return this.racTickets.isEmpty();
	}

	public Ticket getTopRACTicket() {
		return this.racTickets.pop();
	}

	public ArrayList<Ticket> getTickets(User user) {
		if(this.ticketOfuser.containsKey(user)) {
			return this.ticketOfuser.get(user);
		}
		return null;
	}

}
