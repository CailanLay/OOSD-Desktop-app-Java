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
        AboutBookingController aboutBookings = new AboutBookingController(bookings);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("about_booking.fxml")));
        loader.setController(aboutBookings);
        Parent aboutView = loader.load();
        //Parent aboutView = FXMLLoader.load(getClass().getResource("about_booking.fxml"));
        Scene aboutScene = new Scene(aboutView);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();

    }
}
