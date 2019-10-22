/*
 * Author: Harpreet Kalsi
 */
package sample;

public class Suppliers {
    private int SupplierId;
    private String SupName;

    public Suppliers(int supplierId, String supName) {
        SupplierId = supplierId;
        SupName = supName;
    }

    public Suppliers() {

    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        SupplierId = supplierId;
    }

    public String getSupName() {
        return SupName;
    }

    public void setSupName(String supName) {
        SupName = supName;
    }
}
