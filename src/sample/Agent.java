/*
 * Author: Cailan lay
 */
package sample;

public class Agent {
    private int id;
    private String FName;
    private String middleInitial;
    private String LName;
    private String busPhone;
    private String email;
    private String Position;
    private int agencyId;
    private String password;

    // Default Constructor
    public Agent(){};

    // Constructor for id, first name, middle initial, and last name
    public Agent(int id, String FName, String middleInitial, String LName) {
        this.id = id;
        this.FName = FName;
        this.middleInitial = middleInitial;
        this.LName = LName;
    }

    // Constructor
    public Agent(int id, String FName, String middleInitial, String LName, String busPhone, String email, String position, int agencyId, String password) {
        this.id = id;
        this.FName = FName;
        this.middleInitial = middleInitial;
        this.LName = LName;
        this.busPhone = busPhone;
        this.email = email;
        this.Position = position;
        this.agencyId = agencyId;
        this.password = password;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFName() { return FName; }
    public void setFName(String FName) { this.FName = FName; }

    public String getMiddleInitial() { return middleInitial; }
    public void setMiddleInitial(String middleInitial) { this.middleInitial = middleInitial; }

    public String getLName() { return LName; }
    public void setLName(String LName) { this.LName = LName; }

    public String getBusPhone() { return busPhone; }
    public void setBusPhone(String busPhone) { this.busPhone = busPhone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPosition() { return Position; }
    public void setPosition(String position) { Position = position; }

    public int getAgencyId() { return agencyId; }
    public void setAgencyId(int agencyId) { this.agencyId = agencyId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
