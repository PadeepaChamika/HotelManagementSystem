package controller.MainController;

import db.DbConnection;
import model.AvailableRoom;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomController {
    //---------------  Add New Room ---------------------
    public boolean addRoom(Room m, AvailableRoom a) throws SQLException, ClassNotFoundException {
        //System.out.println(m.getRoomId());
       /* Connection con = DbConnection.getInstance().getConnection();
        String query= "INSERT INTO Room VALUES(?,?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(query);
        pst.setObject(1,m.getRoomId());
        pst.setObject(2,m.getRoomName());
        pst.setObject(3,m.getRoomType());
        pst.setObject(4,m.getFloor());
        pst.setObject(5,m.getDescription());
        pst.setObject(6,m.getPrice());
        return pst.executeUpdate()>0;
*/
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement pst = con.
                    prepareStatement("INSERT INTO Room VALUES(?,?,?,?,?,?)");

            pst.setObject(1, m.getRoomId());
            pst.setObject(2, m.getRoomName());
            pst.setObject(3, m.getRoomType());
            pst.setObject(4, m.getFloor());
            pst.setObject(5, m.getDescription());
            pst.setObject(6, m.getPrice());
            if (pst.executeUpdate() > 0) {
                if (new RoomController().addARoom(a)) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }



    public boolean addARoom(AvailableRoom a) throws SQLException, ClassNotFoundException {
        //System.out.println(m.getRoomId());
        Connection con = DbConnection.getInstance().getConnection();
        String query= "INSERT INTO AvailableRoom VALUES(?,?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(query);
        pst.setObject(1,a.getRoomId());
        pst.setObject(2,a.getRoomName());
        pst.setObject(3,a.getRoomType());
        pst.setObject(4,a.getFloor());
        pst.setObject(5,a.getDescription());
        pst.setObject(6,a.getPrice());
        return pst.executeUpdate()>0;
        }


    public static List<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM Room");
        ResultSet rst= pst.executeQuery();

        List<Room> rooms= new ArrayList<>();

        while (rst.next()){
            rooms.add(new Room(
                    rst.getString("roomId"),
                    rst.getString("roomName"),
                    rst.getString("roomType"),
                    rst.getString("floor"),
                    rst.getString("description"),
                    rst.getDouble("price")
            ));
        }
        return rooms;
    }

    //---------------- Modify Room -------------------------
    public boolean modifyRoom(Room mr) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "UPDATE Room SET roomName=?,roomType=?,floor=?,description=?,price=? WHERE roomId=?";

        PreparedStatement pst= con.prepareStatement(query);
        pst.setObject(1,mr.getRoomName());
        pst.setObject(2,mr.getRoomType());
        pst.setObject(3,mr.getFloor());
        pst.setObject(4,mr.getDescription());
        pst.setObject(5,mr.getPrice());
        pst.setObject(6,mr.getRoomId());
        return pst.executeUpdate()>0;
    }

    public static Room getRooms(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement pst= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Room WHERE roomId=?");
        pst.setObject(1,Id);

        ResultSet rst= pst.executeQuery();
        if (rst.next()){
            return new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)
            );
        }else{
            return  null;
        }
    }

    public List<String> getAllRoomsIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Room").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public static boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM Room WHERE roomId=?");
        pst.setObject(1,id);
        return pst.executeUpdate()>0;
    }
}
