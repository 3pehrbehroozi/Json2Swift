package ir.sepehrbehroozi;

public class SwiftVariable {
    String name;
    String type;
    boolean isOptional = false;
    ValueType valueType;

    public SwiftVariable(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public SwiftVariable() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name).append(": ").append(this.type);
        if (this.isOptional)
            result.append("?");
        return result.toString();
    }
}