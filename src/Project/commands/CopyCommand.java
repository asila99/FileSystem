package Project.commands;

import Project.exceptions.CommandException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyCommand implements Command {
    @Override
    public void execute(String args) throws CommandException {
        String[] parts = args.split(" ", 3);
        if (parts.length != 3) {
            throw new CommandException("Error, enter command one more time.");
        }

        String fileName = parts[0];
        String sourceDirPath = parts[1];
        String targetDirPath = parts[2];
        File sourceDir = new File(sourceDirPath);
        File targetDir = new File(targetDirPath);

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            throw new CommandException("Error, source directory is not found.");
        }

        if (!targetDir.exists() || !targetDir.isDirectory()) {
            throw new CommandException("Error, target directory is not found.");
        }

        File sourceFile = new File(sourceDir, fileName);
        if (!sourceFile.exists()) {
            throw new CommandException("Error, file is not found.");
        }

        File targetFile = new File(targetDir, fileName);
        try {
            if (targetFile.createNewFile()) {
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Done, file " + fileName + " is copied.");
            } else {
                int counter = 2;
                while (true) {
                    String newFileName = getNewFileName(fileName, counter);
                    targetFile = new File(targetDir, newFileName);
                    if (targetFile.createNewFile()) {
                        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Done, file " + newFileName + " is copied.");
                        break;
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            throw new CommandException("Error, could not copy file.");
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