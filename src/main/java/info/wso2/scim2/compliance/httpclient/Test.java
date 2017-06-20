package info.wso2.scim2.compliance.httpclient;

import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;


public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println("fetching existing books..");
        SCIMServiceProviderConfig books = new SCIMResourceImplFeign().getAllBooks();
        System.out.println(String.format("%s books received", books.toString()));
    }

}
