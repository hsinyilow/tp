package seedu.tassist.logic.parser;

import static seedu.tassist.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tassist.logic.parser.CliSyntax.PREFIX_EXTENSION;
import static seedu.tassist.logic.parser.CliSyntax.PREFIX_FILENAME;

import java.util.stream.Stream;

import seedu.tassist.logic.commands.ExportDataCommand;
import seedu.tassist.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportDataCommand object.
 */
public class ExportDataCommandParser implements Parser<ExportDataCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportDataCommand
     * and returns an ExportDataCommand object for execution.
     *
     * @param args String input to be parsed
     * @return ExportDataCommand corresponding to the String input
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ExportDataCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILENAME,
                PREFIX_EXTENSION);

        if (!arePrefixesPresent(argMultimap, PREFIX_FILENAME, PREFIX_EXTENSION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportDataCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FILENAME, PREFIX_EXTENSION);

        String fileName = argMultimap.getValue(PREFIX_FILENAME).orElse("");
        String extension = argMultimap.getValue(PREFIX_EXTENSION).orElse("")
                .toLowerCase().trim();

        return new ExportDataCommand(fileName, extension);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                              Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(
                prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
