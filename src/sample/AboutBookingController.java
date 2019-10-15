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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AboutBookingController implements Initializable {
    @FXML
    private TextField txtBookingId;

    @FXML
    private TextField txtBookingDate;

    @FXML
    private TextField txtBookingNo;

    @FXML
    private TextField txtTravelerCount;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtTripTypeId;

    @FXML
    private TextField txtPackageId;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<Bookings> cmbBookings;
    private Bookings bookings = new Bookings();

    // Controller constructor
    public AboutBookingController(Bookings bookings) {
        this.bookings = bookings;
    }

    @FXML
    void onActionBtnBookingBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    @FXML
    void onActionBtnBookingEdit(ActionEvent event) {
        txtBookingId.setEditable(false);
        txtBookingDate.setEditable(true);
        txtBookingNo.setEditable(true);
        txtTravelerCount.setEditable(true);
        txtCustomerId.setEditable(true);
        txtTripTypeId.setEditable(true);
        txtPackageId.setEditable(true);
        btnEdit.setDisable(true);
        btnSave.setDisable(false);
    }

    @FXML
    void onActionBtnBookingSave(ActionEvent event) throws SQLException, IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
        DBConnection helper = new DBConnection();
        Connection conn = helper.returnConnection();
        String sql = " UPDATE `bookings` SET `BookingNo`=?, `BookingDate`=?,`TravelerCount`=?,`CustomerId`=?,`TripTypeId`=?," +
                "`PackageId`=? WHERE `BookingId`=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, Integer.parseInt(txtBookingId.getText()));
        stmt.setString(2, txtBookingDate.getText());
        stmt.setInt(3, Integer.parseInt(txtBookingNo.getText()));
        stmt.setInt(4, Integer.parseInt(txtTravelerCount.getText()));
        stmt.setInt(5, Integer.parseInt(txtCustomerId.getText()));
        stmt.setInt(6, Integer.parseInt(txtTripTypeId.getText()));
        stmt.setInt(7, Integer.parseInt(txtPackageId.getText()));
        btnSave.setDisable(true);
        btnEdit.setDisable(false);

        int rows = stmt.executeUpdate();
        conn.close();
        if (rows == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
            alert.show();
            loadCombo();
        }
    }


    @FXML
    void initialize() throws SQLException{

        assert txtBookingId != null : "fx:id=\"txtBookingId\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtBookingDate != null : "fx:id=\"txtBookingDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtBookingNo != null : "fx:id=\"txtBookingNo\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtTravelerCount != null : "fx:id=\"txtTravelerCount\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtTripTypeId != null : "fx:id=\"txtTripTypeId\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'sample.fxml'.";
        assert cmbBookings != null : "fx:id=\"cmbBookings\" was not injected: check your FXML file 'sample.fxml'.";
        loadCombo();



        cmbBookings.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bookings>() {
            @Override
            public void changed(ObservableValue<? extends Bookings> observable, Bookings oldValue, Bookings newValue) {
                if (newValue != null) {
                    txtBookingId.setText(newValue.getBookingId() + "");
                    txtBookingId.setEditable(false);

                    txtBookingDate.setText(newValue.getDate());
                    txtBookingDate.setEditable(false);

                    txtTravelerCount.setText(newValue.getTravelerCount() + "");
                    txtTravelerCount.setEditable(false);

                    txtCustomerId.setText(newValue.getCustomerId() + "");
                    txtCustomerId.setEditable(false);

                    txtTripTypeId.setText(newValue.getTripTypeId() + "");
                    txtTripTypeId.setEditable(false);

                    txtPackageId.setText(newValue.getPackageId() + "");
                    txtPackageId.setEditable(false);

                    btnSave.setDisable(true);
                    btnEdit.setDisable(false);

                }


            }
        });

    }



    private void loadCombo() throws SQLException {
        DBConnection helper = new DBConnection();
        Connection conn = helper.returnConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from `bookings`");
        ArrayList<Bookings> bookingsArrayList = new ArrayList<>();
        while (rs.next()){
            bookingsArrayList.add(new Bookings(rs.getInt(1),rs.getString(2),rs.getInt(3),
                    rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));


        }
     /*  bookings = FXCollections.observableArrayList(bookingsArrayList);
        cmbBookings.setItems(bookings);
        conn.close();*/
    }

    @FXML
    void onActionBtnDelete(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}




