package finalizedbusresv;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException, InterruptedException {

		System.out.print("\nWelcome To TravelWorld Bus");
		Thread.sleep(700);
		System.out.print(".");
		Thread.sleep(700);
		System.out.print(".");
		Thread.sleep(700);
		System.out.print(".");
		Thread.sleep(500);
		System.out.println();

		Scanner sc = new Scanner(System.in);

		BusDAO busDAO = new BusDAO();
		BookingDAO bookingDAO = new BookingDAO();

		System.out.println("==================== All Bus Details ====================");

		List<Bus> buses = busDAO.getAllBuses();

		for (Bus bus : buses) {
			bus.displayBusInfo();
			System.out.println();
		}
		

		while (true) {

			System.out.println("\n============== MENU ==============");
			System.out.println("1. Book Ticket");
			System.out.println("2. View Details of Booked Ticket");
			System.out.println("3. Cancel the Booked Ticket");
			System.out.println("4. Search Bus");
			System.out.println("5. Exit");

			System.out.print("Enter your choice(1/2/3/4/5): ");

			int choice = sc.nextInt();

			switch (choice) {

				case 1 -> {
					System.out.print("Enter your Preferred Bus Number: ");
					String input = sc.next();
	
					Bus selectedBus = busDAO.getBusByBusNo(input);
	
					if (selectedBus == null) {
						System.out.println("Invalid bus number.");
					} 
					else {
						System.out.print("Enter Journey Date (dd-mm-yyyy): ");
						String dateInput = sc.next();
	
						Date journeyDate = Booking.parseDate(dateInput);
	
						boolean available = bookingDAO.isBusAvailable(selectedBus.getBusNo(), journeyDate, selectedBus.getCapacity());
	
						if (!available) {
							System.out.println("This Bus is Full.. Try in another date or another bus");
						} 
						else {
							System.out.println("Ticket Price: Rs. " + selectedBus.getTicketPrice());
							
							System.out.print("Enter amount to pay: ");
							double amountPaid = sc.nextDouble();
	
							if (amountPaid < selectedBus.getTicketPrice()) {
								System.out.println("Insufficient payment. Booking cancelled.");
							} 
							else {
								System.out.println("Booking...");
								Thread.sleep(1000);
	
								Booking booking = new Booking(sc, selectedBus, amountPaid, journeyDate);
	
								boolean saved = bookingDAO.saveBooking(booking);
	
								if (saved) {
									System.out.println("Booking saved successfully!");
								} 
								else {
									System.out.println("Booking failed. Please try again.");
								}
							}
						}
					}
				}
	
				case 2 -> {
					System.out.print("Enter your Booking ID: ");
					String bookingId = sc.next();
	
					System.out.println("Searching...");
					Thread.sleep(1000);
	
					Booking booking = bookingDAO.getBookingById(bookingId);
				
					if (booking != null) {
						booking.displayBookingDetails();
					}
					else {
						System.out.println("Booking not found!");
					}
				}
	
				case 3 -> {
					System.out.print("Enter your Booking ID to Cancel Booking: ");
	
					String bookingId = sc.next();
	
					System.out.println("Cancelling...");
					Thread.sleep(1000);
	
					Booking booking = bookingDAO.getBookingById(bookingId);
					
					if (booking == null) {
						System.out.println("Invalid BookingID, So Booking Not Found!");
					} 
					else {
						System.out.println("\nAre you sure you want to cancel?");
	
						System.out.println("1. Yes");
						System.out.println("2. No");
	
						System.out.print("Enter your choice (1/2): ");
								
						int cancelChoice = sc.nextInt();
	
						if (cancelChoice == 1) {
	
							boolean cancelled = bookingDAO.cancelBooking(bookingId);
	
							if (cancelled) {
								System.out.println("\nBooking Cancelled Successfully!");
							}
							else {
								System.out.println("\nBooking Cancellation Failed.");
							}
	
						}
						else if (cancelChoice == 2) {
							System.out.println("\nBooking Cancellation Aborted.");
						}
						else {
							System.out.println("\nInvalid Choice! Booking Cancellation Aborted.");
						}
					}
				}
	
				case 4 -> {
					System.out.print("Enter Source Place: ");
					String from = sc.next();
	
					System.out.print("Enter Destination Place: ");
					String to = sc.next();
	
					System.out.println("Searching...");
					Thread.sleep(1000);
	
					List<Bus> searchResults = busDAO.searchBuses(from, to);
	
					System.out.println();
					System.out.println("================ Available Buses ================");
							
					if (searchResults.isEmpty()) {
						System.out.println("No buses available for this route!");
					}
					else {
						for (Bus bus : searchResults) {
							bus.displayBusInfo();
						}
					}
				}
	
				case 5 -> {
					System.out.println("Exiting...");
					Thread.sleep(1000);
	
					System.out.println("Have a Nice Day..");
	
					sc.close();
	
					return;
				}
	
				default -> {
					System.out.println("Invalid choice");
				}
			}
		}
	}
}