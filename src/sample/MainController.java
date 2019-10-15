/*
 * Author: Cailan Lay
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
    private HBox hboxHeader;

    @FXML
    private VBox hbItems;

    @FXML
    private Pane pnCustomers;

    @FXML
    private HBox hboxCustomerHeader;

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
                hbItems.getChildren().add(supplierCards[i]); // add the scene to the vbox
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

    private void makeBookingCards() throws SQLException {

        ArrayList<Bookings> bookings = new ArrayList<Bookings>();

        bookings = getBookings(); // populates the the suppliers array

        // the array of nodes to the same size as the as the the array length
        Node[] bookingsCards = new Node[bookings.size()];      // is also the number of cards to be created
        for (int i = 0; i < bookingsCards.length; i++) {
            try {
                BookingCardController card = new BookingCardController(bookings.get(i)); // create controller and pass the supplier to the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("booking_card.fxml")); // get the FXML file
                loader.setController(card); // set the controller for the fxml file
                bookingsCards[i] = loader.load(); // add the file to the array of nodes
                hbItems.getChildren().add(bookingsCards[i]); // add the scene to the vbox
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6),
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
                hbItems.getChildren().add(agentCards[i]); // add the scene to the vbox
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

    //*************Author Harpreet kalsi**************Bookings table coding********************//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//    @FXML
//    private TextField txtBookingId;
//
//    @FXML
//    private TextField txtBookingDate;
//
//    @FXML
//    private TextField txtBookingNo;
//
//    @FXML
//    private TextField txtTravelerCount;
//
//    @FXML
//    private TextField txtCustomerId;
//
//    @FXML
//    private TextField txtTripTypeId;
//
//    @FXML
//    private TextField txtPackageId;
//
//    @FXML
//    private Button btnEdit;
//
//    @FXML
//    private Button btnSave;
//
//    @FXML
//    private Button btnBack;
//    @FXML
//    private ComboBox<Bookings> cmbBookings;
//
//
//    @FXML
//    void onActionBtnEdit(ActionEvent event) {
//        txtBookingId.setEditable(false);
//        txtBookingDate.setEditable(true);
//        txtBookingNo.setEditable(true);
//        txtTravelerCount.setEditable(true);
//        txtCustomerId.setEditable(true);
//        txtTripTypeId.setEditable(true);
//        txtPackageId.setEditable(true);
//        btnEdit.setDisable(true);
//        btnSave.setDisable(false);
//
//    }
//
//    @FXML
//    void onActionBtnSave(ActionEvent event) throws SQLException {
//        Connection conn = helper.returnConnection();
//        String sql = " UPDATE `bookings` SET `BookingNo`=?, `BookingDate`=?,`TravelerCount`=?,`CustomerId`=?,`TripTypeId`=?," +
//                "`PackageId`=? WHERE `BookingId`=?";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//
//        stmt.setInt(1, Integer.parseInt(txtBookingId.getText()));
//        stmt.setString(2, txtBookingDate.getText());
//        stmt.setInt(3, Integer.parseInt(txtBookingNo.getText()));
//        stmt.setInt(4, Integer.parseInt(txtTravelerCount.getText()));
//        stmt.setInt(5, Integer.parseInt(txtCustomerId.getText()));
//        stmt.setInt(6, Integer.parseInt(txtTripTypeId.getText()));
//        stmt.setInt(7, Integer.parseInt(txtPackageId.getText()));
//        btnSave.setDisable(true);
//        btnEdit.setDisable(false);
//
//        int rows = stmt.executeUpdate();
//        conn.close();
//        if (rows == 0) {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
//            alert.show();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
//            alert.show();
//            loadCombo();
//        }
//    }
//
//
//            @FXML
//            void initialize() throws SQLException{
//
//                assert txtBookingId != null : "fx:id=\"txtBookingId\" was not injected: check your FXML file 'sample.fxml'.";
//                assert txtBookingDate != null : "fx:id=\"txtBookingDate\" was not injected: check your FXML file 'sample.fxml'.";
//                assert txtBookingNo != null : "fx:id=\"txtBookingNo\" was not injected: check your FXML file 'sample.fxml'.";
//                assert txtTravelerCount != null : "fx:id=\"txtTravelerCount\" was not injected: check your FXML file 'sample.fxml'.";
//                assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'sample.fxml'.";
//                assert txtTripTypeId != null : "fx:id=\"txtTripTypeId\" was not injected: check your FXML file 'sample.fxml'.";
//                assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'sample.fxml'.";
//                assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'sample.fxml'.";
//                assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'sample.fxml'.";
//                assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'sample.fxml'.";
//                assert cmbBookings != null : "fx:id=\"cmbBookings\" was not injected: check your FXML file 'sample.fxml'.";
//                loadCombo();
//
//
//
//            cmbBookings.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bookings>() {
//                @Override
//                public void changed(ObservableValue<? extends Bookings> observable, Bookings oldValue, Bookings newValue) {
//                    if (newValue != null) {
//                        txtBookingId.setText(newValue.getBookingId() + "");
//                        txtBookingId.setEditable(false);
//
//                        txtBookingDate.setText(newValue.getDate());
//                        txtBookingDate.setEditable(false);
//
//                        txtTravelerCount.setText(newValue.getTravelerCount() + "");
//                        txtTravelerCount.setEditable(false);
//
//                        txtCustomerId.setText(newValue.getCustomerId() + "");
//                        txtCustomerId.setEditable(false);
//
//                        txtTripTypeId.setText(newValue.getTripTypeId() + "");
//                        txtTripTypeId.setEditable(false);
//
//                        txtPackageId.setText(newValue.getPackageId() + "");
//                        txtPackageId.setEditable(false);
//
//                        btnSave.setDisable(true);
//                        btnEdit.setDisable(false);
//
//                    }
//
//
//                }
//            });
//
//        }
//
//
//
//    private void loadCombo() throws SQLException {
//        Connection conn = helper.returnConnection();
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("select * from `bookings`");
//        ArrayList<Bookings> bookingsArrayList = new ArrayList<>();
//        while (rs.next()){
//            bookingsArrayList.add(new Bookings(rs.getInt(1),rs.getString(2),rs.getInt(3),
//                    rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
//
//
//        }
//     /*  bookings = FXCollections.observableArrayList(bookingsArrayList);
//        cmbBookings.setItems(bookings);
//        conn.close();*/
//    }
//
//    @FXML
//    void onActionBtnDelete(ActionEvent event) {
//
//    }
//    }

