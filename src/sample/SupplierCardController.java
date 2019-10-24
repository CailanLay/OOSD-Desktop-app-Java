/*
 * Author: Harpreet Kalsi
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

public class SupplierCardController implements Initializable {
    @FXML
    private Label lblSupplierID;

    @FXML
    private Label lblSupName;

    @FXML
    private Button btnAboutSupplier;

    private Suppliers suppliers = new Suppliers();
    private DBConnection helper = new DBConnection(); // Database connection helper
    private double x, y; // used for screen positioning when moving the window

    public SupplierCardController(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblSupplierID.setText(String.valueOf(suppliers.getSupplierId()));
        lblSupName.setText(suppliers.getSupName());

    }
    @FXML
    void  onActionBtnSupplierAbout(ActionEvent event) throws IOException, SQLException {
        /*
        AboutSupplierController aboutsuppliers  = new AboutSupplierController(suppliers);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_supplier.fxml")));
        loader.setController(aboutsuppliers);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_suppliers.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
         */

        AboutSupplierController aboutSupplier = new AboutSupplierController(suppliers);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_supplier.fxml"));
        loader.setController(aboutSupplier);
        root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("About" + suppliers.getSupName());
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

    // Author: Cailan Lay
    // Searches the array list of agents to find the modified agent
    private void findSupplier() throws SQLException {
        ArrayList<Suppliers> suppliersArray = new ArrayList();
        suppliersArray = getSuppliers();
        for(int i = 0; suppliersArray.get(i).getSupplierId() != suppliers.getSupplierId(); i++) {
            if(suppliersArray.get(i).getSupplierId() == suppliers.getSupplierId()) {
                suppliers = suppliersArray.get(i);
                break;
            }
        }
    }

    // Author: Cailan Lay
    // Repopulates the labels with the new data from the new agent
    private void renewCard() throws SQLException {
        findSupplier();
        lblSupplierID.setText(String.valueOf(suppliers.getSupplierId()));
        lblSupName.setText(suppliers.getSupName());

    }

}

