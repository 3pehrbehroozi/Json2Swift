package ir.sepehrbehroozi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SwiftClass {
    String name;
    List<SwiftVariable> variables = new ArrayList<>();
    List<SwiftClass> innerClasses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SwiftVariable> getVariables() {
        return variables;
    }

    public void setVariables(List<SwiftVariable> variables) {
        this.variables = variables;
    }

    public List<SwiftClass> getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(List<SwiftClass> innerClasses) {
        this.innerClasses = innerClasses;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("class ").append(this.name).append(" {").append("\n");
        for (SwiftVariable var : variables) {
            result.append("\t");
            result.append("var ").append(var.toString());
            result.append("\n");
        }

        result.append("\n");
        result.append(String.format(Locale.ENGLISH, "\tclass func from(json object: JSONObject?) -> %s? {\n", name));
        result.append("\t\tguard let object = object else {\n");
        result.append("\t\t\treturn nil\n");
        result.append("\t\t}\n\n");
        result.append(String.format(Locale.ENGLISH, "\t\tlet result = %s()\n", name));
        for (SwiftVariable variable : variables) {
            if (variable.valueType == ValueType.OBJECT) {
                result.append("\t\tresult.").append(variable.name).append(" = ");
                result.append(variable.type).append(".from(json: getJsonObject(object, [\"").append(variable.name).append("\"])").append("\n");
            } else if (variable.valueType == ValueType.ARRAY) {
                continue;
            } else if (variable.valueType == ValueType.UNKNOWN) {
                continue;
            } else {
                result.append("\t\tresult.").append(variable.name).append(" = ").append("get").append(variable.valueType.getSwiftTypeString());
                result.append("(object, [\"").append(variable.name).append("\"])").append("\n");
            }
        }
        result.append("return result\n");
        result.append("\t}\n\n");


        result.append(String.format(Locale.ENGLISH, "\tclass func from(json array: JSONArray?) -> [%s] {\n", name));
        result.append(String.format(Locale.ENGLISH, "\t\treturn (array ?? JSONArray()).map { %s.from(json: $0 as? JSONObject) }.filter { $0 != nil }.map { $0! }\n", name));
        result.append("\t}\n\n");


        if (innerClasses.size() > 0) {
            for (SwiftClass innerClass : innerClasses) {
                result.append("\n\n");
                result.append(innerClass.toString()).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
