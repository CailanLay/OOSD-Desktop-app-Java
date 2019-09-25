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

    @FXML
    void onActionBtnAgentBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    @FXML
    void onActionBtnAgentEdit(ActionEvent event) {

    }

    @FXML
    void onActionBtnAgentSave(ActionEvent event) throws IOException {
        // add the save functionality here
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }
}
