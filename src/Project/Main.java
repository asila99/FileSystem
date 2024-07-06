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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = new HashMap<>();
        commands.put("create", new CreateCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("rename", new RenameCommand());
        commands.put("copy", new CopyCommand());

        while (true) {
            System.out.print("Enter command: ");
            String commandLine = scanner.nextLine();
            String[] commandParts = commandLine.split(" ", 2);

            if (commandParts.length < 2) {
                System.out.println("Error, enter command one more time.");
                continue;
            }

            String commandName = commandParts[0];
            Command command = commands.get(commandName);

            if (command == null) {
                System.out.println("Error, enter command one more time.");
                continue;
            }

            try {
                command.execute(commandParts[1]);
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
