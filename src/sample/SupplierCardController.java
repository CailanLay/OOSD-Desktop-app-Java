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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierCardController implements Initializable {
    @FXML
    private Label lblSupplierID;

    @FXML
    private Label lblSupName;

    @FXML
    private Button btnAboutSupplier;

    private Suppliers suppliers = new Suppliers();

    public SupplierCardController(Suppliers suppliers) {
        this.suppliers = suppliers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblSupplierID.setText(String.valueOf(suppliers.getSupplierId()));
        lblSupName.setText(suppliers.getSupName());

    }
    @FXML
    void  onActionBtnSupplierAbout(ActionEvent event) throws IOException {
        AboutSupplierController aboutsuppliers  = new AboutSupplierController(suppliers);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_supplier.fxml")));
        loader.setController(aboutsuppliers);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_suppliers.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();


    }

}

