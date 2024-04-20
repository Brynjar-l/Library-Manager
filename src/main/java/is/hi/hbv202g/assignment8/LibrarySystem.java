package is.hi.hbv202g.assignment8;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a Library Management System that allows for managing books, users, and lendings.
 */
public class LibrarySystem {

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();
    private final List<Lending> lendings = new ArrayList<>();

    /**
     * Constructs an empty LibrarySystem.
     */
    public LibrarySystem() {
    }

    /**
     * Returns a list of all books in the library.
     *
     * @return the list of books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Returns a list of all users registered in the library.
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Adds a new book with a single author to the library.
     *
     * @param title The title of the book
     * @param authorName The name of the author
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        books.add(new Book(title, authorName));
    }

    /**
     * Adds a new book with multiple authors to the library.
     *
     * @param title The title of the book
     * @param authors A list of authors of the book
     * @throws EmptyAuthorListException If the author list is empty
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) throw new EmptyAuthorListException("Empty Authors list");
        books.add(new Book(title, authors));
    }

    /**
     * Adds a new book with one or more authors specified as varargs.
     *
     * @param title The title of the book
     * @param authors Varargs of authors' names
     */
    public void addBookWithTitleAndUnspecifiedAmountOfAuthors(String title, String... authors) {
        books.add(new Book(title, authors));
    }

    /**
     * Removes a book from the library by its name.
     *
     * @param bookName The name of the book to be removed
     */
    public void removeBookFromDatabase(String bookName) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(bookName));
    }

    /**
     * Removes a specific book object from the library.
     *
     * @param book The book to be removed
     */
    public void removeBookFromDatabase(Book book) {
        books.remove(book);
    }

    /**
     * Registers a new student user to the library.
     *
     * @param name The name of the student
     * @param feePaid Indicates whether the student has paid the library fee
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * Registers a new faculty member user to the library.
     *
     * @param name The name of the faculty member
     * @param department The department where the faculty member works
     */
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    /**
     * Finds a book by its title.
     *
     * @param title The title of the book to find
     * @return The book found
     * @throws UserOrBookDoesNotExistException If the book does not exist
     */
    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new UserOrBookDoesNotExistException("Book does not exist"));
    }

    /**
     * Finds a user by their name.
     *
     * @param name The name of the user to find
     * @return The user found
     * @throws UserOrBookDoesNotExistException If the user does not exist
     */
    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new UserOrBookDoesNotExistException("User does not exist"));
    }

    /**
     * Allows a student to borrow a book from the library.
     *
     * @param user The student borrowing the book
     * @param book The book to be borrowed
     */
    public void borrowBook(Student user, Book book) {
        lendings.add(new Lending(book, user));
    }

    /**
     * Retrieves a user by their name.
     *
     * @param name The name of the user to retrieve
     * @return The user retrieved
     * @throws UserOrBookDoesNotExistException If the user does not exist
     */
    public User getUser(String name) throws UserOrBookDoesNotExistException {
        return findUserByName(name);
    }

    /**
     * Retrieves a book by its name.
     *
     * @param bookName The name of the book to retrieve
     * @return The book retrieved
     * @throws UserOrBookDoesNotExistException If the book does not exist
     */
    public Book getBook(String bookName) throws UserOrBookDoesNotExistException {
        return findBookByTitle(bookName);
    }

    /**
     * Checks if a book is currently on lend.
     *
     * @param book The book to check
     * @return true if the book is on lend, false otherwise
     */
    public boolean inLending(Book book) {
        return lendings.stream().anyMatch(lending -> lending.getBook().equals(book));
    }

    /**
     * Searches for a lending record of a specific book.
     *
     * @param book The book to search for in lending records
     * @return The lending record found
     * @throws UserOrBookDoesNotExistException If the book is not on lendings list
     */
    public Lending searchLending(Book book) throws UserOrBookDoesNotExistException {
        return lendings.stream()
                .filter(lending -> lending.getBook().equals(book))
                .findFirst()
                .orElseThrow(() -> new UserOrBookDoesNotExistException("Book is not on lendings list"));
    }

    /**
     * Allows a user to return a borrowed book.
     *
     * @param book The book to be returned
     * @throws UserOrBookDoesNotExistException If the book was never lent
     */
    public void returnBook(Book book) throws UserOrBookDoesNotExistException {
        Lending lending = searchLending(book);
        lending.getUser().returnBook();
        lending.getBook().returned();
        lendings.remove(lending);
    }

}
