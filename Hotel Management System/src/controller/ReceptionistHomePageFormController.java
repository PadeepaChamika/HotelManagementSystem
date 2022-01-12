package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ReceptionistHomePageFormController {
    public AnchorPane tableAnchorPaneContext;
    public AnchorPane ReceptionistHomeAnchorPaneContext;

    public void onAddNewGuest(MouseEvent mouseEvent) throws IOException {
       URL resource = getClass().getResource("../views/AddNewGuestForm.fxml");
       Parent load = FXMLLoader.load(resource);
       tableAnchorPaneContext.getChildren().clear();
       tableAnchorPaneContext.getChildren().add(load);
   }

    public void onAddBooking(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/BookingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onMealPlan(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/MealPlanForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onPayment(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/PaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onRoomDetails(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../views/RoomDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        tableAnchorPaneContext.getChildren().clear();
        tableAnchorPaneContext.getChildren().add(load);
    }

    public void onBackToDashBoard(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) ReceptionistHomeAnchorPaneContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/DashBoardForm.fxml"))));
    }

}
