package com.mulesoft.xsd2raml;

/**
 * Created by eberman on 5/18/17.
 */
public class ScalarType {
    private String xsdType;
    private String ramlType;

    public static ScalarType STRING = new ScalarType("string", "string");
    public static ScalarType DECIMAL = new ScalarType("decimal", "number");
    public static ScalarType INTEGER = new ScalarType("integer", "integer");
    public static ScalarType BOOLEAN = new ScalarType("boolean", "boolean");

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
