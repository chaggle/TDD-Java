package geektime.tdd.args;

import geektime.tdd.args.exceptions.IllegalOptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {
    // Multi Option: -l -p 8080 -d /usr/logs
    @Test
    public void should_parse_multi_options() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    static record Options(@Option("l") boolean logging, @Option("p") int port,@Option("d") String directory) {}

    @Test
    public void should_throw_illegal_option_exception_if_annotation_not_present() {
        IllegalOptionException e =  assertThrows(IllegalOptionException.class, () -> Args.parse(OptionsWithNoAnnotation.class,
                        "-l", "-p", "8080", "-d", "/usr/logs"));

        assertEquals("port", e.getParameter());
    }

    static record OptionsWithNoAnnotation(@Option("l") boolean logging,  int port,@Option("d") String directory) {}
    // 两个大步子，不适合作为小步迭代的方式。

    @Test
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertArrayEquals(new Integer[]{1, 2, -3, 5}, options.decimals());
    }

    static record ListOptions(@Option("g") String[] group, @Option("d") Integer[] decimals) {}

}
