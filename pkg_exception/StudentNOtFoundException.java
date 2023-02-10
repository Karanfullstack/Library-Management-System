package pkg_exception;

import java.io.Serial;

public class StudentNOtFoundException extends Exception {

 @Serial
 private static final long serialVersionUID = 1L;

    public StudentNOtFoundException() {
        super();
    }

    @Override
    public String toString() {
        return "StudentNOtFoundException is Generated";
    }
}
