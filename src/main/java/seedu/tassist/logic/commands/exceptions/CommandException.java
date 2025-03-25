package seedu.tassist.logic.commands.exceptions;

import seedu.tassist.model.Operations;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
        Operations.removeRecording();
    }

    /**
     * Constructs a new {@code CommandException} with the
     * specified detail {@code message} and {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
        Operations.removeRecording();
    }
}
