package is.hi.hbv202g.assignment8;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class LibrarySystemTest {

    private LibrarySystem librarySystem;

    @Before
    public void setUp() {
        librarySystem = new LibrarySystem();
        librarySystem.addStudentUser("Alice", true);
        librarySystem.addFacultyMemberUser("Bob", "History");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Moby Dick", "Herman Melville");
    }

    @Test
    public void testAddAndFindUser() throws UserOrBookDoesNotExistException {
        assertEquals("Should find user by name", "Alice", librarySystem.findUserByName("Alice").getName());
    }

    @Test
    public void testAddAndFindBook() throws UserOrBookDoesNotExistException {
        assertEquals("Should find book by title", "Moby Dick", librarySystem.findBookByTitle("Moby Dick").getTitle());
    }

    @Test
    public void testBorrowAndReturnBook() throws UserOrBookDoesNotExistException {
        Student student = (Student) librarySystem.findUserByName("Alice");
        Book book = librarySystem.findBookByTitle("Moby Dick");
        librarySystem.borrowBook(student, book);
        assertTrue("Book should be on lending list after borrowing", librarySystem.inLending(book));

        librarySystem.returnBook(book);
        assertFalse("Book should not be on lending list after returning", librarySystem.inLending(book));
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindUserFail() throws UserOrBookDoesNotExistException {
        librarySystem.findUserByName("Nonexistent");
    }

    @Test(expected = UserOrBookDoesNotExistException.class)
    public void testFindBookFail() throws UserOrBookDoesNotExistException {
        librarySystem.findBookByTitle("Nonexistent");
    }

}
