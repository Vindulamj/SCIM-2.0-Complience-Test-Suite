package info.wso2.scim2.compliance.httpclient;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;


public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println("fetching existing books..");
        SPC books = new SCIMResourceImplFeign().getAllBooks();
        System.out.println(String.format("%s books received", books.toString()));
    }

}
