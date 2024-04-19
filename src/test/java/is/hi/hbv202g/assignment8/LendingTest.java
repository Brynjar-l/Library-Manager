package is.hi.hbv202g.assignment8;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class LendingTest {

    private Book book;
    private Student student;
    private Lending lending;

    @Before
    public void setUp() {
        book = new Book("1984", "George Orwell");
        student = new Student("Jane Doe",true);
        lending = new Lending(book, student);
    }

    @Test
    public void testLendingConstructor() throws UserOrBookDoesNotExistException {
        assertTrue("Constructor should mark the book as lent", book.isLent());
        assertEquals("Constructor should set the user of the lending", student, lending.getUser());
        assertEquals("Book rented by student should be the one lent", book, student.getBookRented());
        assertEquals("Due date should be 30 days from now", LocalDate.now().plusDays(30), lending.getDueDate());
    }

    @Test
    public void testGetDueDate() {
        LocalDate expectedDate = LocalDate.now().plusDays(30);
        assertEquals("getDueDate should return the correct due date", expectedDate, lending.getDueDate());
    }

    @Test
    public void testSetDueDate() {
        LocalDate newDueDate = LocalDate.now().plusDays(45);
        lending.setDueDate(newDueDate);
        assertEquals("setDueDate should update the due date correctly", newDueDate, lending.getDueDate());
    }

    @Test
    public void testGetBook() {
        assertEquals("getBook should return the correct book", book, lending.getBook());
    }

    @Test
    public void testSetBook() {
        Book newBook = new Book("Brave New World", "Aldous Huxley");
        lending.setBook(newBook);
        assertEquals("setBook should update the book correctly", newBook, lending.getBook());
    }

    @Test
    public void testGetUser() {
        assertEquals("getUser should return the correct user", student, lending.getUser());
    }

    @Test
    public void testSetUser() {
        Student newUser = new Student("John Smith", true);
        lending.setUser(newUser);
        assertEquals("setUser should update the user correctly", newUser, lending.getUser());
    }
}
