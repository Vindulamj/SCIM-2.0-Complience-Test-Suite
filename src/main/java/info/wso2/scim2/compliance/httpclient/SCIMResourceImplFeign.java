package info.wso2.scim2.compliance.httpclient;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;

public class SCIMResourceImplFeign {
    private static final String URI_BOOK = "https://localhost:9443";

    public List<SPC> getAllBooks() throws Exception {
        BasicAuthRequestInterceptor interceptor = new BasicAuthRequestInterceptor("admin",
                "admin");
        SCIMResourceFeign bookResource = Feign.builder().encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder()).requestInterceptor(interceptor).target(SCIMResourceFeign.class, URI_BOOK);
        return bookResource.getAllBooks();

    }
}
