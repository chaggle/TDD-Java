package geektime.tdd.args;

public class IllegalOptionException extends RuntimeException {
    private final String parameter;

    public IllegalOptionException(String option) {
        super(option);
        this.parameter = option;
    }

    public String getParameter() {
        return parameter;
    }
}

