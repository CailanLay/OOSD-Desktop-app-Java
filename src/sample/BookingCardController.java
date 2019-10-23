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
import java.util.ResourceBundle;

public class BookingCardController  implements Initializable {
    @FXML
    private Label lblBookingID;

    @FXML
    private Label lblBookingDate;

    @FXML
    private Label lblBookingNumber;

    @FXML
    private Button btnAboutBookings;

    private Bookings bookings = new Bookings();
    private double x, y;

    public BookingCardController(Bookings bookings) {
        this.bookings = bookings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblBookingID.setText(String.valueOf(bookings.getBookingId()));
        lblBookingDate.setText(bookings.getDate());
        lblBookingNumber.setText(String.valueOf(bookings.getBookingNo()));
    }
    @FXML
      void onActionbtnAboutBookings(ActionEvent event) throws IOException {
       /*
        AboutBookingController aboutBookings = new AboutBookingController(bookings);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_booking.fxml")));
        loader.setController(aboutBookings);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_booking.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
        */

        AboutBookingController aboutBookings = new AboutBookingController(bookings);
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about_booking.fxml"));
        loader.setController(aboutBookings);
        root = loader.load();
        Stage stage = new Stage();
        //stage.setTitle("About" + bookings.getFName());
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
        // renewCard();
    }
}
