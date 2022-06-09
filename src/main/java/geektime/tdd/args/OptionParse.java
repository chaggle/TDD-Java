package geektime.tdd.args;

import java.util.List;

interface OptionParse<T> {
    T parse(List<String> arguments, Option option);
}
