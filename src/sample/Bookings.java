/*
 * Author: Harpreet Kalsi
 */
package sample;

public class Bookings {
    private int bookingId;
    private String date;
    private String bookingNo;
    private int travelerCount;
    private int customerId;
    private String tripTypeId;
    private int packageId;

    public Bookings(int bookingId, String date, String bookingNo) {
        this.bookingId = bookingId;
        this.date = date;
        this.bookingNo = bookingNo;
    }

    // Default Constructor
    public Bookings() { }


    // Constructor
    public Bookings(int bookingId, String date, String bookingNo, int travelerCount, int customerId, String tripTypeId, int packageId) {
        this.bookingId = bookingId;
        this.date = date;
        this.bookingNo = bookingNo;
        this.travelerCount = travelerCount;
        this.customerId = customerId;
        this.tripTypeId = tripTypeId;
        this.packageId = packageId;
    }


    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public int getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(int travelerCount) {
        this.travelerCount = travelerCount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
