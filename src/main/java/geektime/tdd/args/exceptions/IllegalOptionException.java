package geektime.tdd.args.exceptions;

/**
 * @author chaggle
 */
public class IllegalOptionException extends RuntimeException {
    private final String parameter;

    public IllegalOptionException(String option) {
        this.parameter = option;
    }

    public String getParameter() {
        return parameter;
    }
}

