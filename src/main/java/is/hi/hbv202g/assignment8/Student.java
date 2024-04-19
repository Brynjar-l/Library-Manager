package is.hi.hbv202g.assignment8;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private boolean feePaid;
    private Book bookRented;

    public Student(String name, boolean feePaid) {
        super(name);
        this.feePaid = feePaid;
    }

    public boolean isFeePaid() {
        return feePaid;
    }

    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }

    public void setBookRented(Book book) {
        this.bookRented = book;
        setFeePaid(false);
    }

    public void returnBook() {
        setFeePaid(true);
        this.bookRented = null;
    }

    public Book getBookRented() throws UserOrBookDoesNotExistException {
        if (bookRented != null) {
            return bookRented;
        }
        throw new UserOrBookDoesNotExistException("Book is not rented");
    }
}
