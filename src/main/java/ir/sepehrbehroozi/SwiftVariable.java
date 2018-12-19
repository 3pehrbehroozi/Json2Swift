package ir.sepehrbehroozi;

public class SwiftVariable {
    String name;
    String type;
    String key;
    boolean isOptional = false;
    ValueType valueType;

    public SwiftVariable() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name).append(": ");
        result.append(valueType == ValueType.ARRAY ? "[" : "").append(type).append(valueType == ValueType.ARRAY ? "]" : "");
        if (this.isOptional)
            result.append("?");
        return result.toString();
    }
}