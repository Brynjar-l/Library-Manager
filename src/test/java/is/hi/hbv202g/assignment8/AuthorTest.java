package is.hi.hbv202g.assignment8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorTest {
    private Author author;
    private final String authorName = "Theodore";

    @Before
    public void setUp() throws Exception {
        author = new Author(authorName);
    }

    @Test
    public void getName() {
        assertEquals(author.getName(), authorName);
    }

    @Test
    public void setName() {
        String newName = "Bob";
        author.setName(newName);

        assertEquals(author.getName(), newName);
    }
}