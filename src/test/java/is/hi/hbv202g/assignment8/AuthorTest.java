package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthorTest {

    @Test
    public void testAuthorConstructor() {
        String expectedName = "John Doe";
        Author author = new Author(expectedName);
        assertEquals("Constructor should set the name correctly", expectedName, author.getName());
    }

    @Test
    public void testGetName() {
        String testName = "Jane Doe";
        Author author = new Author(testName);
        assertEquals("getName should return the correct name", testName, author.getName());
    }

    @Test
    public void testSetName() {
        String initialName = "Initial Name";
        String newName = "New Name";
        Author author = new Author(initialName);
        author.setName(newName);
        assertEquals("setName should update the name correctly", newName, author.getName());
    }
}