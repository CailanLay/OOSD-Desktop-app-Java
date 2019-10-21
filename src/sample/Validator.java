package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.regex.Pattern;

public class Validator {

    // Author: Cailan Lay
    // Checks field for only letters with the exception of a few special characters as some names do need them
    private static boolean validName(String input) {
        boolean flag = Pattern.matches("^[a-zA-z ,.'-]+$", input);
        if(flag == true ){
            return true;
        } else {
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks for proper first name only
    private static boolean validSingleName(String input) {
        boolean flag = Pattern.matches("^\b[a-zA-Z]+\b$", input);
        if(flag == true ){
            return true;
        } else {
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks for proper middle initial
    private static boolean validMiddleInitial(String input) {
        boolean flag = Pattern.matches("^[a-zA-Z]$", input);
        if(flag == true ){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Name", ButtonType.OK);
            alert.show();
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks for proper email
    private static boolean validEmail(String input) {
        boolean flag = Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", input);
        if(flag == true) {
            return true;
        } else  {
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks for proper phone
    private static boolean validPhone(String input) {
        boolean flag = Pattern.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", input);
        if(flag == true) {
            return true;
        } else  {
            return false;
        }
    }

    // Author: Cailan Lay
    // validates the agent text fields
    public static boolean validateAgent(String fName, String middleInitial, String lName, String busPhone, String position, String email) {
        if (validSingleName(fName) == true) {
            if(validMiddleInitial(middleInitial) == true) {
                if(validSingleName(lName) == true) {
                    if(validPhone(busPhone) == true) {
                        if(validName(position) == true) {
                            if(validEmail(email)) {
                                return true;
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Email", ButtonType.OK);
                                alert.show();
                                return false;
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid position", ButtonType.OK);
                            alert.show();
                            return false;
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Business phone number", ButtonType.OK);
                        alert.show();
                        return false;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Last Name", ButtonType.OK);
                    alert.show();
                    return false;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Middle initial", ButtonType.OK);
                alert.show();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid First Name", ButtonType.OK);
            alert.show();
            return false;
        }
    }

    public static boolean validateProduct(String name) {
        if(validName(name) == true) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid product name", ButtonType.OK);
            alert.show();
            return false;
        }
    }

}
