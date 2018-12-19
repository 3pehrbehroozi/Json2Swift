package ir.sepehrbehroozi;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsTest {
    @Test
    public void testToLowerCaseFirstChar() {
        assertEquals(Utils.toLowerCaseFirstChar("ASD"), "aSD");
        assertEquals(Utils.toLowerCaseFirstChar("asd"), "asd");
        assertEquals(Utils.toLowerCaseFirstChar("AsD"), "asD");
        assertEquals(Utils.toLowerCaseFirstChar("aSD"), "aSD");
    }

    @Test
    public void testToUpperCaseFirstChar() {
        assertEquals(Utils.toUpperCaseFirstChar("ASD"), "ASD");
        assertEquals(Utils.toUpperCaseFirstChar("asd"), "Asd");
        assertEquals(Utils.toUpperCaseFirstChar("AsD"), "AsD");
        assertEquals(Utils.toUpperCaseFirstChar("aSD"), "ASD");
    }
}
