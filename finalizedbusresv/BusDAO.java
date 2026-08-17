package finalizedbusresv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
	
	public List<Bus> getAllBuses() {

		// Datas from MySQL will be fetching here, so we need to store them
		List<Bus> buses = new ArrayList<>();
		
		String sql = "SELECT * FROM BUS ORDER BY created_at ASC";
		
		try (Connection con = DBConn.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();)
		{
			while (rs.next()) {
				String busNo = rs.getString("bus_no");
                	int capacity = rs.getInt("capacity");
                	boolean isAC = rs.getBoolean("is_ac");
                	double ticketPrice = rs.getDouble("ticket_price");
                	String travelingDuration = rs.getString("traveling_duration");
                	String driverName = rs.getString("driver_name");
                	String fromLocation = rs.getString("from_location");
                	String toLocation = rs.getString("to_location");
                
                Bus bus = new Bus(busNo, capacity, isAC, ticketPrice, travelingDuration, driverName, fromLocation, toLocation);
                
                buses.add(bus);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buses;
	}
	
	public List<Bus> searchBuses(String from, String to) {
		List<Bus> buses = new ArrayList<>();
		
		String sql = """
						SELECT * FROM BUS
						WHERE LOWER(from_location) = LOWER(?) AND LOWER(to_location) = LOWER(?) 
				 		ORDER BY created_at ASC
					 """;
		
		try (Connection con = DBConn.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setString(1, from);
	        ps.setString(2, to);
	        
	        try (ResultSet rs = ps.executeQuery()) 
	        {
		        	while (rs.next()) {
					String busNo = rs.getString("bus_no");
	                	int capacity = rs.getInt("capacity");
	                	boolean isAC = rs.getBoolean("is_ac");
	                	double ticketPrice = rs.getDouble("ticket_price");
	                	String travelingDuration = rs.getString("traveling_duration");
	                	String driverName = rs.getString("driver_name");
	                	String fromLocation = rs.getString("from_location");
	                	String toLocation = rs.getString("to_location");
	                
	                Bus bus = new Bus(busNo, capacity, isAC, ticketPrice, travelingDuration, driverName, fromLocation, toLocation);
	                
	                buses.add(bus);
		        	}
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buses;
		
	}
	
	public Bus getBusByBusNo(String busNo) {
		
		String sql = """
			            SELECT *
			            FROM BUS
			            WHERE bus_no = ?
	            		""";
		
		try (Connection con = DBConn.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setString(1, busNo);
	   
	        
	        try (ResultSet rs = ps.executeQuery()) 
	        {
		        if (rs.next()) {
		        		return new Bus (
	                        rs.getString("bus_no"),
	                        rs.getInt("capacity"),
	                        rs.getBoolean("is_ac"),
	                        rs.getDouble("ticket_price"),
	                        rs.getString("traveling_duration"),
	                        rs.getString("driver_name"),
	                        rs.getString("from_location"),
	                        rs.getString("to_location")
		        		);
		        }
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {

	    BusDAO dao = new BusDAO();

	    System.out.println("=============== ALL BUSES ===============");

	    List<Bus> buses = dao.getAllBuses();

	    for (Bus bus : buses) {
	        bus.displayBusInfo();
	    }


	    System.out.println("\n=============== SEARCH ===============");

	    List<Bus> searchResult =
	            dao.searchBuses("Chennai", "Madurai");

	    for (Bus bus : searchResult) {
	        bus.displayBusInfo();
	    }


	    System.out.println("\n=============== GET ALL BUS BY BUS NUMBER ===============");

	    Bus bus = dao.getBusByBusNo("0001");

	    if (bus != null) {
	        bus.displayBusInfo();
	    } else {
	        System.out.println("Bus not found!");
	    }
	}
}
