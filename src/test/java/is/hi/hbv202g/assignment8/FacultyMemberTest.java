package is.hi.hbv202g.assignment8;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FacultyMemberTest {
    private FacultyMember facultyMember;
    private final String facultyMemberName = "Olaf";
    private final String facultyMemberDepartment = "Engineering";
    @Before
    public void setUp() throws Exception {
        facultyMember = new FacultyMember(facultyMemberName, facultyMemberDepartment);
    }

    @Test
    public void getFacultyMemberNameName() {
        assertEquals(facultyMember.getName(), facultyMemberName);
    }
    @Test
    public void setFacultyMemberNameName() {
        String newName = "Matt";
        facultyMember.setName(newName);

        assertEquals(facultyMember.getName(), newName);
    }
    @Test
    public void getDepartment() {
        assertEquals(facultyMember.getDepartment(), facultyMemberDepartment);
    }

    @Test
    public void setDepartment() {
        String newDepartment = "Philosophy";
        facultyMember.setDepartment(newDepartment);

        assertEquals(facultyMember.getDepartment(), newDepartment);
    }
}