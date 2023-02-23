# railwayreservationappllication
This is RailwayTicket management and reservation application using JAVA.
This console application design is based on MVC design pattern.
The main starting method is placed in file src/com/railwayticketreservation/LoginView.java
There are 3 options shown Mainmenu here.
1.SignUp 2.Login 3.Exit.
There is one admin login credential is here , which is i placed for adminLogin --> username:"adminPanel@123" password is anything.
When admin login happens, Another menu is open which is placed in src/com/railwayticketreservation/Train.java
1.Add Train 2.TicketMenu --Which is placed in src/com/railwayticketreservation/Ticket.java 3.logout--its going to mainMenu. 
The ticket Menu is 1.BookiTicket 2.cancelTicket and logout.
All POJO calsses are placed in src/com/railwayticketreservation/dto.
The signUp option is going to user module which is placed in src/com/railwayticketreservation/user.
There is tatkal booking option is available which is open only a day before the departure date at 10AM anyOne willing to book that time if the Tatkal tickets avail its shown the tatkal ticket details.
Also RAC bookings are alos available which is open when all tickets are booked.
Ticket cancellation allowed onlly normal and RAC tickets only not for tatkal tickets.
When confirmed ticket is booked the first booked waitinglist ticket is moved to confirmed ticket.
