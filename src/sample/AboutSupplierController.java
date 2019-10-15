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
import java.util.ResourceBundle;

public class AboutSupplierController implements Initializable {
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
    void onActionBtnSupplierBack(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

    @FXML
    void onActionBtnSupplierEdit(ActionEvent event) {

    }

    @FXML
    void onActionBtnSupplierSave(ActionEvent event) throws IOException {
        Parent aboutView = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();

    }
    private Suppliers suppliers = new Suppliers();
    public AboutSupplierController(Suppliers suppliers) {
        this.suppliers = suppliers;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBoxes();

    }


    private void loadBoxes() {
        if (suppliers != null) {
            txtSID.setText(String.valueOf(suppliers.getSupplierId()));
            txtSName.setText(suppliers.getSupName());

        }
    }
    }
