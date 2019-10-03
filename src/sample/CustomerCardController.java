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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    private Customer customer;
    private double x, y; // used for screen positioning when moving the window
    private DBConnection helper = new DBConnection(); // Database connection helper

    // controller constructor
    public CustomerCardController(Customer customer) {
        this.customer = customer;
    }

    // initialize called after the constructor every time a card is made
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblCustomerID.setText(String.valueOf(customer.getId()));
        lblCustomerFName.setText(customer.getFName());
        lblCustomerLName.setText(customer.getLName());
    }

    // Action handler for the about button on customer card
    @FXML
    void onActionBtnCustomerAbout(ActionEvent event) throws IOException {
        AboutCustomerController aboutCustomer = new AboutCustomerController(customer);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_agent.fxml"));
        loader.setController(aboutCustomer);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("About" + customer.getFName());
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setOpacity(0.7); this makes the stage transparent
        // this allows the window to be dragged
        root.setOnMousePressed(eventTwo -> {
            x = eventTwo.getSceneX();
            y = eventTwo.getSceneY();
        });
        root.setOnMouseDragged(eventTwo -> {
            stage.setX(eventTwo.getScreenX() - x);
            stage.setY(eventTwo.getScreenY() - y);
        });
        stage.showAndWait();
        renewCard();
    }


}
