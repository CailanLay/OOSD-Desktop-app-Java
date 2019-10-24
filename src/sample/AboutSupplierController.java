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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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

    private Suppliers agent = new Suppliers();
    private boolean newSupplier;
    // Author: Harpreet kalsi
    // Controller constructor
    private Suppliers suppliers = new Suppliers();

    // Author: Harpreet kalsi
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
        loadBoxes();
        txtSID.setEditable(false);
        txtSName.setEditable(false);

    }
    // Author: Harpreet kalsi
    private void loadBoxes() {
        if (suppliers != null) {
            txtSID.setText(String.valueOf(suppliers.getSupplierId()));
            txtSID.setEditable(true);
            txtSName.setText(suppliers.getSupName());
            txtSName.setEditable(true);
        }
    }
    // Author: Harpreet kalsi
    @FXML
    void onActionBtnSupplierBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    @FXML
    void onActionBtnSupplierEdit(ActionEvent event) {
        txtSID.setEditable(true);
        txtSName.setEditable(true);
    }
    // Author: Harpreet kalsi
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
            Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene aboutScene = new Scene(aboutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(aboutScene);
            window.show();
        }
    }
    // Author: Harpreet kalsi
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
            Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene aboutScene = new Scene(aboutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(aboutScene);
            window.show();
        }
    }
}
