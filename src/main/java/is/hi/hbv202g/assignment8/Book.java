package is.hi.hbv202g.assignment8;
import java.util.ArrayList;
import java.util.List;

public class Book {
    
    private String title;
    private boolean isLent; // TODO isNEW
    private List<Author> authors = new ArrayList<Author>();

    public Book(String title, String authorName) { // No need for Exception throwing
        this.title = title;
        authors.add(new Author(authorName));
        this.isLent = false;
    }
    
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("List of Authors provided is empty");
        }
        this.title = title;
        this.authors = authors;
        this.isLent = false;
    }
    public Book(String title, String... authors) {  // TODO NEW
        for (String author : authors) {
            this.authors.add(new Author(author));
        }
        this.title = title;
        this.isLent = false;
    }
    public List<Author> getAuthorsRAW() { // NEW TODO
        return authors;
    }
    public String getAuthors() {   // NEW TODO
        String toReturn = "";
        int i = 0;
        for (Author author : authors) {
            if (i != 0) {
                toReturn += ", ";
            }
            toReturn += author.getName();
            i++;
        }
        return toReturn;
    }
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) throw new EmptyAuthorListException("List of Authors provided is empty");
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // NEW TODO
    public boolean isLent() {
        return isLent;
    }

    // NEW TODO
    public void setLent(boolean bool) {
        isLent = bool;
    }


}
