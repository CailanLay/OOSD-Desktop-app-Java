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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerCardController implements Initializable {
    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblCustomerFName;

    @FXML
    private Label lblCustomerLName;

    @FXML
    private Button btnCustomerAbout;

    private Customer customer = new Customer();

    public CustomerCardController(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblCustomerID.setText(String.valueOf(customer.getId()));
        lblCustomerFName.setText(customer.getFName());
        lblCustomerLName.setText(customer.getLName());
    }

    @FXML
    void onActionBtnCustomerAbout(ActionEvent event) throws IOException {
        AboutCustomerController aboutCustomer = new AboutCustomerController(customer);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_customer.fxml")));
        loader.setController(aboutCustomer);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_agent.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }
}
