/*
 * Author: Cailan Lay
 * Add stuff also by Harpreet kalsi
 */
package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Supplier;

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
    private Pane pnCustomers;

    @FXML
    private HBox hboxHeaderAgents;

    @FXML
    private HBox hboxHeaderBookings;

    @FXML
    private HBox hboxHeaderSuppliers;

    @FXML
    private HBox hboxCustomerHeader;

    @FXML
    private VBox hbItemsbookings;

    @FXML
    private VBox hbitemsAgents;

    @FXML
    private VBox hbItemsSuppliers;

    @FXML
    private VBox vbCustomerItems;


    DBConnection helper = new DBConnection(); // Global object
    //ObservableList<Bookings>bookings;


    @FXML
    public void initialize(URL location, ResourceBundle resource) {
        try {
            makeSupplierCards();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            makeBookingCards();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        makeAgentCards();
        try {
            makeCustomerCards();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    /*
     * Author: Harpreet Kalsi
     */
    private void makeSupplierCards() throws SQLException {
        ArrayList<Suppliers> suppliers = new ArrayList<Suppliers>();

        suppliers = getSuppliers(); // populates the the suppliers array

        // the array of nodes to the same size as the as the the array length
        Node[] supplierCards = new Node[suppliers.size()];      // is also the number of cards to be created
        for (int i = 0; i < supplierCards.length; i++) {
            try {
                SupplierCardController card = new SupplierCardController(suppliers.get(i)); // create controller and pass the supplier to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("supplier_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                supplierCards[i] = loader.load(); // add the file to the array of nodes
                hbItemsSuppliers.getChildren().add(supplierCards[i]); // add the scene to the vbox
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * Author: Harpreet Kalsi
     */
    private ArrayList<Suppliers> getSuppliers() throws SQLException {
        ArrayList<Suppliers> suppliers = new ArrayList();

        Connection connection = helper.returnConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `suppliers`");

        // Populates the arraylist with supplier created from database
        while (rs.next()) {
            suppliers.add(new Suppliers(
                    rs.getInt(1),
                    rs.getString(2)));

        }
        connection.close();
        return suppliers; // returns the an array of customer{


    }
    /*
     * Author: Harpreet Kalsi
     */
    private void makeBookingCards() throws SQLException {

        ArrayList<Bookings> bookings = new ArrayList<Bookings>();

        bookings = getBookings(); // populates the the bookings array

        // the array of nodes to the same size as the as the the array length
        Node[] bookingsCards = new Node[bookings.size()];      // is also the number of cards to be created
        for (int i = 0; i < bookingsCards.length; i++) {
            try {
                BookingCardController card = new BookingCardController(bookings.get(i)); // create controller and pass the bookings to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("booking_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                bookingsCards[i] = loader.load(); // add the file to the array of nodes
                hbItemsbookings.getChildren().add(bookingsCards[i]); // add the scene to the vbox
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * Author: Harpreet Kalsi
     */
    private ArrayList<Bookings> getBookings() throws SQLException {
        ArrayList<Bookings> bookings = new ArrayList();

        Connection connection = helper.returnConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `bookings`");

        // Populates the arraylist with supplier created from database
        while (rs.next()) {
            bookings.add(new Bookings(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getInt(7)));

        }
        connection.close();
        return bookings; // returns the an array of agents {

    }


    private void makeCustomerCards() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        customers = getCustomers(); // populates the the agents array

        // the array of nodes to the same size as the as the the array length
        Node[] customerCards = new Node[customers.size()]; // is also the number of cards to be created
        for (int i = 0; i < customerCards.length; i++) {
            try {
                CustomerCardController card = new CustomerCardController(customers.get(i)); // create controller and pass the agent to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customer_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                customerCards[i] = loader.load(); // add the file to the array of nodes
                vbCustomerItems.getChildren().add(customerCards[i]); // add the scene to the vbox
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method creates an arraylist of customers from the database
    private ArrayList<Customer> getCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList();

        Connection connection = helper.returnConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `customers`");

        // Populates the arraylist with agents created from database
        while (rs.next()) {
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
    private void makeAgentCards() {
        ArrayList<Agent> agents = new ArrayList<Agent>();
        try {
            agents = getAgents(); // populates the the agents array
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // the array of nodes to the same size as the as the the array length
        Node[] agentCards = new Node[agents.size()]; // is also the number of cards to be created
        for (int i = 0; i < agentCards.length; i++) {
            try {
                AgentCardController card = new AgentCardController(agents.get(i)); // create controller and pass the agent to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("agent_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                agentCards[i] = loader.load(); // add the file to the array of nodes
                hbitemsAgents.getChildren().add(agentCards[i]); // add the scene to the vbox
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to create an arraylist of agents from the databse
    private ArrayList<Agent> getAgents() throws SQLException {
        ArrayList<Agent> agents = new ArrayList();

        try (Connection connection = helper.returnConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `agents`");

            // Populates the arraylist with agents created from database
            while (rs.next()) {
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
        }

        return agents; // returns the an array of agents
    }


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
    }

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
    }

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
    }

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
    }
}

