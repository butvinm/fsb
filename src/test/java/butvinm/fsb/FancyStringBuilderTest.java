package butvinm.fsb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FancyStringBuilderTest {
    private FancyStringBuilder fsb;

    @BeforeEach
    public void setUp() {
        fsb = new FancyStringBuilder();
    }

    @Test
    public void testConstructors() {
        var fsb0 = new FancyStringBuilder();
        var fsb1 = new FancyStringBuilder(new StringBuilder());
        var fsb2 = FancyStringBuilder.fsb();
        var fsb3 = FancyStringBuilder.fsb(new StringBuilder());
    }
    @Test
    public void testAppendObject() {
        fsb.a(123);
        assertEquals("123", fsb.toString());
    }

    @Test
    public void testAppendStringWithFormatting() {
        fsb.a("Value: %d", 123);
        assertEquals("Value: 123", fsb.toString());
    }

    @Test
    public void testAppendObjectWithLineBreak() {
        fsb.l(123);
        assertEquals("123\n", fsb.toString());
    }

    @Test
    public void testAppendFormattedStringWithLineBreak() {
        fsb.l("Value: %d", 123);
        assertEquals("Value: 123\n", fsb.toString());
    }

    @Test
    public void testAppendSpace() {
        fsb.s();
        assertEquals(" ", fsb.toString());
    }

    @Test
    public void testAppendLineBreak() {
        fsb.n();
        assertEquals("\n", fsb.toString());
    }

    @Test
    public void testAppendTabulation() {
        fsb.t();
        assertEquals("\t", fsb.toString());
    }

    @Test
    public void testIndentation() {
        fsb
            .bb()
                .l("Indented")
                .a("Indented").s().a("Same line")
            .eb()
            .a("Not indented");

        assertEquals(
            "\tIndented\n\tIndented Same lineNot indented",
            fsb.toString()
        );
    }

    @Test
    public void testComplexScenario() {
        var expected = "" +
            "You can make classes,\n " +
            "but you can only ever make one instance of them.\n" +
            "\t\tThis shouldn't affect how most object-oriented programmers work" +
            ".\n" +
            ":3\n";

        fsb
            .l("You can make classes,").s()
            .a("but %s %s", "you can only", "ever make").s()
            .a("one instance of them.")
            .n()
            .bb()
            .t()
            .a("This shouldn't affect how most object-oriented programmers work")
            .eb()
            .a(".").n()
            .l(":3");

        assertEquals(expected, fsb.toString());
    }

    @Test
    public void testNegativeIndentationThrowsException() {
        FancyStringBuilder builder = new FancyStringBuilder();
        assertThrows(RuntimeException.class, () -> {
            builder.eb();
        });
    }
}
