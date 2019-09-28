/*
 * Author: Cailan Lay
 */
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AboutCustomerController implements Initializable {

    @FXML
    private TextField tfCustomerID;

    @FXML
    private TextField tfCustomerAddress;

    @FXML
    private TextField tfCustomerFName;

    @FXML
    private TextField tfCustomerLName;

    @FXML
    private TextField tfCustomerCity;

    @FXML
    private TextField tfCustomerProvince;

    @FXML
    private TextField tfCustomerPostalCode;

    @FXML
    private TextField tfCustomerCountry;

    @FXML
    private TextField tfCustomerHomePhone;

    @FXML
    private Button btnCustomerSave;

    @FXML
    private Button btnCustomerEdit;

    @FXML
    private Button btnCustomerBack;

    @FXML
    private TextField tfCustomerBusPhone;

    @FXML
    private TextField tfCustomerEmail;

    @FXML
    private TextField tfCustomerAgentID;

    private Customer customer = new Customer();

    // Constructor
    public AboutCustomerController(Customer customer) {
        this.customer = customer;
    }

    // initialize is called after the constructor when the page loads
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBoxes();
        notEditable();
        btnCustomerSave.setVisible(false);
    }

    // set the text fields no non editable
    private void notEditable(){
        tfCustomerID.setEditable(false);
        tfCustomerFName.setEditable(false);
        tfCustomerLName.setEditable(false);
        tfCustomerAddress.setEditable(false);
        tfCustomerCity.setEditable(false);
        tfCustomerProvince.setEditable(false);
        tfCustomerPostalCode.setEditable(false);
        tfCustomerCountry.setEditable(false);
        tfCustomerHomePhone.setEditable(false);
        tfCustomerBusPhone.setEditable(false);
        tfCustomerEmail.setEditable(false);
        tfCustomerAgentID.setEditable(false);
    }

    // populates the boxes from the data stored in the agent
    private void loadBoxes() {
        tfCustomerID.setText(String.valueOf(customer.getId()));
        tfCustomerFName.setText(customer.getFName());
        tfCustomerLName.setText(customer.getLName());
        tfCustomerAddress.setText(customer.getAddress());
        tfCustomerCity.setText(customer.getCity());
        tfCustomerProvince.setText(customer.getProvince());
        tfCustomerPostalCode.setText(customer.getPostalCode());
        tfCustomerCountry.setText(customer.getCountry());
        tfCustomerHomePhone.setText(customer.getHomePhone());
        tfCustomerBusPhone.setText(customer.getBusPhone());
        tfCustomerEmail.setText(customer.getEmail());
        tfCustomerAgentID.setText(String.valueOf(customer.getAgentId()));
    }

    // action handler for the back button on the about agents page
    @FXML
    void onActionBtnCustomerBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    // action handler for the edit customer button on the about customer page
    @FXML
    void onActionBtnCustomerEdit(ActionEvent event) {
        btnCustomerSave.setVisible(true);
        btnCustomerEdit.setVisible(false);
        tfCustomerID.setEditable(true);
        tfCustomerFName.setEditable(true);
        tfCustomerLName.setEditable(true);
        tfCustomerAddress.setEditable(true);
        tfCustomerCity.setEditable(true);
        tfCustomerProvince.setEditable(true);
        tfCustomerPostalCode.setEditable(true);
        tfCustomerCountry.setEditable(true);
        tfCustomerHomePhone.setEditable(true);
        tfCustomerBusPhone.setEditable(true);
        tfCustomerEmail.setEditable(true);
        tfCustomerAgentID.setEditable(true);
    }

    // action handler for the save button on the about customer page
    @FXML
    void onActionBtnCustomerSave(ActionEvent event) throws SQLException {
        DBConnection helper = new DBConnection();
        Connection connection = helper.returnConnection();
        String sql = "UPDATE `customers` SET `CustFirstName`=?,`CustLastName`=?,`CustAddress`=?,`CustCity`=?,`CustProv`=?,`CustPostal`=?,`CustCountry`=?,`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=?,`AgentId`=? WHERE `CustomerId`=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, tfCustomerFName.getText());
        stmt.setString(2, tfCustomerLName.getText());
        stmt.setString(3, tfCustomerAddress.getText());
        stmt.setString(4, tfCustomerCity.getText());
        stmt.setString(5, tfCustomerProvince.getText());
        stmt.setString(6, tfCustomerPostalCode.getText());
        stmt.setString(7, tfCustomerCountry.getText());
        stmt.setString(8, tfCustomerHomePhone.getText());
        stmt.setString(9, tfCustomerBusPhone.getText());
        stmt.setString(10, tfCustomerEmail.getText());
        stmt.setString(11, tfCustomerAgentID.getText());
        stmt.setString(12, String.valueOf(customer.getId()));
        int rows = stmt.executeUpdate();
        if(rows == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update Successful", ButtonType.OK);
            alert.show();
        }
    }
}
