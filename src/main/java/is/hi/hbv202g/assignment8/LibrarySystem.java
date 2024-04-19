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

    public List<Book> getBooks() {
        return books;
    }
    public List<User> getUsers() {
        return users;
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) throw new EmptyAuthorListException("Empty Authors list");
        books.add(new Book(title, authors));
    }
    public void addBookWithTitleAndUnspecifiedAmountOfAuthors(String title, String... authors) {
        books.add(new Book(title, authors));
    }
    public void removeBookFromDatabase(String bookName) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                books.remove(book);
            }
        }
    }


    public void removeBookFromDatabase(Book bookName) {
        for (Book book : books) {
            if (book.equals(bookName)) {
                books.remove(bookName);
            }
        }
    }

    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
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
    public void borrowBook(Student user, Book book) {
        lendings.add(new Lending(book, user));
    }

    /*
    public void borrowBook(Student user, String bookName) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName) && !book.isLent() && user.isFeePaid()) {
                user.setFeePaid(false);
                lendings.add(new Lending(book, user));
                book.setLentTo(user);
            }
        }
    }

     */

    public User getUser(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User does not exist");
    }

    public Book getBook(String bookName) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookName)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book does not exist");
    }

    public boolean inLending(Book name) {
        for (Lending len : lendings) {
            if (len.getBook().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public Lending searchLending(Book book) throws UserOrBookDoesNotExistException {
        for (Lending len: lendings) {
            if (len.getBook().equals(book)) {
                return len;
            }
        }
        throw new UserOrBookDoesNotExistException("Book is not on lendings list");
    }


    public void returnBook(Book book) throws UserOrBookDoesNotExistException {

        if (inLending(book)) {
            Lending lending = searchLending(book);
            lending.getUser().returnBook();
            lending.getBook().returned();

            lendings.remove(lending);

        } else {


            throw new UserOrBookDoesNotExistException("Book was never lent");
        }

    }

    public static void main(String[] args) throws UserOrBookDoesNotExistException {
        LibrarySystem lib = new LibrarySystem();
        lib.addStudentUser("B", true);
        lib.addBookWithTitleAndUnspecifiedAmountOfAuthors("1900", "Brynjar");
        lib.borrowBook(((Student)lib.getUser("B")), lib.getBook("1900"));

        System.out.println("BOOK IS BORROWED? " + lib.inLending(lib.getBook("1900")));

        lib.returnBook(lib.getBook("1900"));


    }

    public void returnBook(String book) {
        for (Lending lending : lendings) {
            if (lending.getBook().getTitle().equalsIgnoreCase(book)) {
                lendings.remove(lending);
            }
        }
    }
}
