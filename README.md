# Library Manager

Library Manager is a Java based tool to help manage and organize digital records for a library. The project was built using Maven and tested with JUnit 4, this project features a TUI crafted using ConsoleUI, which leverages JLine and Jansi for a clean and efficient user experience.

This project uses ![ConsoleUI](https://github.com/awegmann/consoleui) to create it's TUI components.
ConsoleUI is distributed under an ![Apache License 2.0](https://github.com/awegmann/consoleui/blob/master/LICENSE.txt).

## Good to know
When you launch Library Manager. you will be greeted by 4 options: 1. Search, 2. User Management, 3. Book Management and EXIT.
User management and Book management have the same features, just for their respective Objects. both allow to see a list of all objects, create new objects and remove objects. Listing objects will include if book is lent or if Student has a book lent at the moment.
When you want to lend out a book or return a book, you use the search feature, search a book and if its available you will be prompted if you want to rent it and then you have to assign it to a student, each student can only rent out one book at a time. if the book you typed in is already lent out, you will be prompted with the option to return it.
If you do not know what books are available, go see the book list in Book Manager, this is due to an unforeseen problem with ConsoleUI which didnt not allow us to make Lists interactive.

## License

![MIT License](LICENSE)

### Prerequisites

- Java JDK 17 or later
- Maven
- Any non-IDE terminal

### Installing, Compiling and Executing

To set up the Library Manager on your local machine, follow these steps:
> [!IMPORTANT]
> Take note that this program only works in **non-IDE** terminals.

1. Clone the repository to your local machine using Git:
```console
foo@bar:~/Library Manager$ git clone https://github.com/Brynjar-l/Final-Project.git
```
2. Compile the program using maven:
```console
foo@bar:~/Library Manager$ mvn clean compile assembly:single
```
3. Run the program:
```console
foo@bar:~/Library Manager$ java -jar target/FinalProject-1.0-SNAPSHOT-jar-with-dependencies.jar
```
4. Run the tests:
```console
foo@bar:~/Library Manager$ mvn test
```

## Authors
Hópur 20
- Brynjar Sighvatsson [(brs87@hi.is)](mailto:brs87@hi.is)
- Daníel Andri Styrmisson [(das68@hi.is)](mailto:das68@hi.is)
