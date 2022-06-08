package geektime.tdd.args;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author chaggle
 */
public class Args {
    public static <T> T parse(Class<T> optionClass, String... args) {
        try{
            List<String> arguments = Arrays.asList(args);
            Constructor<?> constructor = optionClass.getDeclaredConstructors()[0];

            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();

            return (T) constructor.newInstance(values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object parseOption(List<String> arguments, Parameter parameter) {
        return getOptionParser(parameter.getType()).parse(arguments, parameter.getAnnotation(Option.class));
    }

    private static Map<Class<?>, OptionParse> PARSER = Map.of(
            boolean.class, new BooleanParser(),
            int.class, new SingleValueOptionParser<>(Integer::parseInt),
            String.class, new SingleValueOptionParser<>(String::valueOf));

    private static OptionParse getOptionParser(Class<?> type) {
        return PARSER.get(type);
    }
}

