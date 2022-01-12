package controller;

import controller.MainController.MealPackageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MealPackage;
import views.tm.AddNewMealPackageTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPackageFormController {
    public AnchorPane addNewMealPackageAnchorPaneContext;
    public TextField txtMealPackageId;
    public ComboBox<String> cmbMealPackageType;
    public TextField txtDescription;
    public TableView<AddNewMealPackageTm> tblMealPackage;
    public TableColumn colMealPackageId;
    public TableColumn colMealPackageName;
    public TableColumn colMealPackageType;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TextField txtPrice;
    public ComboBox<String> cmbMealPackageName;

    ObservableList<AddNewMealPackageTm> tableData = FXCollections.observableArrayList();

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

        loadTableData();

        colMealPackageId.setCellValueFactory(new PropertyValueFactory<>("mealPackageId"));
        colMealPackageName.setCellValueFactory(new PropertyValueFactory<>("mealPackageName"));
        colMealPackageType.setCellValueFactory(new PropertyValueFactory<>("mealPackageType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblMealPackage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getTableData(newValue);
        });

    }

    public void onAddNewMealPackage(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        MealPackage mp =new MealPackage(
                txtMealPackageId.getText(),cmbMealPackageName.getSelectionModel().getSelectedItem(),
                cmbMealPackageType.getSelectionModel().getSelectedItem(),txtDescription.getText(),Double.parseDouble(txtPrice.getText())
        );

        if (new MealPackageController().addMealPackage(mp)){
            new Alert(Alert.AlertType.CONFIRMATION,"Add New Meal Package ..").show();
            loadTableData();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            Stage stage = (Stage)addNewMealPackageAnchorPaneContext.getScene().getWindow();
            stage.close();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        List<MealPackage> allMealPackages = MealPackageController.getAllMealPackages();
        ArrayList<AddNewMealPackageTm> mealPackages =new ArrayList<>();
        for (MealPackage mp : allMealPackages){
            mealPackages.add(new AddNewMealPackageTm(
                    mp.getMealPackageId(),
                    mp.getMealPackageName(),
                    mp.getMealPackageType(),
                    mp.getDescription(),
                    mp.getPrice()
            ));
            tableData.clear();
            tableData.addAll(mealPackages);
            tblMealPackage.setItems(tableData);
        }
    }

    public void getTableData(AddNewMealPackageTm mpt){
        try{
            String id=tblMealPackage.getSelectionModel().getSelectedItem().getMealPackageId();
           MealPackage mp= MealPackageController.getMealPackage(id);
            if (mpt==null){
                new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
            }else {
                setData(mp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setData(MealPackage mp){
        txtMealPackageId.setText(mp.getMealPackageId());
        cmbMealPackageName.setValue(String.valueOf(mp.getMealPackageName()));
        cmbMealPackageType.setValue(String.valueOf(mp.getMealPackageType()));
        txtDescription.setText(mp.getDescription());
        txtPrice.setText(String.valueOf(mp.getPrice()));
    }

    public void onModifyMealPackage(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        MealPackage mp1 = new MealPackage(
                txtMealPackageId.getText(),cmbMealPackageName.getSelectionModel().getSelectedItem(),cmbMealPackageType.getSelectionModel().getSelectedItem(),
                txtDescription.getText(),Double.parseDouble(txtPrice.getText())
        );

        if (new MealPackageController().modifyMealPackage(mp1)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            loadTableData();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void deleteMealPackage(MouseEvent mouseEvent) {
        try {
            String id = tblMealPackage.getSelectionModel().getSelectedItem().getMealPackageId();
            boolean isDelete;
            isDelete = MealPackageController.deleteMealPackage(id);
            if (isDelete){
                new Alert(Alert.AlertType.INFORMATION,"Done..").show();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error..").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
