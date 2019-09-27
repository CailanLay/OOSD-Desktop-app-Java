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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AgentCardController implements Initializable {

    @FXML
    private HBox hbCard;

    @FXML
    private Label lblAgentID;

    @FXML
    private Label lblAgentFName;

    @FXML
    private Label lblMiddleInitial;

    @FXML
    private Label lblAgentLName;

    @FXML
    private Button btnAbout;

    private Agent agent = new Agent();

    public AgentCardController(Agent agent) {
        this.agent = agent;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resource) {
        lblAgentID.setText(String.valueOf(agent.getId()));
        lblAgentFName.setText(agent.getFName());
        lblMiddleInitial.setText(agent.getMiddleInitial());
        lblAgentLName.setText(agent.getLName());
    }

    // This is the action handler for the about button on each card
    @FXML
    void onActionBtnAbout(ActionEvent event) throws IOException {
        AboutAgentController aboutAgent = new AboutAgentController(agent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_agent.fxml")));
        loader.setController(aboutAgent);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_agent.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

}