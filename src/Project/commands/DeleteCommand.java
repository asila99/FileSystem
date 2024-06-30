package Project.commands;


import Project.exceptions.CommandException;

import java.io.File;

public class DeleteCommand implements Command {
    @Override
    public void execute(String args) throws CommandException {
        String[] parts = args.split(" ", 2);
        if (parts.length != 2) {
            throw new CommandException("Error, enter command one more time.");
        }

        String fileName = parts[0];
        String directoryPath = parts[1];
        File file = new File(directoryPath, fileName);

        if (!file.exists()) {
            throw new CommandException("Error, file is not found.");
        } else if (file.delete()) {
            System.out.println("Done, file " + fileName + " is deleted.");
        } else {
            throw new CommandException("Error, could not delete file.");
        }
    }
}
