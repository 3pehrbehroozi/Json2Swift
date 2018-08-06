package ir.sepehrbehroozi;

import com.martiansoftware.jsap.*;

import java.io.File;

public class Configuration {
    private static final String NAME_MAP_ID = "NAME_MAP_ID";
    private static final String SOURCE_PATH_ID = "SOURCE_PATH_ID";
    private static final String DEST_PATH_ID = "DEST_PATH_ID";
    private static final String VERBOSE_ID = "VERBOSE_ID";
    private static final String BASE_CLASS_NAME_ID = "BASE_CLASS_NAME_ID";

    private String nameMap;
    private String sourcePath;
    private String destPath;
    private boolean verbose;
    private String baseClassName;

    Configuration(String[] args) {
        JSAP jsap = new JSAP();

        FlaggedOption[] options = new FlaggedOption[]{
                new FlaggedOption(NAME_MAP_ID)
                        .setStringParser(JSAP.STRING_PARSER)
                        .setRequired(false)
                        .setShortFlag('n')
                        .setLongFlag("nam-map"),
                new FlaggedOption(SOURCE_PATH_ID)
                        .setStringParser(JSAP.STRING_PARSER)
                        .setRequired(true)
                        .setShortFlag('s')
                        .setLongFlag("source"),
                new FlaggedOption(DEST_PATH_ID)
                        .setStringParser(JSAP.STRING_PARSER)
                        .setRequired(true)
                        .setShortFlag('d')
                        .setLongFlag("destination"),
                new FlaggedOption(BASE_CLASS_NAME_ID)
                        .setStringParser(JSAP.STRING_PARSER)
                        .setRequired(false)
                        .setShortFlag('b')
                        .setLongFlag("base-class-name"),

        };

        Switch[] switches = new Switch[]{
                new Switch(VERBOSE_ID)
                        .setDefault("false")
                        .setShortFlag('v')
                        .setLongFlag("verbose"),
        };


        try {
            for (FlaggedOption option : options)
                jsap.registerParameter(option);
            for (Switch sw : switches)
                jsap.registerParameter(sw);
        } catch (JSAPException e) {
            e.printStackTrace();
            System.exit(-1);
            return;
        }


        JSAPResult config = jsap.parse(args);
        nameMap = config.getString(NAME_MAP_ID);
        sourcePath = config.getString(SOURCE_PATH_ID);
        destPath = config.getString(DEST_PATH_ID);
        verbose = config.getBoolean(VERBOSE_ID);
        baseClassName = config.getString(BASE_CLASS_NAME_ID);
        if (baseClassName == null) {
            File tFile = new File(destPath);
            baseClassName = Utils.toUpperCaseFirstChar(tFile.getName().substring(0, tFile.getName().lastIndexOf('.')));
        }
    }

    public String getNameMap() {
        return nameMap;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestPath() {
        return destPath;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public String getBaseClassName() {
        return baseClassName;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Config: \n");
        result.append("sourcePath : ").append(sourcePath).append("\n");
        result.append("destPath : ").append(destPath).append("\n");
        result.append("nameMap : ").append(nameMap).append("\n");
        result.append("baseClassName : ").append(baseClassName).append("\n");
        result.append("verbose : ").append(verbose).append("\n");
        return result.toString();
    }
}
