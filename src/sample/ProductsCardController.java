package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private Label lblEditID;

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
        loadLabels();
        HBoxView.toFront();
        HBoxView.setVisible(true);
        HBoxEdit.toBack();
        HBoxEdit.setVisible(false);
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
        lblEditID.setText(String.valueOf(product.getId()));
        tfProductName.setText(product.getName());
    }

    // this is the event handler for the save button on the card
    @FXML
    void onActionBtnProductsSave(ActionEvent event) throws SQLException {
        if(Validator.validateProduct(tfProductName.getText())) {
            DBConnection helper = new DBConnection();
            Connection connection = helper.returnConnection();
            String sql = "UPDATE `products` SET `ProdName`=? WHERE ProductId=" + product.getId();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tfProductName.getText());
            product.setName(tfProductName.getText());
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving changes", ButtonType.OK);
                alert.show();
            }
            loadView();
        } else {
            loadTextFeilds();
        }
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
