package info.wso2.scim2.compliance.feignclient;

import feign.RequestTemplate;
import feign.gson.GsonEncoder;
import java.lang.reflect.Type;


public class CustomEncoder extends GsonEncoder {

    private String reason;

    @Override
    public void encode(Object request, Type type, RequestTemplate requestTemplate) {
        reason = new String((byte[]) request);
        super.encode(request, type, requestTemplate);
    }

    public String getReason() {
        return reason;
    }
}