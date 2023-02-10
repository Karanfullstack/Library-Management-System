package package_person;

public class Librarian extends Person {
    private int id;
    private String doj;

 // Getter Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    // Getter Setter Ends

    // Constructor Starts

    public Librarian(String name, String emailId, String phoneNumber, String address, String dob, int id, String doj) {
        super(name, emailId, phoneNumber, address, dob);
        this.id = id;
        this.doj = doj;
    }

    // Constructor Ends

// Default Constructor

    public Librarian() {

        super();
    }
    // Default Constructor Ends..

}
