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
    public void testAppendObject() {
        assertNotNull(fsb.a(123));
        assertEquals("123", fsb.toString());
    }

    @Test
    public void testAppendStringWithFormatting() {
        assertNotNull(fsb.a("Value: %d", 123));
        assertEquals("Value: 123", fsb.toString());
    }

    @Test
    public void testAppendObjectWithLineBreak() {
        assertNotNull(fsb.l(123));
        assertEquals("123\n", fsb.toString());
    }

    @Test
    public void testAppendFormattedStringWithLineBreak() {
        assertNotNull(fsb.l("Value: %d", 123));
        assertEquals("Value: 123\n", fsb.toString());
    }

    @Test
    public void testAppendSpace() {
        assertNotNull(fsb.s());
        assertEquals(" ", fsb.toString());
    }

    @Test
    public void testAppendLineBreak() {
        assertNotNull(fsb.n());
        assertEquals("\n", fsb.toString());
    }

    @Test
    public void testAppendTabulation() {
        assertNotNull(fsb.t());
        assertEquals("\t", fsb.toString());
    }

    @Test
    public void testIndentation() {
        assertNotNull(
            fsb
                .bb()
                    .l("Indented")
                    .a("Indented").s().a("Same line")
                .eb()
                .a("Not indented")
        );
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

        assertNotNull(
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
                .l(":3")
        );
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
