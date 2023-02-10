package pkg_main;

import package_person.Student;
import package_person.StudentManger;
import pkg_book.Book;
import pkg_book.BookManager;
import pkg_exception.BookNotFoundException;
import pkg_exception.StudentNOtFoundException;
import pkg_transaction.BookTransactionManager;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);

        BookManager bm = new BookManager();
        StudentManger sm = new StudentManger();
        BookTransactionManager btm = new BookTransactionManager();

        do {
            System.out.println("Enter 1 if Student\nEnter 2 if Librarian\nEnter 3 if want to Exist");
            choice = sc.nextInt();
            if(choice == 1) { // if user is Student

                System.out.println("Enter Your Roll Number");
                int rollNo = sc.nextInt();
                try{
                    Student s = sm.get(rollNo);
                    if(s == null)
                       throw new StudentNOtFoundException();
                    int stud_choice;
                    do{
                        System.out.println("Enter 1 to View All Books\nEnter 2 to Search Book by ISBN\nEnter 3 to List Books By Subjects\nEnter 4 to Issue a Book\nEnter 5 to Return a Book\nEnter 99 to Exist");
                        stud_choice = sc.nextInt();
                        switch(stud_choice){
                            case 1:
                                System.out.println("All the Book Records are");
                                bm.viewAllBooks();
                                break;
                            case 2:
                                System.out.println("Please Enter ISBN to Search");
                                System.out.println("Enter ISBN of the Book to Search");
                                int search_isbn = sc.nextInt();
                                Book book = bm.searchBookIsbn(search_isbn);
                                if(book == null)
                                    System.out.println("No Book with this ISBN in our Library");
                                else
                                    System.out.println(book);
                                break;
                            case 3:
                                System.out.println("Enter the Subject to Search");
                                sc.nextLine();
                                String search_subject = sc.nextLine();
                               bm.listBooksBySubject(search_subject);
                                break;
                            case 4:
                                System.out.println("Enter The ISBN to Issue a Book");
                                int issue_isbn = sc.nextInt();
                                book = bm.searchBookIsbn(issue_isbn);
                                try{
                                    if(book == null){
                                        throw new BookNotFoundException();
                                    }
                                    if(book.getAvilable_quantity() > 0){
                                            if(btm.issueBook(rollNo,issue_isbn)){
                                                book.setAvilable_quantity(book.getAvilable_quantity()-1);
                                                System.out.println("Book Has Been Issued");
                                            }
                                    }
                                    else{
                                        System.out.println("The Book Has been Issued");
                                    }
                                } catch (BookNotFoundException bnfe) {
                                    System.out.println(bnfe);
                                }

                                break;
                            case 5:
                                System.out.println("Please Entre the ISBN to Return a Book");
                                int return_isbn = sc.nextInt();
                                book = bm.searchBookIsbn(return_isbn);
                                if(book != null) {
                                    if (btm.returnBook(rollNo, return_isbn)) {
                                        book.setAvilable_quantity(book.getAvilable_quantity() + 1);
                                        System.out.println("Thank Your for Returning the Book");
                                    }
                                    else
                                        System.out.println("Could Not Return the Book");
                                }
                                else
                                    System.out.println("Book with this ISBN Does not Exists");
                                break;
                            case 99:
                                System.out.println("Thank You for Using Library");
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }
                    }while(stud_choice  != 99);
                }
                catch(StudentNOtFoundException snfe) {
                    System.out.println(snfe);
                }
            }
            else if(choice == 2) {
                int lib_choice;
                do {
                    // user is Librarian
                    System.out.println("Enter 11 to View All Students\nEnter 12 to Print a Student by Roll Number\nEnter 13 to Register a Student\nEnter 14 to Update a Student\nEnter 15 to Delete a Student");
                    System.out.println("Enter 21 to View All Books\nEnter 22 to Print a Book by ISBN Number\nEnter 23 to Add a New Book\nEnter 24 to Update a Book\nEnter 25 to Delete a Book");
                    System.out.println("Enter 31 to view all Transaction");
                    System.out.println("Enter 99 to Exist");
                     lib_choice = sc.nextInt();
                    switch (lib_choice) {
                        case 11:
                            System.out.println("All The Students Records");
                            sm.viewAllStudents();
                            break;
                        case 12: // search a student based on roll number
                            System.out.println("Enter Roll Number to Fetch Student's Record");
                            int get_rollNo = sc.nextInt();
                            Student student = sm.get(get_rollNo);
                            if (student == null) {
                                System.out.println("No Record Matches  this Roll Number");
                            } else
                                System.out.println(student);
                            break;
                        case 13: // Register Student
                            System.out.println("Enter Student Details to Add");
                            String name;
                            String emailId;
                            String phoneNumber;
                            String address;
                            String dob;
                            int rollNo;
                            int std;
                            String division;
                            sc.nextLine();
                            System.out.println("Name");
                            name = sc.nextLine();

                            System.out.println("EmailID");
                            emailId = sc.nextLine();

                            System.out.println("Phone Number");
                            phoneNumber = sc.nextLine();

                            System.out.println("Address");
                            address = sc.nextLine();

                            System.out.println("Date of Birth");
                            dob = sc.nextLine();

                            System.out.println("Roll Number as Integer");
                            rollNo = sc.nextInt();

                            System.out.println("Standard as Integer");
                            std = sc.nextInt();

                            sc.nextLine();
                            System.out.println("Division");
                            division = sc.nextLine();

                            student = new Student(name, emailId, phoneNumber, address, dob, rollNo, std, division);
                            sm.addStudent(student);
                            System.out.println("Student is Added");
                            break;
                        case 14: // Update a Student
                            System.out.println("Enter The Roll Number to Modify the Record");
                            int modify_rollNo = sc.nextInt();
                            student = sm.get(modify_rollNo);
                            try {
                                if (student == null)
                                    throw new StudentNOtFoundException();
                                sc.nextLine();
                                System.out.println("Name");
                                name = sc.nextLine();

                                System.out.println("EmailID");
                                emailId = sc.nextLine();

                                System.out.println("Phone Number");
                                phoneNumber = sc.nextLine();

                                System.out.println("Address");
                                address = sc.nextLine();

                                System.out.println("Date of Birth");
                                dob = sc.nextLine();

                                System.out.println("Standard as Integer");
                                std = sc.nextInt();

                                sc.nextLine();
                                System.out.println("Division");
                                division = sc.nextLine();

                                sm.updateStudent(modify_rollNo, name, emailId, phoneNumber, address, dob, std, division);
                                System.out.println("Student Record has been Updateded");

                            } catch (StudentNOtFoundException snfe) {
                                System.out.println(snfe);
                            }
                            break;

                        case 15: // to delete Student
                            System.out.println("Enter The Roll No To Delete Student");
                            int delte_rollNo = sc.nextInt();

                            if (sm.deleteStudents(delte_rollNo))
                                System.out.println("Student has been removed");
                            else
                                System.out.println("No Record with Given Roll Number Exists");
                            break;
                        case 21: // view all books
                            bm.viewAllBooks();
                            break;
                        case 22: // search a book by isbn
                            int search_isbn;
                            System.out.println("Enter ISBN of the Book to Search");
                            search_isbn = sc.nextInt();
                           Book book = bm.searchBookIsbn(search_isbn);
                           if(book == null)
                               System.out.println("No Book with this ISBN in our Library");
                           else
                               System.out.println(book);
                           break;
                        case 23: // add a book
                            System.out.println("please Enter Book Details to Add");
                             int isbn;
                             String title;
                             String author;
                             String publisher;
                             int edition;
                             String subject;
                             int avilable_quantity;
                            System.out.println("ISBN");
                            isbn = sc.nextInt();

                            sc.nextLine();
                            System.out.println("Title");
                            title = sc.nextLine();

                            System.out.println("Author");
                            author = sc.nextLine();

                            System.out.println("Publisher");
                            publisher = sc.nextLine();

                            System.out.println("Edition");
                            edition = sc.nextInt();

                            sc.nextLine();

                            System.out.println("Subject");
                            subject = sc.nextLine();

                            System.out.println("Quantity");
                            avilable_quantity = sc.nextInt();

                            book = new Book( isbn,  title,  author,  publisher,  edition,  subject,  avilable_quantity);
                            bm.addBook(book);
                            System.out.println("A Book Record Has been Added");
                            break;
                        case 24: // Update a record of a book
                            System.out.println("Please Enter the ISBN to update the record");
                            int update_isbn;
                            update_isbn = sc.nextInt();
                            try{
                                book = bm.searchBookIsbn(update_isbn);
                                if(book == null)
                                    throw new BookNotFoundException();
                                System.out.println("Enter Book Detials to Modify");

                                sc.nextLine();
                                System.out.println("Title");
                                title = sc.nextLine();

                                System.out.println("Author");
                                author = sc.nextLine();

                                System.out.println("Publisher");
                                publisher = sc.nextLine();

                                System.out.println("Edition");
                                edition = sc.nextInt();

                                sc.nextLine();

                                System.out.println("Subject");
                                subject = sc.nextLine();

                                System.out.println("Quantity");
                                avilable_quantity = sc.nextInt();
                                System.out.println("Book Has Been Updated");
                               bm.updateBookleteBook(update_isbn, title,  author,  publisher,  edition,  subject,  avilable_quantity);
                            } catch (BookNotFoundException e) {
                                System.out.println("Such Book Not Existed - BookNotFundException Generated");
                            }
                            break;
                        case 25: // delete record
                            System.out.println("Please Enter the ISBN to Delete the Record");
                            int delete_isbn;
                            delete_isbn = sc.nextInt();
                            try {
                                book = bm.searchBookIsbn(delete_isbn);
                                if (book == null)
                                    throw new BookNotFoundException();
                                bm.deleteBook(delete_isbn);
                                System.out.println("Enter Book has been  to deleted successfully");
                            }
                                catch(BookNotFoundException bnfe){
                                    System.out.println("Book ISBN is Wrong Try a Valid One " + bnfe);
                                }
                            break;

                        case 31: // to view all transaction
                            System.out.println("All the Transaction are ");
                            btm.showAll();
                        case 99:
                            System.out.println("Thank You for Using Library");
                            break;
                        default:
                            System.out.println("Invalid Choice");
                    }
                    }while(lib_choice != 99);
            }

        } while(choice != 3);
        System.out.println("Thank You For Using Our Service");
        sm.writeToFile();
        bm.writeToFile();
        btm.writeToFile();
        sc.close();
    }
}
