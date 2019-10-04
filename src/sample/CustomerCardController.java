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

    // Author: Cailan Lay
    // Action handler for the about button on customer card
    @FXML
    void onActionBtnCustomerAbout(ActionEvent event) throws IOException, SQLException {
        AboutCustomerController aboutCustomer = new AboutCustomerController(customer);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_customer.fxml"));
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

    // Author: Cailan Lay
    // Method creates an arraylist of customers from the database
    private ArrayList<Customer> getCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList();

        Connection connection = helper.returnConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `customers`");

        // Populates the arraylist with agents created from database
        while(rs.next()) {
            customers.add(new Customer(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getInt(12)));
        }
        connection.close();
        return customers; // returns the an array of agents
    }

    // Author: Cailan Lay
    // Searches the array list of agents to find the modified customer
    private void findCustomer() throws SQLException {
        ArrayList<Customer> customers = new ArrayList();
        customers = getCustomers();
        for(int i = 0; customers.get(i).getId() != customer.getId(); i++) {
            if(customers.get(i).getId() == customer.getId()) {
                customer = customers.get(i);
                break;
            }
        }
    }

    // Author: Cailan Lay
    // Repopulates the labels with the new data from the new customer
    private void renewCard() throws SQLException {
        findCustomer();
        lblCustomerID.setText(String.valueOf(customer.getId()));
        lblCustomerFName.setText(customer.getFName());
        lblCustomerLName.setText(customer.getLName());
    }

}
