package info.wso2.scim2.compliance.feignclient;

import feign.RequestTemplate;
import feign.gson.GsonEncoder;
import java.lang.reflect.Type;

public class CustomEncoder extends GsonEncoder {

    private String reason = "Asd";

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        reason = "FG";
        super.encode(object, bodyType, template);
    }

    public String getReason() {
        return reason;
    }
}