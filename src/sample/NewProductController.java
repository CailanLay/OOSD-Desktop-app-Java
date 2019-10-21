package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class NewProductController {

    @FXML
    private TextField txtNewProductName;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnCancel;

    // Author: Cailan Lay
    // Constructor for the contrller
    public NewProductController(){}

    // Author Cailan Lay
    // Action handler for the add button
    @FXML
    void onActionBtnAddProduct(ActionEvent event) throws SQLException {
        if(Validator.validateProduct(txtNewProductName.getText())) {
            DBConnection helper = new DBConnection();
            Connection connection = helper.returnConnection();
            String sql = "INSERT INTO `products`(`ProdName`) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, txtNewProductName.getText());
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding product", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New product added", ButtonType.OK);
                alert.show();
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    void onActionBtnCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
