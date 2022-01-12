package controller;

import controller.MainController.MealPackageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MealPackage;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModifyMealPackageFormController {
    public AnchorPane modifyMealPackageAnchorPaneContext;
    public TextField txtMealPackageId;
    public ComboBox<String> cmbMealPackageName;
    public ComboBox<String> cmbMealPackageType;
    public TextField txtDescription;
    public TextField txtPrice;

    private static final ArrayList<String> MealPackageName = new ArrayList<>();

    static {
        MealPackageName.add("Local Meal");
        MealPackageName.add("Indian Meal");
        MealPackageName.add("American Meal");
    }

    private static final ArrayList<String> MealPackageType = new ArrayList<>();

    static {
        MealPackageType.add("Local");
        MealPackageType.add("International");
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList(MealPackageName);
        cmbMealPackageName.setItems(obList);

        ObservableList<String> objList = FXCollections.observableList(MealPackageType);
        cmbMealPackageType.setItems(objList);
    }

        public void onModifyMealPackageDetails(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        MealPackage mp1 = new MealPackage(
                txtMealPackageId.getText(),cmbMealPackageName.getSelectionModel().getSelectedItem(),cmbMealPackageType.getSelectionModel().getSelectedItem(),
                txtDescription.getText(),Double.parseDouble(txtPrice.getText())
        );

        if (new MealPackageController().modifyMealPackage(mp1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            Stage stage = (Stage)modifyMealPackageAnchorPaneContext.getScene().getWindow();
            stage.close();
        }
    }

    void setDate(MealPackage mp){
        txtMealPackageId.setText(mp.getMealPackageId());
        cmbMealPackageName.setValue(String.valueOf(mp.getMealPackageName()));
        cmbMealPackageType.setValue(String.valueOf(mp.getMealPackageType()));
        txtDescription.setText(mp.getDescription());
        txtPrice.setText(String.valueOf(mp.getPrice()));
    }

    public void onSetAllMealPackages(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id= txtMealPackageId.getText();

        MealPackage mp1 = new MealPackageController().getMealPackage(id);
        if(mp1==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setDate(mp1);
        }
    }

}
