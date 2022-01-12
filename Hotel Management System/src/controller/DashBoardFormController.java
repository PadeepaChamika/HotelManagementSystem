package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane homePageAnchorPaneContext;

    public void loginHomePage(MouseEvent mouseEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Admin") && txtPassword.getText().equals("A1234")) {
            URL resource = getClass().getResource("../views/AdminHomePageForm.fxml");
            Parent load = FXMLLoader.load(resource);
            homePageAnchorPaneContext.getChildren().clear();
            homePageAnchorPaneContext.getChildren().add(load);
        } else if (txtUserName.getText().equalsIgnoreCase("Receptionist") && txtPassword.getText().equals("R1234")) {
            URL resource = getClass().getResource("../views/ReceptionistHomePageForm.fxml");
            Parent load = FXMLLoader.load(resource);
            homePageAnchorPaneContext.getChildren().clear();
            homePageAnchorPaneContext.getChildren().add(load);
        }
    }

}
