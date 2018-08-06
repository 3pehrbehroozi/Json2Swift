package ir.sepehrbehroozi;

import org.json.JSONArray;
import org.json.JSONObject;

public enum ValueType {
    STRING, INT, DOUBLE, OBJECT, ARRAY, UNKNOWN;

    public static ValueType getValueTypeOf(Object value) {
        if (value instanceof String)
            return STRING;
        if (value instanceof Integer || value instanceof Long)
            return INT;
        if (value instanceof Double || value instanceof Float)
            return DOUBLE;
        if (value instanceof JSONObject)
            return OBJECT;
        if (value instanceof JSONArray)
            return ARRAY;
        else
            return UNKNOWN;
    }

    public String getSwiftTypeString() {
        switch (this) {
            case INT:
                return "Int";
            case DOUBLE:
                return "Double";
            case STRING:
                return "String";
            default:
                return "";
        }
    }
}
