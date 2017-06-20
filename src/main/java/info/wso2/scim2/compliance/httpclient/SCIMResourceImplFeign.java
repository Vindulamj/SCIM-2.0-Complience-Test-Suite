package info.wso2.scim2.compliance.httpclient;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;
import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;

public class SCIMResourceImplFeign {
    private static final String URI_BOOK = "https://localhost:9443";

    public SCIMServiceProviderConfig getAllBooks() throws Exception {
        BasicAuthRequestInterceptor interceptor = new BasicAuthRequestInterceptor("admin",
                "admin");

        SCIMResourceFeign bookService = Feign.builder().
                contract(new JAXRSContract())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(interceptor)
                .target(SCIMResourceFeign.class,
                        "https://localhost:9443/scim2/ServiceProviderConfig");

        return (bookService.getAllBooks());
    }
}
