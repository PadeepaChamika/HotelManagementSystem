package controller.MainController;

import db.DbConnection;
import model.AvailableRoom;
import model.BookingDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableLoadController {
    public static List<AvailableRoom> getAllRooms() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM AvailableRoom");
        ResultSet rs = pst.executeQuery();

        List<AvailableRoom> Rooms = new ArrayList<>();

        while (rs.next()) {
            Rooms.add(new AvailableRoom(
                    rs.getString("roomId"),
                    rs.getString("roomName"),
                    rs.getString("roomType"),
                    rs.getString("floor"),
                    rs.getString("description"),
                    rs.getDouble("price")
            ));
        }

        return Rooms;
    }

    public static List<AvailableRoom> searchAvailableRoom(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM AvailableRoom WHERE roomName LIKE '%"+value+"%'");
        ResultSet rs = pst.executeQuery();

        List<AvailableRoom> rooms = new ArrayList<>();

        while (rs.next()) {
            rooms.add(new AvailableRoom(
                    rs.getString("roomId"),
                    rs.getString("roomName"),
                    rs.getString("roomType"),
                    rs.getString("floor"),
                    rs.getString("description"),
                    rs.getDouble("price")
            ));
        }

        return rooms;
    }
    //-----------------------------------------------------------------

    public static List<BookingDetails> getAllBookings() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM BookingDetails");
        ResultSet rs = pst.executeQuery();

        List<BookingDetails> Bookings = new ArrayList<>();

        while (rs.next()) {
            Bookings.add(new BookingDetails(
                    rs.getString("bookingId"),
                    rs.getString("roomId"),
                    rs.getString("guestId"),
                    rs.getString("checkingIn"),
                    rs.getString("checkingInTime"),
                    rs.getString("checkingOut"),
                    rs.getString("checkingOutTime"),
                    rs.getDouble("cost")
            ));
        }

        return Bookings;
    }

    public static List<BookingDetails> searchBookingDetails(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM BookingDetails WHERE guestId LIKE '%"+value+"%'");
        ResultSet rs = pst.executeQuery();

        List<BookingDetails> bookings = new ArrayList<>();

        while (rs.next()) {
            bookings.add(new BookingDetails(
                    rs.getString("bookingId"),
                    rs.getString("roomId"),
                    rs.getString("guestId"),
                    rs.getString("checkingIn"),
                    rs.getString("checkingInTime"),
                    rs.getString("checkingOut"),
                    rs.getString("checkingOutTime"),
                    rs.getDouble("cost")
            ));
        }

        return bookings;
    }
}
