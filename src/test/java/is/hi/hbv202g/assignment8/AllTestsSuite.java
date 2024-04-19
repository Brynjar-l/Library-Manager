package is.hi.hbv202g.assignment8;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthorTest.class,
        BookTest.class,
        FacultyMemberTest.class,
        LendingTest.class,
        LibrarySystemTest.class,
        StudentTest.class
})
public class AllTestsSuite {
}