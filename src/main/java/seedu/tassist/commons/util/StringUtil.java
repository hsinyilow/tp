package seedu.tassist.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.tassist.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1,
                "Word parameter should be a single word");

        String[] wordsInPreppedSentence = sentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer.
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed),
     * "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);
        try {
            int value = Integer.parseInt(s);
            // "+1" is successfully parsed by Integer#parseInt(String)
            return value > 0 && !s.startsWith("+");
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} represents a valid week.
     * e.g. 1, 2, 3, ..., 13 <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed),
     * "3 0" (contains whitespace), "1 a" (contains letters),
     * "14" (greater than 13)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isValidWeek(String s) {
        requireNonNull(s);
        try {
            int value = Integer.parseInt(s);
            // "+1" is successfully parsed by Integer#parseInt(String)
            return value > 0 && value < 14
                    && !s.startsWith("+");
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code text} contains {@code word}, ignoring case.
     */
    public static boolean containsIgnoreCase(String text, String word) {
        requireNonNull(text);
        requireNonNull(word);
        return text.toLowerCase().contains(word.toLowerCase());
    }
}
