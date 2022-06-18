package geektime.tdd.args.exceptions;

/**
 * @author chaggle
 */
public class InsufficientArgumentsException extends RuntimeException {
    private final String option;

    public InsufficientArgumentsException(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
