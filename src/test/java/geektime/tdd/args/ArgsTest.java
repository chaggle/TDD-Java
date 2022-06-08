package geektime.tdd.args;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {
    // -l -p 8080 -d /usr/logs

    // 小步迭代，single Option, Multi Option
    // TODO Default:
    // TODO       - Bool: false
    // TODO       - Integer: 0
    // TODO       - String: ""
    // Single Option:
    // - Bool: -l
    @Test
    public void should_set_boolean_to_true_if_flag_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");

        assertTrue(option.logging());
    }

    @Test
    public void should_set_boolean_to_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class);

        assertFalse(option.logging());
    }

    static record BooleanOption(@Option("l") boolean logging) {}
    //- Integer: -p 8080

    @Test
    public void should_parse_int_as_option_value() {
        IntOption option = Args.parse(IntOption.class, "-p", "8080");

        assertEquals(8080, option.port());
    }

    static record  IntOption(@Option("p") int port){}

    // 以上为 happy path 的情况
    // - String: -d /usr/logs
    @Test
    public void should_get_string_as_option_value() {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr/logs");

        assertEquals("/usr/logs", option.directory());
    }

    static record StringOption(@Option("d") String directory) {}


    // TODO Multi Option: -l -p 8080 -d /usr/logs
    @Test
    public void should_parse_multi_options() {
        Options options = Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.directory());
    }

    static record Options(@Option("l") boolean logging, @Option("p") int port,@Option("d") String directory) {}

    // 以下为 sad path 的情况 
    // TODO Sad path:
    // TODO       - Bool: -l t / -l t f
    // TODO       - Integer: -p / / -p 8080 8081
    // TODO       - String: -d /  / -d /usr/logs /usr/vars

    // 两个大步子，不适合作为小步迭代的方式。


    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "8080", "-d", "1", "2", "-3", "5");

        assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        assertEquals(new int[] {1, 2, -3, 5}, options.decimals());
    }

    static record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {}

}
