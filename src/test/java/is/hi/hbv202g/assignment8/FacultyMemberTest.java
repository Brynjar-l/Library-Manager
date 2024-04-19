package is.hi.hbv202g.assignment8;

import org.junit.Test;
import static org.junit.Assert.*;

public class FacultyMemberTest {

    @Test
    public void testFacultyMemberConstructor() {
        String expectedName = "Dr. Smith";
        String expectedDepartment = "Biology";
        FacultyMember facultyMember = new FacultyMember(expectedName, expectedDepartment);
        assertEquals("Constructor should set the name correctly", expectedName, facultyMember.getName());
        assertEquals("Constructor should set the department correctly", expectedDepartment, facultyMember.getDepartment());
    }

    @Test
    public void testGetDepartment() {
        String department = "Chemistry";
        FacultyMember facultyMember = new FacultyMember("Prof. Jane", department);
        assertEquals("getDepartment should return the correct department", department, facultyMember.getDepartment());
    }

    @Test
    public void testSetDepartment() {
        String initialDepartment = "Mathematics";
        String newDepartment = "Physics";
        FacultyMember facultyMember = new FacultyMember("Prof. Doe", initialDepartment);
        facultyMember.setDepartment(newDepartment);
        assertEquals("setDepartment should update the department correctly", newDepartment, facultyMember.getDepartment());
    }
}