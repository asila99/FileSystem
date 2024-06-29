package Project.commands;

import Project.exceptions.CommandException;

public interface Command {
    void execute(String args) throws CommandException;
}
