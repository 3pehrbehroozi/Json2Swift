package ir.sepehrbehroozi;

import org.json.JSONObject;

import java.io.*;
import java.util.Locale;

public class Utils {

    public static String toLowerCaseFirstChar(String text) {
        if (text == null || text.length() == 0)
            return text;
        char[] textChars = text.toCharArray();
        textChars[0] = Character.toLowerCase(textChars[0]);
        return new String(textChars);
    }

    public static String toUpperCaseFirstChar(String text) {
        if (text == null || text.length() == 0)
            return text;
        char[] textChars = text.toCharArray();
        textChars[0] = Character.toUpperCase(textChars[0]);
        return new String(textChars);
    }

    public static JSONObject getObjectFrom(String filePath) {
        try {
            File inputFile = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            char[] inputBytes = new char[(int) inputFile.length()];
            reader.read(inputBytes, 0, (int) inputFile.length());
            String jsonString = new String(inputBytes);
            JSONObject inputJson = new JSONObject(jsonString);
            reader.close();
            return inputJson;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeFileHeaderComments(BufferedWriter writer, String fileName) throws IOException {
        writer.write("//\n");
        writer.write(String.format(Locale.ENGLISH, "//\t%s\n", fileName));
        writer.write("//\tCreated by SJsonToSwift script\n");
        writer.write("//\tFork me on github: https://github.com/3pehrbehroozi/Json2Swift\n");
        writer.write("//\n\n");
    }
}
