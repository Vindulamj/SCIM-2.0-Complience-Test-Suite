package info.wso2.scim2.compliance.feignclient;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;

public class FeignClientImpl {

    private BasicAuthRequestInterceptor interceptor;
    CustomDecoder customDecoder = new CustomDecoder();
    CustomEncoder customEncoder = new CustomEncoder();

    public FeignClientImpl(ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
        interceptor = new BasicAuthRequestInterceptor(complianceTestMetaDataHolder.getUsername(),
                        complianceTestMetaDataHolder.getPassword());
    }

    public SCIMServiceProviderConfig GetServiceProviderConfig(String url) throws Exception {

        FeignClient ServiceProviderConfigService = Feign.builder().
                contract(new JAXRSContract())
                .encoder(customEncoder)
                .decoder(customDecoder)
                .requestInterceptor(interceptor)
                .target(FeignClient.class, url);
        return (ServiceProviderConfigService.GetServiceProviderConfig());
    }

    public String getHeaders(){
        return customDecoder.getHeaders();
    }

    public String getBody(){
        return customDecoder.getBody();
    }

    public int getStatus(){
        return customDecoder.getStatus();
    }

    public String getReason(){
        return  customDecoder.getReason();
    }

    public String get(){
        return  customEncoder.getReason();
    }
}
