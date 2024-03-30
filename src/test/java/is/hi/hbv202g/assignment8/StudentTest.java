package is.hi.hbv202g.assignment8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class StudentTest {
    private Student student;

    private final String studentName = "John";
    private final boolean isFeePaid = false;
    @Before
    public void setUp() throws Exception {
        student = new Student(studentName, isFeePaid);
    }

    @Test
    public void getStudentName() {
        assertEquals(student.getName(), studentName);
    }
    @Test
    public void setStudentName() {
        String newName = "Tom";
        student.setName(newName);

        assertEquals(student.getName(), newName);
    }
    @Test
    public void isFeePaidWhenFalse() {
        assertFalse(student.isFeePaid());
    }

    @Test
    public void setFeePaid() {
        student.setFeePaid(true);
        assertTrue(student.isFeePaid());

    }
}