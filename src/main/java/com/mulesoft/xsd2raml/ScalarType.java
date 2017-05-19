package com.mulesoft.xsd2raml;

/**
 * Created by eberman on 5/18/17.
 */
public class ScalarType {
    private String xsdType;
    private String ramlType;

    public static ScalarType STRING = new ScalarType("string", "string");
    public static ScalarType DECIMAL = new ScalarType("decimal", "number");
    public static ScalarType DOUBLE = new ScalarType("double", "number");
    public static ScalarType INTEGER = new ScalarType("integer", "integer");
    public static ScalarType BOOLEAN = new ScalarType("boolean", "boolean");
    public static ScalarType BASE64 = new ScalarType("base64Binary", "file");

    public static ScalarType forXsdType(String xsdType) {
        if ("string".equals(xsdType))
            return ScalarType.STRING;
        if ("decimal".equals(xsdType))
            return ScalarType.DECIMAL;
        if ("integer".equals(xsdType))
            return ScalarType.INTEGER;
        if ("boolean".equals(xsdType))
            return ScalarType.BOOLEAN;
        if ("base64Binary".equals(xsdType))
            return ScalarType.BASE64;
        if ("double".equals(xsdType))
            return ScalarType.DOUBLE;

        return null;
    }

    private ScalarType(String xsdType, String ramlType) {
        this.xsdType = xsdType;
        this.ramlType = ramlType;
    }

    public String getXsdType() {
        return xsdType;
    }

    public String getRamlType() {
        return ramlType;
    }
}
