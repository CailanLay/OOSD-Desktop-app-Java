
//Author:Harpreet kalsi

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.css.CSSValueList;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AboutBookingController implements Initializable {

    DBConnection helper = new DBConnection(); // Global object

    @FXML
    private Pane pnViewBooking;

    @FXML
    private Pane pnNewBooking;

    @FXML
    private Pane pnEditBookings;

    @FXML
    private Label lblBookingID;

    @FXML
    private Label lblBookingDate;

    @FXML
    private Label lblTripID;

    @FXML
    private Label lblBookingNO;

    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblPackageID;

    @FXML
    private Label lblTravelerCount;

    @FXML
    private Label lblBookingsTitle;

    @FXML
    private TextField txtBID;

    @FXML
    private TextField txtBDate;

    @FXML
    private TextField txtBNo;

    @FXML
    private TextField txtTCount;

    @FXML
    private TextField txtCId;

    @FXML
    private TextField txtTTypeId;

    @FXML
    private TextField txtPId;


    @FXML
    private Button btnBookingEdit;

    @FXML
    private Button btnBookingSave;

    @FXML
    private Button btnBookingBack;

    @FXML
    private Button btnAdd;

    private Bookings bookings = new Bookings();
    private boolean newBooking;

    // Controller constructor
    // Author: Harpreet kalsi
    public AboutBookingController(Bookings bookings) {
        this.bookings = bookings;
    }

    // Author: Harpreet kalsi
    public AboutBookingController(boolean newBooking) {
        this.newBooking = newBooking;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (newBooking == true) {
            loadnewBookingView();
        } else {
            loadLabeles();
        }
    }

        //loadBoxes();
        private void notEditable() {
        txtBID.setEditable(false);
        txtBDate.setEditable(false);
        txtBNo.setEditable(false);
        txtTCount.setEditable(false);
        txtCId.setEditable(false);
        txtTTypeId.setEditable(false);
        txtPId.setEditable(false);
    }

    private boolean notNull(Bookings bookings){
        if(bookings != null) {
            return true;
        } else {
            return false;
        }
    }

    private void loadnewBookingView() {
        pnNewBooking.toFront();
        pnNewBooking.setVisible(true);
        pnEditBookings.toBack();
        pnEditBookings.setVisible(false);
        pnViewBooking.toBack();
        pnViewBooking.setVisible(false);
    }

    private void loadLabeles() {
        pnEditBookings.toBack();
        pnEditBookings.setVisible(false);
        pnNewBooking.toBack();
        pnNewBooking.setVisible(false);
        pnViewBooking.toFront();
        pnViewBooking.setVisible(true);
        btnBookingBack.setText("Done");
        notEditable();
        if(notNull(bookings)) {
            lblBookingID.setText(String.valueOf(bookings.getBookingId()));
            lblBookingDate.setText(String.valueOf(bookings.getDate()));
            lblBookingNO.setText(String.valueOf(bookings.getBookingNo()));
            lblTravelerCount.setText(String.valueOf(bookings.getTravelerCount()));
            lblCustomerID.setText(String.valueOf(bookings.getCustomerId()));
            lblTripID.setText(bookings.getTripTypeId());
            lblPackageID.setText(String.valueOf(bookings.getPackageId()));
        }
    }


    // Author: Harpreet kalsi
    // populates the text fields
    private void loadBoxes() {
        if (notNull(bookings )) {
            txtBID.setText(String.valueOf(bookings.getBookingId()));
            txtBDate.setText(bookings.getDate());
            txtBNo.setText(String.valueOf(bookings.getBookingNo()));
            txtTCount.setText(String.valueOf(bookings.getTravelerCount()));
            txtCId.setText(String.valueOf(bookings.getCustomerId()));
            txtTTypeId.setText(String.valueOf(bookings.getTripTypeId()));
            txtPId.setText(String.valueOf(bookings.getPackageId()));
        }
    }

    // Author: Harpreet kalsi
    @FXML
    void onActionBtnBookingBack(ActionEvent event) throws IOException {
        if(pnEditBookings.isVisible() && !pnViewBooking.isVisible()) {
            btnBookingBack.setText("Back");
            loadLabeles();
        } else {
            Stage stage = (Stage) btnBookingBack.getScene().getWindow();
            stage.close();
        }
    }

    // Author: Harpreet kalsi
    @FXML
    void onActionBtnBookingEdit(ActionEvent event) {
        pnEditBookings.toFront();
        pnEditBookings.setVisible(true);
        pnNewBooking.toBack();
        pnNewBooking.setVisible(false);
        pnViewBooking.toBack();
        pnViewBooking.setVisible(false);
        loadBoxes();
        txtBID.setEditable(true);
        txtBDate.setEditable(true);
        txtBNo.setEditable(true);
        txtTCount.setEditable(true);
        txtCId.setEditable(false);
        txtTTypeId.setEditable(false);
        txtPId.setEditable(false);
        btnBookingEdit.setDisable(true);
        btnBookingSave.setDisable(false);
    }

    // Author: Harpreet kalsi
    @FXML
    void onActionBtnBookingSave(ActionEvent event) throws SQLException, IOException {
        if (Validator.validateBooking(txtBID.getText(), txtBDate.getText(), txtBNo.getText(), txtTCount.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " UPDATE `bookings` SET `BookingNo`=?, `BookingDate`=?,`TravelerCount`=?,`CustomerId`=?,`TripTypeId`=?," +
                    "`PackageId`=? WHERE `BookingId`=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, String.valueOf(txtBNo.getText()));
            bookings.setBookingNo(txtBNo.getText());
            stmt.setString(2, String.valueOf(txtBDate.getText()));
            bookings.setDate(String.valueOf(txtBDate.getText()));
            stmt.setInt(3, Integer.parseInt(txtTCount.getText()));
            bookings.setTravelerCount(Integer.valueOf(txtTCount.getText()));
            stmt.setInt(4, Integer.parseInt(txtCId.getText()));
            bookings.setCustomerId(Integer.valueOf(txtCId.getText()));
            stmt.setString(5, String.valueOf(txtTTypeId.getText()));
            bookings.setTripTypeId(txtTTypeId.getText());
            stmt.setInt(6, Integer.parseInt(txtPId.getText()));
            bookings.setPackageId(Integer.valueOf(txtPId.getText()));
            stmt.setInt(7, Integer.parseInt(txtBID.getText()));
            btnBookingSave.setDisable(true);
            btnBookingEdit.setDisable(false);

            int rows = stmt.executeUpdate();
            connection.close();
            if (rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
                alert.show();
            }
            Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene aboutScene = new Scene(aboutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(aboutScene);
            window.show();
        }
    }

    // Author: Harpreet kalsi
    // Action handler for the add new booking button
    @FXML
    void onActionbtnBookingAdd(ActionEvent event) throws IOException, SQLException {
        if (Validator.validateBooking(txtBID.getText(), txtBDate.getText(), txtBNo.getText(), txtTCount.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " INSERT INTO `bookings` (BookingNo, BookingDate,TravelerCount,CustomerId,TripTypeId, PackageId,BookingId) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, String.valueOf(txtBNo.getText()));
            bookings.setBookingNo(txtBNo.getText());
            stmt.setString(2, String.valueOf(txtBDate.getText()));
            bookings.setBookingId(Integer.valueOf(txtBDate.getText()));
            stmt.setInt(3, Integer.parseInt(txtTCount.getText()));
            bookings.setTravelerCount(Integer.valueOf(txtTCount.getText()));
            stmt.setInt(4, Integer.parseInt(txtCId.getText()));
            bookings.setCustomerId(Integer.valueOf(txtCId.getText()));
            stmt.setString(5, String.valueOf(txtTTypeId.getText()));
            bookings.setTripTypeId(txtTTypeId.getText());
            stmt.setInt(6, Integer.parseInt(txtPId.getText()));
            bookings.setPackageId(Integer.valueOf(txtPId.getText()));
            stmt.setInt(7, Integer.parseInt(txtBID.getText()));
            bookings.setBookingId(Integer.valueOf(txtBID.getText()));
            btnBookingSave.setDisable(true);
            btnBookingEdit.setDisable(false);
            int rows = stmt.executeUpdate();
            // connection.close();
            if (rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding Booking, contact tech support", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "New Booking Added", ButtonType.OK);
                alert.show();
            }
            loadLabeles();
        }   else {
            loadLabeles();
        }


    }
}


















