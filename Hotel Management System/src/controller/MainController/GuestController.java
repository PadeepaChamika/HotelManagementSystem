package controller.MainController;

import db.DbConnection;
import model.BookingDetails;
import model.Employee;
import model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestController {
    public boolean addGuest(Guest gu) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Guest VALUES(?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setObject(1,gu.getGuestId());
        pst.setObject(2,gu.getName());
        pst.setObject(3,gu.getAddress());
        pst.setObject(4,gu.getNic());
        pst.setObject(5,gu.getContact());
        return pst.executeUpdate()>0;
    }

    public static List<Guest> getAllGuests() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT * FROM Guest");
        ResultSet rst = pst.executeQuery();
         List<Guest> guest= new ArrayList<>();

         while (rst.next()){
             guest.add(new Guest(
                     rst.getString("guestId"),
                     rst.getString("guestName"),
                     rst.getString("address"),
                     rst.getString("nic"),
                     rst.getString("contact")
             ));
         }
         return guest;
    }

    public boolean modifyGuest(Guest gs) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Guest SET guestName=?,address=?,nic=?,contact=? WHERE guestId=?");
        pst.setObject(1,gs.getName());
        pst.setObject(2,gs.getAddress());
        pst.setObject(3,gs.getNic());
        pst.setObject(4,gs.getContact());
        pst.setObject(5,gs.getGuestId());
        return pst.executeUpdate()>0;
    }

    public static Guest getGuest(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Guest WHERE guestId=?");
        pst.setObject(1,id);

        ResultSet rst= pst.executeQuery();
        if (rst.next()){
            return new Guest(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }else {
            return null;
        }
    }

    public static boolean deleteGuest(String id) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM Guest WHERE guestId=?");
        pst.setObject(1,id);
        return pst.executeUpdate()>0;
    }
}
