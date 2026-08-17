package finalizedbusresv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {

    public boolean saveBooking(Booking booking) {

        String sql = """
		                INSERT INTO BOOKING
		                (booking_id, passenger_name, age, gender,
		                 bus_no, journey_date, amount_paid)
		                VALUES (?, ?, ?, ?, ?, ?, ?)
	                	""";

        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, booking.getBookingId());
            ps.setString(2, booking.getPassengerName());
            ps.setInt(3, booking.getAge());
            ps.setString(4, booking.getGender());
            ps.setString(5, booking.getBus().getBusNo());

            ps.setDate(
                    6,
                    new java.sql.Date(
                            booking.getJourneyDate().getTime()
                    )
            );

            ps.setDouble(7, booking.getAmountPaid());

            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Booking getBookingById(String bookingId) {

        String sql = """
                SELECT
                    b.booking_id,
                    b.passenger_name,
                    b.age,
                    b.gender,
                    b.bus_no,
                    b.journey_date,
                    b.amount_paid,

                    bus.capacity,
                    bus.is_ac,
                    bus.ticket_price,
                    bus.traveling_duration,
                    bus.driver_name,
                    bus.from_location,
                    bus.to_location

                FROM BOOKING b

                JOIN BUS bus
                    ON b.bus_no = bus.bus_no

                WHERE b.booking_id = ?
                """;

        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bookingId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Bus bus = new Bus(
                            rs.getString("bus_no"),
                            rs.getInt("capacity"),
                            rs.getBoolean("is_ac"),
                            rs.getDouble("ticket_price"),
                            rs.getString("traveling_duration"),
                            rs.getString("driver_name"),
                            rs.getString("from_location"),
                            rs.getString("to_location")
                    );

                    return new Booking(
                            rs.getString("passenger_name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getDate("journey_date"),
                            bus,
                            rs.getString("booking_id"),
                            rs.getDouble("amount_paid")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean cancelBooking(String bookingId) {

        String sql = """
                DELETE FROM BOOKING
                WHERE booking_id = ?
                """;

        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bookingId);

            int rowsDeleted = ps.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean isBusAvailable(String busNo, java.util.Date journeyDate, int capacity) {
    		
    		String sql = """
                SELECT COUNT(*) AS booked_seats
                FROM BOOKING
                WHERE bus_no = ?
                AND journey_date = ?
                """;

        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, busNo);

            ps.setDate(2, new java.sql.Date(journeyDate.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    int bookedSeats = rs.getInt("booked_seats");
                    return bookedSeats < capacity;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}