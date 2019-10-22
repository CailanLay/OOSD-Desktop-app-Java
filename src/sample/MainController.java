/*
 * Author: Cailan Lay
 */
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane apMenu;

    @FXML
    private Button btnAgents;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnBookings;

    @FXML
    private Button btnSuppliers;

    @FXML
    private Pane pnSuppliers;

    @FXML
    private Pane pnBookings;

    @FXML
    private Pane pnAgents;

    @FXML
    private HBox hboxHeader;

    @FXML
    private VBox hbItems;

    @FXML
    private Pane pnCustomers;

    @FXML
    private HBox hboxCustomerHeader;

    @FXML
    private VBox vbCustomerItems;

    @FXML
    private Pane pnProducts;

    @FXML
    private Button btnClose;


    DBConnection helper = new DBConnection(); // Global object

    @FXML
    public void initialize(URL location, ResourceBundle resource) {
         makeAgentCards();
         makeCustomerCards();
    }

    private void makeCustomerCards(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            customers = getCustomers(); // populates the the agents array
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // the array of nodes to the same size as the as the the array length
        Node[] customerCards = new Node[customers.size()]; // is also the number of cards to be created
        for(int i = 0; i < customerCards.length; i++) {
            try {
                CustomerCardController card = new CustomerCardController(customers.get(i)); // create controller and pass the agent to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                customerCards[i] = loader.load(); // add the file to the array of nodes
                vbCustomerItems.getChildren().add(customerCards[i]); // add the scene to the vbox
            } catch(IOException  e) { e.printStackTrace();}
        }
    }

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

    // Create and adds the agents cards
    private void makeAgentCards(){
        ArrayList<Agent> agents = new ArrayList<Agent>();
        try {
            agents = getAgents(); // populates the the agents array
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // the array of nodes to the same size as the as the the array length
        Node[] agentCards = new Node[agents.size()]; // is also the number of cards to be created
        for(int i = 0; i < agentCards.length; i++) {
            try {
                AgentCardController card = new AgentCardController(agents.get(i)); // create controller and pass the agent to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("agent_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                agentCards[i] = loader.load(); // add the file to the array of nodes
                hbItems.getChildren().add(agentCards[i]); // add the scene to the vbox
            } catch(IOException  e) { e.printStackTrace();}
        }
    }

    // Method to create an arraylist of agents from the databse
    private ArrayList<Agent> getAgents() throws SQLException {
        ArrayList<Agent> agents = new ArrayList();

        Connection connection = helper.returnConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `agents`");

        // Populates the arraylist with agents created from database
        while(rs.next()) {
            agents.add(new Agent(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9)));
        }
        connection.close();
        return agents; // returns the an array of agents
    }


    // Author: Cailan Lay
    // shows the agent page when agent menu button is clicked
    @FXML
    void onActionBtnAgents(ActionEvent event) {
        pnAgents.toFront();
        pnAgents.setVisible(true);
        pnCustomers.toBack();
        pnCustomers.setVisible(false);
        pnBookings.toBack();
        pnBookings.setVisible(false);
        pnSuppliers.toBack();
        pnSuppliers.setVisible(false);
        pnProducts.toBack();
        pnProducts.setVisible(false);
    }

    // Author: Cailan Lay
    // shows the customers page when the customer menu is clicked
    @FXML
    void onActionBtnCustomers(ActionEvent event) {
        pnCustomers.toFront();
        pnCustomers.setVisible(true);
        pnAgents.toBack();
        pnAgents.setVisible(false);
        pnBookings.toBack();
        pnBookings.setVisible(false);
        pnSuppliers.toBack();
        pnSuppliers.setVisible(false);
        btnClose.toFront();
        btnClose.setVisible(true);
        pnProducts.toBack();
        pnProducts.setVisible(false);
    }

    // Author: Cailan Lay
    // shows the bookings page when bookings menu button is clicked
    @FXML
    void onActionBtnBookings(ActionEvent event) {
        pnBookings.toFront();
        pnBookings.setVisible(true);
        pnCustomers.toBack();
        pnCustomers.setVisible(false);
        pnAgents.toBack();
        pnAgents.setVisible(false);
        pnSuppliers.toBack();
        pnSuppliers.setVisible(false);
        pnProducts.toBack();
        pnProducts.setVisible(false);
    }

    // Author: Cailan Lay
    // shows the suppliers page when the customer menu is clicked
    @FXML
    void onActionBtnSuppliers(ActionEvent event) {
        pnSuppliers.toFront();
        pnSuppliers.setVisible(true);
        pnCustomers.toBack();
        pnCustomers.setVisible(false);
        pnBookings.toBack();
        pnBookings.setVisible(false);
        pnAgents.toBack();
        pnAgents.setVisible(false);
        pnProducts.toBack();
        pnProducts.setVisible(false);
    }

    // shows the products page when the customer menu is clicked
    @FXML
    void onactionBtnProducts(ActionEvent event) {
        pnSuppliers.toBack();
        pnSuppliers.setVisible(false);
        pnCustomers.toBack();
        pnCustomers.setVisible(false);
        pnBookings.toBack();
        pnBookings.setVisible(false);
        pnAgents.toBack();
        pnAgents.setVisible(false);
        pnProducts.toFront();
        pnProducts.setVisible(true);
    }

    @FXML
    void onActionBtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
