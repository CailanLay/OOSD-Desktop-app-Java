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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private Button btnAgentSave;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBoxes();
    }

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

    @FXML
    void onActionBtnCustomerBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    @FXML
    void onActionBtnCustomerEdit(ActionEvent event) {

    }

    @FXML
    void onActionBtnCustomerSave(ActionEvent event) {

    }
}
