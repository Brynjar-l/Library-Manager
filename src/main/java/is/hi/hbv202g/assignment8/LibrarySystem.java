package is.hi.hbv202g.assignment8;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
public class LibrarySystem {

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();
    private final List<Lending> lendings = new ArrayList<>();



    public LibrarySystem() {
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) throw new EmptyAuthorListException("Empty Authors list");
        books.add(new Book(title, authors));
    }
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }
    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book does not exist");
    }
    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User does not exist");
    }
    public void borrowBook(User user, Book book) {
        lendings.add(new Lending(book, user));
    }
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(facultyMember.getName()) && user instanceof FacultyMember) { // only facultyMembers can extend Lending Duration
                for (Lending lending : lendings) {
                    if (book.equals(lending.getBook())) {
                        lending.setDueDate(newDueDate);
                    }
                }
            }
        }

        throw new UserOrBookDoesNotExistException("Invalid FacultyMember entered");
    }
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {

        for (Lending lending : lendings) {   // Check if the book was actually lent
            if (book.equals(lending.getBook())) {
                lendings.remove(lending);    // removes the book from the lendings list if its returned
                /*
                 *  if (user instanceof Student) ((Student) user).setFeePaid(true);
                 */
                break;
            }
        }

        throw new UserOrBookDoesNotExistException("Book was never lent");

        // Added a code that checks if User exists in the list, since it will only get checked if the book exists.
        // I removed it because I realised that it doesn't matter if the User exists by itself,
        // only that it's the correct user for the lent book
        // which I even debated on, since does it matter if the same user returns or not?
        // But it was in the description for parameter variables, so I kept it
        // Am wondering if I should update FeePaid in Student class, but it wasn't stated the feePaid was in regards to books lent so
        // And the parameter says User? I could maybe use if (user instanceof Student)? and then casting or something to do feePaid,
        // but like I Said, Im too unsure if thats what I should do, so im leaving you this text

    }
}
