package ir.sepehrbehroozi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SwiftClass {
    String name;
    List<SwiftVariable> variables = new ArrayList<>();
    List<SwiftClass> innerClasses = new ArrayList<>();
    int level = 0;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<SwiftClass> getInnerClasses() {
        return innerClasses;
    }

    public void setInnerClasses(List<SwiftClass> innerClasses) {
        this.innerClasses = innerClasses;
    }

    private String tabs(int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count + this.level; i++)
            result.append("\t");
        return result.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(tabs(0)).append("class ").append(this.name).append(" {").append("\n");
        for (SwiftVariable var : variables) {
            result.append(tabs(1));
            result.append("var ").append(var.toString());
            result.append("\n");
        }

        result.append("\n");
        result.append(tabs(1)).append(String.format(Locale.ENGLISH, "class func from(json object: JSONObject?) -> %s? {\n", name));
        result.append(tabs(2)).append("guard let object = object else {\n");
        result.append(tabs(3)).append("return nil\n");
        result.append(tabs(2)).append("}\n\n");
        result.append(tabs(2)).append(String.format(Locale.ENGLISH, "let result = %s()\n", name));
        for (SwiftVariable variable : variables) {
            if (variable.valueType == ValueType.OBJECT) {
                result.append(tabs(2)).append("result.").append(variable.name).append(" = ");
                result.append(variable.type).append(".from(json: getJsonObject(object, [\"").append(variable.key).append("\"]))").append("\n");
            } else if (variable.valueType == ValueType.ARRAY) {
                result.append(tabs(2)).append("result.").append(variable.name).append(" = ");
                result.append(variable.type).append(".from(json: getJsonArray(object, [\"").append(variable.key).append("\"]))").append("\n");
            } else if (variable.valueType == ValueType.UNKNOWN) {
                continue;
            } else {
                result.append(tabs(2)).append("result.").append(variable.name).append(" = ").append("get").append(variable.valueType.getSwiftTypeString());
                result.append("(object, [\"").append(variable.key).append("\"])").append("\n");
            }
        }
        result.append(tabs(2)).append("return result\n");
        result.append(tabs(1)).append("}\n\n");


        result.append(tabs(1)).append(String.format(Locale.ENGLISH, "class func from(json array: JSONArray?) -> [%s] {\n", name));
        result.append(tabs(2)).append(String.format(Locale.ENGLISH, "return (array ?? JSONArray()).compactMap { %s.from(json: $0 as? JSONObject) }\n", name));
        result.append(tabs(1)).append("}\n\n");


        if (innerClasses.size() > 0) {
            for (SwiftClass innerClass : innerClasses) {
                result.append("\n\n");
                result.append(innerClass.toString()).append("\n");
            }
        }
        result.append(tabs(0)).append("}");
        return result.toString();
    }
}
