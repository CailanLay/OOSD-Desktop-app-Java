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
import java.sql.*;
import java.util.ResourceBundle;

public class AboutAgentController implements Initializable {

    @FXML
    private Button btnAgentBack;

    @FXML
    private Label lblTitleName;

    @FXML
    private Line line;

    @FXML
    private Pane pnViewAgent;

    @FXML
    private Button btnAgentEdit;

    @FXML
    private Label lblAgentID;

    @FXML
    private Label lblFName;

    @FXML
    private Label lblLName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAgencyID;

    @FXML
    private Label lblMiddleInitial;

    @FXML
    private Label lblBusPhone;

    @FXML
    private Label lblPosition;

    @FXML
    private Label lblPassword;

    @FXML
    private Pane pnNewAgent;

    @FXML
    private TextField tfNewAgentID;

    @FXML
    private TextField tfNewAgentFName;

    @FXML
    private TextField tfNewAgentLName;

    @FXML
    private TextField tfNewAgentMiddleInitial;

    @FXML
    private TextField tfNewAgentBusPhone;

    @FXML
    private TextField tfNewAgentEmail;

    @FXML
    private TextField tfNewAgentPosition;

    @FXML
    private TextField tfNewAgencyID;

    @FXML
    private TextField tfNewAgentPassword;

    @FXML
    private Button btnAddAgent;

    @FXML
    private Pane pnEditAgent;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtMiddleInitial;

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
    private Button btnAgentSave;

    @FXML
    private Label lblEditAgentID;

    private Agent agent = new Agent();
    private boolean newAgent;

    // Author: Cailan Lay
    // Controller constructor
    public AboutAgentController(Agent agent) {
        this.agent = agent;
    }

    // Author: Cailan Lay
    // Controller default constructor
    public AboutAgentController(boolean newAgent){ this.newAgent = newAgent; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(newAgent == true) {
            loadNewAgentView();
        } else {
            loadLabeles();
        }
    }

    // Author: Cailan Lay
    // sets the text fields to non editable
    private void notEditable() {
        //txtID.setEditable(false);
        txtFName.setEditable(false);
        txtMiddleInitial.setEditable(false);
        txtBusPhone.setEditable(false);
        txtEmail.setEditable(false);
        txtPosition.setEditable(false);
        txtAgencyID.setEditable(false);
        txtPassword.setEditable(false);
    }

    // Author: Cailan Lay
    // checks if the agent object is not null
    private boolean notNull(Agent agent){
        if(agent != null) {
            return true;
        } else {
            return false;
        }
    }

    // Author: Cailan Lay
    // load the window for a new agent to be added
    private void loadNewAgentView() {
        pnNewAgent.toFront();
        pnNewAgent.setVisible(true);
        pnEditAgent.toBack();
        pnEditAgent.setVisible(false);
        pnViewAgent.toBack();
        pnViewAgent.setVisible(false);
    }

    // Author: Cailan Lay
    // set the label text to to the agent data
    private void loadLabeles() {
        pnEditAgent.toBack();
        pnEditAgent.setVisible(false);
        pnNewAgent.toBack();
        pnNewAgent.setVisible(false);
        pnViewAgent.toFront();
        pnViewAgent.setVisible(true);
        btnAgentBack.setText("Done");
        notEditable();
        if(notNull(agent)) {
            lblTitleName.setText(agent.getFName());
            lblAgentID.setText(String.valueOf(agent.getId()));
            lblFName.setText(String.valueOf(agent.getFName()));
            lblMiddleInitial.setText(agent.getMiddleInitial());
            lblLName.setText(agent.getLName());
            lblBusPhone.setText(agent.getBusPhone());
            lblEmail.setText(agent.getEmail());
            lblPosition.setText(agent.getPosition());
            lblAgencyID.setText(String.valueOf(agent.getAgencyId()));
            lblPassword.setText(agent.getPassword());
        }
    }

    // Author: Cailan Lay
    // populates the text fields
    private void loadBoxes() {
        if (notNull(agent)) {
            lblEditAgentID.setText(String.valueOf(agent.getId()));
            txtFName.setText(agent.getFName());
            txtMiddleInitial.setText(agent.getMiddleInitial());
            txtLName.setText(agent.getLName());
            txtBusPhone.setText(agent.getBusPhone());
            txtEmail.setText(agent.getEmail());
            txtPosition.setText(agent.getPosition());
            txtAgencyID.setText(String.valueOf(agent.getAgencyId()));
            txtPassword.setText(agent.getPassword());
        }
    }

