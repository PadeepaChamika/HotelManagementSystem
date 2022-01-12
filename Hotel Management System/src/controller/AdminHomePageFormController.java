package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminHomePageFormController {
    public AnchorPane tableAnchorPaneContext;
    public AnchorPane adminHomeAnchorPaneContext;

    public void onAddNewEmployee(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageEmployeeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onAdd_Modify_DeleteRoom(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/RoomForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onAdd_Modify_DeleteMealPackage(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/MealPackageForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onAdd_Modify_Delete_Meal(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/FoodForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onIncomeReport(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/IncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onBackToDashBoard(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) adminHomeAnchorPaneContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DashBoardForm.fxml"))));
    }
}
