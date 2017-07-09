package info.wso2.scim2.compliance.feignclient;

import feign.Response;
import feign.gson.GsonDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class CustomDecoder extends GsonDecoder {

    private Map<String, Collection<String>> headers;
    private int status;
    private byte[] responseBody;
    private String reason;

    @Override
    public Object decode(Response response, Type type) throws IOException {
        headers = response.headers();
        responseBody = IOUtils.toByteArray(response.body().asInputStream());
        status = response.status();
        reason  = response.reason();
        return super.decode(response, type);
    }

    public String getHeaders() {
        return headers.toString();
    }

    public String getBody() {
        return new String(responseBody);
    }

    public int getStatus() {
        return status;
    }

    public String getReason(){
        return reason;
    }

    public void decoderReset(){
        headers = null;
        status = 0;
        responseBody = null;
        reason = null;
    }
}