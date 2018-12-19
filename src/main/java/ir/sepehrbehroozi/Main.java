package ir.sepehrbehroozi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ir.sepehrbehroozi.Utils.getObjectFrom;
import static ir.sepehrbehroozi.Utils.writeFileHeaderComments;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration(args);

        JSONObject inputJson = getObjectFrom(configuration.getSourcePath());
        if (inputJson == null) {
            print("Object is null");
            return;
        }


        try {
            File outFile = new File(configuration.getDestPath());
            if (outFile.exists())
                outFile.delete();
            outFile.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            writeFileHeaderComments(writer, outFile.getName());
            writer.write("import Foundation\n\n");

            SwiftClass mainClass = getClass(inputJson, configuration.getBaseClassName(), 0);

            writer.write(mainClass.toString());

            writer.flush();
            writer.close();
            print("SUCCESS");

        } catch (IOException e) {
            e.printStackTrace();
            print("ERROR!");
        }
    }


    public static SwiftClass getClass(JSONObject json, String name, int level) {
        SwiftClass result = new SwiftClass();
        result.setName(name);
        result.setLevel(level);
        List<SwiftVariable> variables = new ArrayList<>();

        for (String key : json.keySet()) {
            SwiftVariable variable = new SwiftVariable();
            variable.isOptional = true;
            variable.name = Utils.toLowerCaseFirstChar(key);
            variable.key = key;
            ValueType type = ValueType.getValueTypeOf(json.get(key));
            variable.valueType = type;

            if (type == ValueType.OBJECT) {
                SwiftClass objectClass = getClass(json.getJSONObject(key), key, level + 1);
                objectClass.setLevel(level + 1);
                variable.type = objectClass.name;
                result.innerClasses.add(objectClass);
            } else if (type == ValueType.ARRAY) {


                JSONArray array = json.getJSONArray(key);
                if(array.length() > 0) {
                    Object firstObject = array.get(0);
                    ValueType firstType = ValueType.getValueTypeOf(firstObject);
                    if(firstType == ValueType.OBJECT) {
                        SwiftClass objectClass = getClass((JSONObject) firstObject, key, level + 1);
                        objectClass.setLevel(level + 1);
                        variable.type = objectClass.name;
                        result.innerClasses.add(objectClass);
                    } else if(firstType == ValueType.UNKNOWN) {
                        continue;
                    } else if(firstType == ValueType.ARRAY) {
                        continue;
                    } else {
                        variable.type = firstType.getSwiftTypeString();
                    }
                }




















            } else if (type == ValueType.UNKNOWN) {
                continue;
            } else {
                variable.type = type.getSwiftTypeString();
            }

            variables.add(variable);
        }

        result.setVariables(variables);
        return result;
    }

    public static void print(String text) {
        System.out.println(text);
    }
}
