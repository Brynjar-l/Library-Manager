package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

public class Lending {
    private LocalDate dueDate;
    private Book book;
    private Student user;

    public Lending(Book book, Student user) {
        this.book = book;
        this.user = user;
        dueDate = LocalDate.now().plusDays(30);

        user.setBookRented(book);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getUser() {
        return user;
    }

    public void setUser(Student user){
        this.user = user;
    }
}
