package geektime.tdd.args.exceptions;

public class UnsupportedOptionTypeException extends RuntimeException {

    private final String option;
    private final Class<?> value;

    public UnsupportedOptionTypeException(String option, Class<?> value) {
        this.option = option;
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public Class<?> getValue() {
        return value;
    }
}
