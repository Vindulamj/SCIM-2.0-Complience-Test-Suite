package info.wso2.scim2.compliance.httpclient;

import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers("Accept: application/json")
public interface SCIMResourceFeign {

    @RequestLine("GET /scim2/Users/5777a1ab-7c26-422d-8f54-9928138eac80?attributes=userName")
    List<SPC> getAllBooks();
}
