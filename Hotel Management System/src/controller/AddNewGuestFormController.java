package controller;

import controller.MainController.GuestController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Guest;
import views.tm.AddNewGuestTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class AddNewGuestFormController {

    public TextField txtGuestId;
    public TextField txtGuestAddress;
    public TextField txtContact;
    public TextField txtGuestName;
    public TextField txtNIC;
    public TableView<AddNewGuestTm> tblGuest;
    public TableColumn colGuestId;
    public TableColumn colGuestName;
    public TableColumn colGuestAddress;
    public TableColumn colNIC;
    public TableColumn colContact;
    public AnchorPane addNewGuestAnchorPaneContext;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^[G][0]{2}[-][0-9]{3,20}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-Z  a-z -9 ]{3,}$");
    Pattern NICPattern = Pattern.compile("^[0-9]{9,}[V]$");
    Pattern contactPattern = Pattern.compile("^[0][7][1,2,4,5,6,7,8]{1}[0-9]{7}$");

    private void storeValidations() {
        map.put(txtGuestId,idPattern);
        map.put(txtGuestName, namePattern);
        map.put(txtGuestAddress,addressPattern);
        map.put(txtNIC,NICPattern);
        map.put(txtContact, contactPattern);
    }

    private Object validate() {
        //btnSaveBook.setDisable(true);

        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()){
                    textFieldKey.getParent().setStyle("-fx-border-color: red");
                }
                return textFieldKey;
            }
            textFieldKey.getParent().setStyle("-fx-border-color: green");
        }
        // btnSaveBook.setDisable(false);
        return true;
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        loadTableData();
        storeValidations();

        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colGuestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        colGuestAddress.setCellValueFactory(new PropertyValueFactory<>("guestAddress"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        tblGuest.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getTableData(newValue);
        });

    }



    ObservableList<AddNewGuestTm> tableData= FXCollections.observableArrayList();

    public void onAddNewGuest(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Guest g = new Guest(
                txtGuestId.getText(),txtGuestName.getText(),txtGuestAddress.getText(),txtNIC.getText(),txtContact.getText()
        );
        if (new GuestController().addGuest(g)){
            new Alert(Alert.AlertType.CONFIRMATION,"Add New Guest..").show();
            loadTableData();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            Stage stage = (Stage)addNewGuestAnchorPaneContext.getScene().getWindow();
            stage.close();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        List<Guest> allGuests = GuestController.getAllGuests();
        ArrayList<AddNewGuestTm> guests = new ArrayList<>();
        for (Guest g : allGuests){
            guests.add(new AddNewGuestTm(
                    g.getGuestId(),
                    g.getName(),
                    g.getAddress(),
                    g.getNic(),
                    g.getContact()
            ));
            tableData.clear();
            tableData.addAll(guests);
            tblGuest.setItems(tableData);
        }
    }

    public void getTableData(AddNewGuestTm ng){
        try{
            String id=tblGuest.getSelectionModel().getSelectedItem().getGuestId();
            Guest g1= GuestController.getGuest(id);
            if (ng==null){
                new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
            }else {
                setData(g1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setData(Guest g){
        txtGuestId.setText(g.getGuestId());
        txtGuestName.setText(g.getName());
        txtGuestAddress.setText(g.getAddress());
        txtNIC.setText(g.getNic());
        txtContact.setText(g.getContact());
    }

    public void modifyGuest(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        Guest g = new Guest(
                txtGuestId.getText(),txtGuestName.getText(),txtGuestAddress.getText(),txtNIC.getText(),txtContact.getText()
        );

        if (new GuestController().modifyGuest(g)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            loadTableData();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void deleteGuest(MouseEvent mouseEvent) {
        try {
            String id = tblGuest.getSelectionModel().getSelectedItem().getGuestId();
            boolean isDelete;
            isDelete = GuestController.deleteGuest(id);
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

    public void ValidationTwoKey(KeyEvent keyEvent) {
        Object response = validate();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void ValidationOneKey(KeyEvent keyEvent) {
        Object response = validate();

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}
