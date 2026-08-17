package finalizedbusresv;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Booking {

	private String passengerName;
	private int age;
	private String gender;
	private Date journeyDate;
	private Bus bus;
	private String bookingId;
	private double amountPaid;
	
	// Constructor used when creating a new booking from user input
	Booking(Scanner sc, Bus bus, double amountPaid, Date journeyDate) {
		this.bus = bus;
		this.amountPaid = amountPaid;
		this.journeyDate = journeyDate;
		
		System.out.println("Enter your Name: ");
		this.passengerName = sc.next().toUpperCase();
		
		System.out.println("Enter your Age: ");
		this.age = sc.nextInt();

		System.out.println("Enter your Gender (M / F): ");
		this.gender = sc.next();
		
		
		this.bookingId = generateBookingId();
		
		System.out.println("\nBooking Confirmed!");
		displayBookingDetails();
	}
	
	// Here Constructor Overloading done, bcoz we need a Constructor to be used when creating a Booking object from database data
	Booking(String passengerName, int age, String gender, Date journeyDate, Bus bus, String bookingId, double amountPaid) {
		this.passengerName = passengerName;
		this.age = age;
		this.gender = gender;
		this.journeyDate = journeyDate;
		this.bus = bus;
		this.bookingId = bookingId;
		this.amountPaid = amountPaid;
	}
	
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	
	public static Date parseDate(String dateInput) throws ParseException {
		Date date = DATE_FORMAT.parse(dateInput);
		return date;
	}

	
	private static Set<String> usedBookingIds = new HashSet<>();
	
	public String generateBookingId() {
		SecureRandom secureRandom = new SecureRandom();
		String id;
		
		do {
			StringBuilder sb = new StringBuilder("BID-");
			
			for (int i = 0; i < 8; i++) {
				sb.append(secureRandom.nextInt(10));
			}
			id = sb.toString();
			
		} while (usedBookingIds.contains(id));
		
		usedBookingIds.add(id);
		
		return id;
	}
	
	// Getters
	public String getPassengerName() {
		return passengerName;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public Bus getBus() {
		return bus;
	}

	public Date getJourneyDate() {
		return journeyDate;
	}
	
	public String getBookingId() {
		return bookingId;
	}
	
	public double getAmountPaid() {
		return amountPaid;
	}
	
	public static boolean isBusAvailable(Bus selectedBus, List<Booking> bookings, Date journeyDate) {
		
		int totalCapacity = selectedBus.getCapacity();
		
		int filledSeats = 0;
		for (Booking booking : bookings) {
			if (booking.getBus().getBusNo().equals(selectedBus.getBusNo()) 
					&& booking.getJourneyDate().equals(journeyDate)) {
				filledSeats++;
			}
		}
		
		return filledSeats < totalCapacity;
	}
		
	public void displayBookingDetails() {
		System.out.println("---------------------------------");
		System.out.println("Booking ID     : " + bookingId);
		System.out.println("Passenger Name : " + passengerName);
		System.out.println("Age            : " + age);
		System.out.println("Gender         : " + gender);
		System.out.println("Bus Number     : " + bus.getBusNo());
		System.out.println("Journey Date   : " + DATE_FORMAT.format(journeyDate));
		System.out.println("Duration       : " + bus.getTravelingDuration());
		System.out.println("Amount Paid    : Rs. " + amountPaid);
		System.out.println("---------------------------------");
	}
	
	public static void viewBooking(List<Booking> bookings, Scanner sc) throws InterruptedException {
		System.out.println("Enter your Booking ID: ");
		String input = sc.next();
		
		System.out.println("Searching...");
		Thread.sleep(1000);
		
		boolean found = false;
		
		for (Booking booking : bookings) {
			if (booking.getBookingId().equalsIgnoreCase(input)) {
				booking.displayBookingDetails();
				found = true;
				break;
			}
		}
		
		if (!found) {
			System.out.println("Booking not found!");
		}
	}
	
	public static void cancelBooking(List<Booking> bookings, Scanner sc) throws InterruptedException {
		System.out.println("Enter your Booking ID to Cancel Booking: ");
		String input = sc.next();
		
		System.out.println("Cancelling...");
		Thread.sleep(1000);
		
		// bookings variable is of Type: LIST
		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).getBookingId().equalsIgnoreCase(input)) {
				System.out.println("\nAre you sure you want to cancel?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				
				System.out.print("Enter your choice (1/2): ");
				int choice = sc.nextInt();
				
				if (choice == 1) {
					bookings.remove(i);
					System.out.println("\nBooking Cancelled Successfully!");
				}
				else if (choice == 2) {
            			System.out.println("\nBooking Cancellation Aborted.");
            			System.out.println();
				}
				else {
            			System.out.println("\nInvalid Choice! Booking Cancellation Aborted.");
				}
				return;
			}
		}
		
		System.out.println("Invalid BookingID, So Booking Not Found!");
	}
}