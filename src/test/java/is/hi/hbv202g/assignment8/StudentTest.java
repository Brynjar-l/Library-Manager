package is.hi.hbv202g.assignment8;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class StudentTest {

    private Student student;
    private Book book;

    @Before
    public void setUp() {
        student = new Student("Alice", true);
        book = new Book("To Kill a Mockingbird", "Harper Lee");
    }

    @Test
    public void testStudentConstructor() {
        assertEquals("Constructor should set the name correctly", "Alice", student.getName());
        assertTrue("Constructor should set feePaid correctly", student.isFeePaid());
    }

    @Test
    public void testIsFeePaidAndSetFeePaid() {
        student.setFeePaid(false);
        assertFalse("setFeePaid should update the feePaid status", student.isFeePaid());
        student.setFeePaid(true);
        assertTrue("setFeePaid should update the feePaid status back to true", student.isFeePaid());
    }

    @Test
    public void testSetBookRented() throws UserOrBookDoesNotExistException {
        student.setBookRented(book);
        assertEquals("setBookRented should set the correct book", book, student.getBookRented());
        assertFalse("Renting a book should set feePaid to false", student.isFeePaid());
    }

    @Test
    public void testReturnBook() {
        student.setBookRented(book);
        student.returnBook();

        try {
            student.getBookRented();
            fail("Expected an UserOrBookDoesNotExistException to be thrown");
        } catch (UserOrBookDoesNotExistException e) {
            assertEquals("Book is not rented", e.getMessage());
        }

        assertTrue("Returning a book should set feePaid to true", student.isFeePaid());
    }

    @Test
    public void testGetBookRented() throws UserOrBookDoesNotExistException {
        student.setBookRented(book);
        assertEquals("getBookRented should return the rented book", book, student.getBookRented());

        student.returnBook(); // Now no book is rented

        try {
            student.getBookRented();
            fail("getBookRented should throw an exception when no book is rented");
        } catch (UserOrBookDoesNotExistException e) {
            assertEquals("Book is not rented", e.getMessage());
        }
    }
}
