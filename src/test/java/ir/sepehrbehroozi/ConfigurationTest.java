package ir.sepehrbehroozi;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigurationTest {

    static Configuration configuration1;
    static Configuration configuration2;
    static Configuration configuration3;
    static Configuration configuration4;
    static Configuration configuration5;
    static Configuration configuration6;
    static Configuration configuration7;

    @BeforeClass
    public static void testInit() {
        configuration1 = new Configuration(new String[]{"-n", "sepehr"});
        configuration2 = new Configuration(new String[]{"-s", "sample.json"});
        configuration3 = new Configuration(new String[]{"-s", "sample.json", "-d", "sample.swift", "-b", "Sample"});
        configuration4 = new Configuration(new String[]{"-s", "sample.json", "-d", "sample.swift", "-b", "Sample"});
        configuration5 = new Configuration(new String[]{"-s", "sample.json", "-d", "sample.swift", "-b", "Sample"});
        configuration6 = new Configuration(new String[]{"-s", "sample.json", "-d", "sample.swift", "-b", "Sample"});
        configuration7 = new Configuration(new String[]{"-s", "sample.json", "-d", "sample.swift", "-b", "Sample"});
    }

    @Test
    public void testNameMap() {
        assertFalse(configuration1.getNameMap().isEmpty());
        assertEquals(configuration1.getNameMap(), "sepehr");
        assertNull(configuration2.getNameMap());
    }

    @Test
    public void testSourcePath() {
        assertNull(configuration1.getSourcePath());
        assertNotNull(configuration2.getSourcePath());
        assertFalse(configuration2.getSourcePath().isEmpty());
        assertEquals(configuration2.getSourcePath(), "sample.json");
    }

    @Test
    public void testDestPath() {
        assertNull(configuration1.getDestPath());
        assertNotNull(configuration3.getDestPath());
        assertFalse(configuration3.getDestPath().isEmpty());
        assertEquals(configuration3.getDestPath(), "sample.swift");
    }




}
