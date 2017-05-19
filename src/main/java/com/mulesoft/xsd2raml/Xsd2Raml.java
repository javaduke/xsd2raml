package com.mulesoft.xsd2raml;

import com.sun.org.apache.xerces.internal.dom.DOMXSImplementationSourceImpl;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.xs.*;

public class Xsd2Raml {
    private static String URI_2001_SCHEMA_XSD = "http://www.w3.org/2001/XMLSchema";

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
            if (nextType instanceof XSSimpleType && !URI_2001_SCHEMA_XSD.equals(nextType.getNamespace())) {
                printSimpleType((XSSimpleType)nextType);
            }
        }
    }

    private static void printSimpleType(XSSimpleType simpleType) {
        System.out.println(simpleType.getName() + ":");
        String baseTypeName = simpleType.getBaseType().getName();
        ScalarType ramlType = ScalarType.forXsdType(baseTypeName);
        if (ramlType != null)
            System.out.println(INDENT + "type: " + ramlType.getRamlType());

        StringList enumerations = simpleType.getLexicalEnumeration();
        if (enumerations.getLength() > 0 ) {
            System.out.print(INDENT + "enum: [ ");
            System.out.print(String.join(", ", (String[])enumerations.toArray(new String[] {})));
            System.out.println(" ]");
        }

        XSObjectList facetList = simpleType.getFacets();
        System.out.println(">>> facets:  ");
        for(int i = 0; i < facetList.getLength(); ++i) {
            XSFacet facet = (XSFacet) facetList.get(i);
            System.out.println("  " + facetKindToString(facet.getFacetKind())+": "+facet.getLexicalFacetValue());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");


        System.out.println("\n");

    }

    private static String facetKindToString(short facetKind) {
        switch(facetKind) {
            case XSSimpleTypeDefinition.FACET_NONE: return "none";
            case XSSimpleTypeDefinition.FACET_LENGTH: return "length";
            case XSSimpleTypeDefinition.FACET_MINLENGTH: return "minLength";
            case XSSimpleTypeDefinition.FACET_MAXLENGTH: return "maxLength";
            case XSSimpleTypeDefinition.FACET_PATTERN: return "pattern";
            case XSSimpleTypeDefinition.FACET_WHITESPACE: return "whitespace";
            case XSSimpleTypeDefinition.FACET_MAXINCLUSIVE: return "maxInclusive";
            case XSSimpleTypeDefinition.FACET_MAXEXCLUSIVE: return "maxExclusive";
            case XSSimpleTypeDefinition.FACET_MINEXCLUSIVE: return "minExclusive";
            case XSSimpleTypeDefinition.FACET_MININCLUSIVE: return "minInclusive";
            case XSSimpleTypeDefinition.FACET_TOTALDIGITS: return "totalDigits";
            case XSSimpleTypeDefinition.FACET_FRACTIONDIGITS: return "fractionDigits";
            case XSSimpleTypeDefinition.FACET_ENUMERATION: return "enumeration";
            default: return "unknown facet kind";
        }
    }
}
