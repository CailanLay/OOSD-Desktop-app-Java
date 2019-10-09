package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsCardController implements Initializable {

    @FXML
    private HBox HBoxView;

    @FXML
    private Label lblProductID;

    @FXML
    private Label lblPRoductName;

    @FXML
    private Button btnEdit;

    @FXML
    private HBox HBoxEdit;

    @FXML
    private TextField tfProductID;

    @FXML
    private TextField tfProductName;

    @FXML
    private Button btnProductsSave;

    private Product product;

    // called when the card is made
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadView();
    }

    // Constructor for the controller
    public ProductsCardController(Product product) { this.product = product; }

    // Author: Cailan Lay
    // This sets the default view for the card
    private void loadView(){
        HBoxView.toFront();
        HBoxView.setVisible(true);
        HBoxEdit.toBack();
        HBoxEdit.setVisible(false);
        loadLabels();
    }

    // Author: Cailan Lay
    // This loads the labels for view on the card
    private void loadLabels() {
        lblProductID.setText(String.valueOf(product.getId()));
        lblPRoductName.setText(product.getName());
    }

    // Author: Cailan Lay
    // This loads the text fields for the card
    private void loadTextFeilds() {
        tfProductName.setText(product.getName());
        tfProductID.setText(String.valueOf(product.getId()));
    }

    // this is the event handler for the save button on the card
    @FXML
    void onActionBtnProductsSave(ActionEvent event) {

    }

    // Author: Cailan Lay
    // This the the event handler for the edit button the card
    @FXML
    void onActionbtnProductsEdit(ActionEvent event) {
        loadTextFeilds();
        HBoxView.toBack();
        HBoxView.setVisible(false);
        HBoxEdit.toFront();
        HBoxEdit.setVisible(true);
    }
}
