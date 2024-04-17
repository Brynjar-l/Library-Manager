package is.hi.hbv202g.assignment8;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        AnsiConsole.systemInstall();
        System.out.println(ansi().render(introText(3)));

        LibrarySystem librarySystem = new LibrarySystem();


        try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();


            promptBuilder.createListPrompt()
                    .name("selectionChoice")
                    .message("TB")
                    .newItem("Book Lending").text("1. Book Lending").add()
                    .newItem("Search").text("2. Search").add()

                    .newItem("User Management").text("3. User Management").add()
                    .newItem("Book Management").text("4. Book Management").add()

                    .newItem("Exit").text("Exit").add()
                    .addPrompt();

            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build()); // #5
            String selected = ((de.codeshelf.consoleui.prompt.ListResult)result.get("selectionChoice")).getSelectedId();

            System.out.println(ansi().render(selected)); // TEST CASE TODO: REMOVE

            switch (selected){
                case "Book Lending":
                    System.out.println(ansi().render("1"));
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
                    selected = ((de.codeshelf.consoleui.prompt.ListResult)result.get("SearchChoice")).getSelectedId();

                    switch (selected) {
                        case "Books":
                            prompt = new ConsolePrompt();
                            promptBuilder = prompt.getPromptBuilder();

                            promptBuilder.createInputPrompt()
                                    .name("nameInput")
                                    .message("Search a Book:")
                                    .defaultValue("Lord Of The Rings")
                                    .addPrompt();

                            result = prompt.prompt(promptBuilder.build());
                            selected = ((de.codeshelf.consoleui.prompt.ListResult)result.get("nameInput")).getSelectedId();

                            System.out.println(ansi().render(selected));


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
                            // TODO GO BACK
                            break;

                        default:
                            break;
                    }


                    break;
                case "User Management":
                    System.out.println(ansi().render("3"));
                    break;
                case "Book Management":
                    System.out.println(ansi().render("4"));
                    break;

                case "Exit":

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