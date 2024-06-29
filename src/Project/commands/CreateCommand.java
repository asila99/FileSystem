package Project.commands;

import Project.exceptions.CommandException;

import java.io.File;
import java.io.IOException;

public class CreateCommand implements Command {
    @Override
    public void execute(String args) throws CommandException {
        String[] parts = args.split(" ", 2);
        if (parts.length != 2) {
            throw new CommandException("Error, enter command one more time.");
        }

        String fileName = parts[0];
        String directoryPath = parts[1];
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new CommandException("Error, enter command one more time.");
        }

        File file = new File(directory, fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("Done, file " + fileName + " is created.");
            } else {
                int counter = 2;
                while (true) {
                    String newFileName = getNewFileName(fileName, counter);
                    file = new File(directory, newFileName);
                    if (file.createNewFile()) {
                        System.out.println("Done, file " + newFileName + " is created.");
                        break;
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            throw new CommandException("Error, could not create file.");
        }
    }

    private String getNewFileName(String fileName, int counter) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return fileName + "-" + counter;
        }
        return fileName.substring(0, dotIndex) + "-" + counter + fileName.substring(dotIndex);
    }
}
