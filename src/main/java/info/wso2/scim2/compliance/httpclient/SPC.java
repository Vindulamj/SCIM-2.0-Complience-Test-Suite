package info.wso2.scim2.compliance.httpclient;


import java.util.List;

public class SPC {
    private Object patch;
    private List<String> schemas;
    @Override
    public String toString() {
        return "SPC{" +
                "userName='" + patch.toString() + '\'' +
                "schemas='" + schemas.get(0) + '\'' +

                '}';
    }
}