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
    private Label lblCustomerTItleName;

    @FXML
    private Pane pnCustomerEdit;

    @FXML
    private TextField tfCustomerID;

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
    private TextField tfCustomerAgentID;

    @FXML
    private Button btnCustomerSave;

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

    private Customer customer;

    // Constructor
    public AboutCustomerController(Customer customer) {
        this.customer = customer;
    }

    // initialize is called after the constructor when the page loads
    @Override
    public void initialize(URL location, ResourceBundle resources) { loadLabeles(); }

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

    // Author: Cailan Lay
    // checks if the agent object is not null
    private boolean notNull(Customer customer){
        if(customer != null) {
            return true;
        } else {
            return false;
        }
    }

    // Author: Cailan Lay
    // set the label text to to the agent data
    private void loadLabeles() {
        pnCustomerEdit.toBack();
        pnCustomerEdit.setVisible(false);
        pnCustomerView.toFront();
        pnCustomerView.setVisible(true);
        btnCustomerBack.setText("Done");
        notEditable();
        if(notNull(customer)) {
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
        if(pnCustomerEdit.isVisible() && !pnCustomerView.isVisible()) {
            btnCustomerBack.setText("Back");
            loadLabeles();
        } else {
            Stage stage = (Stage) btnCustomerBack.getScene().getWindow();
            stage.close();
        }
    }

    // action handler for the edit customer button on the about customer page
    @FXML
    void onActionBtnCustomerEdit(ActionEvent event) {
        pnCustomerEdit.toFront();
        pnCustomerEdit.setVisible(true);
        pnCustomerView.toBack();
        pnCustomerView.setVisible(false);
        loadBoxes();
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
        stmt.setString(11, tfCustomerAgentID.getText());
        customer.setAgentId(Integer.valueOf(tfCustomerAgentID.getText()));
        stmt.setString(12, String.valueOf(customer.getId()));
        int rows = stmt.executeUpdate();
        if(rows == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving changes", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes have been saved", ButtonType.OK);
            alert.show();
        }
    }
}
