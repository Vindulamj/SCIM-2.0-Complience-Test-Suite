package info.wso2.scim2.compliance.httpclient;

/**
 * Created by User on 6/20/2017.
 */
public class Test {

    public static  void  main(String [] args) throws Exception {
        HttpClientImpl httpClient = new HttpClientImpl(null);
        System.out.println(httpClient.GetServiceProviderConfig());
        }

}
