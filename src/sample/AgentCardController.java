/*
 * Author: Cailan Lay
 */
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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

    private Agent agent; // agent to hold the agent
    private double x, y; // used for screen positioning when moving the window
    private DBConnection helper = new DBConnection(); // Database connection helper

    // Author: Cailan Lay
    // Constructor
    public AgentCardController(Agent agent) {
        this.agent = agent;
    }

    // Author: Cailan Lay
    // initialize is called after the constructor when the scene loads
    @FXML
    public void initialize(URL location, ResourceBundle resource) {
        lblAgentID.setText(String.valueOf(agent.getId()));
        lblAgentFName.setText(agent.getFName());
        //lblMiddleInitial.setText(agent.getMiddleInitial());
        lblAgentLName.setText(agent.getLName());
    }

    // Author: Cailan Lay
    // This is the action handler for the about button on each card
    @FXML
    void onActionBtnAbout(ActionEvent event) throws IOException, SQLException {
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
            renewCard();
    }

    // Author: Cailan Lay
    // creates an array list of agents
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
        return agents;
    }

    // Author: Cailan Lay
    // Searches the array list of agents to find the modified agent
    private void findAgent() throws SQLException {
        ArrayList<Agent> agents = new ArrayList();
        agents = getAgents();
        for(int i = 0; agents.get(i).getId() != agent.getId(); i++) {
            if(agents.get(i).getId() == agent.getId()) {
                agent = agents.get(i);
                break;
            }
        }
    }

    // Author: Cailan Lay
    // Repopulates the labels with the new data from the new agent
    private void renewCard() throws SQLException {
        findAgent();
        lblAgentID.setText(String.valueOf(agent.getId()));
        lblAgentFName.setText(agent.getFName());
        lblAgentLName.setText(agent.getLName());
    }

}