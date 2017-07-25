package info.wso2.scim2.compliance.tests.common;

import info.wso2.scim2.compliance.entities.TestResult;
import info.wso2.scim2.compliance.exception.GeneralComplianceException;
import info.wso2.scim2.compliance.protocol.ComplianceUtils;
import org.apache.http.client.methods.HttpPost;
import org.wso2.charon3.core.attributes.AbstractAttribute;
import org.wso2.charon3.core.attributes.Attribute;
import org.wso2.charon3.core.attributes.ComplexAttribute;
import org.wso2.charon3.core.attributes.MultiValuedAttribute;
import org.wso2.charon3.core.exceptions.BadRequestException;
import org.wso2.charon3.core.exceptions.CharonException;
import org.wso2.charon3.core.objects.SCIMObject;
import org.wso2.charon3.core.schema.AttributeSchema;
import org.wso2.charon3.core.schema.SCIMAttributeSchema;
import org.wso2.charon3.core.schema.SCIMResourceTypeSchema;

import java.util.List;
import java.util.Map;

public class ResponseValidateTest {

    public static void runValidateTests(SCIMObject scimObject,
                                        SCIMResourceTypeSchema schema,
                                        HttpPost method,
                                        String responseString,
                                        String headerString,
                                        String responseStatus)
            throws BadRequestException, CharonException, GeneralComplianceException {
        //Check for required attributes
        validateSCIMObjectForRequiredAttributes(scimObject, schema,
                method, responseString, headerString, responseStatus);
        //validate schema list
        validateSchemaList(scimObject, schema, method, responseString, headerString, responseStatus);
    }

    /*
     * Validate SCIMObject for required attributes given the object and the corresponding schema.
     *
     * @param scimObject
     * @param resourceSchema
     */
    private static void validateSCIMObjectForRequiredAttributes(SCIMObject scimObject,
                                                                SCIMResourceTypeSchema resourceSchema,
                                                                HttpPost method,
                                                                String responseString,
                                                                String headerString,
                                                                String responseStatus)
            throws BadRequestException, CharonException, GeneralComplianceException {
        //get attributes from schema.
        List<AttributeSchema> attributeSchemaList = resourceSchema.getAttributesList();
        //get attribute list from scim object.
        Map<String, Attribute> attributeList = scimObject.getAttributeList();
        for (AttributeSchema attributeSchema : attributeSchemaList) {
            //check for required attributes.
            if (attributeSchema.getRequired()) {
                if (!attributeList.containsKey(attributeSchema.getName())) {
                    String error = "Required attribute " + attributeSchema.getName() + " is missing in the SCIM " +
                            "Object.";
                    throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                            error, ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
                }
            }
            //check for required sub attributes.
            AbstractAttribute attribute = (AbstractAttribute) attributeList.get(attributeSchema.getName());
            validateSCIMObjectForRequiredSubAttributes(attribute, attributeSchema,
                    method, responseString, headerString, responseStatus);
        }
    }

    /*
         * Validate SCIMObject for required sub attributes given the object and the corresponding schema.
         *
         * @param attribute
         * @param attributeSchema
         * @throws CharonException
         * @throws BadRequestException
         */
    private static void validateSCIMObjectForRequiredSubAttributes(AbstractAttribute attribute,
                                                                   AttributeSchema attributeSchema,
                                                                   HttpPost method, String responseString,
                                                                   String headerString, String responseStatus)
            throws GeneralComplianceException, CharonException {
        if (attribute != null) {
            List<SCIMAttributeSchema> subAttributesSchemaList =
                    ((SCIMAttributeSchema) attributeSchema).getSubAttributeSchemas();

            if (subAttributesSchemaList != null) {
                for (SCIMAttributeSchema subAttributeSchema : subAttributesSchemaList) {
                    if (subAttributeSchema.getRequired()) {

                        if (attribute instanceof ComplexAttribute) {
                            if (attribute.getSubAttribute(subAttributeSchema.getName()) == null) {
                                String error = "Required sub attribute: " + subAttributeSchema.getName()
                                        + " is missing in the SCIM Attribute: " + attribute.getName();
                                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                                        error, ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
                            }
                        } else if (attribute instanceof MultiValuedAttribute) {
                            List<Attribute> values =
                                    ((MultiValuedAttribute) attribute).getAttributeValues();
                            for (Attribute value : values) {
                                if (value instanceof ComplexAttribute) {
                                    if (value.getSubAttribute(subAttributeSchema.getName()) == null) {
                                        String error = "Required sub attribute: " + subAttributeSchema.getName()
                                                + ", is missing in the SCIM Attribute: " + attribute.getName();
                                        throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                                                error, ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
                                    }
                                }
                            }
                        }
                    }
                    //Following is only applicable for extension schema validation.
                    AbstractAttribute subAttribute = null;
                    if (attribute instanceof ComplexAttribute) {
                        subAttribute = (AbstractAttribute) ((ComplexAttribute) attribute).getSubAttribute
                                (subAttributeSchema.getName());
                    } else if (attribute instanceof MultiValuedAttribute) {
                        List<Attribute> subAttributeList = ((MultiValuedAttribute) attribute).getAttributeValues();
                        for (Attribute subAttrbte : subAttributeList) {
                            if (subAttrbte.getName().equals(subAttributeSchema.getName())) {
                                subAttribute = (AbstractAttribute) subAttrbte;
                            }
                        }
                    }
                    List<SCIMAttributeSchema> subSubAttributesSchemaList =
                            ((SCIMAttributeSchema) subAttributeSchema).getSubAttributeSchemas();
                    if (subSubAttributesSchemaList != null) {
                        validateSCIMObjectForRequiredSubAttributes(subAttribute, subAttributeSchema, method, responseString, headerString, responseStatus);
                    }
                }
            }
        }
    }

    /*
     * Validate SCIMObject for schema list
     *
     * @param scimObject
     * @param resourceSchema
     */
    public static void validateSchemaList(SCIMObject scimObject,
                                          SCIMResourceTypeSchema resourceSchema,
                                          HttpPost method, String responseString,
                                          String headerString, String responseStatus)
            throws  GeneralComplianceException {

        //get resource schema list
        List<String> resourceSchemaList = resourceSchema.getSchemasList();
        //get the scim object schema list
        List<String> objectSchemaList = scimObject.getSchemaList();

        for (String schema : resourceSchemaList) {
            //check for schema.
            if (!objectSchemaList.contains(schema)) {
                throw new GeneralComplianceException(new TestResult(TestResult.ERROR, "Create SCIMUser",
                        "Not all schemas are set", ComplianceUtils.getWire(method, responseString, headerString, responseStatus)));
            }
        }
    }
}
