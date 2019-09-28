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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AboutAgentController implements Initializable {

    @FXML
    private Button btnAgentSave;

    @FXML
    private Button btnAgentEdit;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtMiddleInitial;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtBusPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtAgencyID;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnAgentBack;

    private Agent agent = new Agent();

    // Controller constructor
    public AboutAgentController(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBoxes();
        notEditable();
        btnAgentSave.setVisible(false);
    }

    // sets the text fields to non editable
    private void notEditable() {
        txtID.setEditable(false);
        txtFName.setEditable(false);
        txtMiddleInitial.setEditable(false);
        txtBusPhone.setEditable(false);
        txtEmail.setEditable(false);
        txtPosition.setEditable(false);
        txtAgencyID.setEditable(false);
        txtPassword.setEditable(false);
    }

    // populates the text fields
    private void loadBoxes() {
        if (agent != null) {
            txtID.setText(String.valueOf(agent.getId()));
            txtFName.setText(agent.getFName());
            txtMiddleInitial.setText(agent.getLName());
            txtBusPhone.setText(agent.getBusPhone());
            txtEmail.setText(agent.getEmail());
            txtPosition.setText(agent.getPosition());
            txtAgencyID.setText(String.valueOf(agent.getAgencyId()));
            txtPassword.setText(agent.getPassword());
        }
    }

    // Action handler for the back button on the about agents page
    @FXML
    void onActionBtnAgentBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    // action handler for the edit button on the about agents page
    @FXML
    void onActionBtnAgentEdit(ActionEvent event) {
        btnAgentSave.setVisible(true);
        btnAgentEdit.setVisible(false);
        txtID.setEditable(true);
        txtFName.setEditable(true);
        txtMiddleInitial.setEditable(true);
        txtBusPhone.setEditable(true);
        txtEmail.setEditable(true);
        txtPosition.setEditable(true);
        txtAgencyID.setEditable(true);
        txtPassword.setEditable(true);
    }

    // action handler for the save button on the about agents page
    @FXML
    void onActionBtnAgentSave(ActionEvent event) throws IOException, SQLException {
        DBConnection helper = new DBConnection();
        Connection connection = helper.returnConnection();
        String sql = "UPDATE `agents` SET `AgtFirstName`=?,`AgtMiddleInitial`=?,`AgtLastName`=?,`AgtBusPhone`=?,`AgtEmail`=?,`AgtPosition`=?,`AgencyId`=?,`Password`=? WHERE AgentId = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, txtFName.getText());
        stmt.setString(2, txtMiddleInitial.getText());
        stmt.setString(3, txtLName.getText());
        stmt.setString(4,txtBusPhone.getText());
        stmt.setString(5, txtEmail.getText());
        stmt.setString(6, txtPosition.getText());
        stmt.setString(7, txtAgencyID.getText());
        stmt.setString(8, txtPassword.getText());
        stmt.setString(9, String.valueOf(agent.getId()));
        int rows = stmt.executeUpdate();
        if(rows == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update Successful", ButtonType.OK);
            alert.show();
        }

        // add the save functionality here
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }
}
