package info.wso2.scim2.compliance.httpclient;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "SPC")
public class SPC {
    private String userName;
    private String id;
    private String schemas;
}