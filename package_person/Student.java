package package_person;

import java.io.Serializable;

public class Student extends Person{
    private int rollNO;
    private int std; // standard
    private String division;

    // Setter Getter Method Starts..


    public int getRollNO() {
        return rollNO;
    }

    public void setRollNO(int rollNO) {
        this.rollNO = rollNO;
    }

    public int getStd() {
        return std;
    }

    public void setStd(int std) {
        this.std = std;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
    // Setter Getter Ends **

 // To String Method Starts Here ...

    @Override
    public String toString() {
        return "Student{" +
                "rollNO=" + rollNO +
                ", std='" + std + '\'' +
                ", division='" + division + '\'' +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }


    // To String Methods Ends Here.

    // Constructor Starts Here ...

    public Student(String name, String emailId, String phoneNumber, String address, String dob, int rollNO, int std, String division) {
        super(name, emailId, phoneNumber, address, dob);
        this.rollNO = rollNO;
        this.std = std;
        this.division = division;
    }

    // Constructor Ends Here...

    // Default Constructor Starts Here...


    public Student() {
        super();
    }
}
