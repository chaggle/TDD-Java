package geektime.tdd.args;

import java.util.List;

interface OptionParse {
    Object parse(List<String> arguments, Option option);
}
