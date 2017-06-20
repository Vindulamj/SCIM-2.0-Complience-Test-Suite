package info.wso2.scim2.compliance.httpclient;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;
import info.wso2.scim2.compliance.protocol.ComplianceTestMetaDataHolder;
import info.wso2.scim2.compliance.scimcore.objects.ServiceProviderConfig.SCIMServiceProviderConfig;

public class HttpClientImpl {

    private ComplianceTestMetaDataHolder complianceTestMetaDataHolder;

    public HttpClientImpl(ComplianceTestMetaDataHolder complianceTestMetaDataHolder) {
        this.complianceTestMetaDataHolder = complianceTestMetaDataHolder;
    }

    public SCIMServiceProviderConfig GetServiceProviderConfig() throws Exception {

        String url = "https://localhost:9443" +
                complianceTestMetaDataHolder.getVersion() + "/ServiceProviderConfig";

        BasicAuthRequestInterceptor interceptor = new BasicAuthRequestInterceptor
                (complianceTestMetaDataHolder.getUsername(),
                complianceTestMetaDataHolder.getPassword());

        HttpClient ServiceProviderConfigService = Feign.builder().
                contract(new JAXRSContract())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(interceptor)
                .target(HttpClient.class, url);

        return (ServiceProviderConfigService.GetServiceProviderConfig());
    }
}