    // Author: Cailan Lay
    // Action handler for the add new agent button
    @FXML
    void onActionBtnAddAgent(ActionEvent event) throws SQLException {
        if(Validator.validateAgent(tfNewAgentFName.getText(), tfNewAgentMiddleInitial.getText(), tfNewAgentLName.getText(), tfNewAgentBusPhone.getText(), tfNewAgentPosition.getText(), tfNewAgentEmail.getText()) == true) {
            DBConnection helper = new DBConnection();
            Connection connection = helper.returnConnection();
            String sql = "INSERT INTO `agents`(`AgentId`, `AgtFirstName`, `AgtMiddleInitial`, `AgtLastName`, `AgtBusPhone`, `AgtEmail`, `AgtPosition`, `AgencyId`, `Password`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,Integer.valueOf(tfNewAgentID.getText()));
            agent.setId(Integer.valueOf(tfNewAgentID.getText()));
            stmt.setString(2, tfNewAgentFName.getText());
            agent.setFName(tfNewAgentFName.getText());
            stmt.setString(3, tfNewAgentMiddleInitial.getText());
            agent.setMiddleInitial(tfNewAgentMiddleInitial.getText());
            stmt.setString(4, tfNewAgentLName.getText());
            agent.setLName(tfNewAgentLName.getText());
            stmt.setString(5, tfNewAgentBusPhone.getText());
            agent.setBusPhone(tfNewAgentBusPhone.getText());
            stmt.setString(6, tfNewAgentEmail.getText());
            agent.setEmail(tfNewAgentEmail.getText());
            stmt.setString(7, tfNewAgentPosition.getText());
            agent.setPosition(tfNewAgentPosition.getText());
            stmt.setInt(8, Integer.valueOf(tfNewAgencyID.getText()));
            agent.setAgencyId(Integer.valueOf(tfNewAgencyID.getText()));
            stmt.setString(9, tfNewAgentPassword.getText());
            agent.setPassword(tfNewAgentPassword.getText());
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding agent", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New Agent added", ButtonType.OK);
                alert.show();
            }
            loadLabeles();
        } else {
            loadLabeles();
        }

    }

    // Author: Cailan Lay
    // Action handler for the back button on the about agents page
    @FXML
    void onActionBtnAgentBack(ActionEvent event) {
        if(pnEditAgent.isVisible() && !pnViewAgent.isVisible()) {
            btnAgentBack.setText("Back");
            loadLabeles();
        } else {
            Stage stage = (Stage) btnAgentBack.getScene().getWindow();
            stage.close();
        }
    }

    // Author: Cailan Lay
    // action handler for the edit button on the about agents page
    @FXML
    void onActionBtnAgentEdit(ActionEvent event) {
        pnEditAgent.toFront();
        pnEditAgent.setVisible(true);
        pnNewAgent.toBack();
        pnNewAgent.setVisible(false);
        pnViewAgent.toBack();
        pnViewAgent.setVisible(false);
        loadBoxes();
        //txtID.setEditable(true);
        txtFName.setEditable(true);
        txtMiddleInitial.setEditable(true);
        txtBusPhone.setEditable(true);
        txtEmail.setEditable(true);
        txtPosition.setEditable(true);
        txtAgencyID.setEditable(true);
        txtPassword.setEditable(true);
        btnAgentBack.setText("Cancel");
    }

    // Author: Cailan Lay
    // action handler for the save button on the about agents page
    @FXML
    void onActionBtnAgentSave(ActionEvent event) throws IOException, SQLException {
        if(Validator.validateAgent(txtFName.getText(), txtMiddleInitial.getText(), txtLName.getText(), txtBusPhone.getText(), txtPosition.getText(), txtEmail.getText())) {
            DBConnection helper = new DBConnection();
            Connection connection = helper.returnConnection();
            String sql = "UPDATE `agents` SET `AgtFirstName`=?,`AgtMiddleInitial`=?,`AgtLastName`=?,`AgtBusPhone`=?,`AgtEmail`=?,`AgtPosition`=?,`AgencyId`=?,`Password`=? WHERE AgentId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, txtFName.getText());
            agent.setFName(txtFName.getText());
            stmt.setString(2, txtMiddleInitial.getText());
            agent.setMiddleInitial(txtMiddleInitial.getText());
            stmt.setString(3, txtLName.getText());
            agent.setLName(txtLName.getText());
            stmt.setString(4,txtBusPhone.getText());
            agent.setBusPhone(txtBusPhone.getText());
            stmt.setString(5, txtEmail.getText());
            agent.setEmail(txtEmail.getText());
            stmt.setString(6, txtPosition.getText());
            agent.setPosition(txtPosition.getText());
            stmt.setString(7, txtAgencyID.getText());
            agent.setAgencyId(Integer.valueOf(txtAgencyID.getText()));
            stmt.setString(8, txtPassword.getText());
            agent.setPassword(txtPassword.getText());
            stmt.setString(9, String.valueOf(agent.getId()));
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving changes", ButtonType.OK);
                alert.show();
            }
            connection.close();
            loadLabeles();
        } else {
            loadBoxes();
        }

    }
}
