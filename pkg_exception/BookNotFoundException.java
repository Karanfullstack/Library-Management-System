package pkg_exception;

import java.io.Serial;

public class BookNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public BookNotFoundException(){

    }

    @Override
    public String toString() {
        return "BookNotFoundException is Generated";
    }
}

