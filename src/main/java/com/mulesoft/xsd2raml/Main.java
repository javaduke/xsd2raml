package com.mulesoft.xsd2raml;

import com.sun.org.apache.xerces.internal.dom.DOMXSImplementationSourceImpl;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.xs.*;

public class Main {

    public static String INDENT = "  ";

    public static void main(String[] args) {
        XSImplementation impl = (XSImplementation)
                (new DOMXSImplementationSourceImpl()).getDOMImplementation ("XS-Loader");

        XSLoader schemaLoader = impl.createXSLoader (null);

        XSModel xsModel = schemaLoader.loadURI(args[0]);

        XSNamedMap typeDefs = xsModel.getComponents(XSConstants.TYPE_DEFINITION);

        for (int idx = 0; idx < typeDefs.getLength(); idx++) {
            XSObject nextType = typeDefs.item(idx);
            //System.out.println("NEXT TYPE IS " + nextType);
            if (nextType instanceof XSSimpleType) {
                printSimpleType((XSSimpleType)nextType);
            }
        }
    }

    private static void printSimpleType(XSSimpleType type) {
        System.out.println(type.getName() + ":");
        System.out.println(INDENT + "type: " + type.getBaseType().getName());
        System.out.println("\n");

    }
}
