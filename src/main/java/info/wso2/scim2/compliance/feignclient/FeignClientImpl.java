package info.wso2.scim2.compliance.feignclient;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jaxrs.JAXRSContract;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;
import info.wso2.scim2.compliance.scimcore.objects.User.User;

public class FeignClientImpl {

    private BasicAuthRequestInterceptor interceptor;
    private CustomDecoder customDecoder = new CustomDecoder();
    private CustomEncoder customEncoder = new CustomEncoder();

    public FeignClientImpl(ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
        interceptor = new BasicAuthRequestInterceptor(complianceTestMetaDataHolder.getUsername(),
                        complianceTestMetaDataHolder.getPassword());
    }

    public SCIMServiceProviderConfig GetServiceProviderConfig(String url) throws Exception {

        FeignClient ServiceProviderConfigService = Feign.builder()
                .contract(new JAXRSContract())
                .encoder(customEncoder)
                .decoder(customDecoder)
                .errorDecoder(new CustomErrorDecoder())
                .requestInterceptor(interceptor)
                .target(FeignClient.class, url);
        return (ServiceProviderConfigService.GetServiceProviderConfig());
    }

    public User CreateUser(User user, String url) throws Exception {

        FeignClient UserService = Feign.builder()
                .contract(new JAXRSContract())
                .encoder(customEncoder)
                .decoder(customDecoder)
                .errorDecoder(new CustomErrorDecoder())
                .requestInterceptor(interceptor)
                .target(FeignClient.class, url);
        return (UserService.CreateUser(user));
    }

    public String getResponseHeaders(){
        return customDecoder.getHeaders();
    }

    public String getResponseBody(){
        return customDecoder.getBody();
    }

    public int getResponseStatus(){
        return customDecoder.getStatus();
    }

    public String getResponseReason(){
        return  customDecoder.getReason();
    }
    public String getX(){
        return  customEncoder.getReason();
    }
}
