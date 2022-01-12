package controller;

import controller.MainController.FoodController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Food;
import views.tm.AddNewFoodTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodFormController {
    public AnchorPane addFoodAnchorPaneContext;
    public TextField txtFoodId;
    public TextField txtFoodType;
    public TextField txtQty;
    public TableView<AddNewFoodTm> tblFood;
    public TableColumn colQty;
    public TableColumn colFoodId;
    public TableColumn colFoodType;
    public TableColumn colDelete;

    ObservableList<AddNewFoodTm> tableData = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        loadTableData();
            //loadData();
        colFoodId.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        colFoodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        tblFood.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getTableData(newValue);
        });
    }

    public void onAddNewFood(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Food f = new Food(
                txtFoodId.getText(), txtFoodType.getText(), Integer.parseInt(txtQty.getText())
        );

        if (new FoodController().addFood(f)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add New Meal..").show();
            loadTableData();
           // loadData();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            Stage stage = (Stage) addFoodAnchorPaneContext.getScene().getWindow();
            stage.close();
        }
    }

   public void loadTableData() {
       try {

           List<Food> allFoods = FoodController.getAllFoods();

           ArrayList<AddNewFoodTm> foods = new ArrayList<>();
           for (Food f : allFoods) {
               Button delete = new Button("Delete");
               foods.add(new AddNewFoodTm(
                       f.getFoodId(),
                       f.getFoodType(),
                       f.getQty(),
                       delete

               ));
               tableData.clear();
               tableData.addAll(foods);
               tblFood.setItems(tableData);
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }

    }


    public void getTableData(AddNewFoodTm ft) {
        try {
            String id = tblFood.getSelectionModel().getSelectedItem().getFoodId();
            Food f = FoodController.getFood(id);
            if (ft == null) {
                new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
            } else {
                setData(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setData(Food f) {
        txtFoodId.setText(f.getFoodId());
        txtFoodType.setText(f.getFoodType());
        txtQty.setText(String.valueOf(f.getQty()));

    }

    public void onModifyFood(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Food f1 = new Food(
                txtFoodId.getText(), txtFoodType.getText(), Integer.parseInt(txtQty.getText())
        );

        if (new FoodController().modifyFood(f1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            loadTableData();
           // loadData();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
       /* try {
            String custId = tblCustomer.getSelectionModel().getSelectedItem().getCustId();
            boolean isDeleted = CustomerController.deleteCustomer(custId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully..").show();
                loadData();
            }else {
                new Alert(Alert.AlertType.ERROR,"Not Deleted..").show();
            }

        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
*/
    }
}


