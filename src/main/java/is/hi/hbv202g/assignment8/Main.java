package is.hi.hbv202g.assignment8;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        AnsiConsole.systemInstall();
        System.out.println(ansi().render(introText(3)));

        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("The Secret Garden", "Frances Hodgson Burnett");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "Pride and Prejudice", "Jane Austen");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "1984", "George Orwell");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "To Kill a Mockingbird", "Harper Lee");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "The Great Gatsby", "F. Scott Fitzgerald");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "Moby-Dick", "Herman Melville");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "War and Peace", "Leo Tolstoy");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "The Catcher in the Rye", "J.D. Salinger");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "The Hobbit", "J.R.R. Tolkien");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor( "Fahrenheit 451", "Ray Bradbury");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Jane Eyre", "Charlotte Brontë");

        librarySystem.addStudentUser("John", false);
        librarySystem.addFacultyMemberUser("Smith", "Engineering");

        boolean loops = true;

        while (loops) {
            try {
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();


                promptBuilder.createListPrompt()
                        .name("selectionChoice")
                        .message("MENU")
                        .newItem("Book Lending Overview").text("1. Book Lending").add()
                        .newItem("Search").text("2. Search").add()

                        .newItem("User Management").text("3. User Management").add()
                        .newItem("Book Management").text("4. Book Management").add()

                        .newItem("Exit").text("Exit").add()
                        .addPrompt();

                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build()); // #5
                String selected = ((de.codeshelf.consoleui.prompt.ListResult) result.get("selectionChoice")).getSelectedId();


                switch (selected) {
                    case "Book Lending Overview":



                        break;














                    case "Search":      // TODO: FINISH
                        prompt = new ConsolePrompt();
                        promptBuilder = prompt.getPromptBuilder();

                        promptBuilder.createListPrompt()
                                .name("SearchChoice")
                                .message("Would you like to search Users or Books?")
                                .newItem("Books").text("1. Books").add()
                                .newItem("Users").text("2. Users").add()
                                .newItem("Back").text("Back").add()
                                .addPrompt();

                        result = prompt.prompt(promptBuilder.build());
                        selected = ((de.codeshelf.consoleui.prompt.ListResult) result.get("SearchChoice")).getSelectedId();

                        switch (selected) {
                            case "Books":
                                boolean loops1 = true;
                                while (loops1) {
                                    prompt = new ConsolePrompt();
                                    promptBuilder = prompt.getPromptBuilder();

                                    promptBuilder.createInputPrompt()
                                            .name("nameInput")
                                            .message("Search a Book:")
                                            .addPrompt();

                                    HashMap<String, ? extends PromtResultItemIF> inputResult = prompt.prompt(promptBuilder.build());
                                    String inputSelected = ((de.codeshelf.consoleui.prompt.InputResult) inputResult.get("nameInput")).getInput();

                                    try {
                                        Book book = librarySystem.findBookByTitle(inputSelected);
                                        String authors = book.getAuthors();
                                        String status = book.isLent() ? "Currently not available" : "Available";

                                        System.out.println(ansi().render("\n"));
                                        System.out.println(ansi().render("Title: " + book.getTitle()));
                                        System.out.println(ansi().render("Author/s: " + authors));
                                        System.out.println(ansi().render("Status: " + status));

                                        System.out.println(ansi().render("\n"));

                                        if (!book.isLent()) {
                                            prompt = new ConsolePrompt();
                                            promptBuilder = prompt.getPromptBuilder();

                                            promptBuilder.createConfirmPromp()
                                                    .name("lendBook")
                                                    .message("Would you like to lend the book out?")
                                                    .defaultValue(ConfirmChoice.ConfirmationValue.NO)
                                                    .addPrompt();

                                            HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                            String confirmSelected = String.valueOf(((de.codeshelf.consoleui.prompt.ConfirmResult) confirmResult.get("lendBook")).getConfirmed());


                                            if (confirmSelected.equals("YES")) {
                                                boolean loops4 = true;
                                                while(loops4) {
                                                    prompt = new ConsolePrompt();
                                                    promptBuilder = prompt.getPromptBuilder();

                                                    promptBuilder.createInputPrompt()
                                                            .name("StudentUser")
                                                            .message("Assign Student: ")
                                                            .addPrompt();

                                                    inputResult = prompt.prompt(promptBuilder.build());
                                                    inputSelected = ((de.codeshelf.consoleui.prompt.InputResult) inputResult.get("StudentUser")).getInput();

                                                    for (User user : librarySystem.getUsers()) {
                                                        if (user instanceof Student && user.getName().equalsIgnoreCase(inputSelected)) {
                                                            librarySystem.borrowBook(user, book);
                                                            loops4 = false;
                                                            System.out.println(ansi().render("Book assigned to " + user.getName() + ". Due by " + librarySystem.searchLending(book).getDueDate()));
                                                        }
                                                    }

                                                    if (loops4) {
                                                        System.out.println(ansi().render("User Does not Exist or is not a Student"));
                                                        prompt = new ConsolePrompt();
                                                        promptBuilder = prompt.getPromptBuilder();

                                                        promptBuilder.createConfirmPromp()
                                                                .name("TryAgain")
                                                                .message("Input another User?")
                                                                .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                                                                .addPrompt();

                                                        confirmResult = prompt.prompt(promptBuilder.build());
                                                        confirmSelected = String.valueOf(((de.codeshelf.consoleui.prompt.ConfirmResult) confirmResult.get("lendBook")).getConfirmed());

                                                        if (confirmSelected.equals("NO")) {
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        loops1 = false;
                                        break;
                                    } catch (Exception ex1) {
                                        System.out.println("Book does not exist, try again");
                                    }


                                }
                                break;


                            case "Users":
                                prompt = new ConsolePrompt();
                                promptBuilder = prompt.getPromptBuilder();

                                promptBuilder.createInputPrompt()
                                        .name("nameInput")
                                        .message("Search a User:")
                                        .defaultValue("John Doe")
                                        .addPrompt();


                            case "Back":
                                System.out.println(ansi().eraseScreen());
                                System.out.println(ansi().render(introText(3)));
                                break;

                            default:
                                break;
                        }
                        break;















                    case "User Management":
                        System.out.println(ansi().render("3"));
                        break;


















                    case "Book Management":

                        prompt = new ConsolePrompt();
                        promptBuilder = prompt.getPromptBuilder();

                        promptBuilder.createListPrompt()
                                .name("BookManager")
                                .message("Select an Option")
                                .newItem("Create new book").name("CreateNewBook").add()
                                .newItem("Remove book").name("RemoveBook").add()
                                .newItem("See all books").name("BookList").add()
                                .newItem("Back").name("Back").add()
                                .addPrompt();

                        result = prompt.prompt(promptBuilder.build());
                        selected = ((de.codeshelf.consoleui.prompt.ListResult) result.get("BookManager")).getSelectedId();

                        System.out.println(ansi().render("OPTION SELECTED ----: " + selected));

                        switch (selected) {


                            case "CreateNewBook":       // FINISHED
                                prompt = new ConsolePrompt();
                                promptBuilder = prompt.getPromptBuilder();

                                //

                                promptBuilder.createInputPrompt()
                                        .name("TitleInput")
                                        .message("Book Title: ")
                                        .addPrompt();

                                HashMap<String, ? extends PromtResultItemIF> inputResult = prompt.prompt(promptBuilder.build());
                                String inputSelected = ((de.codeshelf.consoleui.prompt.InputResult) inputResult.get("TitleInput")).getInput();
                                String newTitle = inputSelected;

                                prompt = new ConsolePrompt();
                                promptBuilder = prompt.getPromptBuilder();

                                promptBuilder.createInputPrompt()
                                        .name("AuthorsInput")
                                        .message("Authors/s: ")
                                        .defaultValue("Seperate authors via a comma (,)")
                                        .addPrompt();

                                inputResult = prompt.prompt(promptBuilder.build());
                                inputSelected = ((de.codeshelf.consoleui.prompt.InputResult) inputResult.get("AuthorsInput")).getInput();

                                String newAuthors = inputSelected;
                                String[] newAuthorsArray = newAuthors.split(",");

                                librarySystem.addBookWithTitleAndUnspecifiedAmountOfAuthors(newTitle, newAuthorsArray);

                                System.out.println(ansi().render("Book: " + newTitle + " created!"));


                                break;
                            case "RemoveBook":
                                boolean loops2 = true;
                                while(loops2) {
                                    prompt = new ConsolePrompt();
                                    promptBuilder = prompt.getPromptBuilder();

                                    //

                                    promptBuilder.createInputPrompt()
                                            .name("toRemoveInput")
                                            .message("Book Title: ")
                                            .addPrompt();

                                    inputResult = prompt.prompt(promptBuilder.build());
                                    inputSelected = ((de.codeshelf.consoleui.prompt.InputResult) inputResult.get("toRemoveInput")).getInput();
                                    String titleToRemove = inputSelected;

                                    for (Book book : librarySystem.getBooks()) {
                                        if (book.getTitle().equalsIgnoreCase(titleToRemove)) {
                                            librarySystem.removeBookFromDatabase(titleToRemove);
                                            if (librarySystem.inLending(titleToRemove)) {
                                                librarySystem.returnBook(titleToRemove);
                                            }
                                        }
                                    }
                                }

                                break;
                            case "BookList":        // FINISHED
                                for (Book book : librarySystem.getBooks()) {
                                    System.out.println(ansi().render("(" + (book.isLent() ? "Unavailable" : "Available") + ") " + book.getTitle() + ", by " + book.getAuthors()));
                                }

                                boolean keepGoing = true;

                                while(keepGoing) {
                                    prompt = new ConsolePrompt();
                                    promptBuilder = prompt.getPromptBuilder();

                                    promptBuilder.createConfirmPromp()
                                            .name("goBack")
                                            .message("Go Back?")
                                            .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                                            .addPrompt();

                                    HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                    String confirmSelected = String.valueOf(((de.codeshelf.consoleui.prompt.ConfirmResult) confirmResult.get("goBack")).getConfirmed());

                                    if (confirmSelected.equals("YES")) {
                                        keepGoing = false;

                                        System.out.println(ansi().eraseScreen());
                                        System.out.println(ansi().render(introText(3)));
                                    }
                                }

                                break;

                            case "Back":
                                System.out.println(ansi().eraseScreen());
                                System.out.println(ansi().render(introText(3)));
                                break;
                        }

                        break;
                    case "Exit":    // FINISHED
                            System.out.println(ansi().eraseScreen());
                            loops = false;
                        break;
                    default:
                        break;
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    TerminalFactory.get().restore();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



































    public static String introText(int num) {
        String toReturn;
        switch (num) {
            case 1:
                toReturn = "\n" +
                        "\n" +
                        " ___       ___  ________  ________  ________  ________      ___    ___      _____ ______   ________  ________   ________  ________  _______   ________     \n" +
                        "|\\  \\     |\\  \\|\\   __  \\|\\   __  \\|\\   __  \\|\\   __  \\    |\\  \\  /  /|    |\\   _ \\  _   \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   ____\\|\\  ___ \\ |\\   __  \\    \n" +
                        "\\ \\  \\    \\ \\  \\ \\  \\|\\ /\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\   \\ \\  \\/  / /    \\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|\\ \\   __/|\\ \\  \\|\\  \\   \n" +
                        " \\ \\  \\    \\ \\  \\ \\   __  \\ \\   _  _\\ \\   __  \\ \\   _  _\\   \\ \\    / /      \\ \\  \\\\|__| \\  \\ \\   __  \\ \\  \\\\ \\  \\ \\   __  \\ \\  \\  __\\ \\  \\_|/_\\ \\   _  _\\  \n" +
                        "  \\ \\  \\____\\ \\  \\ \\  \\|\\  \\ \\  \\\\  \\\\ \\  \\ \\  \\ \\  \\\\  \\|   \\/  /  /        \\ \\  \\    \\ \\  \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \\  \\|\\  \\ \\  \\_|\\ \\ \\  \\\\  \\| \n" +
                        "   \\ \\_______\\ \\__\\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\ \\__\\\\ _\\ __/  / /           \\ \\__\\    \\ \\__\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ _\\ \n" +
                        "    \\|_______|\\|__|\\|_______|\\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\___/ /             \\|__|     \\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|\\|_______|\\|_______|\\|__|\\|__|\n" +
                        "                                                          \\|___|/                                                                                          \n" +
                        "                                                                                                                                                           \n" +
                        "                                                                                                                                                           \n" +
                        "\n";
                return toReturn;
            case 2:
                toReturn = " _     _________________  ___  ________   __ ___  ___  ___   _   _   ___  _____  ___________ \n" +
                        "| |   |_   _| ___ \\ ___ \\/ _ \\ | ___ \\ \\ / / |  \\/  | / _ \\ | \\ | | / _ \\|  __ \\|  ___| ___ \\\n" +
                        "| |     | | | |_/ / |_/ / /_\\ \\| |_/ /\\ V /  | .  . |/ /_\\ \\|  \\| |/ /_\\ \\ |  \\/| |__ | |_/ /\n" +
                        "| |     | | | ___ \\    /|  _  ||    /  \\ /   | |\\/| ||  _  || . ` ||  _  | | __ |  __||    / \n" +
                        "| |_____| |_| |_/ / |\\ \\| | | || |\\ \\  | |   | |  | || | | || |\\  || | | | |_\\ \\| |___| |\\ \\ \n" +
                        "\\_____/\\___/\\____/\\_| \\_\\_| |_/\\_| \\_| \\_/   \\_|  |_/\\_| |_/\\_| \\_/\\_| |_/\\____/\\____/\\_| \\_|\n" +
                        "                                                                                             \n" +
                        "                                                                                             \n" +
                        "\n";
                return toReturn;

            case 3:
                toReturn = " _____   _______ ______ ______ _______ ______ ___ ___      _______ _______ _______ _______ _______ _______ ______ \n" +
                        "|     |_|_     _|   __ \\   __ \\   _   |   __ \\   |   |    |   |   |   _   |    |  |   _   |     __|    ___|   __ \\\n" +
                        "|       |_|   |_|   __ <      <       |      <\\     /     |       |       |       |       |    |  |    ___|      <\n" +
                        "|_______|_______|______/___|__|___|___|___|__| |___|      |__|_|__|___|___|__|____|___|___|_______|_______|___|__|\n" +
                        "                                                                                                                  \n" +
                        "\n";


                return toReturn;
            default:
                return "nope";
        }
    }
}