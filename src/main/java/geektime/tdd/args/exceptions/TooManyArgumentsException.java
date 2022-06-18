package geektime.tdd.args.exceptions;

/**
 * @author chaggle
 *
 */
public class TooManyArgumentsException extends RuntimeException {
    private final String option;

    public TooManyArgumentsException(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}