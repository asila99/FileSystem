package Project;

import Project.commands.Command;
import Project.commands.CreateCommand;
import Project.commands.DeleteCommand;
import Project.commands.RenameCommand;
import Project.commands.CopyCommand;
import Project.exceptions.CommandException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("create", new CreateCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("rename", new RenameCommand());
        commands.put("copy", new CopyCommand());
    }

    private static void printTutorial() {
        System.out.println("Welcome to the File System Program! Here are the commands you can use:");
        System.out.println("1. Create a file: create <filename> <directory>");
        System.out.println("2. Delete a file: delete <filename> <directory>");
        System.out.println("3. Rename a file: rename <directory> <oldfilename> <newfilename>");
        System.out.println("4. Copy a file: copy <filename> <sourcedirectory> <targetdirectory>");
        System.out.println("5. Stop the program: stop");
        System.out.println("Please enter your command:");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printTutorial();
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("stop")) {
                System.out.println("Program terminated.");
                break;
            }
            String[] parts = input.split(" ", 2);
            if (parts.length < 2) {
                System.out.println("Error, enter command one more time.");
                continue;
            }

            String commandName = parts[0];
            String commandArgs = parts[1];

            Command command = commands.get(commandName);
            if (command == null) {
                System.out.println("Error, enter command one more time.");
                continue;
            }

            try {
                command.execute(commandArgs);
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
