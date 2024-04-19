package is.hi.hbv202g.assignment8;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookTest {

    private Book singleAuthorBook;
    private Book multipleAuthorsBook;
    private List<Author> authorList;

    @Before
    public void setUp() throws EmptyAuthorListException {
        singleAuthorBook = new Book("1984", "George Orwell");
        authorList = new ArrayList<>(Arrays.asList(new Author("J.K. Rowling"), new Author("H.G. Wells")));
        multipleAuthorsBook = new Book("Book Title", authorList);
    }

    @Test
    public void testSingleAuthorConstructor() {
        assertEquals("1984", singleAuthorBook.getTitle());
        assertEquals("George Orwell", singleAuthorBook.getAuthors());
        assertFalse(singleAuthorBook.isLent());
    }

    @Test
    public void testMultipleAuthorsConstructor() throws EmptyAuthorListException {
        assertEquals("Book Title", multipleAuthorsBook.getTitle());
        assertEquals("J.K. Rowling, H.G. Wells", multipleAuthorsBook.getAuthors());
        assertFalse(multipleAuthorsBook.isLent());
    }

    @Test(expected = EmptyAuthorListException.class)
    public void testEmptyAuthorListException() throws EmptyAuthorListException {
        new Book("Empty Authors", new ArrayList<>());
    }

    @Test
    public void testAddAuthor() {
        singleAuthorBook.addAuthor(new Author("Aldous Huxley"));
        assertEquals("George Orwell, Aldous Huxley", singleAuthorBook.getAuthors());
    }

    @Test
    public void testSetAuthors() throws EmptyAuthorListException {
        List<Author> newAuthors = Arrays.asList(new Author("Orwell"), new Author("Tolkien"));
        singleAuthorBook.setAuthors(newAuthors);
        assertEquals("Orwell, Tolkien", singleAuthorBook.getAuthors());
    }

    @Test
    public void testSetTitle() {
        singleAuthorBook.setTitle("Animal Farm");
        assertEquals("Animal Farm", singleAuthorBook.getTitle());
    }

    @Test
    public void testLending() {
        Student student = new Student("Jane Doe", true);
        singleAuthorBook.setLentTo(student);
        singleAuthorBook.setLent(true);
        assertTrue(singleAuthorBook.isLent());
        assertEquals(student, singleAuthorBook.getLentTo());
    }

    @Test
    public void testReturned() {
        Student student = new Student("Jane Doe", true);
        singleAuthorBook.setLentTo(student);
        singleAuthorBook.setLent(true);
        singleAuthorBook.returned();
        assertFalse(singleAuthorBook.isLent());
        assertNull(singleAuthorBook.getLentTo());
    }
}