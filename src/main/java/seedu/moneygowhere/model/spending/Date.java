package seedu.moneygowhere.model.spending;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.moneygowhere.commons.util.DateUtil;
import seedu.moneygowhere.logic.parser.exceptions.ParseException;

/**
 * Represents a Spending's Date in MoneyGoWhere.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {


    public static final String MESSAGE_CONSTRAINTS =
            "Date numbers can be today, yesterday, tomorrow or a formal date DD/MM/YYYY.";
    public final String value;
    public final LocalDate dateValue;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);

        try {
            dateValue = DateUtil.parseDate(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        value = DateUtil.formatDate(dateValue);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return DateUtil.isValidDate(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Date other) {
        return dateValue.compareTo(other.dateValue);
    }

}