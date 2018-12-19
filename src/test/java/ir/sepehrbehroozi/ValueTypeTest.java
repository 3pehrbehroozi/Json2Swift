package ir.sepehrbehroozi;

import org.junit.Test;

import static org.junit.Assert.*;


public class ValueTypeTest {

    @Test
    public void testGetSwiftTypeString() {
        assertEquals(ValueType.DOUBLE.getSwiftTypeString(), "Double");
        assertEquals(ValueType.INT.getSwiftTypeString(), "Int");
        assertEquals(ValueType.STRING.getSwiftTypeString(), "String");
        assertEquals(ValueType.OBJECT.getSwiftTypeString(), "");
        assertEquals(ValueType.ARRAY.getSwiftTypeString(), "");
    }
}
