package controller;

import controller.MainController.TableLoadController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AvailableRoom;
import model.BookingDetails;
import views.tm.AvailableRoomTm;
import views.tm.BookingDetailsTm;

import java.sql.SQLException;
import java.util.List;

public class RoomDetailsFormController {
    public TableView<AvailableRoomTm> tblAvailableRooms;
    public TableColumn colRoomId;
    public TableColumn colRoomName;
    public TableColumn colRoomType;
    public TableColumn colFloor;
    public TableColumn colPrice;

    public TextField txtSearchAvailable;
    public TableView tblBookingRooms;
    public TableColumn colBookingId;
    public TableColumn colBookingRoomId;
    public TableColumn colGuestId;
    public TableColumn colCheckingInDate;
    public TableColumn colCheckingInTime;
    public TableColumn colCheckingOutDate;
    public TableColumn colCheckingOutTime;
    public TableColumn colCost;
    public TextField txtSearchBooking;

    public void initialize(){
        loadTableData();

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        //------------------------------------------------------------
        loadBookingTableData();

        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colBookingRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        colCheckingInDate.setCellValueFactory(new PropertyValueFactory<>("checkingInDate"));
        colCheckingInTime.setCellValueFactory(new PropertyValueFactory<>("checkingInTime"));
        colCheckingOutDate.setCellValueFactory(new PropertyValueFactory<>("checkingOutDate"));
        colCheckingOutTime.setCellValueFactory(new PropertyValueFactory<>("checkingOutTime"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tblAvailableRooms.getItems().setAll(loadTableData());
        tblBookingRooms.getItems().setAll(loadBookingTableData());


        txtSearchAvailable.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        txtSearchBooking.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search1(newValue);
            }
        });
    }

    public ObservableList<AvailableRoomTm> loadTableData() {
        try {

            List<AvailableRoom> allRooms = TableLoadController.getAllRooms();
            ObservableList<AvailableRoomTm> tableData = FXCollections.observableArrayList();
            for (AvailableRoom ab : allRooms) {
                tableData.add(new AvailableRoomTm(
                        ab.getRoomId(),
                        ab.getRoomName(),
                        ab.getRoomType(),
                        ab.getFloor(),
                        ab.getPrice()
                ));
            }

            return tableData;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public ObservableList<BookingDetailsTm> loadBookingTableData() {
        try {

            List<BookingDetails> allBookings = TableLoadController.getAllBookings();
            ObservableList<BookingDetailsTm> tableData = FXCollections.observableArrayList();
            for (BookingDetails bd : allBookings) {
                tableData.add(new BookingDetailsTm(
                        bd.getBookingId(),
                        bd.getRoomId(),
                        bd.getGuestId(),
                        bd.getCheckingInDate(),
                        bd.getCheckingInTime(),
                        bd.getCheckingOutDate(),
                        bd.getCheckingOutTime(),
                        bd.getCost()
                ));
            }

            return tableData;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public void searchAvailableOnAction(ActionEvent actionEvent) {
        search(txtSearchAvailable.getText());
    }

    public void searchBookingOnAction(ActionEvent actionEvent) {
        search1(txtSearchBooking.getText());
    }

    private void search(String value){
        try {

            List<AvailableRoom> allRooms = TableLoadController.searchAvailableRoom(value);
            ObservableList<AvailableRoomTm> tableData = FXCollections.observableArrayList();
            for (AvailableRoom ab : allRooms) {
                tableData.add(new AvailableRoomTm(
                        ab.getRoomId(),
                        ab.getRoomName(),
                        ab.getRoomType(),
                        ab.getFloor(),
                        ab.getPrice()
                ));
            }

            tblAvailableRooms.setItems(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void search1(String value){
        try {

            List<BookingDetails> allBookings = TableLoadController.searchBookingDetails(value);
            ObservableList<BookingDetailsTm> tableData = FXCollections.observableArrayList();
            for (BookingDetails bd : allBookings) {
                tableData.add(new BookingDetailsTm(
                        bd.getBookingId(),
                        bd.getRoomId(),
                        bd.getGuestId(),
                        bd.getCheckingInDate(),
                        bd.getCheckingInTime(),
                        bd.getCheckingOutDate(),
                        bd.getCheckingOutTime(),
                        bd.getCost()
                ));
            }

            tblBookingRooms.setItems(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
