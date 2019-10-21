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
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AboutCustomerController implements Initializable {

    @FXML
    private Button btnCustomerBack;

    @FXML
    private Line line;

    @FXML
    private Label lblNewCustomerTItleName;

    @FXML
    private Pane pnCustomerView;

    @FXML
    private Button btnCustomerEdit;

    @FXML
    private Label lblCustomerID;

    @FXML
    private Label lblCutomerFName;

    @FXML
    private Label lblCustomerAddress;

    @FXML
    private Label lblCustomerProvince;

    @FXML
    private Label lblCustomerCountry;

    @FXML
    private Label lblCustomerBusPhone;

    @FXML
    private Label lblCustomerAgentID;

    @FXML
    private Label lblCustomerLName;

    @FXML
    private Label lblCustomerCity;

    @FXML
    private Label lblCustomerPostalCode;

    @FXML
    private Label lblCustomerHomePhone;

    @FXML
    private Label lblCustomerEmail;

    @FXML
    private Pane pnNewCustomer;

    @FXML
    private TextField tfNewCustomerID;

    @FXML
    private TextField tfNewCustomerFName;

    @FXML
    private TextField tfNewCustomerLName;

    @FXML
    private TextField tfNewCustomerAddress;

    @FXML
    private TextField tfNewCustomerCity;

    @FXML
    private TextField tfNewCustomerProvince;

    @FXML
    private TextField tfNewCustomerPostalCode;

    @FXML
    private TextField tfNewCustomerCountry;

    @FXML
    private TextField tfNewCustomerHomePhone;

    @FXML
    private TextField tfNewCustomerBusPhone;

    @FXML
    private TextField tfNewCustomerEmail;

    @FXML
    private TextField tfNewCustomerAgentID;

    @FXML
    private Button btnNewCustomer;

    @FXML
    private Pane pnCustomerEdit;

    @FXML
    private TextField tfCustomerFName;

    @FXML
    private TextField tfCustomerLName;

    @FXML
    private TextField tfCustomerAddress;

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
    private TextField tfCustomerBusPhone;

    @FXML
    private TextField tfCustomerEmail;

    @FXML
    private Button btnCustomerSave;

    @FXML
    private Label lblEditCustomerID;

    @FXML
    private Label lblEditCustomerAgentID;

    private Customer customer = new Customer();
    private boolean newCustomer;

    // Author: Cailan Lay
    // Constructor
    public AboutCustomerController(Customer customer) {
        this.customer = customer;
    }

    // Author: Cailan Lay
    // Constructor
    public AboutCustomerController(boolean newCustomer) { this.newCustomer = newCustomer; }

    // Author: Cailan Lay
    // initialize is called after the constructor when the page loads
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(newCustomer == true) {
            loadNewCustomerView();
        } else {
            loadLabeles();
        }
    }

    // Author: Cailan Lay
    // set the text fields no non editable
    private void notEditable(){
        //tfCustomerID.setEditable(false);
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
        //tfCustomerAgentID.setEditable(false);
    }

    // Author: Cailan Lay
    // checks if the agent object is not null
    private boolean notNull(Customer customer){
        if(customer != null) {
            return true;
        } else {
            return false;
        }
    }

    private void loadNewCustomerView() {
        pnNewCustomer.toFront();
        pnNewCustomer.setVisible(true);
        pnCustomerView.toBack();
        pnCustomerView.setVisible(false);
        pnCustomerEdit.toBack();
        pnCustomerEdit.setVisible(false);
    }

    // Author: Cailan Lay
    // set the label text to to the agent data
    private void loadLabeles() {
        pnNewCustomer.toBack();
        pnNewCustomer.setVisible(false);
        pnCustomerEdit.toBack();
        pnCustomerEdit.setVisible(false);
        pnCustomerView.toFront();
        pnCustomerView.setVisible(true);
        btnCustomerBack.setText("Done");
        btnCustomerEdit.setVisible(true);
        btnCustomerEdit.toFront();
        notEditable();
        if(notNull(customer)) {
            lblNewCustomerTItleName.setText(customer.getFName());
            lblCustomerID.setText(String.valueOf(customer.getId()));
            lblCutomerFName.setText(customer.getFName());
            lblCustomerLName.setText(customer.getLName());
            lblCustomerAddress.setText(customer.getAddress());
            lblCustomerCity.setText(customer.getCity());
            lblCustomerProvince.setText(customer.getProvince());
            lblCustomerPostalCode.setText(customer.getPostalCode());
            lblCustomerCountry.setText(customer.getCountry());
            lblCustomerHomePhone.setText(customer.getHomePhone());
            lblCustomerBusPhone.setText(customer.getBusPhone());
            lblCustomerEmail.setText(customer.getEmail());
            lblCustomerAgentID.setText(String.valueOf(customer.getAgentId()));
        }
    }

    // Author: Cailan Lay
    // populates the boxes from the data stored in the agent
    private void loadBoxes() {
        lblEditCustomerID.setText(String.valueOf(customer.getId()));
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
        lblEditCustomerAgentID.setText(String.valueOf(customer.getAgentId()));
    }

    // Author: Cailan Lay
    // action handler for the add button to add new customer
    @FXML
    void onActionBtnNewCustomer(ActionEvent event) throws SQLException {
        DBConnection helper = new DBConnection();
        Connection connection = helper.returnConnection();
        String sql = "INSERT INTO `customers`(`CustomerId`, `CustFirstName`, `CustLastName`, `CustAddress`, `CustCity`, `CustProv`, `CustPostal`, `CustCountry`, `CustHomePhone`, `CustBusPhone`, `CustEmail`, `AgentId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, Integer.valueOf(tfNewCustomerID.getText()));
        customer.setId(Integer.valueOf(tfNewCustomerID.getText()));
        stmt.setString(2, tfNewCustomerFName.getText());
        customer.setFName(tfNewCustomerFName.getText());
        stmt.setString(3, tfNewCustomerLName.getText());
        customer.setLName(tfNewCustomerLName.getText());
        stmt.setString(4, tfNewCustomerAddress.getText());
        customer.setAddress(tfNewCustomerAddress.getText());
        stmt.setString(5, tfNewCustomerCity.getText());
        customer.setCity(tfNewCustomerCity.getText());
        stmt.setString(6, tfNewCustomerProvince.getText());
        customer.setProvince(tfNewCustomerProvince.getText());
        stmt.setString(7, tfNewCustomerPostalCode.getText());
        customer.setPostalCode(tfNewCustomerPostalCode.getText());
        stmt.setString(8, tfNewCustomerCountry.getText());
        customer.setCountry(tfNewCustomerCountry.getText());
        stmt.setString(9, tfNewCustomerHomePhone.getText());
        customer.setHomePhone(tfNewCustomerHomePhone.getText());
        stmt.setString(10, tfNewCustomerBusPhone.getText());
        customer.setBusPhone(tfNewCustomerBusPhone.getText());
        stmt.setString(11, tfNewCustomerEmail.getText());
        customer.setEmail(tfNewCustomerEmail.getText());
        stmt.setInt(12, Integer.valueOf(tfNewCustomerAgentID.getText()));
        customer.setAgentId(Integer.parseInt(tfNewCustomerAgentID.getText()));
        int rows = stmt.executeUpdate();
        if(rows == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding new customer", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New customer has been added", ButtonType.OK);
            alert.show();
        }
        connection.close();
        loadLabeles();
    }

    // Author: Cailan Lay
    // action handler for the back button on the about agents page
    @FXML
    void onActionBtnCustomerBack(ActionEvent event) throws IOException {
        if(pnCustomerEdit.isVisible() && !pnCustomerView.isVisible()) {
            btnCustomerBack.setText("Back");
            loadLabeles();
        } else {
            Stage stage = (Stage) btnCustomerBack.getScene().getWindow();
            stage.close();
        }
    }

    // Author: Cailan Lay
    // action handler for the edit customer button on the about customer page
    @FXML
    void onActionBtnCustomerEdit(ActionEvent event) {
        pnNewCustomer.toBack();
        pnNewCustomer.setVisible(false);
        pnCustomerEdit.toFront();
        pnCustomerEdit.setVisible(true);
        pnCustomerView.toBack();
        pnCustomerView.setVisible(false);
        loadBoxes();
        btnCustomerSave.setVisible(true);
        btnCustomerEdit.setVisible(false);
        //tfCustomerID.setEditable(true);
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
        //tfCustomerAgentID.setEditable(true);
    }

    // Author: Cailan Lay
    // action handler for the save button on the about customer page
    @FXML
    void onActionBtnCustomerSave(ActionEvent event) throws SQLException {
        if(Validator.validateCustomer(tfCustomerFName.getText(), tfCustomerLName.getText(), tfCustomerAddress.getText(), tfCustomerCity.getText(), tfCustomerProvince.getText(), tfCustomerPostalCode.getText(), tfCustomerCountry.getText(),  tfCustomerHomePhone.getText(), tfCustomerBusPhone.getText(), tfCustomerEmail.getText())) {
            DBConnection helper = new DBConnection();
            Connection connection = helper.returnConnection();
            String sql = "UPDATE `customers` SET `CustFirstName`=?,`CustLastName`=?,`CustAddress`=?,`CustCity`=?,`CustProv`=?,`CustPostal`=?,`CustCountry`=?,`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=? WHERE `CustomerId`=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tfCustomerFName.getText());
            customer.setFName(tfCustomerFName.getText());
            stmt.setString(2, tfCustomerLName.getText());
            customer.setLName(tfCustomerLName.getText());
            stmt.setString(3, tfCustomerAddress.getText());
            customer.setAddress(tfCustomerAddress.getText());
            stmt.setString(4, tfCustomerCity.getText());
            customer.setCity(tfCustomerCity.getText());
            stmt.setString(5, tfCustomerProvince.getText());
            customer.setProvince(tfCustomerProvince.getText());
            stmt.setString(6, tfCustomerPostalCode.getText());
            customer.setPostalCode(tfCustomerPostalCode.getText());
            stmt.setString(7, tfCustomerCountry.getText());
            customer.setCountry(tfCustomerCountry.getText());
            stmt.setString(8, tfCustomerHomePhone.getText());
            customer.setHomePhone(tfCustomerHomePhone.getText());
            stmt.setString(9, tfCustomerBusPhone.getText());
            customer.setBusPhone(tfCustomerBusPhone.getText());
            stmt.setString(10, tfCustomerEmail.getText());
            customer.setEmail(tfCustomerEmail.getText());
            //stmt.setString(11, tfCustomerAgentID.getText());
            //customer.setAgentId(Integer.valueOf(tfCustomerAgentID.getText()));
            stmt.setString(11, String.valueOf(customer.getId()));
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving changes", ButtonType.OK);
                alert.show();
            }
            loadLabeles();
        } else {
            loadLabeles();
        }
    }
}
