package controller.MainController;

import db.DbConnection;
import model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodController {
    public boolean addFood(Food f1) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Food VALUES(?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setObject(1,f1.getFoodId());
        pst.setObject(2,f1.getFoodType());
        pst.setObject(3,f1.getQty());
        return pst.executeUpdate()>0;
    }

    public static List<Food> getAllFoods() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pst= con.prepareStatement("SELECT * FROM Food");
        ResultSet rst = pst.executeQuery();

        List<Food> foods = new ArrayList<>();

        while (rst.next()){
            foods.add(new Food(
                    rst.getString("foodId"),
                    rst.getString("foodType"),
                    rst.getInt("qty")
            ));
        }
        return foods;
    }

    public boolean modifyFood(Food mf) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Food SET foodType=?,qty=? WHERE foodId=?");
        pst.setObject(1,mf.getFoodType());
        pst.setObject(2,mf.getQty());
        pst.setObject(3,mf.getFoodId());
        return pst.executeUpdate()>0;
    }

    public static Food getFood(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Food WHERE foodId=?");
        pst.setObject(1,id);

        ResultSet rst= pst.executeQuery();
        if (rst.next()){
            return new Food(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
        }else {
            return null;
        }
    }

    public static boolean deleteFood(String id) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM Food WHERE foodId=?");
        pst.setObject(1,id);
        return pst.executeUpdate()>0;
    }

}
