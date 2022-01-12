package controller.MainController;

import db.DbConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class MealPlanController {
        public static String getLastMealPlanId() throws SQLException, ClassNotFoundException {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT mealPlanId FROM MealPlan ORDER BY mealPlanId DESC LIMIT 1");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("mealPlanId");
            }
            return null;
    }

    public BookingDetails getBooking(String id) throws SQLException, ClassNotFoundException {
            PreparedStatement pst = DbConnection.getInstance().getConnection()
                    .prepareStatement("SELECT * FROM BookingDetails WHERE bookingId=?");
            pst.setObject(1,id);

            ResultSet rst= pst.executeQuery();
            if (rst.next()){
                return new BookingDetails(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7),
                        rst.getDouble(8)
                );
            }else {
                return null;
            }
        }

        public List<String> getAllBookingIds() throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM BookingDetails").executeQuery();
            List<String> ids= new ArrayList<>();
            while (rst.next()){
                ids.add(
                        rst.getString(1)
                );
            }
            return ids;
        }

        public MealPackage getMealPackage(String id) throws SQLException, ClassNotFoundException {
            PreparedStatement pst = DbConnection.getInstance().getConnection()
                    .prepareStatement("SELECT * FROM MealPackage WHERE mealPackageId=?");
            pst.setObject(1,id);

            ResultSet rst= pst.executeQuery();
            if (rst.next()){
                return new MealPackage(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getDouble(5)
                );
            }else {
                return null;
            }
        }

        public List<String> getAllMealPackageIds() throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM MealPackage").executeQuery();
            List<String> ids= new ArrayList<>();
            while (rst.next()){
                ids.add(
                        rst.getString(1)
                );
            }
            return ids;
        }

        public List<String> getAllFoods() throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM Food").executeQuery();
            List<String> ids= new ArrayList<>();
            while (rst.next()){
                ids.add(
                        rst.getString(2)
                );
            }
            return ids;
        }

        public boolean addMealPlan(MealPlan mp) throws SQLException, ClassNotFoundException {
            //System.out.println(m.getRoomId());
            Connection con = DbConnection.getInstance().getConnection();
            String query= "INSERT INTO MealPlan VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst= con.prepareStatement(query);
            pst.setObject(1,mp.getMealPlanId());
            pst.setObject(2,mp.getMealPackageId());
            pst.setObject(3,mp.getBookingId());
            pst.setObject(4,mp.getGuestId());
            pst.setObject(5,mp.getMealPlanType());
            pst.setObject(6,mp.getRemarks());
            pst.setObject(7,mp.getMealPlanPrice());
            pst.setObject(8,mp.getExtraPrice());
            pst.setObject(9,mp.getTotal());
            return pst.executeUpdate()>0;
        }

        public static List<MealPlan> getAllMealPlan() throws SQLException, ClassNotFoundException {
            Connection con = DbConnection.getInstance().getConnection();

            PreparedStatement pst = con.prepareStatement("SELECT * FROM MealPlan");
            ResultSet rst= pst.executeQuery();

            List<MealPlan> mealPlans= new ArrayList<>();

            while (rst.next()){
                mealPlans.add(new MealPlan(
                        rst.getString("mealPlanId"),
                        rst.getString("mealPackageId"),
                        rst.getString("bookingId"),
                        rst.getString("guestId"),
                        rst.getString("mealPlanType"),
                        rst.getString("remarks"),
                        rst.getDouble("mealPlanPrice"),
                        rst.getDouble("extraPrice"),
                        rst.getDouble("total")
                ));
            }
            return mealPlans;
        }

        public static MealPlan getMealPlans(String id) throws SQLException, ClassNotFoundException {
            PreparedStatement pst= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM MealPlan WHERE mealPlanId=?");
            pst.setObject(1,id);

            ResultSet rst= pst.executeQuery();
            if (rst.next()){
                System.out.println(rst.getString(1));
                return new MealPlan(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getDouble(7),
                        rst.getDouble(8),
                        rst.getDouble(9)
                );
            }else{
                return  null;
            }
        }

        public boolean modifyMealPlan(MealPlan mlp) throws SQLException, ClassNotFoundException {
            Connection con = DbConnection.getInstance().getConnection();
            String query = "UPDATE MealPlan SET mealPackageId=?,bookingId=?,guestId=?,mealPlanType=?,remarks=?,total=? WHERE mealPlanId=?";

            PreparedStatement pst= con.prepareStatement(query);
            pst.setObject(1,mlp.getMealPackageId());
            pst.setObject(2,mlp.getBookingId());
            pst.setObject(3,mlp.getGuestId());
            pst.setObject(4,mlp.getMealPlanType());
            pst.setObject(5,mlp.getRemarks());
            pst.setObject(6,mlp.getTotal());
            pst.setObject(7,mlp.getMealPlanId());
            return pst.executeUpdate()>0;
        }

        public static boolean deleteMealPlan(String id) throws SQLException, ClassNotFoundException {
            Connection con = DbConnection.getInstance().getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM MealPlan WHERE mealPlanId=?");
            pst.setObject(1,id);
            return pst.executeUpdate()>0;
        }

}
