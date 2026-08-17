package finalizedbusresv;

import java.util.List;
import java.util.Scanner;

public class Bus {
	private String busNo;
	private int capacity;
	private boolean isAC;
	private double ticketPrice;
	private String travelingDuration;
	private String driverName;
	private String fromLocation;
	private String toLocation;
	
	Bus (String busNo, int capacity, boolean isAC, double ticketPrice, String travelingDuration, String driverName, 
		String fromLocation, String toLocation) {
		this.busNo = busNo;
		this.capacity = capacity;
		this.isAC = isAC;
		this.ticketPrice = ticketPrice;
		this.travelingDuration = travelingDuration;
		this.driverName = driverName;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getTravelingDuration() {
		return travelingDuration;
	}

	public void setTravelingDuration(String travelingDuration) {
		this.travelingDuration = travelingDuration;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getBusNo() {
		return busNo;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isAC() {
		return isAC;
	}
	
	public void displayBusInfo() {
		System.out.println("----------------------");
		System.out.println("Bus Number: " + busNo);
        System.out.println("Bus Type A/C: " + isAC);
        System.out.println("Bus Total Seats: " + capacity);
        System.out.println("Bus Fare: " + ticketPrice + "Rs");
        System.out.println("Bus Journey Timing: " + travelingDuration);
        System.out.println("Bus Driver Name: " + driverName);
        System.out.println("Bus Source Place: " + fromLocation);
        System.out.println("Bus Destination Place: " + toLocation);
		System.out.println("----------------------");
	}
	
	public static void searchBus(List<Bus> buses, Scanner sc) throws InterruptedException {
		System.out.print("Enter Source Place: ");
	    String from = sc.next();

	    System.out.print("Enter Destination Place: ");
	    String to = sc.next();
	    
	    System.out.println("Searching...");
		Thread.sleep(1000);

	    boolean found = false;
	    
	    System.out.println();
	    System.out.println("================ Available Buses ================");
	    
	    for (Bus bus : buses) {
	    		if (bus.getFromLocation().equalsIgnoreCase(from) && bus.getToLocation().equalsIgnoreCase(to)) {
	    			bus.displayBusInfo();
	    			found = true;
	    		}
	    }
	    
	    if (!found) {
	    		System.out.println("No buses available for this route!");
	    }
	}
}
