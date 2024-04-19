package is.hi.hbv202g.assignment8;

import de.codeshelf.consoleui.elements.ConfirmChoice;
import de.codeshelf.consoleui.prompt.*;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import static org.fusesource.jansi.Ansi.ansi;


public class Main {
    public static void main(String[] args) throws InterruptedException, UserOrBookDoesNotExistException {
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

        librarySystem.borrowBook((Student)librarySystem.getUser("John"), librarySystem.getBook("The secret garden"));


        boolean forCreatedBook = false;
        boolean forAssignedBook = false;
        boolean globalLoops2 = false;
        boolean globalnewUserBool = false;
        boolean globalnewFacultyBool = false;
        boolean globalLoops3 = false;
        boolean globalLoopBookReturned = false;

        String globalRemovedUser = null;
        String globalnewFaculty = null;
        String studentForAssignedBookText = null;
        Book bookForAssignedBookText = null;
        String globalnewTitle = null;
        String globalnewTitle2 = null;
        String globalnewUser = null;


        boolean loops = true;
        while (loops) {
            try {
                ConsolePrompt prompt = new ConsolePrompt();
                PromptBuilder promptBuilder = prompt.getPromptBuilder();

                forCreatedBook = false;
                forAssignedBook = false;
                globalLoops2 = false;
                globalnewUserBool = false;
                globalnewFacultyBool = false;
                globalLoops3 = false;
                globalLoopBookReturned = false;

                promptBuilder.createListPrompt()
                        .name("selectionChoice")
                        .message("MENU")
                        .newItem("Search").text("1. Search").add()

                        .newItem("User Management").text("2. User Management").add()
                        .newItem("Book Management").text("3. Book Management").add()

                        .newItem("Exit").text("Exit").add()
                        .addPrompt();

                HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build()); // #5
                String selected = ((ListResult) result.get("selectionChoice")).getSelectedId();


                switch (selected) {

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
                        selected = ((ListResult) result.get("SearchChoice")).getSelectedId();

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
                                    String inputSelected = ((InputResult) inputResult.get("nameInput")).getInput();


                                    try {
                                        Book book = librarySystem.findBookByTitle(inputSelected);       // TRY CATCHED
                                        Book bookForLater = book;

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
                                            String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("lendBook")).getConfirmed());


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
                                                    inputSelected = ((InputResult) inputResult.get("StudentUser")).getInput();

                                                    for (User user : librarySystem.getUsers()) {
                                                        if (user instanceof Student && user.getName().equalsIgnoreCase(inputSelected)) {
                                                            librarySystem.borrowBook((Student) user, book);

                                                            forAssignedBook = true;
                                                            studentForAssignedBookText = user.getName();
                                                            bookForAssignedBookText = book;

                                                            loops4 = false;
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
                                                        confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("TryAgain")).getConfirmed());

                                                        if (confirmSelected.equals("NO")) {
                                                            loops4 = false;
                                                        }
                                                    }
                                                }
                                            }

                                            System.out.println(ansi().eraseScreen());
                                            System.out.println(ansi().render(introText(3)));
                                            if (forAssignedBook) {
                                                System.out.println(ansi().render("Book assigned to " + studentForAssignedBookText + ". Due by " + librarySystem.searchLending(bookForAssignedBookText).getDueDate()));
                                            }
                                        } else {
                                            prompt = new ConsolePrompt();
                                            promptBuilder = prompt.getPromptBuilder();

                                            promptBuilder.createConfirmPromp()
                                                    .name("ReturnBook")
                                                    .message("Would you like to return the book?")
                                                    .defaultValue(ConfirmChoice.ConfirmationValue.NO)
                                                    .addPrompt();

                                            HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                            String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("ReturnBook")).getConfirmed());

                                            if (confirmSelected.equals("YES")) {
                                                librarySystem.returnBook(bookForLater);
                                                globalLoopBookReturned = true;
                                                loops1 = false;

                                                System.out.println(ansi().eraseScreen());
                                                System.out.println(ansi().render(introText(3)));
                                            }
                                        }

                                        loops1 = false;
                                        break;
                                    } catch (Exception ex1) {
                                        prompt = new ConsolePrompt();
                                        promptBuilder = prompt.getPromptBuilder();

                                        promptBuilder.createConfirmPromp()
                                                .name("goBack")
                                                .message("Book does not exist. Try again?")
                                                .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                                                .addPrompt();

                                        HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                        String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("goBack")).getConfirmed());

                                        if (confirmSelected.equals("YES")) {
                                            loops1 = true;
                                        } else {
                                            loops1 = false;
                                            System.out.println(ansi().eraseScreen());
                                            System.out.println(ansi().render(introText(3)));
                                        }
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
                        prompt = new ConsolePrompt();
                        promptBuilder = prompt.getPromptBuilder();

                        promptBuilder.createListPrompt()
                                .name("UserManager")
                                .message("Select an Option")
                                .newItem("1. Create new User").name("CreateNewUser").add()
                                .newItem("2. Remove User").name("RemoveUser").add()
                                .newItem("3. See all Users").name("UserList").add()
                                .newItem("Back").name("Back").add()
                                .addPrompt();

                        result = prompt.prompt(promptBuilder.build());
                        selected = ((ListResult) result.get("UserManager")).getSelectedId();

                        switch (selected) {

                            case "CreateNewUser":

                                prompt = new ConsolePrompt();
                                promptBuilder = prompt.getPromptBuilder();

                                promptBuilder.createListPrompt()
                                        .name("TypeOfNewUser")
                                        .message("Pick Type of User")
                                        .newItem("1. Student User").name("StudentUser").add()
                                        .newItem("2. Faculty User").name("FacultyUser").add()
                                        .newItem("Cancel").name("Cancel").add()
                                        .addPrompt();

                                result = prompt.prompt(promptBuilder.build());
                                selected = ((ListResult) result.get("TypeOfNewUser")).getSelectedId();

                                switch (selected) {
                                    case "StudentUser":
                                        prompt = new ConsolePrompt();
                                        promptBuilder = prompt.getPromptBuilder();

                                        //

                                        promptBuilder.createInputPrompt()
                                                .name("UserInput")
                                                .message("User Name: ")
                                                .addPrompt();

                                        HashMap<String, ? extends PromtResultItemIF> inputResult = prompt.prompt(promptBuilder.build());
                                        String inputSelected = ((InputResult) inputResult.get("UserInput")).getInput();

                                        String newUser = inputSelected;
                                        globalnewUser = newUser;
                                        globalnewUserBool = true;

                                        librarySystem.addStudentUser(newUser, true);
                                        break;

                                    case "FacultyUser":
                                        prompt = new ConsolePrompt();
                                        promptBuilder = prompt.getPromptBuilder();

                                        //

                                        promptBuilder.createInputPrompt()
                                                .name("FacultyInput")
                                                .message("User Name: ")
                                                .addPrompt();

                                        inputResult = prompt.prompt(promptBuilder.build());
                                        inputSelected = ((InputResult) inputResult.get("FacultyInput")).getInput();

                                        String newFaculty = inputSelected;
                                        globalnewFaculty = newFaculty;
                                        globalnewFacultyBool = true;

                                        prompt = new ConsolePrompt();
                                        promptBuilder = prompt.getPromptBuilder();

                                        //

                                        promptBuilder.createInputPrompt()
                                                .name("DepartmentInput")
                                                .message("Department name: ")
                                                .addPrompt();

                                        inputResult = prompt.prompt(promptBuilder.build());
                                        inputSelected = ((InputResult) inputResult.get("DepartmentInput")).getInput();

                                        String newDepartment = inputSelected;

                                        librarySystem.addFacultyMemberUser(newFaculty, newDepartment);

                                        break;

                                    case "Cancel":
                                        break;
                                }

                                break;


                            case "RemoveUser":
                                boolean loops3 = true;

                                while(loops3) {
                                    prompt = new ConsolePrompt();
                                    promptBuilder = prompt.getPromptBuilder();

                                    //

                                    promptBuilder.createInputPrompt()
                                            .name("UserToRemoveInput")
                                            .message("User: ")
                                            .addPrompt();

                                    HashMap<String, ? extends PromtResultItemIF> inputResult = prompt.prompt(promptBuilder.build());
                                    String inputSelected = ((InputResult) inputResult.get("UserToRemoveInput")).getInput();
                                    String UserToRemove = inputSelected;

                                    Iterator<User> iterator = librarySystem.getUsers().iterator();
                                    while (iterator.hasNext()) {
                                        User user = iterator.next();
                                        if (user.getName().equalsIgnoreCase(UserToRemove)) {
                                            iterator.remove();
                                            librarySystem.removeBookFromDatabase(UserToRemove); //////
                                            loops3 = false; //////
                                            globalLoops3 = true;    //////
                                            globalRemovedUser = user.getName();  //////
                                            break;
                                        }
                                    }

                                    if (loops3) {
                                        prompt = new ConsolePrompt();
                                        promptBuilder = prompt.getPromptBuilder();

                                        promptBuilder.createConfirmPromp()
                                                .name("goBack")
                                                .message("Go Back?")
                                                .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                                                .addPrompt();

                                        HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                        String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("goBack")).getConfirmed());

                                        if (confirmSelected.equals("YES")) {
                                            loops3 = false;

                                            System.out.println(ansi().eraseScreen());
                                            System.out.println(ansi().render(introText(3)));
                                        }
                                    }
                                }

                                break;


                            case "UserList":
                                System.out.println(ansi().render("FACULTY MEMBERS: "));
                                for (User user : librarySystem.getUsers()) {
                                    if (user instanceof FacultyMember) {
                                        System.out.println(ansi().render(user.getName() + ", Department of " + ((FacultyMember) user).getDepartment()));
                                    }
                                }

                                System.out.println(ansi().render("\nSTUDENTS: "));
                                for (User user : librarySystem.getUsers()) {
                                    if (user instanceof Student) {
                                        System.out.print(ansi().render(user.getName()));
                                        if (!((Student)user).isFeePaid()) {
                                            System.out.println(ansi().render(", " + ((Student) user).getBookRented().getTitle() + " Due by " + librarySystem.searchLending(((Student) user).getBookRented()).getDueDate()));

                                        } else {
                                            System.out.println(ansi().render(", no books due"));
                                        }
                                    }
                                }

                                boolean keepGoing2 = true;

                                while(keepGoing2) {
                                    prompt = new ConsolePrompt();
                                    promptBuilder = prompt.getPromptBuilder();

                                    promptBuilder.createConfirmPromp()
                                            .name("goBack")
                                            .message("Go Back?")
                                            .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                                            .addPrompt();

                                    HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                    String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("goBack")).getConfirmed());

                                    if (confirmSelected.equals("YES")) {
                                        keepGoing2 = false;

                                        // REMOVED ANSI ERASE
                                    }
                                }




                                break;

                            case "Back":
                                break;

                        }

                        System.out.println(ansi().eraseScreen());
                        System.out.println(ansi().render(introText(3)));
                        if (globalnewUserBool) {
                            System.out.println(ansi().render("User: " + globalnewUser + " created!"));
                        }
                        if (globalnewFacultyBool) {
                            System.out.println(ansi().render("User: " + globalnewFaculty + " created!"));
                        }

                        if (globalLoops3) {
                            System.out.println(ansi().render("User: " + globalRemovedUser + " created!"));
                        }


                        break;

                    case "Book Management":         // FINISHED

                        prompt = new ConsolePrompt();
                        promptBuilder = prompt.getPromptBuilder();

                        promptBuilder.createListPrompt()
                                .name("BookManager")
                                .message("Select an Option")
                                .newItem("1. Create new Book").name("CreateNewBook").add()
                                .newItem("2. Remove Book").name("RemoveBook").add()
                                .newItem("3. See all Books").name("BookList").add()
                                .newItem("Back").name("Back").add()
                                .addPrompt();

                        result = prompt.prompt(promptBuilder.build());
                        selected = ((ListResult) result.get("BookManager")).getSelectedId();


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
                                String inputSelected = ((InputResult) inputResult.get("TitleInput")).getInput();
                                String newTitle = inputSelected;
                                globalnewTitle = newTitle;

                                prompt = new ConsolePrompt();
                                promptBuilder = prompt.getPromptBuilder();

                                promptBuilder.createInputPrompt()
                                        .name("AuthorsInput")
                                        .message("Authors/s: ")
                                        .defaultValue("Seperate authors via a comma (,)")
                                        .addPrompt();

                                inputResult = prompt.prompt(promptBuilder.build());
                                inputSelected = ((InputResult) inputResult.get("AuthorsInput")).getInput();

                                String newAuthors = inputSelected;
                                String[] newAuthorsArray = newAuthors.split(",");

                                librarySystem.addBookWithTitleAndUnspecifiedAmountOfAuthors(newTitle, newAuthorsArray);
                                forCreatedBook = true;




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
                                    inputSelected = ((InputResult) inputResult.get("toRemoveInput")).getInput();
                                    String titleToRemove = inputSelected;

                                    Iterator<Book> iterator = librarySystem.getBooks().iterator();
                                    while (iterator.hasNext()) {
                                        Book book = iterator.next();
                                        if (book.getTitle().equalsIgnoreCase(titleToRemove)) {
                                            iterator.remove();
                                            librarySystem.removeBookFromDatabase(titleToRemove);
                                            loops2 = false;
                                            globalLoops2 = true;
                                            globalnewTitle2 = book.getTitle();
                                            break;
                                        }
                                    }

                                    if (loops2) {
                                        prompt = new ConsolePrompt();
                                        promptBuilder = prompt.getPromptBuilder();

                                        promptBuilder.createConfirmPromp()
                                                .name("goBack")
                                                .message("Go Back?")
                                                .defaultValue(ConfirmChoice.ConfirmationValue.YES)
                                                .addPrompt();

                                        HashMap<String, ? extends PromtResultItemIF> confirmResult = prompt.prompt(promptBuilder.build());
                                        String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("goBack")).getConfirmed());

                                        if (confirmSelected.equals("YES")) {
                                            loops2 = false;

                                            System.out.println(ansi().eraseScreen());
                                            System.out.println(ansi().render(introText(3)));
                                        }
                                    }



                                }

                                break;
                            case "BookList":            // FINISHED
                                for (Book book : librarySystem.getBooks()) {

                                    if (librarySystem.inLending(book)) {
                                        System.out.println(ansi().render("(Due " + librarySystem.searchLending(book).getDueDate() + ") " + book.getTitle() + ", by " + book.getAuthors()));
                                    } else {
                                        System.out.println(ansi().render("(Available) " + book.getTitle() + ", by " + book.getAuthors()));
                                    }
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
                                    String confirmSelected = String.valueOf(((ConfirmResult) confirmResult.get("goBack")).getConfirmed());

                                    if (confirmSelected.equals("YES")) {
                                        keepGoing = false;

                                        // REMOVED ANSI ERASE
                                    }
                                }

                                break;

                            case "Back":
                                System.out.println(ansi().eraseScreen());
                                System.out.println(ansi().render(introText(3)));
                                break;
                        }

                        System.out.println(ansi().eraseScreen());
                        System.out.println(ansi().render(introText(3)));
                        if (forCreatedBook) {
                            System.out.println(ansi().render("Book: " + globalnewTitle + " created!"));
                        }
                        if (globalLoops2) {
                            System.out.println(ansi().render("Book: " + globalnewTitle2 + " removed!"));
                        }

                        break;
                    case "Exit":    // FINISHED
                            System.out.println(ansi().eraseScreen());
                            loops = false;
                        break;
                    default:
                        break;
                }

                if (globalLoopBookReturned) {
                    System.out.println(ansi().render("Book was returned"));
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (UserOrBookDoesNotExistException e) {
                throw new RuntimeException(e);
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