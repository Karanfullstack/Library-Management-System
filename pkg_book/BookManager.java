package pkg_book;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

// Book CRUD Operations On Book Class.
public class BookManager {
    ObjectOutputStream oos_book = null; // for output the data
    ObjectInputStream ois_book = null; // for reading the data.

    File book_file = new File("Books.data"); // creating a file where all data consists of book
    @SuppressWarnings("unchecked")

    ArrayList<Book> book_list = null;  // ArrayList values are null for the time being, but later on data will be put it in.

    public BookManager() {  // Constructor of BookManger where all the logics are written to proceed operations.
        book_list = new ArrayList<Book>();
        if (book_file.exists()) {
            try {
                ois_book = new ObjectInputStream(new FileInputStream(book_file));
                // in case book doesn't exist or system failure occurs - throw exception
                book_list = (ArrayList<Book>) ois_book.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e); // run time (Exception)
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void listBooksBySubject( String subject) {
        for (Book book : book_list) {
            if (book.getSubject().equals(subject))
                System.out.println(book);
        }
    }

    // Some Operations On Books..
    public void addBook(Book book) {
        book_list.add(book);
    }

    public void viewAllBooks() {
        for (Book book : book_list)
            System.out.println(book);
        if(book_list.isEmpty())
            System.out.println("No Records Found");
    }

    public Book searchBookIsbn(int search_isbn) {
        for (Book book : book_list) {
            if (book.getIsbn() == search_isbn)
                return book;
        }
        return null;
    }

    public boolean deleteBook(int delete_isbn) {
        // iterating book form ArrayList..
        ListIterator<Book> book_iterator = (ListIterator<Book>) book_list.listIterator();
        while (book_iterator.hasNext()) {
            Book book = book_iterator.next();
            if (book.getIsbn() == delete_isbn) {
                book_list.remove(book);
                return true;
            }
        }
        return false;
    }

    public boolean updateBookleteBook(int update_isbn, String title, String author, String publisher, int edition, String subject, int avilable_quantity) {
        // iterating book form ArrayList..
        ListIterator<Book> book_iterator = (ListIterator<Book>) book_list.listIterator();
        while (book_iterator.hasNext()) {
            Book book = book_iterator.next();
            if (book.getIsbn() == update_isbn) {
                book.setAuthor(author);
                book.setAvilable_quantity(avilable_quantity);
                book.setEdition(edition);
                book.setPublisher(publisher);
                book.setSubject(subject);
                book.setTitle(title);
                return true;
            }
        }
        return false;
    }
    public void writeToFile(){
        try {
            oos_book = new ObjectOutputStream(new FileOutputStream(book_file));
            oos_book.writeObject(book_list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
