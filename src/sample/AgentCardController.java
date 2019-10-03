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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    private Agent agent;
    private double x, y; // used for screen positioning when moving the window

    // Constructor
    public AgentCardController(Agent agent) {
        this.agent = agent;
    }

    // initialize is called after the constructor when the scene loads
    @FXML
    public void initialize(URL location, ResourceBundle resource) {
        lblAgentID.setText(String.valueOf(agent.getId()));
        lblAgentFName.setText(agent.getFName());
        //lblMiddleInitial.setText(agent.getMiddleInitial());
        lblAgentLName.setText(agent.getLName());
    }


    // This is the action handler for the about button on each card
    @FXML
    void onActionBtnAbout(ActionEvent event) throws IOException {
            AboutAgentController aboutAgent = new AboutAgentController(agent);
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("about_agent.fxml"));
            loader.setController(aboutAgent);
            root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("About" + agent.getFName());
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

            // SELECT edited user from database
            // Use returned object to overwrite this agent's cached data and rebuild card.

        /*
        AboutAgentController aboutAgent = new AboutAgentController(agent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_agent.fxml")));
        loader.setController(aboutAgent);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_agent.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
        */
    }

    private void refreshCard(){

    }

}