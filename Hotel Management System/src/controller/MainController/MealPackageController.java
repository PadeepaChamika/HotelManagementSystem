package controller.MainController;

import db.DbConnection;
import model.MealPackage;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPackageController {
    public boolean addMealPackage(MealPackage mpk) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query= "INSERT INTO MealPackage VALUES(?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(query);
        pst.setObject(1, mpk.getMealPackageId());
        pst.setObject(2, mpk.getMealPackageName());
        pst.setObject(3, mpk.getMealPackageType());
        pst.setObject(4, mpk.getDescription());
        pst.setObject(5, mpk.getPrice());
        return pst.executeUpdate()>0;
    }

    public static List<MealPackage> getAllMealPackages() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM MealPackage");
        ResultSet rst= pst.executeQuery();

        List<MealPackage> mealPackages= new ArrayList<>();

        while (rst.next()){
            mealPackages.add(new MealPackage(
                    rst.getString("mealPackageId"),
                    rst.getString("mealPackageName"),
                    rst.getString("mealPackageType"),
                    rst.getString("description"),
                    rst.getDouble("price")
            ));
        }
        return mealPackages;
    }

    public boolean modifyMealPackage(MealPackage mep) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "UPDATE MealPackage SET mealPackageName=?,mealPackageType=?,description=?,price=? WHERE mealPackageId=?";

        PreparedStatement pst= con.prepareStatement(query);
        pst.setObject(1,mep.getMealPackageName());
        pst.setObject(2,mep.getMealPackageType());
        pst.setObject(3,mep.getDescription());
        pst.setObject(4,mep.getPrice());
        pst.setObject(5,mep.getMealPackageId());
        return pst.executeUpdate()>0;
    }

    public static MealPackage getMealPackage(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement pst= DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM MealPackage WHERE mealPackageId=?");
        pst.setObject(1,id);

        ResultSet rst= pst.executeQuery();
        if (rst.next()){
            System.out.println(rst.getString(1));
            return new MealPackage(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );
        }else{
            return  null;
        }
    }

    public static boolean deleteMealPackage(String id) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM MealPackage WHERE mealPackageId=?");
        pst.setObject(1,id);
        return pst.executeUpdate()>0;
    }
}
