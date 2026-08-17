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
}
