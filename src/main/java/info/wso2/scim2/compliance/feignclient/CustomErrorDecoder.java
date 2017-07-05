package info.wso2.scim2.compliance.feignclient;

import feign.Response;
import feign.codec.ErrorDecoder;

import static feign.FeignException.errorStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 409) {
            return new Exception(response.reason());
        }
        if (response.status() >= 500 && response.status() <= 599) {
            return new Exception(response.reason());
        }
        return errorStatus(methodKey, response);
    }
}