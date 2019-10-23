package seedu.moneygowhere.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.moneygowhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.moneygowhere.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.moneygowhere.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.moneygowhere.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.moneygowhere.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.LinkedHashSet;

import seedu.moneygowhere.logic.commands.SortCommand;
import seedu.moneygowhere.logic.parser.exceptions.ParseException;
import seedu.moneygowhere.logic.sorting.SortAttribute;
import seedu.moneygowhere.logic.sorting.SortField;
import seedu.moneygowhere.logic.sorting.SortOrder;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String[] fieldWords = args.split("\\s+");
        LinkedHashSet<SortField> fields = new LinkedHashSet<>();

        for (String field : fieldWords) {
            if (field.isEmpty()) {
                continue;
            }

            if (field.startsWith(PREFIX_DATE.getPrefix())) {
                String trimmedPrefix = field.substring(PREFIX_DATE.getPrefix().length());
                SortOrder order = convert(trimmedPrefix);
                fields.add(new SortField(SortAttribute.DATE, order));

            } else if (field.startsWith(PREFIX_COST.getPrefix())) {
                String trimmedPrefix = field.substring(PREFIX_COST.getPrefix().length());
                SortOrder order = convert(trimmedPrefix);
                fields.add(new SortField(SortAttribute.COST, order));

            } else if (field.startsWith(PREFIX_NAME.getPrefix())) {
                String trimmedPrefix = field.substring(PREFIX_NAME.getPrefix().length());
                SortOrder order = convert(trimmedPrefix);
                fields.add(new SortField(SortAttribute.NAME, order));

            } else if (field.startsWith(PREFIX_REMARK.getPrefix())) {
                String trimmedPrefix = field.substring(PREFIX_REMARK.getPrefix().length());
                SortOrder order = convert(trimmedPrefix);
                fields.add(new SortField(SortAttribute.REMARK, order));

            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        if (fields.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(fields);
    }

    /**
     * Converts an order string to a valid SortOrder.
     * @param order Order
     * @return SortOrder enum
     * @throws ParseException If the order input is invalid
     */
    private static SortOrder convert(String order) throws ParseException {
        switch (order.toUpperCase()) {
        case "DESC":
            return SortOrder.DESCENDING;
        case "ASC":
            return SortOrder.ASCENDING;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
