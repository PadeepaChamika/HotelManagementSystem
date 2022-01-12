package controller;

import controller.MainController.RoomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AvailableRoom;
import model.Room;
import views.tm.AddNewRoomTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomFormController {
    public TextField txtRoomId;
    public ComboBox<String> cmbRoomType;
    public ComboBox<String> cmbFloor;
    public TextField txtDescription;
    public TableView<AddNewRoomTm> tblAddNewRoomDetails;
    public TableColumn colRoomId;
    public TableColumn colRoomName;
    public TableColumn colRoomType;
    public TableColumn colFloor;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TextField txtPrice;

    public RadioButton rbSingle;
    public ToggleGroup tgRoomName;
    public RadioButton rbDouble;
    public RadioButton rbTriple;
    public AnchorPane addNewRoomAnchorPaneContext;

    ObservableList<AddNewRoomTm> tableData =FXCollections.observableArrayList();
    private static final ArrayList<String> RoomType = new ArrayList<>();

    static {
        RoomType.add("Beach Side AC");
        RoomType.add("Beach Side Non AC");
        RoomType.add("Land Side AC");
        RoomType.add("Land Side Non AC");
    }

    private static final ArrayList<String> Floor = new ArrayList<>();

    static {
        Floor.add("First Floor");
        Floor.add("Second Floor");
        Floor.add("Third Floor");
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList(RoomType);
        cmbRoomType.setItems(obList);

        ObservableList<String> objList = FXCollections.observableList(Floor);
        cmbFloor.setItems(objList);

        loadTableData();

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblAddNewRoomDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getTableData(newValue);
        });

    }


    public void onAddNewRoom(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        RadioButton selectedRadioButton= (RadioButton)tgRoomName.getSelectedToggle();
        //System.out.println(txtRoomId.getText());
        Room r = new Room(
                txtRoomId.getText(),selectedRadioButton.getText(),cmbRoomType.getSelectionModel().getSelectedItem(),cmbFloor.getSelectionModel().getSelectedItem(),txtDescription.getText(),Double.parseDouble(txtPrice.getText())
        );

        AvailableRoom ar = new AvailableRoom(
                txtRoomId.getText(),selectedRadioButton.getText(),cmbRoomType.getSelectionModel().getSelectedItem(),cmbFloor.getSelectionModel().getSelectedItem(),txtDescription.getText(),Double.parseDouble(txtPrice.getText())
        );


        if (new RoomController().addRoom(r,ar)){
            new Alert(Alert.AlertType.CONFIRMATION,"Add New Room..").show();
            loadTableData();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            Stage stage = (Stage)addNewRoomAnchorPaneContext.getScene().getWindow();
            stage.close();
        }


    }

   public void loadTableData() throws SQLException, ClassNotFoundException {
        List<Room> allRooms = RoomController.getAllRooms();
        ArrayList<AddNewRoomTm> rooms =new ArrayList<>();
        for (Room r : allRooms){
            rooms.add(new AddNewRoomTm(
                    r.getRoomId(),
                    r.getRoomName(),
                    r.getRoomType(),
                    r.getFloor(),
                    r.getDescription(),
                    r.getPrice()
            ));
            tableData.clear();
            tableData.addAll(rooms);
            tblAddNewRoomDetails.setItems(tableData);
        }
    }

    public void getTableData(AddNewRoomTm rt){
        try{
            String id=tblAddNewRoomDetails.getSelectionModel().getSelectedItem().getRoomId();
            Room r= RoomController.getRooms(id);
            if (rt==null){
                new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
            }else {
                setData(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setData(Room r){
        txtRoomId.setText(r.getRoomId());
        //txtRoomName.setText(r.getRoomName());
        rbSingle.setAccessibleText(r.getRoomName());
        rbDouble.setAccessibleText(r.getRoomName());
        rbTriple.setAccessibleText(r.getRoomName());
        cmbRoomType.setValue(r.getRoomType());
        cmbFloor.setValue(r.getFloor());
        txtDescription.setText(r.getDescription());
        txtPrice.setText(String.valueOf(r.getPrice()));
    }

    public void onModifyRoom(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        RadioButton selectedRadioButton= (RadioButton)tgRoomName.getSelectedToggle();
        Room r1 =new Room(
                txtRoomId.getText(),selectedRadioButton.getText(),cmbRoomType.getSelectionModel().getSelectedItem(),cmbFloor.getSelectionModel().getSelectedItem(),txtDescription.getText(),Double.parseDouble(txtPrice.getText())
        );

        if (new RoomController().modifyRoom(r1)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            loadTableData();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void onDeleteRoom(MouseEvent mouseEvent) {
        try {
            String id = tblAddNewRoomDetails.getSelectionModel().getSelectedItem().getRoomId();
            boolean isDelete;
            isDelete = RoomController.deleteRoom(id);
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
