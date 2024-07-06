package Project.commands;

import Project.exceptions.CommandException;

import java.io.File;

public class RenameCommand implements Command {
    @Override
    public void execute(String args) throws CommandException {
        String[] parts = args.split(" ", 3);
        if (parts.length != 3) {
            throw new CommandException("Error, enter command one more time.");
        }

        String directoryPath = parts[0];
        String oldFileName = parts[1];
        String newFileName = parts[2];

        File oldFile = new File(directoryPath, oldFileName);
        File newFile = new File(directoryPath, newFileName);

        if (!oldFile.exists()) {
            throw new CommandException("Error, file is not found.");
        } else if (oldFile.renameTo(newFile)) {
            System.out.println("Done, file " + oldFileName + " is renamed. New name is " + newFileName);
        } else {
            throw new CommandException("Error, could not rename file.");
        }
    }
}
