/*
 * Author: Cailan Lay
 */
package sample;

public class Customer {
    private int id;
    private String FName;
    private String LName;
    private String address;
    private String City;
    private String province;
    private String postalCode;
    private String Country;
    private String homePhone;
    private String busPhone;
    private String email;
    private int agentId;

    // Default Constructor
    public Customer(){}

    // Constructor
    public Customer(int id, String FName, String LName, String address, String city, String province, String postalCode, String country, String homePhone, String busPhone, String email, int agentId) {
        this.id = id;
        this.FName = FName;
        this.LName = LName;
        this.address = address;
        this.City = city;
        this.province = province;
        this.postalCode = postalCode;
        this.Country = country;
        this.homePhone = homePhone;
        this.busPhone = busPhone;
        this.email = email;
        this.agentId = agentId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFName() { return FName; }
    public void setFName(String FName) { this.FName = FName; }

    public String getLName() { return LName; }
    public void setLName(String LName) { this.LName = LName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return City; }
    public void setCity(String city) { City = city; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return Country; }
    public void setCountry(String country) { Country = country; }

    public String getHomePhone() { return homePhone; }
    public void setHomePhone(String homePhone) { this.homePhone = homePhone; }

    public String getBusPhone() { return busPhone; }
    public void setBusPhone(String busPhone) { this.busPhone = busPhone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAgentId() { return agentId; }
    public void setAgentId(int agentId) { this.agentId = agentId; }
}
