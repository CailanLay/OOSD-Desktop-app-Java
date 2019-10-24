package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.regex.Pattern;

public class Validator {

    //Author:Harpreet kalsi
    private static boolean validId(String input) {
       // boolean flag = Pattern.matches("^[0-9]*[1-9][0-9]*$", input);
        boolean flag = Pattern.matches("^\\d+", input);
        if (flag == true) {
            return true;
        } else {
            return false;
        }
    }

    //Author:Harpreet kalsi
    private static boolean validBookingDate(String input) {
        //boolean flag = Pattern.matches("\\d{4}-[01]\\d-[0-3]\\d", input);
        boolean flag = Pattern.matches("\\d{4}-[01]\\d-[0-3]\\d \\d{2}:\\d{2}:\\d{2}.\\d", input);
        if (flag == true) {
            return true;
        } else {
            return false;
        }
    }

    //Author:Harpreet kalsi
    private static boolean validTravelerCount(String input) {
        boolean flag = Pattern.matches("^[0-9]*[1-9][0-9]*$", input);
        if (flag == true) {
            return true;
        } else {
            return false;
        }
    }

    //Author:Harpreet kalsi
    private static boolean validBookingNo(String input) {
        boolean flag = Pattern.matches("^[a-zA-Z0-9]+$", input);
        if (flag == true) {
            return true;
        } else {
            return false;
        }
    }

    //Author:Harpreet kalsi
    public static boolean validateBooking(String BookingId, String BookingDate, String BookingNo, String TravelerCount) {
        if (validId(BookingId) == true) {
            if (validBookingDate(BookingDate) == true) {
                if (validBookingNo(BookingNo) == true) {
                    if (validTravelerCount(TravelerCount) == true) {
                        return true;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid TravelerCount", ButtonType.OK);
                        alert.show();
                        return false;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid BookingNo", ButtonType.OK);
                    alert.show();
                    return false;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid BookingDate", ButtonType.OK);
                alert.show();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid BookingId", ButtonType.OK);
            alert.show();
            return false;
        }

    }
    //Author:Harpreet kalsi
    public static boolean validateSupplier(String SupplierId, String SuppName) {

        if (validId(SupplierId) == true) {
            if (validName(SuppName) == true) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid SuppName", ButtonType.OK);
                alert.show();
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid SupplierId", ButtonType.OK);
            alert.show();
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks field for only letters with the exception of a few special characters as some names do need them
    private static boolean validName(String input) {
        boolean flag = Pattern.matches("^[A-z ,.'-]+$", input);
        if(flag == true ){
            return true;
        } else {
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks for proper first name only
    private static boolean validSingleName(String input) {
        boolean flag = Pattern.matches("^\\b[a-zA-Z]+\\b$", input);
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
    // Checks for proper address
    private static boolean validAddress(String input) {
        boolean flag = Pattern.matches("^\\d+\\s[A-z]+\\s[A-z]+\\s?[A-z]{2}", input);
        boolean flagTwo = Pattern.matches("^\\d+[-]+\\d+\\s+\\d+[A-z]+\\s[A-z]+[,]\\s+\\s?[A-z]{2}$", input);
        boolean flagThree = Pattern.matches("^\\d+\\s[A-z]+\\s[A-z]+[.,]+", input);
        boolean flagFour = Pattern.matches("^\\d+\\s\\d+[A-z]+\\s[A-z]+[.,]+\\s?[A-z]+", input);
        boolean flagFive = Pattern.matches("^\\d+[-]\\d+\\s\\d+[A-z]+\\s[A-z]+\\s[#]\\d+[A-z,]+\\s[A-z]+$", input);
        boolean flagSix = Pattern.matches("^\\d+[-]\\d+\\s\\d+[A-z]+\\s[A-z]+[.,]+[A-z]+$", input);
        if(flag == true || flagTwo == true || flagThree == true || flagFour == true || flagFive == true || flagSix == true) {
            return true;
        } else  {
            return false;
        }
    }

    // Author: Cailan Lay
    // Checks for proper postal code or zip code
    private static boolean validPostal(String input) {
        boolean flag = Pattern.matches("[ABCEGHJKLMNPRSTVXYabceghjklmnprstvxy][0-9][ABCEGHJKLMNPRSTVWXYZabceghjklmnprstvwxyz] ?[0-9][ABCEGHJKLMNPRSTVWXYZabceghjklmnprstvwxyz][0-9]", input);
        boolean flagTwo = Pattern.matches("^\\d{5}(?:[-\\s]\\d{4})?$", input);
        if(flag == true || flagTwo == true) {
            return true;
        } else  {
            return false;
        }
    }


    // Author: Cailan Lay
    // validates the agent text fields
    // NOTE: If there is time change this to validate everything at the same time and not one at a time
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

    public static boolean validateCustomer(String fName, String lName, String address, String city, String province, String postal, String country, String homePhone, String busPhone, String email) {
        if (validSingleName(fName) == true) {
            if(validSingleName(lName) == true) {
                if(validAddress(address) == true) {
                    if(validName(city) == true) {
                        if(validName(province) == true) {
                            if(validPostal(postal) == true) {
                                if(validName(country) == true) {
                                    if(validPhone(homePhone) == true) {
                                        if(validPhone(busPhone) == true) {
                                            if(validEmail(email) == true) {
                                                return true;
                                            } else {
                                                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email", ButtonType.OK);
                                                alert.show();
                                                return false;
                                            }
                                        } else {
                                            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid business phone", ButtonType.OK);
                                            alert.show();
                                            return false;
                                        }
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid home phone", ButtonType.OK);
                                        alert.show();
                                        return false;
                                    }
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid country name", ButtonType.OK);
                                    alert.show();
                                    return false;
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid postal or zip code", ButtonType.OK);
                                alert.show();
                                return false;
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid province name", ButtonType.OK);
                            alert.show();
                            return false;
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid city name", ButtonType.OK);
                        alert.show();
                        return false;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid address", ButtonType.OK);
                    alert.show();
                    return false;
                }
            } else  {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Last Name", ButtonType.OK);
                alert.show();
                return false;
            }
        } else  {
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



