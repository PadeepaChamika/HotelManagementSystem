package controller;

import com.jfoenix.controls.JFXTextField;
import controller.MainController.BookingController;
import controller.MainController.MealPackageController;
import controller.MainController.MealPlanController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.*;
import views.tm.MealPlanTm;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MealPlanFormController {
    public ComboBox<String> cmbMealPlanType;
    public Label lblMealPlanId;
    public ComboBox<String> cmbBookingId;
    public JFXTextField txtRoomId;
    public JFXTextField txtGuestId;
    public JFXTextField txtCheckingInDate;
    public JFXTextField txtCheckingOutDate;
    public JFXTextField txtCheckingInTime;
    public JFXTextField txtCheckingOutTime;
    public JFXTextField txtCost;
    public ComboBox<String> cmbMealPackageId;
    public JFXTextField txtMealPackageName;
    public JFXTextField txtMealPackageType;
    public JFXTextField txtPrice;
    public TextField txtMealPlanPrice;
    public TextField txtRemarks;
    public TextField txtExtraPrice;
    public TableView<MealPlanTm> tblMealPlan;
    public TableColumn colBookingId;
    public TableColumn colMealPackageId;
    public TableColumn colMealPlanType;
    public TableColumn colRemarks;
    public TableColumn colTotalPrice;
    public ComboBox<String> cmbMeal;
    public TableColumn colMealPlanId;
    public Label lblTotalCost;
    public Label lblDates;

    ObservableList<MealPlanTm> tableData =FXCollections.observableArrayList();
    private static final ArrayList<String> MealPlanType = new ArrayList<>();

    static {
        MealPlanType.add("BB");
        MealPlanType.add("HB");
        MealPlanType.add("FB");
    }



    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList(MealPlanType);
        cmbMealPlanType.setItems(obList);

        lastMealPlanId();
        loadTableData();
        loadBookingIds();
        loadMealPackageIds();
        loadFood();

        colMealPlanId.setCellValueFactory(new PropertyValueFactory<>("mealPlanId"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colMealPackageId.setCellValueFactory(new PropertyValueFactory<>("mealPackageId"));
        colMealPlanType.setCellValueFactory(new PropertyValueFactory<>("mealPlanType"));
        colRemarks.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("total"));


        cmbBookingId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setBookingData( newValue);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });

        cmbMealPackageId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setMealPackageData( newValue);
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                });

        cmbMealPlanType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setMealPlanTypePriceData( newValue);
        });

        tblMealPlan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            getTableData(newValue);
        });



    }

    public void lastMealPlanId(){
        try {
            String mealPlanId = MealPlanController.getLastMealPlanId();
            String finalId = "MP-001";

            if (mealPlanId != null) {

                String[] splitString = mealPlanId.split("-");
                int id = Integer.parseInt(splitString[1]);
                id++;

                if (id < 10) {
                    finalId = "MP-00" + id;
                } else if (id < 100) {
                    finalId = "MP-0" + id;
                } else {
                    finalId = "MP-" + id;
                }
                lblMealPlanId.setText(finalId);
            } else {
                lblMealPlanId.setText(finalId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadBookingIds() throws SQLException, ClassNotFoundException {
        List<String> bookingIds = new MealPlanController().getAllBookingIds();
        cmbBookingId.getItems().addAll(bookingIds);
    }

    private void setBookingData(String bookingId) throws SQLException, ClassNotFoundException {
        BookingDetails bd1= new MealPlanController().getBooking(bookingId);
        if (bd1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtRoomId.setText(bd1.getRoomId());
            txtGuestId.setText(bd1.getGuestId());
            txtCheckingInDate.setText(bd1.getCheckingInDate());
            txtCheckingInTime.setText(bd1.getCheckingInTime());
            txtCheckingOutDate.setText(bd1.getCheckingOutDate());
            txtCheckingOutTime.setText(bd1.getCheckingOutTime());
            txtCost.setText(String.valueOf(bd1.getCost()));
        }
    }

    private void loadMealPackageIds() throws SQLException, ClassNotFoundException {
        List<String> mealPackageIds = new MealPlanController().getAllMealPackageIds();
        cmbMealPackageId.getItems().addAll(mealPackageIds);
    }

    private void setMealPackageData(String mealPackageId) throws SQLException, ClassNotFoundException {
        MealPackage mp1= new MealPlanController().getMealPackage(mealPackageId);
        if (mp1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtMealPackageName.setText(mp1.getMealPackageName());
            txtMealPackageType.setText(mp1.getMealPackageType());
            txtPrice.setText(String.valueOf(mp1.getPrice()));
        }
    }

    public  void setMealPlanTypePriceData(String mealType){
        if(mealType.equalsIgnoreCase("BB")){
            txtMealPlanPrice.setText("2000.00");
        }else if(mealType.equalsIgnoreCase("HB")){
            txtMealPlanPrice.setText("3000.00");
        }else if(mealType.equalsIgnoreCase("FB")){
            txtMealPlanPrice.setText("5000.00");
        }
    }

    private void loadFood() throws SQLException, ClassNotFoundException {
        List<String> mealIds = new MealPlanController().getAllFoods();
        cmbMeal.getItems().addAll(mealIds);
    }

    public void onAddMealPlan(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
       Double price1=Double.parseDouble( txtPrice.getText());
       Double price2=Double.parseDouble( txtMealPlanPrice.getText());
       Double price3=Double.parseDouble( txtExtraPrice.getText());
       Double total=price1+price2+price3;

       MealPlan m=new MealPlan(
               lblMealPlanId.getText(),
               cmbMealPackageId.getSelectionModel().getSelectedItem(),
               cmbBookingId.getSelectionModel().getSelectedItem(),
               txtGuestId.getText(),
               cmbMealPlanType.getSelectionModel().getSelectedItem(),
               cmbMeal.getSelectionModel().getSelectedItem(),
               Double.parseDouble(txtMealPlanPrice.getText()),
               Double.parseDouble(txtExtraPrice.getText()),total
       );

        if (new MealPlanController().addMealPlan(m)){
            new Alert(Alert.AlertType.CONFIRMATION,"Meal Plan Success..").show();
            loadTableData();
            lastMealPlanId();

        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        List<MealPlan> allMeals = MealPlanController.getAllMealPlan();
        ArrayList<MealPlanTm> mealPlanTms =new ArrayList<>();
        for (MealPlan m : allMeals){
            mealPlanTms.add(new MealPlanTm(
                    m.getMealPlanId(),
                    m.getMealPackageId(),
                    m.getBookingId(),
                    m.getGuestId(),
                    m.getMealPlanType(),
                    m.getRemarks(),
                    m.getMealPlanPrice(),
                    m.getExtraPrice(),
                    m.getTotal()
            ));
            tableData.clear();
            tableData.addAll(mealPlanTms);
            tblMealPlan.setItems(tableData);
        }
    }

    public void onModifyMealPlan(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Double price1=Double.parseDouble( txtPrice.getText());
        Double price2=Double.parseDouble( txtMealPlanPrice.getText());
        Double price3=Double.parseDouble( txtExtraPrice.getText());
        Double total=price1+price2+price3;

        MealPlan mp = new MealPlan(
                lblMealPlanId.getText(),cmbMealPackageId.getSelectionModel().getSelectedItem(),cmbBookingId.getSelectionModel().getSelectedItem(),
                txtGuestId.getText(),cmbMealPlanType.getSelectionModel().getSelectedItem(),cmbMeal.getSelectionModel().getSelectedItem(),
                Double.parseDouble(txtMealPlanPrice.getText()),Double.parseDouble(txtExtraPrice.getText()),total
        );

        if (new MealPlanController().modifyMealPlan(mp)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
            loadTableData();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void getTableData(MealPlanTm mpt){
        try{
            String id=tblMealPlan.getSelectionModel().getSelectedItem().getMealPlanId();
            String bookingId=tblMealPlan.getSelectionModel().getSelectedItem().getBookingId();
            String mealPackageId= tblMealPlan.getSelectionModel().getSelectedItem().getMealPackageId();

           MealPlan  mp= MealPlanController.getMealPlans(id);
            BookingDetails b1 = new BookingController().getBookings(bookingId);
           MealPackage mpk1 = new MealPackageController().getMealPackage(mealPackageId);
            if (mp==null){
                new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
            }else {
                setData(mp,b1,mpk1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    void setData(MealPlan m,BookingDetails bd,MealPackage mp){
        lblMealPlanId.setText(m.getMealPlanId());
        cmbBookingId.setValue(bd.getBookingId());
        txtRoomId.setText(bd.getRoomId());
        txtGuestId.setText(bd.getGuestId());
        txtCheckingInDate.setText(bd.getCheckingInDate());
        txtCheckingInTime.setText(bd.getCheckingInTime());
        txtCheckingOutDate.setText(bd.getCheckingOutDate());
        txtCheckingOutTime.setText(bd.getCheckingOutTime());
        txtCost.setText(String.valueOf(bd.getCost()));
        cmbMealPackageId.setValue(mp.getMealPackageId());
        txtMealPackageName.setText(mp.getMealPackageName());
        txtMealPackageType.setText(mp.getMealPackageType());
        txtPrice.setText(String.valueOf(mp.getPrice()));
        cmbMealPlanType.setValue(m.getMealPlanType());
        txtMealPlanPrice.setText(String.valueOf(m.getMealPlanPrice()));
        cmbMeal.setValue(m.getRemarks());
        txtExtraPrice.setText(String.valueOf(m.getExtraPrice()));
    }

    public void onRemoveMealPlan(MouseEvent mouseEvent) {
        try {
            String id = tblMealPlan.getSelectionModel().getSelectedItem().getMealPlanId();
            boolean isDelete;
            isDelete = MealPlanController.deleteMealPlan(id);
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

    public void CalculateDates(){

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkIn = txtCheckingInDate.getText();
        String checkOut = txtCheckingOutDate.getText();

        Double price1=Double.parseDouble( txtPrice.getText());
        Double price2=Double.parseDouble( txtMealPlanPrice.getText());
        Double price3=Double.parseDouble( txtExtraPrice.getText());
        Double total=price1+price2+price3;

        System.out.println(checkOut);
        System.out.println(checkIn);
        /*String inputString1 = "2021-09-07";
        String inputString2 = "2021-09-17";*/
        if ((!checkOut.equals("") && !checkIn.equals(""))) {
            try {
                Date date1 = myFormat.parse(checkIn);
                Date date2 = myFormat.parse(checkOut);
                long diff = date2.getTime() - date1.getTime();
                System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                String dates=(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
                lblDates.setText(String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)));
                int datesIn=Integer.parseInt(dates);
                Double tot=total*datesIn;
                lblTotalCost.setText(String.valueOf(tot));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void CalculatteTotal(ActionEvent actionEvent) {
        CalculateDates();
    }
}
