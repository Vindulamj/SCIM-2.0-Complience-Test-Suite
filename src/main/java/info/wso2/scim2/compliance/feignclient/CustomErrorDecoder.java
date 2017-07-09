package info.wso2.scim2.compliance.feignclient;

import feign.Response;
import feign.codec.ErrorDecoder;
import info.wso2.scim2.compliance.exception.ComplianceException;
import info.wso2.scim2.compliance.scimcore.objects.common.ErrorResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

import static feign.FeignException.errorStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() > 0) {
            try {
                String responseBody =  new String (IOUtils.toByteArray(response.body().asInputStream()));
                return new ErrorResponse(response.status() , response.reason(),
                        responseBody, response.headers().toString());
            } catch (IOException e) {
                return new ComplianceException("Error in parsing the error response", e);
            }
        }
        return errorStatus(methodKey, response);
    }
}