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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    @FXML
    private VBox VBoxProducts;

    @FXML
    private AnchorPane apScroll;

    @FXML
    private VBox hbItemsSuppliers;

    @FXML
    private VBox hbItemsbookings;

    @FXML
    private Button btnNewBooking;

    private DBConnection helper = new DBConnection(); // Global object
    private double x, y; // used for screen positioning when moving the window
    private double cardHeight = 0.0; // used for increasing the number of cards that can fit in the scroll pane

    @FXML
    public void initialize(URL location, ResourceBundle resource) {
         makeAgentCards();
         makeCustomerCards();
         makeProductCards();
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

    }

    // Author: Cailan Lay
    // Action handler for the button that adds a new Customer
    @FXML
    void onActionBtnNewCustomer(ActionEvent event) throws IOException {
        AboutCustomerController aboutCustomer = new AboutCustomerController(true);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_customer.fxml"));
        loader.setController(aboutCustomer);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Customer");
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
        vbCustomerItems.getChildren().clear();
        makeCustomerCards();
    }

    // Author: Cailan Lay
    // This populates the hbox in the scroll pane with the products cards
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

    // Author Cailan Lay
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
    // Action handler for the button that adds a new agent
    @FXML
    void onActionBtnNewAgent(ActionEvent event) throws IOException {
        AboutAgentController aboutAgent = new AboutAgentController(true);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_agent.fxml"));
        loader.setController(aboutAgent);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Agent");
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
        hbItems.getChildren().clear();
        makeAgentCards();
    }

    // Author: Cailan Lay
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

    // Author: Cailan Lay
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
    // Action handler for the button that adds a new Product
    @FXML
    void onActionBtnNewProduct(ActionEvent event) throws IOException {
        NewProductController newProduct = new NewProductController();
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newProduct.fxml"));
        loader.setController(newProduct);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Product");
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
        VBoxProducts.getChildren().clear();
        makeProductCards();
    }

    // Author: Cailan Lay
    // Create and adds the agents cards
    private void makeProductCards(){
        ArrayList<Product> products = new ArrayList<Product>();
        try {
            products = getProducts(); // populates the the agents array
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cardHeight = 0.0;
        addCardSpace();
        // the array of nodes to the same size as the as the the array length
        Node[] productCards = new Node[products.size()]; // is also the number of cards to be created
        for(int i = 0; i < productCards.length; i++) {
            try {
                ProductsCardController card = new ProductsCardController(products.get(i)); // create controller and pass the agent to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("products_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                productCards[i] = loader.load(); // add the file to the array of nodes
                cardHeight += 50.0;
                addCardSpace(); // used to dynamicaly increase the scroll space to add more cards
                VBoxProducts.getChildren().add(productCards[i]); // add the scene to the vbox
            } catch(IOException  e) { e.printStackTrace();}
        }
    }

    // Author: Cailan Lay
    // Method creates an arraylist of customers from the database
    private ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList();

        Connection connection = helper.returnConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `products`");

        // Populates the arraylist with agents created from database
        while(rs.next()) {
            products.add(new Product(
                    rs.getInt(1),
                    rs.getString(2)));
        }
        connection.close();
        return products; // returns the an array of agents
    }

    @FXML
    void onActionBtnNewSupplier(ActionEvent event) throws IOException, SQLException {
        AboutSupplierController aboutSupplier = new AboutSupplierController(true);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_supplier.fxml"));
        loader.setController(aboutSupplier);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Suppliers");
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
        hbItemsSuppliers.getChildren().clear();
        makeSupplierCards();
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
    @FXML
    void onActionbtnNewBooking(ActionEvent event) throws IOException, SQLException {
        AboutBookingController aboutbooking = new AboutBookingController(true);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_booking.fxml"));
        loader.setController(aboutbooking);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Bookings");
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
        hbItemsbookings.getChildren().clear();
        makeBookingCards();
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

    // Author: Cailan Lay
    // used to increase the space for cards within the scroll pane
    private void addCardSpace(){
        apScroll.setPrefHeight(cardHeight);
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

    // Author: Cailan Lay
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

    // Author: Cailan Lay
    // Closes the application
    @FXML
    void onActionBtnClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
