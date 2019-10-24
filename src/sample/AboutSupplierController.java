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
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AboutSupplierController implements Initializable {

    DBConnection helper = new DBConnection(); // Global object

    @FXML
    private Pane pnViewSupplier;

    @FXML
    private Pane pnEditSupplier;

    @FXML
    private Pane pnNewSupplier;

    @FXML
    private Label lblSupplierID;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Button btnNewSupplier;

    @FXML
    private Button btnSupplierSave;

    @FXML
    private Button btnSupplierEdit;

    @FXML
    private TextField txtSID;

    @FXML
    private TextField txtSName;

    @FXML
    private Button btnSupplierBack;

    @FXML
    private Button btnAdd;

    private Suppliers suppliers = new Suppliers();
    private boolean newSuppliers;

    // Author: Harpreet kalsi
    // Controller constructor
    public AboutSupplierController(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    // Author: Harpreet kalsi
    // Controller default constructor
    public AboutSupplierController(boolean newSuppliers){ this.newSuppliers = newSuppliers; }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // loadBoxes();
        if(newSuppliers== true) {
            loadnewSuppliersView();
        } else {
            loadLabeles();
        }
    }

    // sets the text fields to non editable
    // Author: Harpreet kalsi
    private void notEditable() {
        txtSID.setEditable(false);
        txtSName.setEditable(false);
    }

    // checks if the supplier object is not null
    // Author: Harpreet kalsi
    private boolean notNull(Suppliers suppliers){
        if(suppliers != null) {
            return true;
        } else {
            return false;
        }
    }

    // load the window for a new supplier to be added
    // Author: Harpreet kalsi
    private void loadnewSuppliersView() {
        pnNewSupplier.toFront();
        pnNewSupplier.setVisible(true);
        pnEditSupplier.toBack();
        pnEditSupplier.setVisible(false);
        pnViewSupplier.toBack();
        pnViewSupplier.setVisible(false);
    }

    // set the label text to to the supplier data
    // Author: Harpreet kalsi
    private void loadLabeles() {
        pnEditSupplier.toBack();
        pnEditSupplier.setVisible(false);
        pnNewSupplier.toBack();
        pnNewSupplier.setVisible(false);
        pnViewSupplier.toFront();
        pnViewSupplier.setVisible(true);
        btnSupplierBack.setText("Done");
        notEditable();
        if(notNull(suppliers)) {
            lblSupplierID.setText(String.valueOf(suppliers.getSupplierId()));
            lblSupplierName.setText(String.valueOf(suppliers.getSupName()));

        }
    }

    // Author: Harpreet kalsi
    // populates the text fields
    private void loadBoxes() {
        if (notNull(suppliers )) {
            txtSID.setText(String.valueOf(suppliers.getSupplierId()));
            txtSID.setEditable(true);
            txtSName.setText(suppliers.getSupName());
            txtSName.setEditable(true);
        }
    }
    // Author: Harpreet kalsi
    // Action handler for the back button on the about supplier page
    @FXML
    void onActionBtnSupplierBack(ActionEvent event) throws IOException {
        if(pnEditSupplier.isVisible() && !pnViewSupplier.isVisible()) {
            btnSupplierBack.setText("Back");
            loadLabeles();
        } else {
            Stage stage = (Stage) btnSupplierBack.getScene().getWindow();
            stage.close();
        }
    }

    // action handler for the edit button on the about supplier page
    // Author: Harpreet kalsi
    @FXML
    void onActionBtnSupplierEdit(ActionEvent event) {
        pnEditSupplier.toFront();
        pnEditSupplier.setVisible(false);
        pnNewSupplier.toBack();
        pnNewSupplier.setVisible(true);
        pnViewSupplier.toBack();
        pnViewSupplier.setVisible(false);
        loadBoxes();

        txtSID.setEditable(true);
        txtSName.setEditable(true);

    }
    // Author: Harpreet kalsi
    // action handler for the save button on the about supplier page
    @FXML
    void onActionBtnSupplierSave(ActionEvent event) throws IOException, SQLException {
        if (Validator.validateSupplier(txtSName.getText(),txtSID.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " UPDATE `suppliers` SET `SupName`=? WHERE `SupplierId`=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(txtSName.getText()));
            suppliers.setSupplierId(Integer.valueOf(txtSID.getText()));
            stmt.setString(2, String.valueOf(txtSID.getText()));
            suppliers.setSupName(txtSName.getText());
            btnSupplierSave.setDisable(true);
            btnSupplierEdit.setDisable(false);

            int rows = stmt.executeUpdate();
            connection.close();
            if (rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
                alert.show();
            }
            connection.close();
            loadLabeles();
        } else {
            loadBoxes();
        }
            Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene aboutScene = new Scene(aboutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(aboutScene);
            window.show();
        }

    // Author: Harpreet kalsi
    // Action handler for the add new supplier button
    @FXML
    void onActionbtnSupplierAdd(ActionEvent event) throws IOException, SQLException {
        if (Validator.validateSupplier(txtSName.getText(),txtSID.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " INSERT INTO `suppliers` (SupplierId, `SupName`) VALUES (?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtSID.getText()));
            suppliers.setSupplierId(Integer.valueOf(txtSID.getText()));
            stmt.setString(2, String.valueOf(txtSName.getText()));
            suppliers.setSupName(txtSName.getText());
            btnSupplierSave.setDisable(false);
            btnSupplierEdit.setDisable(true);
            int rows = stmt.executeUpdate();
            connection.close();
            if (rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Insert failed, contact tech support", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Insert successful", ButtonType.OK);
                alert.show();
            }
            loadLabeles();
        } else {
            loadLabeles();
        }
            Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene aboutScene = new Scene(aboutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(aboutScene);
            window.show();
        }
    }

