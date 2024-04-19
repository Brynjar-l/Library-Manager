# Library Manager

Library Manager is a Java based tool to help manage and organize digital records for a library. The project was built using Maven and tested with JUnit 4, this project features a TUI crafted using ConsoleUI, which leverages JLine and Jansi for a clean and efficient user experience.

This project uses ![ConsoleUI](https://github.com/awegmann/consoleui) to create it's TUI components
ConsoleUI is distributed under an ![Apache License 2.0](https://github.com/awegmann/consoleui/blob/master/LICENSE.txt)

## License

![x11 License](LICENSE)

### Prerequisites

- Java JDK 17 or later
- Maven
- Any non-IDE terminal

### Installing

To set up the Library Manager on your local machine, follow these steps:

1. Clone the repository to your local machine using Git:
```console
foo@bar:~/Library Manager$ git clone https://github.com/Brynjar-l/Final-Project.git
```
2. Compile the program using maven:
```console
foo@bar:~/Library Manager$ mvn clean compile assembly:single
```
3. run the program:
```console
foo@bar:~/Library Manager$ java -jar target/FinalProject-1.0-SNAPSHOT-jar-with-dependencies.jar
```
4. run the tests:
```console
foo@bar:~/Library Manager$ mvn test
```
