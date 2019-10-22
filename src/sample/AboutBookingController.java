
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

    // Controller constructor
    public AboutBookingController(Bookings bookings) {
        this.bookings = bookings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert btnBookingSave != null : "fx:id=\"btnBookingSave\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert btnBookingEdit != null : "fx:id=\"btnBookingEdit\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtBID != null : "fx:id=\"txtBID\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtBDate != null : "fx:id=\"txtBDate\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtBNo != null : "fx:id=\"txtBNo\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtTCount != null : "fx:id=\"txtTCount\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtCId != null : "fx:id=\"txtCId\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtTTypeId != null : "fx:id=\"txtTTypeId\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert txtPId != null : "fx:id=\"txtPId\" was not injected: check your FXML file 'about_booking.fxml'.";
        assert btnBookingBack != null : "fx:id=\"btnBookingBack\" was not injected: check your FXML file 'about_booking.fxml'.";
        loadBoxes();

        txtBID.setEditable(false);
        txtBDate.setEditable(false);
        txtBNo.setEditable(false);
        txtTCount.setEditable(false);
        txtCId.setEditable(false);
        txtTTypeId.setEditable(false);
        txtPId.setEditable(false);
    }

    // populates the text fields
    private void loadBoxes() {
        if (bookings != null) {
            txtBID.setText(String.valueOf(bookings.getBookingId()));
            txtBDate.setText(bookings.getDate());
            txtBNo.setText(String.valueOf(bookings.getBookingNo()));
            txtTCount.setText(String.valueOf(bookings.getTravelerCount()));
            txtCId.setText(String.valueOf(bookings.getCustomerId()));
            txtTTypeId.setText(String.valueOf(bookings.getTripTypeId()));
            txtPId.setText(String.valueOf(bookings.getPackageId()));
        }
    }


    @FXML
    void onActionBtnBookingBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    @FXML
    void onActionBtnBookingEdit(ActionEvent event) {
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

    @FXML
    void onActionBtnBookingSave(ActionEvent event) throws SQLException, IOException {
        if (Validator.validateBooking(txtBID.getText(), txtBDate.getText(), txtBNo.getText(), txtTCount.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " UPDATE `bookings` SET `BookingNo`=?, `BookingDate`=?,`TravelerCount`=?,`CustomerId`=?,`TripTypeId`=?," +
                    "`PackageId`=? WHERE `BookingId`=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, String.valueOf(txtBNo.getText()));
            stmt.setString(2, String.valueOf(txtBDate.getText()));
            stmt.setInt(3, Integer.parseInt(txtTCount.getText()));
            stmt.setInt(4, Integer.parseInt(txtCId.getText()));
            stmt.setString(5, String.valueOf(txtTTypeId.getText()));
            stmt.setInt(6, Integer.parseInt(txtPId.getText()));
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

        @FXML
        void onActionbtnBookingAdd(ActionEvent event) throws IOException, SQLException {
            if (Validator.validateBooking(txtBID.getText(), txtBDate.getText(), txtBNo.getText(), txtTCount.getText())) {
                Connection connection = helper.returnConnection();
                String sql = " INSERT INTO `bookings` (BookingNo, BookingDate,TravelerCount,CustomerId,TripTypeId, PackageId,BookingId) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, String.valueOf(txtBNo.getText()));
                stmt.setString(2, String.valueOf(txtBDate.getText()));
                stmt.setInt(3, Integer.parseInt(txtTCount.getText()));
                stmt.setInt(4, Integer.parseInt(txtCId.getText()));
                stmt.setString(5, String.valueOf(txtTTypeId.getText()));
                stmt.setInt(6, Integer.parseInt(txtPId.getText()));
                stmt.setInt(7, Integer.parseInt(txtBID.getText()));
                btnBookingSave.setDisable(true);
                btnBookingEdit.setDisable(false);
                int rows = stmt.executeUpdate();
                connection.close();
                if (rows == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Insert failed, contact tech support", ButtonType.OK);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insert successful", ButtonType.OK);
                    alert.show();
                }
                Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene aboutScene = new Scene(aboutView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(aboutScene);
                window.show();
            } else {

                   }


        }

    }

















