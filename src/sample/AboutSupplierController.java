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
    private Button btnSupplierBack;

    @FXML
    private Pane pnEditSipplier;

    @FXML
    private TextField txtSID;

    @FXML
    private TextField txtSName;

    @FXML
    private Button btnSupplierSave;

    @FXML
    private Pane pnViewSupplier;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblSupplierID;

    @FXML
    private Button btnSupplierEdit;

    @FXML
    private Pane pnAddSupplier;

    @FXML
    private TextField txtSID1;

    @FXML
    private TextField txtSName1;

    @FXML
    private Button btnAdd;

    @FXML
    private Label lblSupplierTitle;

    @FXML
    private Label lblEditSupplierID;

    private Suppliers suppliers = new Suppliers();
    private boolean flag;

    public AboutSupplierController(boolean flag) { this.flag = flag; }
    public AboutSupplierController(Suppliers suppliers) {
        this.suppliers = suppliers;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert btnSupplierSave != null : "fx:id=\"btnSupplierSave\" was not injected: check your FXML file 'about_supplier.fxml'.";
        assert btnSupplierEdit != null : "fx:id=\"btnSupplierEdit\" was not injected: check your FXML file 'about_supplier.fxml'.";
        assert txtSID != null : "fx:id=\"txtSID\" was not injected: check your FXML file 'about_supplier.fxml'.";
        assert txtSName != null : "fx:id=\"txtSName\" was not injected: check your FXML file 'about_supplier.fxml'.";
        assert btnSupplierBack != null : "fx:id=\"btnSupplierBack\" was not injected: check your FXML file 'about_supplier.fxml'.";
        if(flag == true) {
            pnAddSupplier.toFront();
            pnAddSupplier.setVisible(true);
            pnEditSipplier.toBack();
            pnEditSipplier.setVisible(false);
            pnViewSupplier.toBack();
            pnViewSupplier.setVisible(true);
        } else {
            loadLabels();
            //txtSID.setEditable(false);
            txtSName.setEditable(false);
        }
    }

    private void loadLabels() {
        if(suppliers != null) {
            lblSupplierID.setText(String.valueOf(suppliers.getSupplierId()));
            lblSupplierName.setText(String.valueOf(suppliers.getSupName()));
        }
        lblSupplierTitle.setText(suppliers.getSupName());
        pnViewSupplier.toFront();
        pnViewSupplier.setVisible(true);
        pnEditSipplier.toBack();
        pnEditSipplier.setVisible(false);
        pnAddSupplier.toBack();
        pnAddSupplier.setVisible(false);
    }


    private void loadBoxes() {
        if (suppliers != null) {
            lblEditSupplierID.setText(String.valueOf(suppliers.getSupplierId()));
            //txtSID.setEditable(true);
            txtSName.setText(suppliers.getSupName());
            txtSName.setEditable(true);
        }
        pnViewSupplier.toFront();
        pnViewSupplier.setVisible(false);
        pnEditSipplier.toFront();
        pnEditSipplier.setVisible(true);
        pnAddSupplier.toBack();
        pnAddSupplier.setVisible(false);
    }
    @FXML
    void onActionBtnSupplierBack(ActionEvent event) throws IOException {
        if(pnEditSipplier.isVisible() && !pnViewSupplier.isVisible()) {
            btnSupplierBack.setText("Back");
            loadLabels();
        } else {
            Stage stage = (Stage) btnSupplierBack.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void onActionBtnSupplierEdit(ActionEvent event) {
        loadBoxes();
        txtSID.setEditable(true);
        txtSName.setEditable(true);
    }

    @FXML
    void onActionBtnSupplierSave(ActionEvent event) throws IOException, SQLException {
        if (Validator.validateSupplier(txtSName.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " UPDATE `suppliers` SET `SupName`=? WHERE `SupplierId`=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, String.valueOf(txtSName.getText()));
            stmt.setString(2, String.valueOf(lblEditSupplierID.getText()));
            // btnSupplierSave.setDisable(true);
            // btnSupplierEdit.setDisable(false);

            int rows = stmt.executeUpdate();
            connection.close();
            if (rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
                alert.show();
            }
            loadLabels();
        } else {
            loadBoxes();
        }
    }
    @FXML
    void onActionbtnSupplierAdd(ActionEvent event) throws IOException, SQLException {
        if (Validator.validateSupplier(txtSName1.getText())) {
            Connection connection = helper.returnConnection();
            String sql = " INSERT INTO `suppliers` (SupplierId, `SupName`) VALUES (?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtSID1.getText()));
            stmt.setString(2, String.valueOf(txtSName1.getText()));
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
            loadLabels();
        }
    }
}
